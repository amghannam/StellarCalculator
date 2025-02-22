// File: src/main/java/com/stellar/calculator/calculation/orbital/OrbitalPeriodCalculator.java
package com.stellar.calculator.calculation.orbital;

import java.util.Objects;

import com.stellar.calculator.calculation.api.Calculator;
import com.stellar.calculator.domain.model.Star;
import com.stellar.calculator.physics.constants.SolarSystemConstants;
import com.stellar.calculator.physics.equations.OrbitalEquations;

/**
 * Calculates the orbital period for a planet at Earth-equivalent distance.
 * 
 * <p>
 * This calculator uses Kepler's Third Law to compute the orbital period of a
 * hypothetical planet orbiting at the distance where it would receive the same
 * amount of stellar radiation as Earth receives from the Sun.
 *
 * <p>
 * The calculation follows Kepler's Third Law: T² = (4π²/GM) * r³ where:
 * <ul>
 * <li>T is the orbital period
 * <li>G is the gravitational constant
 * <li>M is the stellar mass
 * <li>r is the orbital radius
 * </ul>
 *
 * @author Ahmed Ghannam
 * @version 1.0
 * @see OrbitalPeriodResult
 * @see OrbitalEquations
 */
public class OrbitalPeriodCalculator implements Calculator<Star, OrbitalPeriodResult> {

	/**
	 * Calculates the orbital period for a planet at Earth-equivalent distance.
	 *
	 * @param star the star to analyze, must not be null
	 * @return the calculated orbital period result
	 * @throws NullPointerException     if <b>star</b> is {@code null}
	 * @throws IllegalArgumentException if <b>star</b> has invalid parameters
	 */
	@Override
	public OrbitalPeriodResult calculate(Star star) {
		Objects.requireNonNull(star, "star cannot be null");
		double period = OrbitalEquations.calculatePeriod(star.getMass(), calculateEarthEquivalentDistance(star));
		return new OrbitalPeriodResult(period);
	}

	private double calculateEarthEquivalentDistance(Star star) {
		return Math.sqrt(star.getRadius() * star.getRadius()
				* Math.pow(star.getTemperature() / SolarSystemConstants.SOLAR_TEMPERATURE, 4));
	}

	@Override
	public String getDescription() {
		return "Orbital period at Earth-equivalent distance";
	}
}
