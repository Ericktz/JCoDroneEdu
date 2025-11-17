/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */

package com.otabi.jcodroneedu;

import com.otabi.jcodroneedu.protocol.DataType;
import com.otabi.jcodroneedu.protocol.dronestatus.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for individual sensor axis accessor methods.
 * 
 * Tests Python parity for new axis accessor methods:
 * - get_angular_speed_x/y/z()
 * - get_x_gyro/y_gyro/z_gyro()
 * 
 * @since 1.4.0
 */
@DisplayName("Sensor Axis Accessor Tests")
public class SensorAxisAccessorTest {

    private TelemetryService telemetryService;
    private Drone mockDrone;
    private DroneStatus mockStatus;
    private Motion mockMotion;

    @BeforeEach
    void setUp() {
        // Create mock objects
        mockDrone = mock(Drone.class);
        mockStatus = mock(DroneStatus.class);
        mockMotion = mock(Motion.class);
        
        // Configure mocks
        when(mockDrone.getDroneStatus()).thenReturn(mockStatus);
        
        // Create real TelemetryService with mocked drone
        telemetryService = new TelemetryService(mockDrone);
    }

    @Nested
    @DisplayName("Angular Speed Accessor Tests")
    class AngularSpeedAccessorTests {

        @Test
        @DisplayName("getAngularSpeedX() returns X-axis gyro value")
        void testGetAngularSpeedX() {
            // Arrange
            when(mockStatus.getMotion()).thenReturn(mockMotion);
            when(mockMotion.getGyroRoll()).thenReturn((short) 150);
            when(mockMotion.getGyroPitch()).thenReturn((short) 200);
            when(mockMotion.getGyroYaw()).thenReturn((short) 100);

            // Act
            int angularSpeedX = telemetryService.getAngularSpeedX();

            // Assert
            assertEquals(150, angularSpeedX, "Angular speed X should match gyro[0]");
        }

        @Test
        @DisplayName("getAngularSpeedY() returns Y-axis gyro value")
        void testGetAngularSpeedY() {
            // Arrange
            when(mockStatus.getMotion()).thenReturn(mockMotion);
            when(mockMotion.getGyroRoll()).thenReturn((short) 150);
            when(mockMotion.getGyroPitch()).thenReturn((short) 200);
            when(mockMotion.getGyroYaw()).thenReturn((short) 100);

            // Act
            int angularSpeedY = telemetryService.getAngularSpeedY();

            // Assert
            assertEquals(200, angularSpeedY, "Angular speed Y should match gyro[1]");
        }

        @Test
        @DisplayName("getAngularSpeedZ() returns Z-axis gyro value")
        void testGetAngularSpeedZ() {
            // Arrange
            when(mockStatus.getMotion()).thenReturn(mockMotion);
            when(mockMotion.getGyroRoll()).thenReturn((short) 150);
            when(mockMotion.getGyroPitch()).thenReturn((short) 200);
            when(mockMotion.getGyroYaw()).thenReturn((short) 100);

            // Act
            int angularSpeedZ = telemetryService.getAngularSpeedZ();

            // Assert
            assertEquals(100, angularSpeedZ, "Angular speed Z should match gyro[2]");
        }

        @Test
        @DisplayName("Angular speed accessors handle negative values correctly")
        void testAngularSpeedNegativeValues() {
            // Arrange
            when(mockStatus.getMotion()).thenReturn(mockMotion);
            when(mockMotion.getGyroRoll()).thenReturn((short) -50);
            when(mockMotion.getGyroPitch()).thenReturn((short) -100);
            when(mockMotion.getGyroYaw()).thenReturn((short) -25);
            
            // Act & Assert
            assertEquals(-50, telemetryService.getAngularSpeedX(), "Should handle negative X value");
            assertEquals(-100, telemetryService.getAngularSpeedY(), "Should handle negative Y value");
            assertEquals(-25, telemetryService.getAngularSpeedZ(), "Should handle negative Z value");
        }

        @Test
        @DisplayName("Angular speed accessors work with zero values")
        void testAngularSpeedZeroValues() {
            // Arrange
            when(mockStatus.getMotion()).thenReturn(mockMotion);
            when(mockMotion.getGyroRoll()).thenReturn((short) 0);
            when(mockMotion.getGyroPitch()).thenReturn((short) 0);
            when(mockMotion.getGyroYaw()).thenReturn((short) 0);
            
            // Act & Assert
            assertEquals(0, telemetryService.getAngularSpeedX(), "Should handle zero X value");
            assertEquals(0, telemetryService.getAngularSpeedY(), "Should handle zero Y value");
            assertEquals(0, telemetryService.getAngularSpeedZ(), "Should handle zero Z value");
        }
    }

    @Nested
    @DisplayName("Gyro Accessor Tests")
    class GyroAccessorTests {

