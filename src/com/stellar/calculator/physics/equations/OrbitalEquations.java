package com.stellar.calculator.physics.equations;

import com.stellar.calculator.physics.constants.PhysicalConstants;
import com.stellar.calculator.physics.constants.SolarSystemConstants;

/**
 * Orbital mechanics calculations for stellar systems.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public final class OrbitalEquations {

	private OrbitalEquations() {
	}

	/**
	 * Calculates the orbital period of a planet using Kepler's Third Law.
	 *
	 * @param stellarMass the mass of the central body in solar masses
	 * @param orbitRadius the orbital radius in AU
	 * @return the orbital period in seconds
	 */
	public static double calculatePeriod(double stellarMass, double orbitRadius) {
		double r = orbitRadius * SolarSystemConstants.AU; // Convert to meters
		double m = stellarMass * SolarSystemConstants.SOLAR_MASS;
		return 2 * Math.PI * Math.sqrt(Math.pow(r, 3) / (PhysicalConstants.G * m));
	}
}
