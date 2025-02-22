package com.stellar.calculator.domain.parameters;

import com.stellar.calculator.domain.model.Star;

/**
 * Represents a complete set of parameters needed for stellar calculations. Acts
 * as a builder for creating Star objects with optional parameters.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public class StarParameters {
	private double mass;
	private double radius;
	private double temperature;

	private StarParameters() {
	}

	public static StarParameters create() {
		return new StarParameters();
	}

	public StarParameters mass(double mass) {
		this.mass = mass;
		return this;
	}

	public StarParameters radius(double radius) {
		this.radius = radius;
		return this;
	}

	public StarParameters temperature(double temperature) {
		this.temperature = temperature;
		return this;
	}

	/**
	 * Builds a Star object from the current parameters.
	 * 
	 * @return newly created Star
	 * @throws IllegalStateException if required parameters are missing
	 */
	public Star build() {
		validateRequiredParameters();
		return new Star(mass, radius, temperature);
	}

	private void validateRequiredParameters() {
		if (mass <= 0)
			throw new IllegalStateException("Mass must be set and positive");
		if (radius <= 0)
			throw new IllegalStateException("Radius must be set and positive");
		if (temperature <= 0)
			throw new IllegalStateException("Temperature must be set and positive");
	}
}
