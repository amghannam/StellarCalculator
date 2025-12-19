package com.stellar.calculator.calculation.classification;

import java.util.Objects;

import com.stellar.calculator.calculation.api.Calculator;
import com.stellar.calculator.domain.model.Star;

/**
 * Estimates the Morgan–Keenan (MK) spectral class of a star based primarily on
 * effective surface temperature.
 *
 * <p>
 * The MK spectral sequence is ordered (hot → cool) as: O, B, A, F, G, K, M, L, T,
 * Y. Subtypes 0–9 are estimated by linearly interpolating within each
 * temperature band.
 *
 * <p>
 * Note: This is an <em>estimator</em>. True spectral classification depends on
 * spectroscopy (line strengths, metallicity, gravity, etc.).
 *
 * @author Ahmed Ghannam
 * @version 1.0
 */
public final class SpectralClassCalculator implements Calculator<Star, SpectralClassResult> {

	@Override
	public SpectralClassResult calculate(Star star) {
		Objects.requireNonNull(star, "star cannot be null");
		double t = star.getTemperature();
		if (t <= 0) {
			throw new IllegalArgumentException("temperature must be positive");
		}

		var band = SpectralTemperatureBand.forTemperature(t);
		int subtype = band.estimateSubtype(t);

		return new SpectralClassResult(band.type(), subtype, t);
	}

	@Override
	public String getDescription() {
		return "Estimated MK spectral class based on temperature";
	}
}
