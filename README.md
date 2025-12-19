# Stellar System Habitability Calculator

A Java application for analyzing stellar properties and calculating potential habitability parameters. 
This tool is designed for astronomers, students, and enthusiasts who want a physically grounded, explainable analysis of stars and the environments they create for surrounding planets.

The calculator emphasizes clarity, correctness, and transparency, producing results that are both numerically sound and easy to interpret.

---

## Features

### Stellar Properties Analysis
* **Total Stellar Luminosity Calculation** (derived via Stefan–Boltzmann law)
* **Radiation Output at 1 AU**
* **Apparent Magnitude Comparison with the Sun**
* **Angular Size Calculations**
* **Earth-Equivalent Apparent Brightness**

### Spectral & Luminosity Classification
* **Spectral Class Estimation (O–Y)**
    * Based on effective surface temperature
    * Includes subtype resolution (e.g. B0, G2)
* **Luminosity Class Detection (MK System)**
    * Supports classes I–VII (supergiants → white dwarfs)
    * Uses multiple physical signals:
        * Radius
        * Luminosity
        * Mass–luminosity main-sequence consistency
* **Produces:**
    * Estimated luminosity class
    * Confidence score
    * Human-readable rationale explaining the decision

### Habitability Assessment
* **Theoretical Habitable Zone Boundaries**
* **Earth-Equivalent Distance**
* **Orbital Period at Earth-Equivalent Distance**
* **Stellar Radiation Levels Relative to Earth**

> [!IMPORTANT]
> **Note:** Habitability calculations are radiation-based only. Actual habitability depends on many additional factors such as atmosphere, magnetic fields, planetary mass, and orbital stability.

### Comparative Analysis
* **Solar System Baseline Comparison:** All results are expressed relative to familiar Solar System values.
* **Radiation Intensity Relative to Earth**
* **Apparent Brightness Comparisons**
* **Angular Size Compared to the Sun**

---

## Design Philosophy

* **Physically grounded:** Uses established astrophysical relations rather than arbitrary scaling.
* **Explainable heuristics:** Classification results include rationales and confidence levels.
* **Extensible architecture:** New calculators (e.g. evolutionary stage, HR-diagram placement) can be added without refactoring existing code.
* **Readable output:** Results are formatted as a clear, structured analysis report rather than raw numbers.

---

## Disclaimer

This application is intended for educational and exploratory purposes. While the calculations are physically motivated, they are simplified models and should not be used as substitutes for professional astrophysical simulations or observational data.
