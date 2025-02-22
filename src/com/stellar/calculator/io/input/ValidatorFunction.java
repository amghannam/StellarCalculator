package com.stellar.calculator.io.input;

/**
 * Functional interface for input validation.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
@FunctionalInterface
public interface ValidatorFunction {
	/**
	 * Validates a double value.
	 *
	 * @param value the value to validate
	 * @throws IllegalArgumentException if validation fails
	 */
	void validate(double value) throws IllegalArgumentException;
}
