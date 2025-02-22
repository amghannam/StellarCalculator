package com.stellar.calculator.physics.constants;

/**
 * Time-related constants used in astronomical calculations.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public final class TimeConstants {
	private TimeConstants() {
	}

	/** Seconds in a day */
	public static final double SECONDS_PER_DAY = 86400.0;
	
	/** Seconds in an hour */ 
	public static final double SECONDS_PER_HOUR = 3600.0;

	/** Days in a year */
	public static final double DAYS_PER_YEAR = 365.25;

	/** Seconds in a year */
	public static final double SECONDS_PER_YEAR = DAYS_PER_YEAR * SECONDS_PER_DAY;
}
