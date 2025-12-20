package com.stellar.calculator.calculation.classification;

import java.util.Objects;

import com.stellar.calculator.calculation.api.Calculator;
import com.stellar.calculator.domain.model.Star;
import com.stellar.calculator.physics.constants.SolarSystemConstants;
import com.stellar.calculator.physics.equations.ThermalEquations;

/**
 * Detects a star's MK luminosity class (I–V, plus VI/VII) using robust
 * heuristics that combine:
 *
 * <ul>
 * <li>Stefan–Boltzmann luminosity (from radius and temperature)</li>
 * <li>Radius-based evolutionary regime checks</li>
 * <li>Mass–luminosity relation consistency (main sequence vs evolved)</li>
 * </ul>
 *
 * <p>
 * True luminosity class is a spectroscopic gravity indicator; this detector is
 * a pragmatic classifier for the calculator's input parameters.
 *
 * @author Ahmed Ghannam
 * @version 1.0
 */
public final class LuminosityClassCalculator implements Calculator<Star, LuminosityClassResult> {

	@Override
	public LuminosityClassResult calculate(Star star) {
		Objects.requireNonNull(star, "star cannot be null");

		double m = star.getMass();
		double r = star.getRadius();
		double t = star.getTemperature();

		if (m <= 0 || r <= 0 || t <= 0) {
			throw new IllegalArgumentException("mass, radius and temperature must be positive");
		}

		double flux = ThermalEquations.calculateRadiantFlux(r, t);
		double lSolar = flux / SolarSystemConstants.SOLAR_LUMINOSITY;
		double logL = Math.log10(lSolar);

		double lExpectedMs = estimateMainSequenceLuminosityFromMass(m);
		double deltaLogMs = Math.log10(lSolar / lExpectedMs);

		var inputs = new LuminosityClassHeuristics.LuminosityClassInputs(
				m,          // massSolar
				r,          // radiusSolar
				t,          // temperatureK
				lSolar,     // luminositySolar
				logL,       // log10(L/Lsun)
				deltaLogMs  // ΔlogL_MS
		);

		var detection = LuminosityClassHeuristics.detect(inputs);

		return new LuminosityClassResult(
				detection.luminosityClass(),
				detection.confidence(),
				lSolar, r, t, m, deltaLogMs,
				detection.rationale()
		);
	}

	@Override
	public String getDescription() {
		return "Estimated MK luminosity class using radius/luminosity heuristics";
	}

	/**
	 * Piecewise approximation of the main-sequence mass–luminosity relation in
	 * solar units (L/L☉).
	 */
	private static double estimateMainSequenceLuminosityFromMass(double massSolar) {
		// Common approximation bands; provides a reasonable baseline for
		// "is this consistent with a main-sequence star?"
		if (massSolar < 0.43) {
			return 0.23 * Math.pow(massSolar, 2.3);
		}
		
		if (massSolar < 2.0) {
			return Math.pow(massSolar, 4.0);
		}
		
		if (massSolar < 20.0) {
			return 1.5 * Math.pow(massSolar, 3.5);
		}
		
		// Very massive: radiation pressure effects flatten the relation.
		return 3200.0 * massSolar;
	}
}
