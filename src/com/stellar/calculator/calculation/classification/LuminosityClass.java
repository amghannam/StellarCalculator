package com.stellar.calculator.calculation.classification;

/**
 * Morgan–Keenan luminosity classes.
 *
 * <ul>
 * <li>I: supergiants</li>
 * <li>II: bright giants</li>
 * <li>III: giants</li>
 * <li>IV: subgiants</li>
 * <li>V: main sequence (dwarfs)</li>
 * <li>VI: subdwarfs</li>
 * <li>VII: white dwarfs</li>
 * </ul>
 * 
 * @author Ahmed Ghannam
 * @version 1.0
 */
public enum LuminosityClass {
	I("I"),
	II("II"),
	III("III"),
	IV("IV"),
	V("V"),
	VI("VI"),
	VII("VII"),
	UNKNOWN("?");

	private final String mkLabel;

	LuminosityClass(String mkLabel) {
		this.mkLabel = mkLabel;
	}

	public String mkLabel() {
		return mkLabel;
	}
}
