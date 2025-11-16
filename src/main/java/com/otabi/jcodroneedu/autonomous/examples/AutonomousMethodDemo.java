/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu.autonomous.examples;

import com.otabi.jcodroneedu.Drone;
import com.otabi.jcodroneedu.DroneNotFoundException;
import com.otabi.jcodroneedu.autonomous.AutonomousMethod;
import com.otabi.jcodroneedu.autonomous.AutonomousMethodRegistry;
import com.otabi.jcodroneedu.autonomous.ParameterBuilder;

/**
 * Demonstration program showing how to use the Autonomous Method framework.
 * 
 * <p>This class provides comprehensive examples of how students and educators
 * can leverage the autonomous method system for educational drone programming.
 * It demonstrates best practices and common usage patterns.</p>
 * 
 * <h3>🎯 Educational Objectives:</h3>
 * <ul>
 *   <li>Learn to use the autonomous method framework</li>
 *   <li>Understand parameter validation and error handling</li>
 *   <li>Practice registry pattern usage</li>
 *   <li>Experience fluent API design</li>
 * </ul>
 * 
 * <h3>📚 Course Integration:</h3>
 * <ul>
 *   <li><strong>L0201:</strong> Introduction to autonomous methods</li>
 *   <li><strong>L0202:</strong> Parameter building and validation</li>
 *   <li><strong>L0203:</strong> Creating custom autonomous methods</li>
 *   <li><strong>L0204:</strong> Advanced algorithms and 3D patterns</li>
 * </ul>
 * 
 * @author Stephen Cerruti
 * @version 1.0.0
 * @educational
 */
public class AutonomousMethodDemo {
    
    /**
     * main method.
     * @since 1.0.0
     */
    public static void main(String[] args) {
        System.out.println("🤖 === Autonomous Method Framework Demo ===");
        
        // Step 1: Demonstrate registry usage
        demonstrateRegistry();
        
        // Step 2: Show parameter building
        demonstrateParameterBuilder();
        
        // Step 3: Execute autonomous methods with a real drone
        try {
            demonstrateAutonomousExecution();
        } catch (DroneNotFoundException e) {
            System.out.println("ℹ️ No drone connected - skipping flight demonstration");
            System.out.println("   Connect a CoDrone EDU controller to see autonomous flight");
        }
        
        // Step 4: Generate documentation
        demonstrateDocumentationGeneration();
        
        System.out.println("✅ Autonomous Method Framework Demo Complete!");
    }
    
    /**
     * Demonstrate how to use the registry to discover and access methods.
     */
    private static void demonstrateRegistry() {
        System.out.println("\n📋 === Registry Usage Demo ===");
        
        // Get the singleton registry instance
        AutonomousMethodRegistry registry = AutonomousMethodRegistry.getInstance();
        
        // List all available methods
        System.out.println("Available autonomous methods:");
        for (String methodName : registry.listMethods()) {
            AutonomousMethod method = registry.getMethod(methodName);
            System.out.printf("  • %s: %s%n", methodName, method.getDescription());
        }
        
        // Show method count
        System.out.printf("Total methods: %d%n", registry.getMethodCount());
        
        // Demonstrate method retrieval
        AutonomousMethod circleMethod = registry.getMethod("circle");
        System.out.printf("Retrieved method: %s%n", circleMethod.getMethodName());
        
        // Show parameter information
        System.out.println("\nCircle method parameters:");
        circleMethod.getParameterDefinitions().forEach((name, def) -> {
            System.out.printf("  • %s (%d-%d): %s%n", 
                name, def.minValue, def.maxValue, def.description);
        });
    }
    
    /**
     * Demonstrate the fluent parameter builder API.
     */
    private static void demonstrateParameterBuilder() {
        System.out.println("\n🏗️ === Parameter Builder Demo ===");
        
        // Basic parameter building
        var basicParams = ParameterBuilder.create()
            .set("radius", 80)
            .set("speed", 50)
            .set("direction", 1)
            .build();
        
        System.out.printf("Basic parameters: %s%n", basicParams);
        
        // Using convenience methods
        var convenientParams = ParameterBuilder.create()
            .radius(100)
            .speed(60)
            .direction(-1)
            .build();
        
        System.out.printf("Convenient parameters: %s%n", convenientParams);
        
        // Complex parameter set for spiral
        var spiralParams = ParameterBuilder.create()
            .set("start_radius", 30)
            .set("end_radius", 120)
            .set("turns", 3)
            .speed(40)
            .set("altitude_change", 50)
            .build();
        
        System.out.printf("Spiral parameters: %s%n", spiralParams);
        
        // Demonstrate builder reuse
        ParameterBuilder builder = ParameterBuilder.create().speed(70);
        
        var circleParams = builder.copy().radius(90).direction(1).build();
        var squareParams = builder.copy().size(100).direction(-1).build();
        
        System.out.printf("Reused builder - Circle: %s%n", circleParams);
        System.out.printf("Reused builder - Square: %s%n", squareParams);
    }
    
