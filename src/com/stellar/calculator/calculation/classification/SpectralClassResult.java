package com.stellar.calculator.calculation.classification;

import com.stellar.calculator.calculation.api.CalculationResult;

/**
 * Result of a spectral class estimation. Provides a formatted MK spectral type
 * (e.g., G2) and underlying temperature used for the estimation.
 *
 * @author Ahmed Ghannam
 * @version 1.0
 */
public record SpectralClassResult(SpectralType type, int subtype, double temperatureK) implements CalculationResult {

	public SpectralClassResult {
		if (type == null) {
			throw new IllegalArgumentException("type cannot be null");
		}
		if (subtype < 0 || subtype > 9) {
			throw new IllegalArgumentException("subtype must be between 0 and 9");
		}
		if (temperatureK <= 0) {
			throw new IllegalArgumentException("temperature must be positive");
		}
	}

	/**
	 * Returns the full MK label (e.g., "G2").
	 *
	 * @return MK label
	 */
	public String mkLabel() {
		return type.name() + subtype;
	}

	@Override
	public String format() {
		return String.format("""
				Spectral Class (Estimated)
				Output: %s
				(Based on effective temperature: %.0f K)""", mkLabel(), temperatureK);
	}

	/**
	 * A categorical result; returns NaN as no single numeric value represents the
	 * classification.
	 */
	@Override
	public double getValue() {
		return Double.NaN;
	}
}
