package com.stellar.calculator.calculation.radiation;

import com.stellar.calculator.calculation.api.Calculator;
import com.stellar.calculator.domain.model.Star;
import com.stellar.calculator.physics.constants.SolarSystemConstants;

/**
 * Calculates the distance at which a planet would receive Earth-like stellar
 * radiation.
 * 
 * <p>
 * This calculator determines the orbital distance where a planet would receive
 * the same amount of stellar radiation as Earth receives from the Sun (1361
 * W/m²). The calculation uses the inverse square law of radiation and accounts
 * for both the star's radius and surface temperature.
 * 
 * <p>
 * The distance is calculated using the formula: d = √(R²(T/T⊙)⁴) where:
 * <ul>
 * <li>d is the distance in AU
 * <li>R is the star's radius in solar radii
 * <li>T is the star's temperature in Kelvin
 * <li>T⊙ is the Sun's temperature (5778K)
 * </ul>
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 * @see EarthEquivalentDistanceResult
 * @see com.stellar.calculator.physics.constants.SolarSystemConstants#SOLAR_TEMPERATURE
 */
public class EarthEquivalentDistanceCalculator implements Calculator<Star, EarthEquivalentDistanceResult> {

	@Override
	public EarthEquivalentDistanceResult calculate(Star star) {
		// Calculate the distance where irradiance equals Earth's
		double distance = Math.sqrt(star.getRadius() * star.getRadius()
				* Math.pow(star.getTemperature() / SolarSystemConstants.SOLAR_TEMPERATURE, 4));

		return new EarthEquivalentDistanceResult(distance);
	}

	@Override
	public String getDescription() {
		return "Distance where irradiance equals Earth's";
	}
}
