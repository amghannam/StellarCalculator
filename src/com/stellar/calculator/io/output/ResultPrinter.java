package com.stellar.calculator.io.output;

import java.util.List;

import com.stellar.calculator.calculation.api.CalculationResult;
import com.stellar.calculator.domain.model.Star;

/**
 * Handles the formatted display of all calculation results. Provides structured
 * output with headers and separators.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public class ResultPrinter {

	/**
	 * Prints the complete analysis for the given stellar parameters.
	 * 
	 * @param star    the analyzed star
	 * @param results list of calculation results
	 */
	public void printResults(Star star, List<CalculationResult> results) {
		printHeader(star);
		System.out.println("\nAnalysis Results");
		System.out.println("================");

		results.forEach(result -> {
			System.out.println("\n" + result.format());
			System.out.println("-".repeat(50)); // Separator between results
		});

		printSummary();
	}

	private void printHeader(Star star) {
		System.out.println("""

				Stellar Analysis Complete
				========================
				Input Parameters:
				Mass: %.2f solar masses
				Radius: %.2f solar radii
				Temperature: %.0fK
				""".formatted(star.getMass(), star.getRadius(), star.getTemperature()));
	}

	private void printSummary() {
		System.out.println("""

				Note: All Earth-equivalent calculations are based on receiving
				the same amount of stellar radiation as Earth receives from the Sun.
				Actual habitability depends on many additional factors including
				atmospheric composition, magnetic fields, and planetary mass.
				""");
	}

	/**
	 * Prints an error message with formatting.
	 * 
	 * @param message the error message to display
	 */
	public void printError(String message) {
		System.err.println("""

				ERROR
				=====
				%s

				Please check your input values and try again.
				""".formatted(message));
	}
}
