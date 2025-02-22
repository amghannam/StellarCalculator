package com.stellar.calculator.service.api;

import java.util.List;

import com.stellar.calculator.calculation.api.CalculationResult;
import com.stellar.calculator.calculation.api.Calculator;
import com.stellar.calculator.domain.model.Star;

/**
 * Defines the contract for calculation services. Services coordinate multiple
 * calculations and aggregate results.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public interface CalculationService {
	/**
	 * Performs all registered calculations for a star.
	 *
	 * @param star the star to analyze
	 * @return the list of calculation results
	 */
	List<CalculationResult> calculateAll(Star star);

	/**
	 * Registers a new calculator with the service.
	 *
	 * @param calculator the calculator to add
	 */
	void registerCalculator(Calculator<Star, ? extends CalculationResult> calculator);
}
