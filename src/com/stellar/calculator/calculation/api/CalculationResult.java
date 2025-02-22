package com.stellar.calculator.calculation.api;

/**
 * Base interface for all calculation results. Defines the contract for
 * formatting and accessing calculation results.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public interface CalculationResult {
	/**
	 * Formats the result for display.
	 * 
	 * @return formatted the string representation
	 */
	String format();

	/**
	 * Returns the raw numerical value of the result.
	 * 
	 * @return numerical the result value
	 */
	double getValue();
}
