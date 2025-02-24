package com.stellar.calculator.physics.equations;

import com.stellar.calculator.physics.constants.SolarSystemConstants;

/**
 * Geometric calculations for stellar observations.
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
		double distance = distanceInAU * SolarSystemConstants.AU; // Convert to meters
		return 2 * Math.toDegrees(Math.atan(radius / distance));
	}
}
