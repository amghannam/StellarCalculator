package com.stellar.calculator.calculation.magnitude;

import com.stellar.calculator.calculation.api.CalculationResult;
import com.stellar.calculator.physics.constants.SolarSystemConstants;

/**
 * Represents the result of an apparent magnitude calculation.
 * 
 * <p>
 * This record stores the calculated apparent magnitude and provides formatted
 * output including a comparison with the Sun's brightness. The comparison uses
 * the standard magnitude difference formula: brightness ratio = 2.512^(m₁ - m₂)
 * where m₁ and m₂ are the magnitudes being compared.
 *
 * <p>
 * The formatted output includes:
 * <ul>
 * <li>The calculated apparent magnitude
 * <li>A brightness comparison with the Sun
 * <li>Contextual explanation of the measurement
 * </ul>
 * 
 * @param magnitude the calculated apparent magnitude value
 * @author Ahmed Ghannam
 * @version 1.1
 * @see ApparentMagnitudeCalculator
 */
public record ApparentMagnitudeResult(double magnitude) implements CalculationResult {
	@Override
	public String format() {
		double magDiff = SolarSystemConstants.SOLAR_APPARENT_MAG - magnitude;
		double brightnessFactor = Math.pow(2.512, magDiff);

		String comparison;
		if (Math.abs(brightnessFactor - 1.0) < 0.05) {
			comparison = "About as bright as the Sun";
		} else if (brightnessFactor > 1.0) {
			comparison = String.format("%.1f times brighter than the Sun", brightnessFactor);
		} else {
			comparison = String.format("%.1f times dimmer than the Sun", 1 / brightnessFactor);
		}

		return String.format("""
				Apparent Magnitude at 1 AU
				Magnitude: %.2f
				%s
				(As it would appear from a distance of 1 AU)""", magnitude, comparison);
	}

	@Override
	public double getValue() {
		return magnitude;
	}
}