        @Test
        @DisplayName("getGyroX() returns X-axis gyro value")
        void testGetGyroX() {
            // Arrange
            when(mockStatus.getMotion()).thenReturn(mockMotion);
            when(mockMotion.getGyroRoll()).thenReturn((short) 150);
            when(mockMotion.getGyroPitch()).thenReturn((short) 200);
            when(mockMotion.getGyroYaw()).thenReturn((short) 100);

            // Act
            int gyroX = telemetryService.getGyroX();

            // Assert
            assertEquals(150, gyroX, "Gyro X should match gyro[0]");
        }

        @Test
        @DisplayName("getGyroY() returns Y-axis gyro value")
        void testGetGyroY() {
            // Arrange
            when(mockStatus.getMotion()).thenReturn(mockMotion);
            when(mockMotion.getGyroRoll()).thenReturn((short) 150);
            when(mockMotion.getGyroPitch()).thenReturn((short) 200);
            when(mockMotion.getGyroYaw()).thenReturn((short) 100);

            // Act
            int gyroY = telemetryService.getGyroY();

            // Assert
            assertEquals(200, gyroY, "Gyro Y should match gyro[1]");
        }

        @Test
        @DisplayName("getGyroZ() returns Z-axis gyro value")
        void testGetGyroZ() {
            // Arrange
            when(mockStatus.getMotion()).thenReturn(mockMotion);
            when(mockMotion.getGyroRoll()).thenReturn((short) 150);
            when(mockMotion.getGyroPitch()).thenReturn((short) 200);
            when(mockMotion.getGyroYaw()).thenReturn((short) 100);

            // Act
            int gyroZ = telemetryService.getGyroZ();

            // Assert
            assertEquals(100, gyroZ, "Gyro Z should match gyro[2]");
        }

        @Test
        @DisplayName("Gyro accessors handle negative values correctly")
        void testGyroNegativeValues() {
            // Arrange
            when(mockStatus.getMotion()).thenReturn(mockMotion);
            when(mockMotion.getGyroRoll()).thenReturn((short) -50);
            when(mockMotion.getGyroPitch()).thenReturn((short) -100);
            when(mockMotion.getGyroYaw()).thenReturn((short) -25);
            
            // Act & Assert
            assertEquals(-50, telemetryService.getGyroX(), "Should handle negative X value");
            assertEquals(-100, telemetryService.getGyroY(), "Should handle negative Y value");
            assertEquals(-25, telemetryService.getGyroZ(), "Should handle negative Z value");
        }
    }

    @Nested
    @DisplayName("Equivalence Tests")
    class EquivalenceTests {

        @Test
        @DisplayName("getAngularSpeedX() equals getGyroX()")
        void testAngularSpeedXEqualsGyroX() {
            // Arrange
            when(mockStatus.getMotion()).thenReturn(mockMotion);
            when(mockMotion.getGyroRoll()).thenReturn((short) 150);
            when(mockMotion.getGyroPitch()).thenReturn((short) 200);
            when(mockMotion.getGyroYaw()).thenReturn((short) 100);

            // Act & Assert
            assertEquals(telemetryService.getGyroX(), telemetryService.getAngularSpeedX(),
                "getAngularSpeedX() should return the same value as getGyroX()");
        }

        @Test
        @DisplayName("getAngularSpeedY() equals getGyroY()")
        void testAngularSpeedYEqualsGyroY() {
            // Arrange
            when(mockStatus.getMotion()).thenReturn(mockMotion);
            when(mockMotion.getGyroRoll()).thenReturn((short) 150);
            when(mockMotion.getGyroPitch()).thenReturn((short) 200);
            when(mockMotion.getGyroYaw()).thenReturn((short) 100);

            // Act & Assert
            assertEquals(telemetryService.getGyroY(), telemetryService.getAngularSpeedY(),
                "getAngularSpeedY() should return the same value as getGyroY()");
        }

        @Test
        @DisplayName("getAngularSpeedZ() equals getGyroZ()")
        void testAngularSpeedZEqualsGyroZ() {
            // Arrange
            when(mockStatus.getMotion()).thenReturn(mockMotion);
            when(mockMotion.getGyroRoll()).thenReturn((short) 150);
            when(mockMotion.getGyroPitch()).thenReturn((short) 200);
            when(mockMotion.getGyroYaw()).thenReturn((short) 100);

            // Act & Assert
            assertEquals(telemetryService.getGyroZ(), telemetryService.getAngularSpeedZ(),
                "getAngularSpeedZ() should return the same value as getGyroZ()");
        }

        @Test
        @DisplayName("Axis accessors match array-based access")
        void testAxisAccessorsMatchArrayAccess() {
            // Arrange
            when(mockStatus.getMotion()).thenReturn(mockMotion);
            when(mockMotion.getGyroRoll()).thenReturn((short) 150);
            when(mockMotion.getGyroPitch()).thenReturn((short) 200);
            when(mockMotion.getGyroYaw()).thenReturn((short) 100);

            // Act
            int[] gyroData = telemetryService.getGyro();

            // Assert
            assertEquals(gyroData[0], telemetryService.getAngularSpeedX(), "getAngularSpeedX() should equal getGyro()[0]");
            assertEquals(gyroData[1], telemetryService.getAngularSpeedY(), "getAngularSpeedY() should equal getGyro()[1]");
            assertEquals(gyroData[2], telemetryService.getAngularSpeedZ(), "getAngularSpeedZ() should equal getGyro()[2]");
            assertEquals(gyroData[0], telemetryService.getGyroX(), "getGyroX() should equal getGyro()[0]");
            assertEquals(gyroData[1], telemetryService.getGyroY(), "getGyroY() should equal getGyro()[1]");
            assertEquals(gyroData[2], telemetryService.getGyroZ(), "getGyroZ() should equal getGyro()[2]");
        }
    }

