package com.stellar.calculator.calculation.luminosity;

import java.util.Objects;

import com.stellar.calculator.calculation.api.Calculator;
import com.stellar.calculator.domain.model.Star;
import com.stellar.calculator.physics.constants.SolarSystemConstants;
import com.stellar.calculator.physics.equations.ThermalEquations;

/**
 * Calculates the total (bolometric) luminosity of a star relative to the Sun.
 * 
 * <p>
 * This calculator uses the Stefan-Boltzmann law to compute the total energy
 * output of a star based on its surface temperature and radius. The result is
 * normalized to solar units, where 1.0 represents the Sun's luminosity.
 *
 * <p>
 * The calculation follows the formula: L = 4πR²σT⁴ where:
 * <ul>
 * <li>L is luminosity
 * <li>R is stellar radius
 * <li>σ is the Stefan-Boltzmann constant
 * <li>T is surface temperature
 * </ul>
 *
 * @author Ahmed Ghannam
 * @version 1.0
 * @see LuminosityResult
 * @see ThermalEquations
 */
public class LuminosityCalculator implements Calculator<Star, LuminosityResult> {

	/**
	 * Calculates the luminosity of the given star in solar units.
	 *
	 * @param star the star to analyze, must not be {@code null}
	 * @return the calculated luminosity result
	 * @throws NullPointerException     if <b>star</b> is {@code null}
	 * @throws IllegalArgumentException if <b>star</b> has invalid parameters
	 */
	@Override
	public LuminosityResult calculate(Star star) {
		Objects.requireNonNull(star, "star must not be null");
		double flux = ThermalEquations.calculateRadiantFlux(star.getRadius(), star.getTemperature());
		double luminosityInSolarUnits = flux / SolarSystemConstants.SOLAR_LUMINOSITY;
		return new LuminosityResult(luminosityInSolarUnits);
	}

	/**
	 * Returns a description of what this calculator computes.
	 *
	 * @return a human-readable description of the calculation
	 */
	@Override
	public String getDescription() {
		return "Star's luminosity relative to the Sun";
	}
}
