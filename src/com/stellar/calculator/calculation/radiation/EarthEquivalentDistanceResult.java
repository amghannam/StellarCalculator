package com.stellar.calculator.calculation.radiation;

import com.stellar.calculator.calculation.api.CalculationResult;

/**
 * Represents the result of an Earth-equivalent distance calculation.
 * 
 * <p>
 * This record stores the calculated distance (in astronomical units) where a
 * planet would receive the same amount of stellar radiation as Earth receives
 * from the Sun. This distance is crucial for understanding potential
 * habitability, though it's important to note that habitability depends on many
 * other factors beyond just radiation levels.
 *
 * <p>
 * The distance is expressed in astronomical units (AU), where:
 * <ul>
 * <li>1 AU = average Earth-Sun distance = 149,597,870,700 meters
 * <li>Values > 1 AU indicate a more luminous star than the Sun
 * <li>Values < 1 AU indicate a less luminous star than the Sun
 * </ul>
 * 
 * @param distanceInAU the calculated distance in astronomical units
 * @author Ahmed Ghannam
 * @version 1.0
 * @see EarthEquivalentDistanceCalculator
 */
public record EarthEquivalentDistanceResult(double distanceInAU) implements CalculationResult {

	@Override
	public String format() {
		return String.format("""
				Earth-equivalent Distance
				Distance: %.2f AU
				(Where a planet would receive the same radiation as Earth)""", distanceInAU);
	}

	@Override
	public double getValue() {
		return distanceInAU;
	}
}
