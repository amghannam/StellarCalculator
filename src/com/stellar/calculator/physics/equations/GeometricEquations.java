package com.stellar.calculator.physics.equations;

import com.stellar.calculator.physics.constants.SolarSystemConstants;

/**
 * Geometric calculations for stellar observations. Includes angular size and
 * distance-related calculations.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public final class GeometricEquations {
	
	private GeometricEquations() {
	}

	/**
	 * Calculates the angular size of a star as seen from a given distance.
	 *
	 * @param radiusInSol  the stellar radius in solar radii
	 * @param distanceInAU the distance in astronomical units
	 * @return the angular size in degrees
	 */
	public static double calculateAngularSize(double radiusInSol, double distanceInAU) {
		double radius = radiusInSol * SolarSystemConstants.SOLAR_RADIUS;
		double distance = distanceInAU * SolarSystemConstants.AU;
		return 2 * Math.toDegrees(Math.atan(radius / distance));
	}

	/**
	 * Converts a distance from astronomical units to meters.
	 *
	 * @param distanceInAU the distance in astronomical units
	 * @return the distance in meters
	 */
	public static double auToMeters(double distanceInAU) {
		return distanceInAU * SolarSystemConstants.AU;
	}
}
