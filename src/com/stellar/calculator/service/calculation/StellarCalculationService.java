package com.stellar.calculator.service.calculation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.stellar.calculator.calculation.angular.AngularSizeCalculator;
import com.stellar.calculator.calculation.api.CalculationResult;
import com.stellar.calculator.calculation.api.Calculator;
import com.stellar.calculator.calculation.luminosity.LuminosityCalculator;
import com.stellar.calculator.calculation.magnitude.ApparentMagnitudeCalculator;
import com.stellar.calculator.calculation.orbital.OrbitalPeriodCalculator;
import com.stellar.calculator.calculation.radiation.EarthEquivalentDistanceCalculator;
import com.stellar.calculator.calculation.radiation.IrradianceCalculator;
import com.stellar.calculator.calculation.thermal.HabitableZoneCalculator;
import com.stellar.calculator.domain.model.Star;
import com.stellar.calculator.service.api.CalculationService;

/**
 * Implementation of the calculation service. Manages a collection of
 * calculators and executes them in sequence.
 *
 * @author Ahmed Ghannam
 * @version 1.0
 */
public class StellarCalculationService implements CalculationService {
	private final List<Calculator<Star, ? extends CalculationResult>> calculators;

	/**
	 * Creates a new calculation service and registers default calculators.
	 */
	public StellarCalculationService() {
		this.calculators = new ArrayList<>();
		registerDefaultCalculators();
	}

	/**
	 * Registers the default set of stellar calculators.
	 */
	private void registerDefaultCalculators() {
		registerCalculator(new LuminosityCalculator());
		registerCalculator(new HabitableZoneCalculator());
		registerCalculator(new EarthEquivalentDistanceCalculator());
		registerCalculator(new OrbitalPeriodCalculator());
		registerCalculator(new ApparentMagnitudeCalculator());
		registerCalculator(new AngularSizeCalculator());
		registerCalculator(new IrradianceCalculator());
	}

	/**
	 * Executes all registered calculators on the given star.
	 *
	 * @param star the star to analyze
	 * @return the list of calculation results
	 * @throws NullPointerException     if <b>star</b> is {@code null}
	 * @throws IllegalArgumentException if <b>star</b> has invalid parameters
	 */
	@Override
	public List<CalculationResult> calculateAll(Star star) {
		Objects.requireNonNull(star, "star cannot be null");
		return calculators.stream()
				.map(c -> c.calculate(star))
				.collect(Collectors.toList());
	}

	/**
	 * Registers a new calculator with this service.
	 *
	 * @param calculator the calculator to register
	 * @throws NullPointerException     if <b>star</b> is {@code null}
	 * @throws IllegalArgumentException if <b>star</b> has invalid parameters
	 */
	@Override
	public void registerCalculator(Calculator<Star, ? extends CalculationResult> calculator) {
		Objects.requireNonNull("calculator cannot be null");
		calculators.add(calculator);
	}
}
