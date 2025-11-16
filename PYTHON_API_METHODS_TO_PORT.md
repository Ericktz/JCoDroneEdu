# Python API Methods to Port to Java

This document provides agent-ready templates for creating GitHub issues to implement missing Python API methods in Java. Each method includes Javadoc descriptions extracted from the Python API documentation.

## Sensor Axis Accessor Methods

### Issue: Add individual sensor axis accessor methods
**Methods:** getAngularSpeedX/Y/Z, getAccelX/Y/Z, getAngleX/Y/Z, getGyroX/Y/Z

#### getAngularSpeedX()
```
/**
 * Gets the angular speed (gyroscope) data for the X axis.
 * 
 * <p>Returns the rotational velocity around the X axis in degrees per second.
 * This method provides Python API compatibility for accessing individual axis
 * gyro data. Equivalent to getGyro()[0].</p>
 *
 * @return gyro X data in degrees per second
 * @since 1.4.0
 * @pythonEquivalent get_angular_speed_x
 * @pythonReference https://docs.robolink.com/docs/CoDroneEDU/Python/Function-Documentation#get_angular_speed_x
 */
```

#### getAngularSpeedY()
```
/**
 * Gets the angular speed (gyroscope) data for the Y axis.
 * 
 * <p>Returns the rotational velocity around the Y axis in degrees per second.
 * This method provides Python API compatibility for accessing individual axis
 * gyro data. Equivalent to getGyro()[1].</p>
 *
 * @return gyro Y data in degrees per second
 * @since 1.4.0
 * @pythonEquivalent get_angular_speed_y
 * @pythonReference https://docs.robolink.com/docs/CoDroneEDU/Python/Function-Documentation#get_angular_speed_y
 */
```

#### getAngularSpeedZ()
```
/**
 * Gets the angular speed (gyroscope) data for the Z axis.
 * 
 * <p>Returns the rotational velocity around the Z axis in degrees per second.
 * This method provides Python API compatibility for accessing individual axis
 * gyro data. Equivalent to getGyro()[2].</p>
 *
 * @return gyro Z data in degrees per second
 * @since 1.4.0
 * @pythonEquivalent get_angular_speed_z
 * @pythonReference https://docs.robolink.com/docs/CoDroneEDU/Python/Function-Documentation#get_angular_speed_z
 */
```

#### getAccelX()
```
/**
 * Gets the acceleration data for the X axis.
 * 
 * <p>Returns the linear acceleration along the X axis in units of measurement.
 * This method provides Python API compatibility for accessing individual axis
 * acceleration data. Equivalent to getAccel()[0].</p>
 *
 * @return acceleration X data
 * @since 1.4.0
 * @pythonEquivalent get_x_accel
 * @pythonReference https://docs.robolink.com/docs/CoDroneEDU/Python/Function-Documentation#get_x_accel
 */
```

#### getAccelY()
```
/**
 * Gets the acceleration data for the Y axis.
 * 
 * <p>Returns the linear acceleration along the Y axis in units of measurement.
 * This method provides Python API compatibility for accessing individual axis
 * acceleration data. Equivalent to getAccel()[1].</p>
 *
 * @return acceleration Y data
 * @since 1.4.0
 * @pythonEquivalent get_y_accel
 * @pythonReference https://docs.robolink.com/docs/CoDroneEDU/Python/Function-Documentation#get_y_accel
 */
```

#### getAccelZ()
```
/**
 * Gets the acceleration data for the Z axis.
 * 
 * <p>Returns the linear acceleration along the Z axis in units of measurement.
 * This method provides Python API compatibility for accessing individual axis
 * acceleration data. Equivalent to getAccel()[2].</p>
 *
 * @return acceleration Z data
 * @since 1.4.0
 * @pythonEquivalent get_z_accel
 * @pythonReference https://docs.robolink.com/docs/CoDroneEDU/Python/Function-Documentation#get_z_accel
 */
```

#### getAngleX()
```
/**
 * Gets the angle data for the X axis (roll).
 * 
 * <p>Returns the roll angle in degrees. This method provides Python API 
 * compatibility for accessing individual axis angle data. Equivalent to getAngle()[0].</p>
 *
 * @return angle X data in degrees
 * @since 1.4.0
 * @pythonEquivalent get_x_angle
 * @pythonReference https://docs.robolink.com/docs/CoDroneEDU/Python/Function-Documentation#get_x_angle
 */
```

