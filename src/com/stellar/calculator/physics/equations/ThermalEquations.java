package com.stellar.calculator.physics.equations;

import com.stellar.calculator.physics.constants.PhysicalConstants;
import com.stellar.calculator.physics.constants.SolarSystemConstants;

/**
 * Thermal physics calculations for stellar properties. Includes radiation,
 * luminosity, and temperature calculations.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public final class ThermalEquations {

	private ThermalEquations() {
	}

	/**
	 * Calculates the incident radiation at a specific distance from a star.
	 *
	 * @param radiusInSol  the star radius in solar radii
	 * @param temperature  the surface temperature in Kelvin
	 * @param distanceInAU the distance in astronomical units
	 * @return the irradiance in W/m²
	 */
	public static double calculateIrradianceAtDistance(double radiusInSol, double temperature, double distanceInAU) {
		double totalFlux = calculateRadiantFlux(radiusInSol, temperature);
		double distance = distanceInAU * SolarSystemConstants.AU; // Convert to meters
		double surfaceArea = 4 * Math.PI * Math.pow(distance, 2);
		return totalFlux / surfaceArea;
	}

	/**
	 * Calculates the total radiant flux using the Stefan-Boltzmann law.
	 *
	 * @param radiusInSol the star radius in solar radii
	 * @param temperature the surface temperature in Kelvin
	 * @return the total radiant flux in watts
	 */
	public static double calculateRadiantFlux(double radiusInSol, double temperature) {
		double radius = radiusInSol * SolarSystemConstants.SOLAR_RADIUS;
		double surfaceArea = 4 * Math.PI * Math.pow(radius, 2);
		return PhysicalConstants.STEFAN_BOLTZMANN * surfaceArea * Math.pow(temperature, 4);
	}
}
