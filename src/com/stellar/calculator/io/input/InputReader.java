package com.stellar.calculator.io.input;

import java.util.Scanner;

import com.stellar.calculator.domain.model.Star;
import com.stellar.calculator.validation.InputValidator;

/**
 * Handles user input for stellar parameters with clear prompts.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public class InputReader {
	private final Scanner scanner;
	private final InputValidator validator;

	public InputReader() {
		this.scanner = new Scanner(System.in);
		this.validator = new InputValidator();
	}

	public Star readStarParameters() {
		displayWelcomeMessage();

		double mass = readDouble("- Enter the star's mass (in solar masses): ", validator::validateMass);
		double radius = readDouble("- Enter the star's radius (in solar radii): ", validator::validateRadius);
		double temp = readDouble("- Enter the star's surface temperature (in Kelvin): ",
				validator::validateTemperature);

		return new Star(mass, radius, temp);
	}

	private void displayWelcomeMessage() {
		System.out.println("\nStellar System Calculator");
		System.out.println("========================");
		System.out.println("Provide stellar parameters for any star as indicated below in order to analyze it.\n");
	}

	private double readDouble(String prompt, ValidatorFunction validator) {
		while (true) {
			try {
				System.out.print(prompt);
				double value = Double.parseDouble(scanner.nextLine().trim());
				validator.validate(value);
				return value;
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid number.");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
