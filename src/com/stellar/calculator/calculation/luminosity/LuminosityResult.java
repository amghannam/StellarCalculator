package com.stellar.calculator.calculation.luminosity;

import com.stellar.calculator.calculation.api.CalculationResult;

/**
 * Represents the result of a stellar luminosity calculation. Stores and formats
 * the calculated luminosity in solar units.
 *
 * <p>
 * This class is immutable and thread-safe. The luminosity value is stored as a
 * ratio to solar luminosity (L☉), where 1.0 represents exactly one solar
 * luminosity.
 *
 * @author Ahmed Ghannam
 * @version 1.0
 * @see LuminosityCalculator
 */
public record LuminosityResult(double luminosity) implements CalculationResult {
	public LuminosityResult {
		if (luminosity < 0) {
			throw new IllegalArgumentException("luminosity cannot be negative");
		}
	}

	@Override
	public String format() {
		return String.format("""
				Total Stellar Luminosity
				Output: %.3f L☉
				(Total energy output compared to our Sun)""", luminosity);
	}

	@Override
	public double getValue() {
		return luminosity;
	}
}
