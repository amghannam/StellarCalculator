package com.stellar.calculator.physics.equations;

import static java.lang.Math.log10;
import static java.lang.Math.pow;

import com.stellar.calculator.physics.constants.SolarSystemConstants;

/**
 * Apparent magnitude calculation for stars.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public final class MagnitudeEquations {

	private MagnitudeEquations() {
	}

	/**
	 * Calculates the apparent magnitude of a star as seen from an Earth-equivalent
	 * distance. Uses the relationship between luminosity and magnitude.
	 * 
	 * @return the apparent magnitude in degrees
	 */
	public static double calculateApparentMagnitude(double radiusInSol, double temperature) {
		// Calculate luminosity relative to Sun
		double luminosity = pow(radiusInSol, 2) * pow(temperature / SolarSystemConstants.SOLAR_TEMPERATURE, 4);

		// Calculate apparent magnitude
		// M = -2.5 log10(L/L☉) + M☉
		return -2.5 * log10(luminosity) + SolarSystemConstants.SOLAR_APPARENT_MAG;
	}
}
