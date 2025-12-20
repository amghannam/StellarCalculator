package com.stellar.calculator.calculation.classification;

import java.util.List;

/**
 * Internal temperature bands used to map an effective temperature to an MK
 * spectral type and subtype.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
enum SpectralTemperatureBand {

	O(SpectralType.O, 30000, Double.POSITIVE_INFINITY), 
	B(SpectralType.B, 10000, 30000), 
	A(SpectralType.A, 7500, 10000),
	F(SpectralType.F, 6000, 7500), 
	G(SpectralType.G, 5200, 6000), 
	K(SpectralType.K, 3700, 5200),
	M(SpectralType.M, 2400, 3700), 
	L(SpectralType.L, 1300, 2400), 
	T(SpectralType.T, 550, 1300),
	Y(SpectralType.Y, 0, 550);

	private final SpectralType type;
	private final double minInclusive;
	private final double maxExclusive;

	SpectralTemperatureBand(SpectralType type, double minInclusive, double maxExclusive) {
		this.type = type;
		this.minInclusive = minInclusive;
		this.maxExclusive = maxExclusive;
	}

	SpectralType type() {
		return type;
	}

	static SpectralTemperatureBand forTemperature(double temperatureK) {
		// Handle extreme hot end first (O)
		if (temperatureK >= O.minInclusive) {
			return O;
		}
		// Then all finite bands
		for (var b : List.of(B, A, F, G, K, M, L, T, Y)) {
			if (temperatureK >= b.minInclusive && temperatureK < b.maxExclusive) {
				return b;
			}
		}
		// Fallback (shouldn't happen with positive temperatures)
		return Y;
	}

	int estimateSubtype(double temperatureK) {
		// Subtype is 0 (hot end) to 9 (cool end) within the band's range.
		// For O (unbounded upper), clamp using a reasonable hot cap.
		double hi = maxExclusive;
		double lo = minInclusive;

		if (Double.isInfinite(hi)) {
			hi = 50000; // practical upper cap for subtype estimation
		}
		
		// Avoid divide-by-zero (Y band min is 0)
		if (hi <= lo + 1e-9) {
			return 0;
		}

		double x = (hi - temperatureK) / (hi - lo); // 0 at hi, 1 at lo
		int subtype = (int) Math.floor(x * 10.0);
		// Subtype must strictly be between 0 and 9.
		if (subtype < 0)
			subtype = 0;
		
		if (subtype > 9)
			subtype = 9;
		
		return subtype;
	}
}
