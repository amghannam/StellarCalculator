package com.stellar.calculator.validation;

/**
 * Represents the result of a validation operation. Used to collect validation
 * errors and warnings.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public record ValidationResult(boolean isValid, String message) {
	
	public static ValidationResult valid() {
		return new ValidationResult(true, "");
	}

	public static ValidationResult invalid(String message) {
		return new ValidationResult(false, message);
	}

	/**
	 * Checks if this result represents a valid state.
	 *
	 * @return {@code true} if the validation has passed
	 */
	public boolean isValid() {
		return isValid;
	}

	/**
	 * Returns the validation message, if any.
	 *
	 * @return the validation message or empty string if valid
	 */
	public String getMessage() {
		return message;
	}
}