    /**
     * Demonstrate autonomous method execution with a real drone.
     * This requires a connected CoDrone EDU controller.
     */
    private static void demonstrateAutonomousExecution() throws DroneNotFoundException {
        System.out.println("\n🚁 === Autonomous Execution Demo ===");
        
        try (Drone drone = new Drone()) {
            // Connect to drone
            if (!drone.connect()) {
                throw new DroneNotFoundException("Could not connect to drone");
            }
            
            System.out.println("✅ Connected to drone");
            
            // Take off
            drone.takeoff();
            System.out.println("🛫 Drone in the air");
            
            // Get registry and methods
            AutonomousMethodRegistry registry = AutonomousMethodRegistry.getInstance();
            
            // Execute circle pattern
            System.out.println("\n🔵 Executing circle autonomous method...");
            AutonomousMethod circle = registry.getMethod("circle");
            var circleParams = ParameterBuilder.create()
                .radius(80)
                .speed(50)
                .direction(1)
                .build();
            
            circle.execute(drone, circleParams);
            
            // Brief pause between patterns
            drone.hover(2.0);
            
            // Execute spiral pattern
            System.out.println("\n🌀 Executing spiral autonomous method...");
            AutonomousMethod spiral = registry.getMethod("spiral");
            var spiralParams = ParameterBuilder.create()
                .set("start_radius", 40)
                .set("end_radius", 100)
                .set("turns", 2)
                .speed(45)
                .set("altitude_change", 30)
                .build();
            
            spiral.execute(drone, spiralParams);
            
            // Safe landing
            drone.land();
            System.out.println("🛬 Drone landed safely");
            
        } catch (Exception e) {
            System.err.printf("❌ Error during autonomous execution: %s%n", e.getMessage());
            throw e;
        }
    }
    
    /**
     * Demonstrate documentation generation capabilities.
     */
    private static void demonstrateDocumentationGeneration() {
        System.out.println("\n📖 === Documentation Generation Demo ===");
        
        AutonomousMethodRegistry registry = AutonomousMethodRegistry.getInstance();
        
        // Generate usage guide
        System.out.println("Generated Usage Guide (first 500 chars):");
        String usageGuide = registry.generateUsageGuide();
        System.out.println(usageGuide.substring(0, Math.min(500, usageGuide.length())) + "...");
        
        // Generate individual method documentation
        System.out.println("\nIndividual Method Documentation:");
        AutonomousMethod circle = registry.getMethod("circle");
        String circleDoc = circle.generateDocumentation();
        
        // Show just the first few lines
        String[] lines = circleDoc.split("\\n");
        for (int i = 0; i < Math.min(8, lines.length); i++) {
            System.out.println(lines[i]);
        }
        System.out.println("... (truncated for demo)");
        
        // Show documentation statistics
        String fullDocs = registry.generateDocumentation();
        int lineCount = fullDocs.split("\\n").length;
        int wordCount = fullDocs.split("\\s+").length;
        
        System.out.printf("📊 Documentation Statistics:%n");
        System.out.printf("  • Methods documented: %d%n", registry.getMethodCount());
        System.out.printf("  • Total lines: %d%n", lineCount);
        System.out.printf("  • Total words: %d%n", wordCount);
    }
    
    /**
     * Demonstrate error handling and validation.
     */
    @SuppressWarnings("unused")
    private static void demonstrateErrorHandling() {
        System.out.println("\n⚠️ === Error Handling Demo ===");
        
        AutonomousMethodRegistry registry = AutonomousMethodRegistry.getInstance();
        AutonomousMethod circle = registry.getMethod("circle");
        
        // Invalid parameter range
        try {
            var invalidParams = ParameterBuilder.create()
                .radius(300)  // Too large
                .speed(50)
                .direction(1)
                .build();
            
            // This would fail during execution:
            // circle.execute(drone, invalidParams);
            System.out.println("❌ Would fail: radius=300 is outside valid range (20-200)");
            
        } catch (Exception e) {
            System.out.printf("Caught expected error: %s%n", e.getMessage());
        }
        
        // Missing parameter
        try {
            var incompleteParams = ParameterBuilder.create()
                .radius(80)
                .speed(50)
                // Missing direction parameter
                .build();
            
            System.out.println("❌ Would fail: missing 'direction' parameter");
            
        } catch (Exception e) {
            System.out.printf("Caught expected error: %s%n", e.getMessage());
        }
        
        // Unknown method
        try {
            registry.getMethod("nonexistent");
        } catch (IllegalArgumentException e) {
            System.out.printf("✅ Caught expected error: %s%n", e.getMessage());
        }
    }
}
