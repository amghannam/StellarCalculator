package com.stellar.calculator.physics.constants;

/**
 * Fundamental physical constants used in stellar calculations. All constants
 * are in SI units unless otherwise specified.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public final class PhysicalConstants {
	private PhysicalConstants() {
	}

	/** Gravitational constant (m³⋅kg⁻¹⋅s⁻²) */
	public static final double G = 6.674E-11;

	/** Stefan-Boltzmann constant (W⋅m⁻²⋅K⁻⁴) */
	public static final double STEFAN_BOLTZMANN = 5.670374419E-8;

	/** Speed of light in vacuum (m/s) */
	public static final double C = 2.99792458E8;
}
