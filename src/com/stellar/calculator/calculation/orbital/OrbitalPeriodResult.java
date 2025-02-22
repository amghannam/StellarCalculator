package com.stellar.calculator.calculation.orbital;

import com.stellar.calculator.calculation.api.CalculationResult;
import com.stellar.calculator.formatter.NumberFormatter;

/**
 * Represents the result of an orbital period calculation. Stores and formats
 * the calculated orbital period in seconds.
 *
 * <p>
 * This class is immutable and thread-safe. The period is stored in seconds but
 * can be formatted into more readable units (years or days) depending on its
 * magnitude.
 *
 * @author Ahmed Ghannam
 * @version 1.0
 * @see OrbitalPeriodCalculator
 */
public record OrbitalPeriodResult(double periodInSeconds) implements CalculationResult {
	private static final NumberFormatter formatter = new NumberFormatter();

	public OrbitalPeriodResult {
		if (periodInSeconds <= 0) {
			throw new IllegalArgumentException("period must be positive");
		}
	}

	@Override
	public String format() {
		return String.format("""
				Orbital Period at Earth-equivalent Distance
				Period: %s
				(Time for one complete orbit at a distance receiving Earth-like radiation)""",
				formatter.formatPeriod(periodInSeconds));
	}

	@Override
	public double getValue() {
		return periodInSeconds;
	}
}
