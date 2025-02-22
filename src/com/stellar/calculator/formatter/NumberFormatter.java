package com.stellar.calculator.formatter;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.stellar.calculator.physics.constants.TimeConstants;

/**
 * A utility class for formatting astronomical and physical measurements into
 * human-readable strings. Handles formatting of orbital periods, astronomical
 * distances, temperatures, and numerical rounding.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public class NumberFormatter {

	/**
	 * Formats an orbital period from seconds into a human-readable string with
	 * appropriate units. The method automatically selects the most appropriate time
	 * unit (years, days, hours, or seconds) based on the magnitude of the input
	 * value.
	 *
	 * @param periodInSeconds the orbital period in seconds
	 * @return a formatted string with the period and appropriate unit (e.g., "1.23
	 *         years", "45.6 days", "12.0 hours", or "30.0 seconds")
	 */
	public String formatPeriod(double periodInSeconds) {
		if (periodInSeconds >= TimeConstants.SECONDS_PER_YEAR) {
			double years = periodInSeconds / TimeConstants.SECONDS_PER_YEAR;
			return String.format("%.2f years", years);
		} else if (periodInSeconds >= TimeConstants.SECONDS_PER_DAY) {
			double days = periodInSeconds / TimeConstants.SECONDS_PER_DAY;
			return String.format("%.1f days", days);
		} else if (periodInSeconds >= TimeConstants.SECONDS_PER_HOUR) {
			double hours = periodInSeconds / TimeConstants.SECONDS_PER_HOUR;
			return String.format("%.1f hours", hours);
		} else {
			return String.format("%.1f seconds", periodInSeconds);
		}
	}

	/**
	 * Formats an astronomical distance from Astronomical Units (AU) into a
	 * human-readable string. For distances less than 0.01 AU, converts to millions
	 * of kilometers for better readability.
	 *
	 * @param distanceInAU the distance in Astronomical Units (AU)
	 * @return a formatted string with the distance in either AU or million km
	 *         (e.g., "1.23 AU" or "149.6 million km")
	 */
	public String formatDistance(double distanceInAU) {
		if (distanceInAU < 0.01) {
			return String.format("%.1f million km", distanceInAU * 149.6);
		}
		return String.format("%.2f AU", distanceInAU);
	}

	/**
	 * Formats a temperature value in Kelvin to a specified temperature scale.
	 * Supports conversion to Kelvin, Celsius, or Fahrenheit with appropriate
	 * formatting.
	 *
	 * @param temperatureK the temperature in Kelvin
	 * @param format       the desired temperature format (KELVIN, CELSIUS, or
	 *                     FAHRENHEIT)
	 * @return a formatted string with the temperature in the specified format
	 *         (e.g., "300 K", "26.9 °C", or "80.3 °F")
	 */
	public String formatTemperature(double temperatureK, TemperatureFormat format) {
		return switch (format) {
		case KELVIN -> String.format("%.0fK", temperatureK);
		case CELSIUS -> String.format("%.1f °C", temperatureK - 273.15);
		case FAHRENHEIT -> String.format("%.1f °F", (temperatureK - 273.15) * 9 / 5 + 32);
		};
	}

	/**
	 * Rounds a double value to a specified number of decimal places using HALF_UP
	 * rounding mode.
	 *
	 * @param value  the value to round
	 * @param places the number of decimal places to round to
	 * @return the rounded value
	 */
	public double round(double value, int places) {
		return BigDecimal.valueOf(value).setScale(places, RoundingMode.HALF_UP).doubleValue();
	}
}