#### getAngleY()
```
/**
 * Gets the angle data for the Y axis (pitch).
 * 
 * <p>Returns the pitch angle in degrees. This method provides Python API 
 * compatibility for accessing individual axis angle data. Equivalent to getAngle()[1].</p>
 *
 * @return angle Y data in degrees
 * @since 1.4.0
 * @pythonEquivalent get_y_angle
 * @pythonReference https://docs.robolink.com/docs/CoDroneEDU/Python/Function-Documentation#get_y_angle
 */
```

#### getAngleZ()
```
/**
 * Gets the angle data for the Z axis (yaw).
 * 
 * <p>Returns the yaw angle in degrees. This method provides Python API 
 * compatibility for accessing individual axis angle data. Equivalent to getAngle()[2].</p>
 *
 * @return angle Z data in degrees
 * @since 1.4.0
 * @pythonEquivalent get_z_angle
 * @pythonReference https://docs.robolink.com/docs/CoDroneEDU/Python/Function-Documentation#get_z_angle
 */
```

#### getGyroX() [deprecated in Python]
```
/**
 * Gets the gyroscope data for the X axis.
 * 
 * <p>Returns the rotational velocity around the X axis in degrees per second.
 * Equivalent to getGyro()[0]. Note: Python API deprecated this in favor of 
 * get_angular_speed_x().</p>
 *
 * @return gyro X data in degrees per second
 * @since 1.4.0
 * @pythonEquivalent get_x_gyro
 * @pythonReference https://docs.robolink.com/docs/CoDroneEDU/Python/Function-Documentation#get_x_gyro
 */
```

#### getGyroY() [deprecated in Python]
```
/**
 * Gets the gyroscope data for the Y axis.
 * 
 * <p>Returns the rotational velocity around the Y axis in degrees per second.
 * Equivalent to getGyro()[1]. Note: Python API deprecated this in favor of 
 * get_angular_speed_y().</p>
 *
 * @return gyro Y data in degrees per second
 * @since 1.4.0
 * @pythonEquivalent get_y_gyro
 * @pythonReference https://docs.robolink.com/docs/CoDroneEDU/Python/Function-Documentation#get_y_gyro
 */
```

#### getGyroZ() [deprecated in Python]
```
/**
 * Gets the gyroscope data for the Z axis.
 * 
 * <p>Returns the rotational velocity around the Z axis in degrees per second.
 * Equivalent to getGyro()[2]. Note: Python API deprecated this in favor of 
 * get_angular_speed_z().</p>
 *
 * @return gyro Z data in degrees per second
 * @since 1.4.0
 * @pythonEquivalent get_z_gyro
 * @pythonReference https://docs.robolink.com/docs/CoDroneEDU/Python/Function-Documentation#get_z_gyro
 */
```

---

## Implementation Notes for Agents

### Task Template
When creating an issue from this document:
1. Copy the method descriptions into the Javadoc
2. Use `@since 1.4.0` for all new methods
3. Add proper `@pythonEquivalent` and `@pythonReference` annotations
4. Implement as delegation to existing array methods
5. Python docs URL for all methods: https://docs.robolink.com/docs/CoDroneEDU/Python/Function-Documentation
   - Anchor pattern: Use exact Python method name as anchor (e.g., `#get_angular_speed_x`)
   - Full format: `https://docs.robolink.com/docs/CoDroneEDU/Python/Function-Documentation#[python_method_name]`

### Code Pattern

**For Drone.java (public API):**
- Single method that delegates to helper class
- Uses existing array methods (getGyro(), getAccel(), getAngle())
- Returns specific array index

```java
public double getAngularSpeedX() {
    int[] gyro = getGyro();
    return gyro[0];
}
```

**For Helper Classes (FlightController, etc.):**
- NO overloaded methods - only implement once with all parameters
- Drone.java handles delegation and defaults
- Helper class stays simple and lean

### Overloading Pattern (When Methods Have Defaults)
For methods with optional parameters, use this pattern:

```java
// In Drone.java - convenience overloads with defaults
public void exampleMethod(int param1, int param2) {
    exampleMethod(param1, param2, DroneSystem.DEFAULT_CONSTANT);
}

public void exampleMethod(int param1, int param2, int param3) {
    flightController.exampleMethod(param1, param2, param3);  // Delegates
}

// In FlightController.java - only full signature
public void exampleMethod(int param1, int param2, int param3) {
    // Implementation here - no overloads
}
```

### Review Checklist
- [ ] All 12 methods implemented
- [ ] All have @since 1.4.0
- [ ] All have @pythonEquivalent
- [ ] All have @pythonReference with correct anchor
- [ ] Javadoc matches descriptions above
- [ ] Code follows existing patterns (single delegation)
- [ ] Helper class has NO overloads (only Drone.java has them)
- [ ] No compilation errors
- [ ] Tests added if applicable

