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
	 * Calculates total radiant flux using Stefan-Boltzmann law.
	 *
	 * @param radiusInSol the star radius in solar radii
	 * @param temperature the surface temperature in Kelvin
	 * @return the total radiant flux in watts
	 */
	public static double calculateRadiantFlux(double radiusInSol, double temperature) {
		double radius = radiusInSol * SolarSystemConstants.SOLAR_RADIUS;
		double surfaceArea = 4 * Math.PI * radius * radius;
		return PhysicalConstants.STEFAN_BOLTZMANN * surfaceArea * Math.pow(temperature, 4);
	}

	/**
	 * Calculates irradiance at a specific distance from a star.
	 *
	 * @param radiusInSol  the star radius in solar radii
	 * @param temperature  the surface temperature in Kelvin
	 * @param distanceInAU the distance in astronomical units
	 * @return the irradiance in W/m²
	 */
	public static double calculateIrradianceAtDistance(double radiusInSol, double temperature, double distanceInAU) {
		double totalFlux = calculateRadiantFlux(radiusInSol, temperature);
		double distance = auToMeters(distanceInAU);
		double surfaceArea = 4 * Math.PI * distance * distance;
		return totalFlux / surfaceArea;
	}

	private static double auToMeters(double au) {
		return au * SolarSystemConstants.AU;
	}
}
