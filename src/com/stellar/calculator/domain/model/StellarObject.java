package com.stellar.calculator.domain.model;

/**
 * Base interface for all stellar objects in the system. Defines common
 * properties that all stellar objects must have.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public interface StellarObject {
	/**
	 * Returns the mass of the stellar object in solar masses.
	 * 
	 * @return the mass in solar masses
	 */
	double getMass();

	/**
	 * Returns the radius of the stellar object in solar radii.
	 * 
	 * @return the radius in solar radii
	 */
	double getRadius();
}
