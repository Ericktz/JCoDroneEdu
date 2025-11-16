/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.examples.demos;

import com.otabi.jcodroneedu.Drone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstrates temperature sensor calibration.
 * 
 * <p>Shows the difference between raw sensor temperature and calibrated
 * ambient temperature, teaching students about sensor accuracy and offset correction.</p>
 * 
 * <h3>Educational Concepts:</h3>
 * <ul>
 *   <li>Sensor die temperature vs ambient air temperature</li>
 *   <li>Calibration offset correction</li>
 *   <li>Experimental measurement techniques</li>
 *   <li>Error analysis</li>
 * </ul>
 */
public class TemperatureCalibrationDemo {
    private static final Logger log = LoggerFactory.getLogger(TemperatureCalibrationDemo.class);

    public static void main(String[] args) {
        log.info("Starting Temperature Calibration Demo");
        
        try (Drone drone = new Drone()) {
            // Connect to drone
            System.out.println("Connecting to drone...");
            drone.pair();
            System.out.println("Connected!\n");
            
            // Wait for sensor stabilization
            System.out.println("Waiting 2 seconds for sensor stabilization...");
            drone.sendRequest(com.otabi.jcodroneedu.protocol.DataType.Altitude);
            Thread.sleep(2000);
            
            System.out.println("╔════════════════════════════════════════════════════════════╗");
            System.out.println("║         Temperature Sensor Calibration Demo               ║");
            System.out.println("╚════════════════════════════════════════════════════════════╝");
            System.out.println();
            
            // Test 2: Explicit methods (always work the same)
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println("TEST 2: Explicit Methods (Always Consistent)");
            System.out.println("═══════════════════════════════════════════════════════════");
            double uncalibrated = drone.getUncalibratedTemperature();
            double calibrated = drone.getCalibratedTemperature();
            double offset = calibrated - uncalibrated;
            
            System.out.printf("  getUncalibratedTemperature(): %.2f°C (ALWAYS raw sensor)\n", uncalibrated);
            System.out.printf("  getCalibratedTemperature():   %.2f°C (ALWAYS calibrated)\n", calibrated);
            System.out.printf("  Offset Applied:               %.2f°C\n", offset);
            System.out.println();
            System.out.println("  ⚠️  Note: Sensor die is 10-15°C cooler than ambient air!");
            System.out.println();
            
            // Test 3: Switchable getDroneTemperature() - Python compatible by default
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println("TEST 3: Switchable getDroneTemperature() Method");
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println("  Default behavior (Python-compatible, uncalibrated):");
            double temp1 = drone.getDroneTemperature();
            System.out.printf("    getDroneTemperature(): %.2f°C (uncalibrated)\n", temp1);
            System.out.println();
            
            System.out.println("  After calling useCalibratedTemperature(true):");
            drone.useCalibratedTemperature(true);
            double temp2 = drone.getDroneTemperature();
            System.out.printf("    getDroneTemperature(): %.2f°C (NOW calibrated!)\n", temp2);
            System.out.println();
            
            System.out.println("  🎯 The SAME method now returns calibrated values!");
            System.out.println("  🎯 Students can flip ONE switch to get all calibrated temps!");
            System.out.println();
            
            // Reset back to uncalibrated for remaining tests
            drone.useCalibratedTemperature(false);
            
            // Test 4: Custom calibration with explicit methods
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println("TEST 4: Custom Calibration with Explicit Methods");
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println("  Simulating experimental calibration...");
            System.out.println("  (In real use, measure with reference thermometer)");
            System.out.println();
            
            // Simulate different custom offsets
            double customOffset10 = 10.0;
            double customOffset15 = 15.0;
            
            double custom10C = drone.getCalibratedTemperature(customOffset10);
            double custom15C = drone.getCalibratedTemperature(customOffset15);
            double custom15F = drone.getCalibratedTemperature(customOffset15, "F");
            
            System.out.printf("  With +%.1f°C offset: %.2f°C\n", customOffset10, custom10C);
            System.out.printf("  With +%.1f°C offset: %.2f°C\n", customOffset15, custom15C);
            System.out.printf("  With +%.1f°C offset: %.2f°F\n", customOffset15, custom15F);
            System.out.println();
            
            // Test 5: Unit conversion with both explicit methods
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println("TEST 5: Unit Conversion (All Methods Support C/F/K)");
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.printf("  Uncalibrated: %.2f°C = %.2f°F = %.2f K\n",
                drone.getUncalibratedTemperature("C"),
                drone.getUncalibratedTemperature("F"),
                drone.getUncalibratedTemperature("K"));
            System.out.printf("  Calibrated:   %.2f°C = %.2f°F = %.2f K\n",
                drone.getCalibratedTemperature("C"),
                drone.getCalibratedTemperature("F"),
                drone.getCalibratedTemperature("K"));
            System.out.println();
            
            // Comparison table
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println("SUMMARY: Temperature Reading Comparison");
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println();
            System.out.println("  ╔═══════════════════════════════════════════╦═══════════╗");
            System.out.println("  ║ Method                                    ║ Value     ║");
            System.out.println("  ╠═══════════════════════════════════════════╬═══════════╣");
            System.out.printf("  ║ getUncalibratedTemperature() [EXPLICIT]   ║ %6.2f°C ║\n", uncalibrated);
            System.out.printf("  ║ getCalibratedTemperature() [EXPLICIT]     ║ %6.2f°C ║\n", calibrated);
            System.out.printf("  ║ getDroneTemperature() [SWITCHABLE]        ║ %6.2f°C ║\n", drone.getDroneTemperature());
            System.out.printf("  ║ Custom Offset (+10°C)                     ║ %6.2f°C ║\n", custom10C);
            System.out.printf("  ║ Custom Offset (+15°C)                     ║ %6.2f°C ║\n", custom15C);
            System.out.println("  ╚═══════════════════════════════════════════╩═══════════╝");
            System.out.println();
            
            // Educational summary
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println("EDUCATIONAL INSIGHTS");
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println();
            System.out.println("  📚 Why does the sensor read low?");
            System.out.println("     • The sensor measures its own chip temperature (die temp)");
            System.out.println("     • The chip dissipates heat and is cooler than surrounding air");
            System.out.println("     • This is a physical property, not a firmware bug!");
            System.out.println();
            System.out.println("  🔧 THREE ways to get temperature:");
            System.out.println();
            System.out.println("     1️⃣  EXPLICIT UNCALIBRATED (always raw sensor):");
            System.out.println("        double temp = drone.getUncalibratedTemperature();");
            System.out.println("        • Use for: Raw data, sensor calibration experiments");
            System.out.println();
            System.out.println("     2️⃣  EXPLICIT CALIBRATED (always ambient estimate):");
            System.out.println("        double temp = drone.getCalibratedTemperature();");
            System.out.println("        • Use for: Accurate ambient temperature");
            System.out.println("        • Custom offset: getCalibratedTemperature(13.5)");
            System.out.println();
            System.out.println("     3️⃣  SWITCHABLE (Python-compatible, students can control):");
            System.out.println("        drone.useCalibratedTemperature(true);  // Flip the switch!");
            System.out.println("        double temp = drone.getDroneTemperature();");
            System.out.println("        • Use for: Easy switching without code changes");
            System.out.println("        • Students learn about settings/configuration");
            System.out.println();
            System.out.println("  🎯 API Design Pattern (Matches Elevation API):");
            System.out.println("     • getUncalibratedTemperature() ← ALWAYS raw");
            System.out.println("     • getCalibratedTemperature()   ← ALWAYS calibrated");
            System.out.println("     • getDroneTemperature()        ← Switchable (default: raw)");
            System.out.println("     • useCalibratedTemperature(boolean) ← The switch!");
            System.out.println();
            System.out.println("  ✅ This gives teachers AND students the best of both worlds!");
            System.out.println();
            
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println("Demo complete!");
            System.out.println("═══════════════════════════════════════════════════════════");
            
        } catch (Exception e) {
            log.error("Error in temperature demo", e);
            e.printStackTrace();
        }
    }
}
