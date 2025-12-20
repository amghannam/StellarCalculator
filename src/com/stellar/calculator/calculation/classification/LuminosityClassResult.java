package com.stellar.calculator.calculation.classification;

import com.stellar.calculator.calculation.api.CalculationResult;

/**
 * Result of a luminosity class detection. Includes the MK luminosity class,
 * confidence, and key computed properties used by the heuristic.
 *
 * @author Ahmed Ghannam
 * @version 1.0
 */
public record LuminosityClassResult(
		LuminosityClass luminosityClass,
		double confidence,
		double luminositySolar,
		double radiusSolar,
		double temperatureK,
		double massSolar,
		double deltaLogMainSequence,
		String rationale) implements CalculationResult {

	public LuminosityClassResult {
		if (luminosityClass == null) {
			throw new IllegalArgumentException("luminosityClass cannot be null");
		}
		
		if (confidence < 0.0 || confidence > 1.0) {
			throw new IllegalArgumentException("confidence must be between 0 and 1");
		}
		
		if (Double.isNaN(luminositySolar) || luminositySolar <= 0) {
			throw new IllegalArgumentException("luminositySolar must be positive");
		}
		
		if (radiusSolar <= 0 || temperatureK <= 0 || massSolar <= 0) {
			throw new IllegalArgumentException("radius, temperature and mass must be positive");
		}
		
		if (rationale == null) {
			rationale = "";
		}
	}

	@Override
	public String format() {
		return String.format("""
				Luminosity Class (Estimated)
				Output: %s
				Confidence: %.0f%%
				Inputs: M=%.2f M☉, R=%.2f R☉, T=%.0fK
				Derived: L=%.3f L☉ (ΔlogL_MS=%.2f)
				%s""",
				luminosityClass.mkLabel(),
				confidence * 100.0,
				massSolar, radiusSolar, temperatureK,
				luminositySolar, deltaLogMainSequence,
				rationale == null || rationale.isBlank() ? "" : ("Rationale: " + rationale));
	}

	/**
	 * A categorical result; returns NaN as no single numeric value represents the
	 * classification.
	 */
	@Override
	public double getValue() {
		return Double.NaN;
	}
}
