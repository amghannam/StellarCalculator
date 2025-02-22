package com.stellar.calculator.calculation.radiation;

import com.stellar.calculator.calculation.api.Calculator;
import com.stellar.calculator.domain.model.Star;
import com.stellar.calculator.physics.equations.ThermalEquations;

/**
 * Calculates the irradiance (incident radiation) at a fixed distance of 1 AU.
 * This provides a standardized comparison with the Solar System.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public class IrradianceCalculator implements Calculator<Star, IrradianceResult> {

    @Override
    public IrradianceResult calculate(Star star) {
        // Calculate irradiance at fixed 1 AU distance
        double irradiance = ThermalEquations.calculateIrradianceAtDistance(
            star.getRadius(),
            star.getTemperature(),
            1.0  // Fixed at 1 AU (for illustrative purposes)
        );
        return new IrradianceResult(irradiance);
    }

    @Override
    public String getDescription() {
        return "Incident radiation at 1 AU";
    }
}
