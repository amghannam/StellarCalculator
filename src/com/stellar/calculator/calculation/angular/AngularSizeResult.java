package com.stellar.calculator.calculation.angular;

import com.stellar.calculator.calculation.api.CalculationResult;
import com.stellar.calculator.physics.constants.SolarSystemConstants;

/**
 * Represents the angular size of a star as seen from a specific distance.
 * 
 * @author Ahmed Ghannam
 * @version 1.0 
 */
public record AngularSizeResult(double angularSize) implements CalculationResult {
	public AngularSizeResult {
		if (angularSize < 0) {
			throw new IllegalArgumentException("angular size cannot be negative");
		}
	}

	@Override
	public String format() {
		double percentOfSolar = (angularSize / SolarSystemConstants.ANGULAR_SIZE_SOL) * 100;
		return String.format("""
				Angular Size at Earth-equivalent Distance
				Size: %.2f degrees
				Apparent size relative to our Sun: %.1f%%
				(This is how large the star would appear in the sky)""", angularSize, percentOfSolar);
	}

	@Override
	public double getValue() {
		return angularSize;
	}
}
