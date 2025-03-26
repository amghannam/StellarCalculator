package com.stellar.calculator.core;

import com.stellar.calculator.io.input.InputReader;
import com.stellar.calculator.io.output.ResultPrinter;
import com.stellar.calculator.service.calculation.StellarCalculationService;
import com.stellar.calculator.service.orchestration.CalculationOrchestrator;

/**
 * Main entry point for the Stellar Calculator application. Responsible for
 * initializing core services and starting the calculation process.
 *
 * @author Ahmed Ghannam
 * @version 1.0
 */
public class StellarCalculator {
	
	/**
	 * Application entry point. Initializes services and starts the calculation
	 * process.
	 *
	 * @param args command line arguments (unused)
	 */
	public static void main(String[] args) {
		var orchestrator = new CalculationOrchestrator(new InputReader(), 
				new StellarCalculationService(),
				new ResultPrinter());
		orchestrator.process();
	}
}
