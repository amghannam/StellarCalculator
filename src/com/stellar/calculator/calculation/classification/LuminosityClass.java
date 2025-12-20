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
	/**
	 * Denotes supergiants.
	 */
	I("I"),
	
	/**
	 * Denotes bright giants.
	 */
	II("II"),
	
	/**
	 * Denotes giants.
	 */
	III("III"),
	
	/**
	 * Denotes subgiants.
	 */
	IV("IV"),
	
	/**
	 * Denotes main sequence stars (dwarfs).
	 */
	V("V"),
	
	/**
	 * Denotes subdwarfs.
	 */
	VI("VI"),
	
	/**
	 * Denotes white dwarfs.
	 */
	VII("VII"),
	
	/**
	 * Indicates that a luminosity class is inconclusive or otherwise unknown.
	 */
	UNKNOWN("?");

	private final String mkLabel;

	LuminosityClass(String mkLabel) {
		this.mkLabel = mkLabel;
	}

	public String mkLabel() {
		return mkLabel;
	}
}
