package com.stellar.calculator.service.orchestration;

import java.util.Objects;

import com.stellar.calculator.domain.model.Star;
import com.stellar.calculator.io.input.InputReader;
import com.stellar.calculator.io.output.ResultPrinter;
import com.stellar.calculator.service.api.CalculationService;

/**
 * Orchestrates the overall calculation process. Coordinates input, calculation,
 * and output operations.
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public class CalculationOrchestrator {
	private final InputReader inputReader;
	private final CalculationService calculationService;
	private final ResultPrinter resultPrinter;

	/**
	 * Constructs an orchestrator in preparation for processing.
	 * 
	 * @param inputReader        the input reader
	 * @param calculationService the calculation service
	 * @param resultPrinter      the result printer
	 * @throws NullPointerException if any argument is {@code null}
	 */
	public CalculationOrchestrator(InputReader inputReader, 
			CalculationService calculationService,
			ResultPrinter resultPrinter) {
		this.inputReader = Objects.requireNonNull(inputReader);
		this.calculationService = Objects.requireNonNull(calculationService);
		this.resultPrinter = Objects.requireNonNull(resultPrinter);
	}

	/**
	 * Executes the complete calculation workflow.
	 */
	public void process() {
		try {
			Star star = inputReader.readStarParameters();
			var results = calculationService.calculateAll(star);
			resultPrinter.printResults(star, results); // Now passing both star and results
		} catch (Exception e) {
			resultPrinter.printError("Calculation error: " + e.getMessage());
		}
	}
}
