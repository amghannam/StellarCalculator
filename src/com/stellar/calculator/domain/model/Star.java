package com.stellar.calculator.domain.model;

/**
 * Represents a star with its fundamental properties. Immutable class containing
 * all basic stellar parameters.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public record Star(double mass, double radius, double temperature) implements StellarObject {

	/**
	 * Creates a new star with validated parameters.
	 * 
	 * @throws IllegalArgumentException if any parameter is invalid
	 */
	public Star {
		if (mass <= 0)
			throw new IllegalArgumentException("mass must be positive");
		if (radius <= 0)
			throw new IllegalArgumentException("radius must be positive");
		if (temperature <= 0)
			throw new IllegalArgumentException("temperature must be positive");
	}

	@Override
	public double getMass() {
		return mass;
	}

	@Override
	public double getRadius() {
		return radius;
	}

	/**
	 * Returns the surface temperature of the star in Kelvin.
	 * 
	 * @return surface temperature in Kelvin
	 */
	public double getTemperature() {
		return temperature;
	}
}
