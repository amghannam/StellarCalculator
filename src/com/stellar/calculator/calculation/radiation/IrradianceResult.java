package com.stellar.calculator.calculation.radiation;

import com.stellar.calculator.calculation.api.CalculationResult;
import com.stellar.calculator.physics.constants.SolarSystemConstants;

/**
 * Represents the result of a stellar irradiance calculation. Stores the amount
 * of radiation received at a specific distance from the star.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public record IrradianceResult(double irradiance) implements CalculationResult {

	public IrradianceResult {
		if (irradiance < 0) {
			throw new IllegalArgumentException("irradiance cannot be negative");
		}
	}

	@Override
	public String format() {
		String radiationLevel = formatIrradianceValue();
		double percentOfEarth = (irradiance / SolarSystemConstants.SOLAR_CONSTANT) * 100;
		return String.format("""
				Radiation at 1 AU
				%s
				Comparison: %.1f%% of Earth's solar radiation
				(How irradiated a planet would be at 1 AU from this star relative to Earth)""", radiationLevel, percentOfEarth);
	}

	private String formatIrradianceValue() {
		if (irradiance >= 1_000_000_000) {
			return String.format("Level: %.2f GW/m²", irradiance / 1_000_000_000.0);
		} else if (irradiance >= 1_000_000) {
			return String.format("Level: %.2f MW/m²", irradiance / 1_000_000.0);
		} else if (irradiance >= 1_000) {
			return String.format("Level: %.2f kW/m²", irradiance / 1_000.0);
		}
		return String.format("Level: %.2f W/m²", irradiance);
	}

	@Override
	public double getValue() {
		return irradiance;
	}
}
