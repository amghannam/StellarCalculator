package com.stellar.calculator.physics.equations;

import com.stellar.calculator.physics.constants.PhysicalConstants;
import com.stellar.calculator.physics.constants.SolarSystemConstants;

/**
 * Orbital mechanics calculations for stellar systems. Includes period,
 * velocity, and orbital distance calculations.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public final class OrbitalEquations {

	private OrbitalEquations() {
	}

	/**
	 * Calculates orbital period using Kepler's Third Law.
	 *
	 * @param stellarMass the mass of the central body in solar masses
	 * @param orbitRadius the orbital radius in AU
	 * @return the orbital period in seconds
	 */
	public static double calculatePeriod(double stellarMass, double orbitRadius) {
		double r = orbitRadius * SolarSystemConstants.AU;
		double m = stellarMass * SolarSystemConstants.SOLAR_MASS;
		return 2 * Math.PI * Math.sqrt(Math.pow(r, 3) / (PhysicalConstants.G * m));
	}

	/**
	 * Calculates circular orbital velocity.
	 *
	 * @param stellarMass the mass of central body in solar masses
	 * @param orbitRadius the orbital radius in AU
	 * @return the orbital velocity in m/s
	 */
	public static double calculateVelocity(double stellarMass, double orbitRadius) {
		double r = orbitRadius * SolarSystemConstants.AU;
		double m = stellarMass * SolarSystemConstants.SOLAR_MASS;
		return Math.sqrt(PhysicalConstants.G * m / r);
	}

	/**
	 * Calculates the Hill sphere radius (gravitational sphere of influence).
	 *
	 * @param stellarMass   the mass of central body in solar masses
	 * @param orbitRadius   the orbital radius in AU
	 * @param satelliteMass the mass of orbiting body in solar masses
	 * @return the Hill sphere radius in AU
	 */
	public static double calculateHillSphere(double stellarMass, double orbitRadius, double satelliteMass) {
		return orbitRadius * Math.pow(satelliteMass / (3 * stellarMass), 1.0 / 3.0);
	}
}
