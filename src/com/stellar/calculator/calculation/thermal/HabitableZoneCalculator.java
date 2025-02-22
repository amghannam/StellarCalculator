package com.stellar.calculator.calculation.thermal;

import java.util.Objects;

import com.stellar.calculator.calculation.api.Calculator;
import com.stellar.calculator.calculation.luminosity.LuminosityCalculator;
import com.stellar.calculator.domain.model.Star;

/**
 * Estimates the boundaries of a star's habitable zone.
 * 
 * <p>
 * This calculator roughly estimates the range of distances from a star where a
 * planet could potentially maintain liquid water on its surface. The
 * calculation uses the star's luminosity to estimate the "Goldilocks zone"
 * where temperatures would be appropriate for liquid water.
 *
 * <p>
 * The boundaries are approximated using the star's luminosity:
 * <ul>
 * <li>Inner boundary ≈ √L * 0.95 AU
 * <li>Outer boundary ≈ √L * 1.37 AU
 * </ul>
 * where L is the star's luminosity in solar units.
 *
 * <p>
 * This estimate is a rough rule of thumb and not universally applicable for all
 * stellar and planetary configurations.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 * @see HabitableZoneResult
 * @see LuminosityCalculator
 */
public class HabitableZoneCalculator implements Calculator<Star, HabitableZoneResult> {
	private final LuminosityCalculator luminosityCalc = new LuminosityCalculator();

	/**
	 * Estimates the habitable zone boundaries for the given star.
	 *
	 * @param star the star to analyze, must not be null
	 * @return the calculated habitable zone boundaries
	 * @throws NullPointerException     if <b>star</b> is {@code null}
	 * @throws IllegalArgumentException if <b>star</b> has invalid parameters
	 */
	@Override
	public HabitableZoneResult calculate(Star star) {
		Objects.requireNonNull(star, "star cannot be null");
		double luminosity = luminosityCalc.calculate(star).getValue();
		double baseRadius = Math.sqrt(luminosity);

		return new HabitableZoneResult(baseRadius * 0.95, // Conservative inner boundary
				baseRadius * 1.37 // Conservative outer boundary
		);
	}

	@Override
	public String getDescription() {
		return "Habitable zone boundaries";
	}
}
