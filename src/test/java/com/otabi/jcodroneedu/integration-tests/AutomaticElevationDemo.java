/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.integrationtests;

import com.otabi.jcodroneedu.Drone;
import com.otabi.jcodroneedu.protocol.DataType;
import com.otabi.jcodroneedu.util.WeatherService;

/**
 * Demonstrates automatic location and pressure detection.
 * 
 * <p>This example shows how the system automatically determines the best
 * sea-level pressure using a multi-tier fallback strategy:</p>
 * <ol>
 *   <li>OS location services (if JNI provider available)</li>
 *   <li>IP-based geolocation</li>
 *   <li>Standard atmosphere fallback</li>
 * </ol>
 * 
 * <p>All failures are handled gracefully - you always get a working altitude!</p>
 */
public class AutomaticElevationDemo {
    
    public static void main(String[] args) {
        Drone drone = new Drone();
        drone.pair();
        
        try {
            System.out.println("╔════════════════════════════════════════════════════════════╗");
            System.out.println("║        Automatic Elevation Detection Demo                  ║");
            System.out.println("╚════════════════════════════════════════════════════════════╝");
            System.out.println();
            System.out.println("This demo shows automatic location detection and pressure");
            System.out.println("calibration with graceful fallback.");
            System.out.println();
            
            // Stabilize sensors
            System.out.println("Reading sensors...");
            for (int i = 0; i < 10; i++) {
                drone.sendRequest(DataType.Altitude);
                Thread.sleep(100);
            }
            
            // Try to get location from IP
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println("STEP 1: Automatic Location Detection");
            System.out.println("═══════════════════════════════════════════════════════════");
            
            double[] location = WeatherService.getLocationFromIP();
            if (location != null) {
                System.out.println("✅ Location detected from IP:");
                System.out.printf("   Latitude:  %.4f°\n", location[0]);
                System.out.printf("   Longitude: %.4f°\n", location[1]);
            } else {
                System.out.println("⚠️  IP geolocation unavailable (offline or blocked)");
                System.out.println("   Will use standard atmosphere pressure");
            }
            System.out.println();
            
            // Get automatic pressure
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println("STEP 2: Automatic Pressure Detection");
            System.out.println("═══════════════════════════════════════════════════════════");
            
            double autoPressure = WeatherService.getAutomaticSeaLevelPressure();
            System.out.printf("Detected sea-level pressure: %.2f hPa (%.0f Pa)\n", 
                autoPressure / 100.0, autoPressure);
            
            if (autoPressure == 101325.0) {
                System.out.println("Source: Standard atmosphere (fallback)");
            } else {
                System.out.println("Source: Weather API (location-based) ✓");
            }
            System.out.println();
            
            // Compare elevation methods
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println("STEP 3: Compare Elevation Methods");
            System.out.println("═══════════════════════════════════════════════════════════");
            
            double uncorrected = drone.getUncorrectedElevation();
            double automatic = drone.getCorrectedElevation(); // Uses automatic detection!
            double standard = drone.getCorrectedElevation(101325.0);
            
            System.out.printf("1. Uncorrected (firmware):    %7.2f m  ⚠️  (has offset)\n", uncorrected);
            System.out.printf("2. Automatic (smart):         %7.2f m  ✨ (auto-calibrated!)\n", automatic);
            System.out.printf("3. Standard (fallback):       %7.2f m  ✓  (1013.25 hPa)\n", standard);
            
            double improvement = Math.abs(automatic - standard);
            if (improvement > 0.1) {
                System.out.println();
                System.out.printf("📊 Automatic calibration adjusted by: %.2f m\n", improvement);
            }
            System.out.println();
            
            // Show fallback strategy
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println("FALLBACK STRATEGY:");
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println("The system tries multiple sources in order:");
            System.out.println();
            System.out.println("1️⃣  OS Location (JNI)");
            System.out.println("   └─> Requires native library");
            System.out.println("   └─> Most accurate (GPS/WiFi)");
            System.out.println("   └─> Status: Not configured (waiting for JNI library)");
            System.out.println();
            System.out.println("2️⃣  IP Geolocation");
            System.out.println("   └─> Uses ipinfo.io API");
            System.out.println("   └─> City-level accuracy (~50-100km)");
            System.out.println("   └─> Status: " + (location != null ? "Available ✓" : "Unavailable (offline)"));
            System.out.println();
            System.out.println("3️⃣  Standard Atmosphere");
            System.out.println("   └─> Always available (fallback)");
            System.out.println("   └─> 1013.25 hPa (101325 Pa)");
            System.out.println("   └─> Status: Always available ✓");
            System.out.println();
            
            // Summary
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println("USAGE:");
            System.out.println("═══════════════════════════════════════════════════════════");
            System.out.println("Just call getCorrectedElevation() - it's now smart!");
            System.out.println();
            System.out.println("  double elevation = drone.getCorrectedElevation();");
            System.out.println();
            System.out.println("The system automatically:");
            System.out.println("  ✓ Detects your location");
            System.out.println("  ✓ Fetches current weather");
            System.out.println("  ✓ Falls back gracefully if offline");
            System.out.println("  ✓ Always returns a valid result");
            System.out.println();
            System.out.println("For manual control, use:");
            System.out.println("  drone.getCorrectedElevation(pressurePa)");
            System.out.println("  drone.getCorrectedElevation(lat, lon)");
            System.out.println("═══════════════════════════════════════════════════════════");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            drone.close();
        }
    }
}
