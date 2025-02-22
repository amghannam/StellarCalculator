package com.stellar.calculator.calculation.api;

/**
 * Base interface for all stellar calculators. Defines the contract for
 * performing calculations on stellar objects.
 *
 * @param <T> the type of the input parameter
 * @param <R> the type of the calculation result
 * @author Ahmed Ghannam
 * @version 1.0
 */
public interface Calculator<T, R extends CalculationResult> {
	/**
	 * Performs calculation on the input parameter.
	 * 
	 * @param input the calculation input
	 * @return the calculation result
	 * @throws IllegalArgumentException if the input is invalid
	 */
	R calculate(T input);

	/**
	 * Returns a description of what this calculator computes.
	 * 
	 * @return calculator the calculation description
	 */
	String getDescription();
}
