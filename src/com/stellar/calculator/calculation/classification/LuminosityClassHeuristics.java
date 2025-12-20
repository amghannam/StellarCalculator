package com.stellar.calculator.calculation.classification;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Internal heuristics for MK luminosity class detection.
 *
 * <p>
 * This class intentionally uses soft thresholds rather than a single "perfect"
 * astrophysical model. The goal is stable, human-friendly MK-style
 * classification from limited inputs while returning a confidence score and
 * rationale.
 * </p>
 *
 * <p>
 * Package-private by design: the public surface should be the calculator that
 * produces {@code LuminosityClassResult}.
 * </p>
 *
 * @author Ahmed Ghannam
 * @version 1.2
 */
final class LuminosityClassHeuristics {

	private LuminosityClassHeuristics() {}

	private static final double MS_DELTA_STRICT = 0.60;
	private static final double SD_DELTA = -0.60;

	private static final Rule[] EVOLVED_RULES = {
			Rule.of(LuminosityClass.I, 100.0, 5.0, 100.0, 0.90, 0.90,
					"Extremely large radius and/or very high luminosity."),
			Rule.of(LuminosityClass.II, 30.0, 4.0, 50.0, 0.75, 0.85,
					"Large radius/high luminosity consistent with bright giants."),
			Rule.of(LuminosityClass.III, 10.0, 2.5, 15.0, 0.70, 0.80,
					"Expanded radius and elevated luminosity consistent with giants."),
			Rule.of(LuminosityClass.IV, 3.0, 1.2, 4.0, 0.60, 0.70,
					"Moderately expanded radius suggests subgiant evolution.")
	};

	/**
	 * Detects an MK luminosity class using a sequence of heuristics (first match
	 * wins).
	 *
	 * <p>
	 * <b>Signals used today:</b> radius, log luminosity, and main-sequence delta.
	 * </p>
	 * <p>
	 * <b>Signals carried for future refinement:</b> mass, temperature, luminosity.
	 * </p>
	 *
	 * @param in aggregated physical inputs required for luminosity class detection
	 * @return detection result including luminosity class, confidence in [0..1],
	 *         and rationale
	 * @throws NullPointerException if {@code in} is null
	 */
	static Detection detect(LuminosityClassInputs in) {
		Objects.requireNonNull(in, "in");

		// Precedence matters: first match wins.
		var pipeline = DetectionPipeline.of(
				LuminosityClassHeuristics::whiteDwarf,
				LuminosityClassHeuristics::mainSequence,
				LuminosityClassHeuristics::subdwarf,
				LuminosityClassHeuristics::evolved  // e.g. giants, subgiants, etc.
		);

		return pipeline.firstOrDefault(in, defaultDwarf());
	}

	// -- Individual detectors ----------------------------------------------------

	private static Optional<Detection> whiteDwarf(LuminosityClassInputs in) {
		if (!(in.radiusSolar() < 0.05 && in.logL() < -1.0)) return Optional.empty();

		double conf = clamp01(0.75 + 0.25 * strong(in.radiusSolar(), 0.05) * strong(-in.logL(), 1.0));
		return Optional.of(new Detection(LuminosityClass.VII, conf,
				"Very small radius and low luminosity suggest a compact object (white dwarf regime)."));
	}

	private static Optional<Detection> mainSequence(LuminosityClassInputs in) {
		if (!isRadiusInMainSequenceWindow(in.radiusSolar())) return Optional.empty();
		if (Math.abs(in.deltaLogMainSequence()) > MS_DELTA_STRICT) return Optional.empty();

		double conf = 0.70 + 0.30 * (1.0 - Math.min(1.0, Math.abs(in.deltaLogMainSequence()) / MS_DELTA_STRICT));
		return Optional.of(new Detection(LuminosityClass.V, clamp01(conf),
				"Consistent with the main-sequence mass–luminosity baseline (|ΔlogL_MS| ≤ 0.6)."));
	}

	private static Optional<Detection> subdwarf(LuminosityClassInputs in) {
		if (!(in.deltaLogMainSequence() <= SD_DELTA && in.radiusSolar() < 1.5)) return Optional.empty();

		double conf = 0.65 + 0.25 * (1.0 - Math.min(1.0, Math.abs(in.deltaLogMainSequence()) / 1.5));
		return Optional.of(new Detection(LuminosityClass.VI, clamp01(conf),
				"Under-luminous for its mass with a compact radius suggests a subdwarf (metal-poor/high-gravity) regime."));
	}

	private static Optional<Detection> evolved(LuminosityClassInputs in) {
		return firstMatch(EVOLVED_RULES, in.radiusSolar(), in.logL())
				.map(rule -> new Detection(rule.luminosityClass(), rule.confidence(in.radiusSolar()), rule.rationale()));
	}

	// -- Shared helpers ----------------------------------------------------------

	private static Detection defaultDwarf() {
		return new Detection(LuminosityClass.V, 0.55,
				"Falls outside strong giant/compact regimes; defaulting to dwarf/main-sequence classification.");
	}

	private static boolean isRadiusInMainSequenceWindow(double radiusSolar) {
		return radiusSolar >= 0.10 && radiusSolar <= 15.0;
	}

	private static Optional<Rule> firstMatch(Rule[] rules, double radiusSolar, double logL) {
		// Streams keep the method expression-based while preserving "first match wins".
		return Arrays.stream(rules)
				.filter(r -> r.matches(radiusSolar, logL))
				.findFirst();
	}

	private static double strong(double value, double threshold) {
		double x = (value - threshold) / threshold;
		return clamp01(x);
	}

	private static double clamp01(double v) {
		return Math.max(0.0, Math.min(1.0, v));
	}

	// -- Small internal types ----------------------------------------------------

	/**
	 * Parameter object for luminosity class detection.
	 *
	 * <p>This eliminates the "many doubles" signature and gives each signal a name.</p>
	 */
	record LuminosityClassInputs(
			double massSolar,
			double radiusSolar,
			double temperatureK,
			double luminositySolar,
			double logL,
			double deltaLogMainSequence
	) {}

	private record Rule(
			LuminosityClass luminosityClass,
			double radiusMin,
			double logLMin,
			double radiusHighForConf,
			double confLow,
			double confHigh,
			String rationale
	) {
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

	private record DetectionPipeline(List<Function<LuminosityClassInputs, Optional<Detection>>> steps) {

		@SafeVarargs
		static DetectionPipeline of(Function<LuminosityClassInputs, Optional<Detection>>... steps) {
			Objects.requireNonNull(steps, "steps");
			return new DetectionPipeline(List.of(steps));
		}

		Detection firstOrDefault(LuminosityClassInputs in, Detection fallback) {
			return steps.stream()
					.map(step -> step.apply(in))
					.flatMap(Optional::stream)
					.findFirst()
					.orElse(fallback);
		}
	}

	record Detection(LuminosityClass luminosityClass, double confidence, String rationale) {}
}
