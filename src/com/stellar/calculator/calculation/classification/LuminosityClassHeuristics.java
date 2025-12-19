package com.stellar.calculator.calculation.classification;

import java.util.Objects;
import java.util.function.Function;

/**
 * Internal heuristics for luminosity class detection.
 *
 * <p>
 * This class intentionally uses soft thresholds rather than a single "perfect"
 * astrophysical model. The goal is stable, human-friendly MK-style
 * classification from limited inputs, while providing a confidence score and a
 * rationale.
 * </p>
 *
 * <p>
 * All methods are package-private by design: the public surface should be the
 * calculator that produces {@code LuminosityClassResult}.
 * </p>
 *
 * @author Ahmed Ghannam
 * @version 1.0
 */
final class LuminosityClassHeuristics {

	private LuminosityClassHeuristics() {
	}

	private static final double MS_DELTA_STRICT = 0.60;
	private static final double SD_DELTA = -0.60;

	/**
	 * Ordered rules for evolved stars.
	 *
	 * <p>
	 * Order matters: we want the earliest match to win (supergiant before bright
	 * giant, etc.). The confidence bump radius is preserved to match the original
	 * behavior exactly.
	 * </p>
	 */
	private static final Rule[] EVOLVED_RULES = {
			Rule.of(LuminosityClass.I, 100.0, 5.0, 100.0, 0.90, 0.90,
					"Extremely large radius and/or very high luminosity."),
			Rule.of(LuminosityClass.II, 30.0, 4.0, 50.0, 0.75, 0.85,
					"Large radius/high luminosity consistent with bright giants."),
			Rule.of(LuminosityClass.III, 10.0, 2.5, 15.0, 0.70, 0.80,
					"Expanded radius and elevated luminosity consistent with giants."),
			Rule.of(LuminosityClass.IV, 3.0, 1.2, 4.0, 0.60, 0.70,
					"Moderately expanded radius suggests subgiant evolution.") };

	/**
	 * Detects an MK luminosity class using a sequence of heuristics.
	 *
	 * <p>
	 * Heuristic precedence (first match wins):
	 * </p>
	 * <ol>
	 * <li>Compact objects (white dwarf regime)</li>
	 * <li>Main-sequence consistency (mass–luminosity baseline)</li>
	 * <li>Subdwarfs (under-luminous for mass, compact radius)</li>
	 * <li>Evolved stars (subgiant → giant → bright giant → supergiant)</li>
	 * <li>Fallback to dwarf (lower confidence)</li>
	 * </ol>
	 *
	 * <p>
	 * The method returns both a class and a confidence score, plus a short
	 * rationale for transparency in CLI output.
	 * </p>
	 *
	 * @param massSolar            stellar mass in solar masses (M☉)
	 * @param radiusSolar          stellar radius in solar radii (R☉)
	 * @param temperatureK         effective temperature in Kelvin (currently unused
	 *                             but kept for future refinement)
	 * @param luminositySolar      stellar luminosity in solar luminosities (L☉)
	 *                             (currently unused; we use logL)
	 * @param logL                 log10(L/L☉)
	 * @param deltaLogMainSequence log10(L/L☉) - log10(L_MS/L☉); ~0 means
	 *                             MS-consistent
	 * @return detection result including luminosity class, confidence in [0..1],
	 *         and a rationale string
	 */
	static Detection detect(double massSolar, double radiusSolar, double temperatureK, 
			double luminositySolar, double logL, double deltaLogMainSequence) {

		// Pipeline keeps the main entry-point tiny and makes precedence explicit.
		var pipeline = DetectionPipeline.of(d -> d.whiteDwarf(), d -> d.mainSequence(), d -> d.subdwarf(),
				d -> d.evolved());

		return pipeline.firstOrDefault(new Inputs(radiusSolar, logL, deltaLogMainSequence), defaultDwarf());
	}

	// -- Individual detectors ----------------------------------------------------

	private static Detection whiteDwarf(Inputs in) {
		if (!(in.radiusSolar() < 0.05 && in.logL() < -1.0))
			return null;

		// Stronger evidence (smaller radius, lower luminosity) increases confidence.
		double conf = clamp01(0.75 + 0.25 * strong(in.radiusSolar(), 0.05) * strong(-in.logL(), 1.0));
		return new Detection(LuminosityClass.VII, conf,
				"Very small radius and low luminosity suggest a compact object (white dwarf regime).");
	}

