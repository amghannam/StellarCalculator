package com.stellar.calculator.validation;

import java.util.function.Predicate;

/**
 * Generic validator for checking conditions with custom error messages.
 * Provides a fluent interface for combining validation rules.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public class Validator<T> {
	private final T value;

	private Validator(T value) {
		this.value = value;
	}

	/**
	 * Creates a new validator for the given value.
	 *
	 * @param <T>   the type of the value to validate
	 * @param value the value to validate
	 * @return new the validator instance
	 */
	public static <T> Validator<T> of(T value) {
		return new Validator<>(value);
	}

	/**
	 * Adds a validation rule with custom error message.
	 *
	 * @param predicate    the validation condition
	 * @param errorMessage message if the validation fails
	 * @return this the validator for chaining
	 * @throws IllegalArgumentException if the validation fails
	 */
	public Validator<T> validate(Predicate<T> predicate, String errorMessage) {
		if (!predicate.test(value)) {
			throw new IllegalArgumentException(errorMessage);
		}
		return this;
	}

	/**
	 * Returns the validated value if all checks pass.
	 *
	 * @return the validated value
	 */
	public T get() {
		return value;
	}
}
