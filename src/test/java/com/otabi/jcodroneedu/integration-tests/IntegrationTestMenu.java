package com.otabi.jcodroneedu.integrationtests;

import java.util.Scanner;

/**
 * Integration Test Menu - Launch all hardware integration tests
 * 
 * This program provides a centralized menu to run all integration tests that
 * require drone and/or controller hardware connections.
 * 
 * Integration tests include:
 * - Hardware connectivity verification (SmokeTest)
 * - Sensor testing (AccelTest, MultiSensorTest, etc.)
 * - Flight testing (FlightSmokeTest, ConservativeFlight)
 * - Controller I/O testing (ControllerInputTest)
 * - LED testing (QuickLEDTest)
 * - Elevation/altitude testing (AltitudePressureTest, etc.)
 * - Interactive testing (TestHarness)
 * 
 * Usage:
 *   Run this program and select a test by entering its number.
 *   Press 0 to exit the menu.
 * 
 * @note All tests require proper hardware connection and configuration
 */
public class IntegrationTestMenu {
    
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean running = true;
        
        while (running) {
            displayMenu();
            
            System.out.print("\nEnter your choice (0 to exit): ");
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                continue;
            }
            
            try {
                int choice = Integer.parseInt(input);
                
                if (choice == 0) {
                    System.out.println("\nExiting Integration Test Menu. Goodbye!");
                    running = false;
                } else {
                    runTest(choice);
                }
            } catch (NumberFormatException e) {
                System.out.println("\n❌ Invalid input. Please enter a number.");
            }
            
