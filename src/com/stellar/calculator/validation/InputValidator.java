package com.stellar.calculator.validation;

/**
 * Validates input values for stellar calculations. Provides specific validation
 * rules for different types of astronomical data.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public class InputValidator {

	/**
	 * Validates a mass value in solar masses.
	 *
	 * @param mass the mass in solar masses
	 * @throws IllegalArgumentException if the mass is invalid
	 */
	public void validateMass(double mass) {
		if (mass <= 0) {
			throw new IllegalArgumentException("mass must be positive");
		}
		if (mass > 150) {
			throw new IllegalArgumentException("mass exceeds theoretical stellar limit (150 solar masses)");
		}
	}

	/**
	 * Validates a radius value in solar radii.
	 *
	 * @param radius the radius in solar radii
	 * @throws IllegalArgumentException if the  radius is invalid
	 */
	public void validateRadius(double radius) {
		if (radius <= 0) {
			throw new IllegalArgumentException("radius must be positive");
		}
		if (radius > 2000) {
			throw new IllegalArgumentException("radius exceeds largest known star (~1700 solar radii)");
		}
	}

	/**
	 * Validates a temperature value in Kelvin.
	 *
	 * @param temperature the temperature in Kelvin
	 * @throws IllegalArgumentException if the temperature is invalid
	 */
	public void validateTemperature(double temperature) {
		if (temperature <= 0) {
			throw new IllegalArgumentException("temperature must be positive");
		}
		if (temperature > 200000) {
			throw new IllegalArgumentException("temperature exceeds hottest known stars (~150000K)");
		}
	}

	/**
	 * Validates an orbital distance in astronomical units.
	 *
	 * @param distance the distance in AU
	 * @throws IllegalArgumentException if the distance is invalid
	 */
	public void validateDistance(double distance) {
		if (distance <= 0) {
			throw new IllegalArgumentException("distance must be positive");
		}
	}
}
