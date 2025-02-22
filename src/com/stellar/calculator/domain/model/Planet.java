package com.stellar.calculator.domain.model;

import java.util.Objects;

/**
 * Represents a planet with its fundamental properties. Includes orbital
 * parameters relative to its host star.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public record Planet(double mass, double radius, double orbitRadius, Star hostStar) implements StellarObject {

	/**
	 * Creates a new planet with validated parameters.
	 * 
	 * @throws NullPointerException     if <b>star</b> is {@code null}
	 * @throws IllegalArgumentException if any parameter is invalid
	 */
	public Planet {
		Objects.requireNonNull(hostStar, "host star cannot be null");

		if (mass <= 0)
			throw new IllegalArgumentException("mass must be positive");
		if (radius <= 0)
			throw new IllegalArgumentException("radius must be positive");
		if (orbitRadius <= 0)
			throw new IllegalArgumentException("orbit radius must be positive");
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
	 * Returns the orbital radius in astronomical units.
	 * 
	 * @return orbital radius in AU
	 */
	public double getOrbitRadius() {
		return orbitRadius;
	}

	/**
	 * Returns the host star of this planet.
	 * 
	 * @return the host star
	 */
	public Star getHostStar() {
		return hostStar;
	}
}
