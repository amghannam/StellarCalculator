package com.stellar.calculator.calculation.magnitude;

import com.stellar.calculator.calculation.api.Calculator;
import com.stellar.calculator.domain.model.Star;
import com.stellar.calculator.physics.equations.MagnitudeEquations;

/**
 * Calculates the apparent magnitude of a star at a fixed distance of 1 AU.
 * 
 * <p>
 * The apparent magnitude is a measure of a star's brightness as seen by an
 * observer. The scale is logarithmic and inverse - the brighter the object, the
 * lower (more negative) its magnitude. A difference of 5 magnitudes corresponds
 * to a factor of 100 in brightness.
 * 
 * <p>
 * For reference:
 * <ul>
 * <li>Sun's apparent magnitude: -26.74
 * <li>Full Moon: -12.5
 * <li>Venus at brightest: -4.4
 * <li>Sirius (brightest star): -1.46
 * </ul>
 * 
 * @author Ahmed Ghannam
 * @version 1.1
 * @see ApparentMagnitudeResult
 * @see com.stellar.calculator.physics.equations.MagnitudeEquations
 */
public class ApparentMagnitudeCalculator implements Calculator<Star, ApparentMagnitudeResult> {

	@Override
	public ApparentMagnitudeResult calculate(Star star) {
		double magnitude = MagnitudeEquations.calculateApparentMagnitude(star.getRadius(), star.getTemperature());
		return new ApparentMagnitudeResult(magnitude);
	}

	@Override
	public String getDescription() {
		return "Apparent magnitude at Earth-equivalent distance";
	}
}
