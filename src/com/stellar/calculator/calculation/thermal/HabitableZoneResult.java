package com.stellar.calculator.calculation.thermal;

import com.stellar.calculator.calculation.api.CalculationResult;

/**
 * Represents the result of a habitable zone calculation. Stores and formats the
 * calculated inner and outer boundaries of the habitable zone.
 *
 * <p>
 * This class is immutable and thread-safe. The boundaries are stored in
 * astronomical units (AU), representing the range where liquid water could
 * potentially exist on a planetary surface.
 *
 * @author Claude
 * @version 1.0
 * @see HabitableZoneCalculator
 */
public record HabitableZoneResult(double innerBoundary, double outerBoundary) implements CalculationResult {
	public HabitableZoneResult {
		if (innerBoundary <= 0) {
			throw new IllegalArgumentException("inner boundary must be positive");
		}
		if (outerBoundary <= innerBoundary) {
			throw new IllegalArgumentException("outer boundary must be greater than inner boundary");
		}
	}

	@Override
	public String format() {
		return String.format("""
				Habitable Zone Boundaries
				Inner boundary: %.2f AU (too hot beyond this point)
				Outer boundary: %.2f AU (too cold beyond this point)
				For reference: Earth orbits at 1.0 AU""", innerBoundary, outerBoundary);
	}

	@Override
	public double getValue() {
		return innerBoundary;
	}
}
