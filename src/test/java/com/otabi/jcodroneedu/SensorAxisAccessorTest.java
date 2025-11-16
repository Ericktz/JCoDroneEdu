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

    private FlightController flightController;
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
        
        // Create real FlightController with mocked drone
        flightController = new FlightController(mockDrone);
    }

    @Nested
    @DisplayName("Angular Speed Accessor Tests")
    class AngularSpeedAccessorTests {

        @Test
        @DisplayName("getAngularSpeedX() returns X-axis gyro value")
        void testGetAngularSpeedX() {
            // Arrange
            when(mockStatus.getMotion()).thenReturn(mockMotion);
            when(mockMotion.getGyroRoll()).thenReturn((short) 150);   // X
            when(mockMotion.getGyroPitch()).thenReturn((short) 200);  // Y
            when(mockMotion.getGyroYaw()).thenReturn((short) 100);    // Z

            // Act - Create a real Drone instance to test delegation
            Drone realDrone = spy(new Drone());
            when(realDrone.getGyro()).thenReturn(new int[]{150, 200, 100});
            int angularSpeedX = realDrone.getAngularSpeedX();

            // Assert
            assertEquals(150, angularSpeedX, "Angular speed X should match gyro[0]");
        }

        @Test
        @DisplayName("getAngularSpeedY() returns Y-axis gyro value")
        void testGetAngularSpeedY() {
            // Arrange & Act
            Drone realDrone = spy(new Drone());
            when(realDrone.getGyro()).thenReturn(new int[]{150, 200, 100});
            int angularSpeedY = realDrone.getAngularSpeedY();

            // Assert
            assertEquals(200, angularSpeedY, "Angular speed Y should match gyro[1]");
        }

        @Test
        @DisplayName("getAngularSpeedZ() returns Z-axis gyro value")
        void testGetAngularSpeedZ() {
            // Arrange & Act
            Drone realDrone = spy(new Drone());
            when(realDrone.getGyro()).thenReturn(new int[]{150, 200, 100});
            int angularSpeedZ = realDrone.getAngularSpeedZ();

            // Assert
            assertEquals(100, angularSpeedZ, "Angular speed Z should match gyro[2]");
        }

        @Test
        @DisplayName("Angular speed accessors handle negative values correctly")
        void testAngularSpeedNegativeValues() {
            // Arrange & Act
            Drone realDrone = spy(new Drone());
            when(realDrone.getGyro()).thenReturn(new int[]{-50, -100, -25});
            
            // Assert
            assertEquals(-50, realDrone.getAngularSpeedX(), "Should handle negative X value");
            assertEquals(-100, realDrone.getAngularSpeedY(), "Should handle negative Y value");
            assertEquals(-25, realDrone.getAngularSpeedZ(), "Should handle negative Z value");
        }

        @Test
        @DisplayName("Angular speed accessors work with zero values")
        void testAngularSpeedZeroValues() {
            // Arrange & Act
            Drone realDrone = spy(new Drone());
            when(realDrone.getGyro()).thenReturn(new int[]{0, 0, 0});
            
            // Assert
            assertEquals(0, realDrone.getAngularSpeedX(), "Should handle zero X value");
            assertEquals(0, realDrone.getAngularSpeedY(), "Should handle zero Y value");
            assertEquals(0, realDrone.getAngularSpeedZ(), "Should handle zero Z value");
        }
    }

    @Nested
    @DisplayName("Gyro Accessor Tests")
    class GyroAccessorTests {

        @Test
        @DisplayName("getGyroX() returns X-axis gyro value")
        void testGetGyroX() {
            // Arrange & Act
            Drone realDrone = spy(new Drone());
            when(realDrone.getGyro()).thenReturn(new int[]{150, 200, 100});
            int gyroX = realDrone.getGyroX();

            // Assert
            assertEquals(150, gyroX, "Gyro X should match gyro[0]");
        }

        @Test
        @DisplayName("getGyroY() returns Y-axis gyro value")
        void testGetGyroY() {
            // Arrange & Act
            Drone realDrone = spy(new Drone());
            when(realDrone.getGyro()).thenReturn(new int[]{150, 200, 100});
            int gyroY = realDrone.getGyroY();

            // Assert
            assertEquals(200, gyroY, "Gyro Y should match gyro[1]");
        }

        @Test
        @DisplayName("getGyroZ() returns Z-axis gyro value")
        void testGetGyroZ() {
            // Arrange & Act
            Drone realDrone = spy(new Drone());
            when(realDrone.getGyro()).thenReturn(new int[]{150, 200, 100});
            int gyroZ = realDrone.getGyroZ();

            // Assert
            assertEquals(100, gyroZ, "Gyro Z should match gyro[2]");
        }

        @Test
        @DisplayName("Gyro accessors handle negative values correctly")
        void testGyroNegativeValues() {
            // Arrange & Act
            Drone realDrone = spy(new Drone());
            when(realDrone.getGyro()).thenReturn(new int[]{-50, -100, -25});
            
            // Assert
            assertEquals(-50, realDrone.getGyroX(), "Should handle negative X value");
            assertEquals(-100, realDrone.getGyroY(), "Should handle negative Y value");
            assertEquals(-25, realDrone.getGyroZ(), "Should handle negative Z value");
        }
    }

    @Nested
    @DisplayName("Equivalence Tests")
    class EquivalenceTests {

        @Test
        @DisplayName("getAngularSpeedX() equals getGyroX()")
        void testAngularSpeedXEqualsGyroX() {
            // Arrange & Act
            Drone realDrone = spy(new Drone());
            when(realDrone.getGyro()).thenReturn(new int[]{150, 200, 100});

            // Assert
            assertEquals(realDrone.getGyroX(), realDrone.getAngularSpeedX(),
                "getAngularSpeedX() should return the same value as getGyroX()");
        }

        @Test
        @DisplayName("getAngularSpeedY() equals getGyroY()")
        void testAngularSpeedYEqualsGyroY() {
            // Arrange & Act
            Drone realDrone = spy(new Drone());
            when(realDrone.getGyro()).thenReturn(new int[]{150, 200, 100});

            // Assert
            assertEquals(realDrone.getGyroY(), realDrone.getAngularSpeedY(),
                "getAngularSpeedY() should return the same value as getGyroY()");
        }

        @Test
        @DisplayName("getAngularSpeedZ() equals getGyroZ()")
        void testAngularSpeedZEqualsGyroZ() {
            // Arrange & Act
            Drone realDrone = spy(new Drone());
            when(realDrone.getGyro()).thenReturn(new int[]{150, 200, 100});

            // Assert
            assertEquals(realDrone.getGyroZ(), realDrone.getAngularSpeedZ(),
                "getAngularSpeedZ() should return the same value as getGyroZ()");
        }

        @Test
        @DisplayName("Axis accessors match array-based access")
        void testAxisAccessorsMatchArrayAccess() {
            // Arrange & Act
            Drone realDrone = spy(new Drone());
            int[] gyroData = new int[]{150, 200, 100};
            when(realDrone.getGyro()).thenReturn(gyroData);

            // Assert
            assertEquals(gyroData[0], realDrone.getAngularSpeedX(), "getAngularSpeedX() should equal getGyro()[0]");
            assertEquals(gyroData[1], realDrone.getAngularSpeedY(), "getAngularSpeedY() should equal getGyro()[1]");
            assertEquals(gyroData[2], realDrone.getAngularSpeedZ(), "getAngularSpeedZ() should equal getGyro()[2]");
            assertEquals(gyroData[0], realDrone.getGyroX(), "getGyroX() should equal getGyro()[0]");
            assertEquals(gyroData[1], realDrone.getGyroY(), "getGyroY() should equal getGyro()[1]");
            assertEquals(gyroData[2], realDrone.getGyroZ(), "getGyroZ() should equal getGyro()[2]");
        }
    }

    @Nested
    @DisplayName("Python Parity Tests")
    class PythonParityTests {

        @Test
        @DisplayName("Methods provide Python API compatibility")
        void testPythonApiCompatibility() {
            // Arrange
            Drone realDrone = spy(new Drone());
            when(realDrone.getGyro()).thenReturn(new int[]{150, 200, 100});

            // Act - Verify all methods work as expected for Python parity
            int angularSpeedX = realDrone.getAngularSpeedX();  // Python: get_angular_speed_x()
            int angularSpeedY = realDrone.getAngularSpeedY();  // Python: get_angular_speed_y()
            int angularSpeedZ = realDrone.getAngularSpeedZ();  // Python: get_angular_speed_z()
            int gyroX = realDrone.getGyroX();                  // Python: get_x_gyro()
            int gyroY = realDrone.getGyroY();                  // Python: get_y_gyro()
            int gyroZ = realDrone.getGyroZ();                  // Python: get_z_gyro()

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
            // Arrange
            Drone realDrone = spy(new Drone());
            
            // Test rapid rotation scenario
            when(realDrone.getGyro()).thenReturn(new int[]{200, 150, 300});
            boolean rapidRotation = Math.abs(realDrone.getAngularSpeedZ()) > 250;
            assertTrue(rapidRotation, "Should detect rapid rotation when gyro Z > 250");
            
            // Test stable scenario
            when(realDrone.getGyro()).thenReturn(new int[]{10, 5, 15});
            boolean stable = Math.abs(realDrone.getAngularSpeedX()) < 50 &&
                           Math.abs(realDrone.getAngularSpeedY()) < 50 &&
                           Math.abs(realDrone.getAngularSpeedZ()) < 50;
            assertTrue(stable, "Should detect stable state when all gyro values are small");
        }

        @Test
        @DisplayName("L0106 Conditionals - Motion pattern detection")
        void testMotionPatternDetection() {
            // Arrange
            Drone realDrone = spy(new Drone());
            
            // Test spinning pattern (high yaw rotation)
            when(realDrone.getGyro()).thenReturn(new int[]{5, 10, 200});
            boolean isSpinning = Math.abs(realDrone.getGyroZ()) > 100 &&
                               Math.abs(realDrone.getGyroX()) < 50 &&
                               Math.abs(realDrone.getGyroY()) < 50;
            assertTrue(isSpinning, "Should detect spinning when only Z-axis has high rotation");
            
            // Test tumbling pattern (all axes rotating)
            when(realDrone.getGyro()).thenReturn(new int[]{150, 180, 200});
            boolean isTumbling = Math.abs(realDrone.getAngularSpeedX()) > 100 &&
                                Math.abs(realDrone.getAngularSpeedY()) > 100 &&
                                Math.abs(realDrone.getAngularSpeedZ()) > 100;
            assertTrue(isTumbling, "Should detect tumbling when all axes have high rotation");
        }
    }
}
