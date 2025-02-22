package com.stellar.calculator.physics.constants;

/**
 * Relevant solar system reference values and astronomical constants. These
 * constants define the solar-based unit system.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public final class SolarSystemConstants {

	private SolarSystemConstants() {
	}

	/** Solar radius (m) */
	public static final double SOLAR_RADIUS = 6.957E8;

	/** Solar mass (kg) */
	public static final double SOLAR_MASS = 1.989E30;

	/** Solar luminosity (W) */
	public static final double SOLAR_LUMINOSITY = 3.828E26;

	/** Solar effective temperature (K) */
	public static final double SOLAR_TEMPERATURE = 5778.0;

	/** Solar constant - irradiance at 1 AU (W/m²) */
	public static final double SOLAR_CONSTANT = 1361.0;

	/** Apparent magnitude of the Sun as seen from Earth */
	public static final double SOLAR_APPARENT_MAG = -26.74;

	/** Astronomical Unit (m) */
	public static final double AU = 1.495978707E11;

	/** Solar angular diameter as seen from Earth (degrees) */
	public static final double ANGULAR_SIZE_SOL = 0.53;

	/** Days in a year */
	public static final double DAYS_IN_YEAR = 365.25;

	/** Seconds in a day */
	public static final double SECONDS_PER_DAY = 86400.0;

	/** Seconds in a year (derived) */
	public static final double SECONDS_PER_YEAR = DAYS_IN_YEAR * SECONDS_PER_DAY;
}