    @Nested
    @DisplayName("Python Parity Tests")
    class PythonParityTests {

        @Test
        @DisplayName("Methods provide Python API compatibility")
        void testPythonApiCompatibility() {
            // Arrange
            when(mockStatus.getMotion()).thenReturn(mockMotion);
            when(mockMotion.getGyroRoll()).thenReturn((short) 150);
            when(mockMotion.getGyroPitch()).thenReturn((short) 200);
            when(mockMotion.getGyroYaw()).thenReturn((short) 100);

            // Act - Verify all methods work as expected for Python parity
            int angularSpeedX = telemetryService.getAngularSpeedX();  // Python: get_angular_speed_x()
            int angularSpeedY = telemetryService.getAngularSpeedY();  // Python: get_angular_speed_y()
            int angularSpeedZ = telemetryService.getAngularSpeedZ();  // Python: get_angular_speed_z()
            int gyroX = telemetryService.getGyroX();                  // Python: get_x_gyro()
            int gyroY = telemetryService.getGyroY();                  // Python: get_y_gyro()
            int gyroZ = telemetryService.getGyroZ();                  // Python: get_z_gyro()

            // Assert
            assertEquals(150, angularSpeedX, "Angular speed X should match Python behavior");
            assertEquals(200, angularSpeedY, "Angular speed Y should match Python behavior");
            assertEquals(100, angularSpeedZ, "Angular speed Z should match Python behavior");
            assertEquals(150, gyroX, "Gyro X should match Python behavior");
            assertEquals(200, gyroY, "Gyro Y should match Python behavior");
            assertEquals(100, gyroZ, "Gyro Z should match Python behavior");
        }
    }

    @Nested
    @DisplayName("Educational Integration Tests")
    class EducationalIntegrationTests {

        @Test
        @DisplayName("L0106 Conditionals - Rotation detection example")
        void testRotationDetectionConditional() {
            // Arrange & Test rapid rotation scenario
            when(mockStatus.getMotion()).thenReturn(mockMotion);
            when(mockMotion.getGyroRoll()).thenReturn((short) 200);
            when(mockMotion.getGyroPitch()).thenReturn((short) 150);
            when(mockMotion.getGyroYaw()).thenReturn((short) 300);
            
            boolean rapidRotation = Math.abs(telemetryService.getAngularSpeedZ()) > 250;
            assertTrue(rapidRotation, "Should detect rapid rotation when gyro Z > 250");
            
            // Test stable scenario
            when(mockMotion.getGyroRoll()).thenReturn((short) 10);
            when(mockMotion.getGyroPitch()).thenReturn((short) 5);
            when(mockMotion.getGyroYaw()).thenReturn((short) 15);
            
            boolean stable = Math.abs(telemetryService.getAngularSpeedX()) < 50 &&
                           Math.abs(telemetryService.getAngularSpeedY()) < 50 &&
                           Math.abs(telemetryService.getAngularSpeedZ()) < 50;
            assertTrue(stable, "Should detect stable state when all gyro values are small");
        }

        @Test
        @DisplayName("L0106 Conditionals - Motion pattern detection")
        void testMotionPatternDetection() {
            // Test spinning pattern (high yaw rotation)
            when(mockStatus.getMotion()).thenReturn(mockMotion);
            when(mockMotion.getGyroRoll()).thenReturn((short) 5);
            when(mockMotion.getGyroPitch()).thenReturn((short) 10);
            when(mockMotion.getGyroYaw()).thenReturn((short) 200);
            
            boolean isSpinning = Math.abs(telemetryService.getGyroZ()) > 100 &&
                               Math.abs(telemetryService.getGyroX()) < 50 &&
                               Math.abs(telemetryService.getGyroY()) < 50;
            assertTrue(isSpinning, "Should detect spinning when only Z-axis has high rotation");
            
            // Test tumbling pattern (all axes rotating)
            when(mockMotion.getGyroRoll()).thenReturn((short) 150);
            when(mockMotion.getGyroPitch()).thenReturn((short) 180);
            when(mockMotion.getGyroYaw()).thenReturn((short) 200);
            
            boolean isTumbling = Math.abs(telemetryService.getAngularSpeedX()) > 100 &&
                                Math.abs(telemetryService.getAngularSpeedY()) > 100 &&
                                Math.abs(telemetryService.getAngularSpeedZ()) > 100;
            assertTrue(isTumbling, "Should detect tumbling when all axes have high rotation");
        }
    }
}