	private static Detection mainSequence(Inputs in) {
		if (!isRadiusInMainSequenceWindow(in.radiusSolar()))
			return null;
		if (Math.abs(in.deltaLogMainSequence()) > MS_DELTA_STRICT)
			return null;

		double conf = 0.70 + 0.30 * (1.0 - Math.min(1.0, Math.abs(in.deltaLogMainSequence()) / MS_DELTA_STRICT));
		return new Detection(LuminosityClass.V, clamp01(conf),
				"Consistent with the main-sequence mass–luminosity baseline (|ΔlogL_MS| ≤ 0.6).");
	}

	private static Detection subdwarf(Inputs in) {
		if (!(in.deltaLogMainSequence() <= SD_DELTA && in.radiusSolar() < 1.5))
			return null;

		double conf = 0.65 + 0.25 * (1.0 - Math.min(1.0, Math.abs(in.deltaLogMainSequence()) / 1.5));
		return new Detection(LuminosityClass.VI, clamp01(conf),
				"Under-luminous for its mass with a compact radius suggests a subdwarf (metal-poor/high-gravity) regime.");
	}

	private static Detection evolved(Inputs in) {
		Rule rule = firstMatch(EVOLVED_RULES, in.radiusSolar(), in.logL());
		if (rule == null)
			return null;
		return new Detection(rule.luminosityClass(), rule.confidence(in.radiusSolar()), rule.rationale());
	}

	// -- Shared helpers ----------------------------------------------------------

	private static Detection defaultDwarf() {
		// Fallback is deliberately lower confidence: it means "nothing else strongly
		// matched".
		return new Detection(LuminosityClass.V, 0.55,
				"Falls outside strong giant/compact regimes; defaulting to dwarf/main-sequence classification.");
	}

	private static boolean isRadiusInMainSequenceWindow(double radiusSolar) {
		return radiusSolar >= 0.10 && radiusSolar <= 15.0;
	}

	private static Rule firstMatch(Rule[] rules, double radiusSolar, double logL) {
		for (Rule r : rules) {
			if (r.matches(radiusSolar, logL))
				return r;
		}
		return null;
	}

	private static double strong(double value, double threshold) {
		// Returns 0..1 where 0 is barely meeting threshold and 1 is much stronger.
		double x = (value - threshold) / threshold;
		return clamp01(x);
	}

	private static double clamp01(double v) {
		return Math.max(0.0, Math.min(1.0, v));
	}

	// -- Small internal types ----------------------------------------------------

	/**
	 * Minimal set of inputs this heuristic currently needs.
	 *
	 * <p>
	 * Keeping this small prevents the detector signatures from ballooning, and
	 * allows the pipeline to pass a single value.
	 * </p>
	 */
	private record Inputs(double radiusSolar, double logL, double deltaLogMainSequence) {

		Detection whiteDwarf() {
			return LuminosityClassHeuristics.whiteDwarf(this);
		}

		Detection mainSequence() {
			return LuminosityClassHeuristics.mainSequence(this);
		}

		Detection subdwarf() {
			return LuminosityClassHeuristics.subdwarf(this);
		}

		Detection evolved() {
			return LuminosityClassHeuristics.evolved(this);
		}
	}

	private record Rule(LuminosityClass luminosityClass, 
			double radiusMin, 
			double logLMin, 
			double radiusHighForConf,
			double confLow, 
			double confHigh, 
			String rationale) {
		static Rule of(LuminosityClass lc, 
				double radiusMin, 
				double logLMin, 
				double radiusHighForConf, 
				double confLow,
				double confHigh, 
				String rationale) {
			return new Rule(lc, radiusMin, logLMin, radiusHighForConf, confLow, confHigh, rationale);
		}

		boolean matches(double radiusSolar, double logL) {
			return radiusSolar >= radiusMin || logL >= logLMin;
		}

		double confidence(double radiusSolar) {
			return radiusSolar >= radiusHighForConf ? confHigh : confLow;
		}
	}

	/**
	 * A tiny pipeline runner: applies detectors in order and returns the first
	 * non-null result.
	 *
	 * <p>
	 * This avoids long if-chains and makes the precedence list easy to read.
	 * </p>
	 */
	private record DetectionPipeline(Function<Inputs, Detection>[] steps) {

		@SafeVarargs
		static DetectionPipeline of(Function<Inputs, Detection>... steps) {
			Objects.requireNonNull(steps, "steps");
			return new DetectionPipeline(steps);
		}

		Detection firstOrDefault(Inputs in, Detection fallback) {
			for (Function<Inputs, Detection> step : steps) {
				Detection d = step.apply(in);
				if (d != null)
					return d;
			}
			return fallback;
		}
	}

	record Detection(LuminosityClass luminosityClass, double confidence, String rationale) {
	}
}