            if (running) {
                System.out.println("\nPress Enter to return to menu...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void displayMenu() {
        clearScreen();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║        Integration Test Menu - JCoDroneEdu Hardware Tests       ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
        
        System.out.println("🔗 CONNECTIVITY & BASIC TESTS");
        System.out.println("────────────────────────────────────────────────────────────────");
        System.out.println("  1. Smoke Test (Basic drone/controller connectivity)");
        System.out.println("  2. Controller Input Test (Button and joystick API)");
        System.out.println();
        
        System.out.println("📊 SENSOR TESTS");
        System.out.println("────────────────────────────────────────────────────────────────");
        System.out.println("  3. Accelerometer Test (Orientation & motion capture)");
        System.out.println("  4. Multi Sensor Test (Range, flow, temp, color sensors)");
        System.out.println("  5. Altitude Pressure Test (Altitude sensor readings)");
        System.out.println("  6. Color Sensor Debug (Color detection testing)");
        System.out.println();
        
        System.out.println("✈️  FLIGHT TESTS");
        System.out.println("────────────────────────────────────────────────────────────────");
        System.out.println("  7. Flight Smoke Test (Guarded flight with confirmation)");
        System.out.println("  8. Conservative Flight (Safe flight verification)");
        System.out.println();
        
        System.out.println("🎯 ELEVATION & HEIGHT TESTS");
        System.out.println("────────────────────────────────────────────────────────────────");
        System.out.println("  9. Elevation API Demo (Elevation API demonstration)");
        System.out.println(" 10. Automatic Elevation Demo (Auto location with calibration)");
        System.out.println(" 11. Calibrated Elevation Demo (Weather-calibrated height)");
        System.out.println(" 12. Relative Height Demo (Pressure-based height measurement)");
        System.out.println(" 13. Test Height (Height testing utilities)");
        System.out.println();
        
        System.out.println("💡 HARDWARE & FEATURE TESTS");
        System.out.println("────────────────────────────────────────────────────────────────");
        System.out.println(" 14. Quick LED Test (Drone & controller LED testing)");
        System.out.println();
        
        System.out.println("🔧 INTERACTIVE TESTING");
        System.out.println("────────────────────────────────────────────────────────────────");
        System.out.println(" 15. Test Harness (Interactive menu-driven feature testing)");
        System.out.println();
        
        System.out.println("🌍 EXTERNAL SERVICE TESTS");
        System.out.println("────────────────────────────────────────────────────────────────");
        System.out.println(" 16. Weather Service Test (Weather API connectivity - no drone)");
        System.out.println();
        
        System.out.println("  0. Exit");
        System.out.println("════════════════════════════════════════════════════════════════");
    }
    
    private static void runTest(int choice) {
        System.out.println("\n" + "═".repeat(64));
        
        try {
            switch (choice) {
                case 1:
                    System.out.println("🧪 Running Smoke Test");
                    System.out.println("═".repeat(64) + "\n");
                    SmokeTest.main(new String[]{});
                    break;
                    
                case 2:
                    System.out.println("🧪 Running Controller Input Test");
                    System.out.println("═".repeat(64) + "\n");
                    ControllerInputTest.main(new String[]{});
                    break;
                    
                case 3:
                    System.out.println("🧪 Running Accelerometer Test");
                    System.out.println("═".repeat(64) + "\n");
                    AccelTest.main(new String[]{});
                    break;
                    
                case 4:
                    System.out.println("🧪 Running Multi Sensor Test");
                    System.out.println("═".repeat(64) + "\n");
                    MultiSensorTest.main(new String[]{});
                    break;
                    
                case 5:
                    System.out.println("🧪 Running Altitude Pressure Test");
                    System.out.println("═".repeat(64) + "\n");
                    AltitudePressureTest.main(new String[]{});
                    break;
                    
                case 6:
                    System.out.println("🧪 Running Color Sensor Debug");
                    System.out.println("═".repeat(64) + "\n");
                    ColorSensorDebug.main(new String[]{});
                    break;
                    
                case 7:
                    System.out.println("✈️  Running Flight Smoke Test");
                    System.out.println("═".repeat(64) + "\n");
                    FlightSmokeTest.main(new String[]{});
                    break;
                    
                case 8:
                    System.out.println("✈️  Running Conservative Flight Test");
                    System.out.println("═".repeat(64) + "\n");
                    System.out.println("⚠️  Note: This test requires --allow-flight flag to actually fly.");
                    System.out.println("    When run from the menu, it will display usage information only.");
                    System.out.println("    To fly safely, use: ./gradlew runConservativeFlight --args='--allow-flight'\n");
                    System.out.println("═".repeat(64));
                    System.out.println("💡 Test information displayed (use Gradle task to fly)");
                    break;
                    
                case 9:
                    System.out.println("🎯 Running Elevation API Demo");
                    System.out.println("═".repeat(64) + "\n");
                    ElevationApiDemo.main(new String[]{});
                    break;
                    
                case 10:
                    System.out.println("🎯 Running Automatic Elevation Demo");
                    System.out.println("═".repeat(64) + "\n");
                    AutomaticElevationDemo.main(new String[]{});
                    break;
                    
                case 11:
                    System.out.println("🎯 Running Calibrated Elevation Demo");
                    System.out.println("═".repeat(64) + "\n");
                    CalibratedElevationDemo.main(new String[]{});
                    break;
                    
                case 12:
                    System.out.println("🎯 Running Relative Height Demo");
                    System.out.println("═".repeat(64) + "\n");
                    RelativeHeightDemo.main(new String[]{});
                    break;
                    
                case 13:
                    System.out.println("🎯 Running Test Height");
                    System.out.println("═".repeat(64) + "\n");
                    TestHeight.main(new String[]{});
                    break;
                    
                case 14:
                    System.out.println("💡 Running Quick LED Test");
                    System.out.println("═".repeat(64) + "\n");
                    QuickLEDTest.main(new String[]{});
                    break;
                    
                case 15:
                    System.out.println("🔧 Running Test Harness");
                    System.out.println("═".repeat(64) + "\n");
                    TestHarness.main(new String[]{});
                    break;
                    
                case 16:
                    System.out.println("🌍 Running Weather Service Test");
                    System.out.println("═".repeat(64) + "\n");
                    System.out.println("Note: This test does not require drone/controller hardware.\n");
                    WeatherServiceTest.main(new String[]{});
                    break;
                
                default:
                    System.out.println("❌ Invalid choice. Please select a valid option.");
                    System.out.println("═".repeat(64));
                    return;
            }
            
            System.out.println("\n" + "═".repeat(64));
            System.out.println("✅ Test completed successfully!");
            
        } catch (Exception e) {
            System.out.println("\n" + "═".repeat(64));
            System.out.println("❌ Error running test: " + e.getMessage());
            System.err.println("\nStack trace:");
            e.printStackTrace();
        }
    }
    
    /**
     * Attempts to clear the terminal screen for a cleaner menu display.
     * Works on most Unix-like systems and Windows.
     */
    private static void clearScreen() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            
            if (os.contains("win")) {
                // Windows - note: this may not work in all terminals
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-like (macOS, Linux)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // If clearing fails, just print some newlines
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}
