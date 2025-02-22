package com.stellar.calculator.calculation.angular;

import com.stellar.calculator.calculation.api.Calculator;
import com.stellar.calculator.domain.model.Star;
import com.stellar.calculator.physics.constants.SolarSystemConstants;
import com.stellar.calculator.physics.equations.GeometricEquations;

/**
 * Calculates the angular size of a star as seen from Earth-equivalent distance.
 * 
 * @author Ahmed Ghannam
 * @version 1.0 
 */
public class AngularSizeCalculator implements Calculator<Star, AngularSizeResult> {

	@Override
	public AngularSizeResult calculate(Star star) {
		double earthEquivDistance = calculateEarthEquivalentDistance(star);
		double angularSize = GeometricEquations.calculateAngularSize(star.getRadius(), earthEquivDistance);
		return new AngularSizeResult(angularSize);
	}

	private double calculateEarthEquivalentDistance(Star star) {
		return Math.sqrt(star.getRadius() * star.getRadius()
				* Math.pow(star.getTemperature() / SolarSystemConstants.SOLAR_TEMPERATURE, 4));
	}

	@Override
	public String getDescription() {
		return "Angular size as seen from Earth-equivalent distance";
	}
}
