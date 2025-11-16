# Python API Method Porting Instructions for Agents

This document provides detailed instructions for implementing missing Python API methods in the Java library to improve Python API compatibility.

## Overview

The Java API currently has 188 public methods while Python API v2.6.0 has 259 methods. The goal is to systematically port the 145 missing methods to achieve API parity.

**Key Resources:**
- [API Comparison Report](../API_COMPARISON_vs_2.6.md) - Lists all missing methods
- [Python API Documentation](https://docs.robolink.com/docs/CoDroneEDU/Python/Function-Documentation)
- [PYTHON_API_METHODS_TO_PORT.md](../PYTHON_API_METHODS_TO_PORT.md) - Detailed templates by category

## Task Assignment Pattern

Each GitHub issue will specify:
1. **Category**: Sensor accessors, Display graphics, Motor control, etc.
2. **Methods**: List of specific methods to implement (typically 4-12 per issue)
3. **Acceptance Criteria**: Specific requirements for the PR
4. **References**: Links to Python docs and this guide

### Example Issue Title
```
"Add individual sensor axis accessor methods"
"Implement display graphics drawing methods"
"Add waypoint navigation methods"
```

## General Implementation Guidelines

### 1. Method Resolution Pattern

**Order of precedence:**
1. Check if method already exists in Java (may have different name)
2. Check if method delegates to existing helper method
3. Implement new method with proper delegation

### 2. Java Naming Conventions

- **Python**: `snake_case` (e.g., `get_angular_speed_x()`)
- **Java**: `camelCase` (e.g., `getAngularSpeedX()`)

Convert using standard Java conventions. For unusual cases, reference existing method naming in Drone.java.

### 3. Annotations Required

Every new public method must have:

```java
/**
 * [JAVADOC_DESCRIPTION]
 * 
 * [Additional details if applicable]
 *
 * @return [return value description]
 * @throws [ExceptionType] [when applicable]
 * @since 1.4.0
 * @pythonEquivalent [python_method_name]
 * @pythonReference https://docs.robolink.com/docs/CoDroneEDU/Python/Function-Documentation#[anchor]
 */
public [ReturnType] [methodName]([parameters]) {
    // implementation
}
```

### 4. Documentation Sources

**Priority order:**
1. Existing Java method Javadoc (if similar method exists)
2. Python API documentation at: https://docs.robolink.com/docs/CoDroneEDU/Python/Function-Documentation
3. Python source code docstrings: `reference/python-venv/lib/python3.12/site-packages/codrone_edu/drone.py`

**Python Documentation URL:**
All Python API methods are documented at a single page:
```
https://docs.robolink.com/docs/CoDroneEDU/Python/Function-Documentation
```

**Finding method anchors:**
- Use exact Python method name in snake_case as the anchor
- Example: Python method `get_angular_speed_x` → anchor `#get_angular_speed_x`
- Full reference URL: `https://docs.robolink.com/docs/CoDroneEDU/Python/Function-Documentation#get_angular_speed_x`
- Pattern: Copy Python method name directly as-is for the anchor

### 5. Architecture Rules

#### Rule 1: Public API in Drone.java Only
- All public methods go in `Drone.java`
- Drone.java is the single public API surface

#### Rule 2: Helper Class Delegation
- FlightController, LinkController, TelemetryService, etc. are helpers
- **ONLY** implement the full-signature method in helper classes
- **NO** overloaded methods in helper classes
- Drone.java handles all convenience overloads

#### Rule 3: Delegation Pattern for Simple Getters
For methods that simply return existing data:

```java
// In Drone.java
public double getAngularSpeedX() {
    int[] gyro = getGyro();
    return gyro[0];  // Direct delegation to existing array method
}
```

#### Rule 4: Overloading with Default Parameters
When method has optional parameters, use DroneSystem constants:

```java
// In Drone.java - Convenience overload
public void sendMotor(int speed) {
    sendMotor(speed, DroneSystem.DEFAULT_MOTOR_PARAM);
}

// In Drone.java - Full signature delegates to helper
public void sendMotor(int speed, int param2) {
    flightController.sendMotor(speed, param2);
}

// In FlightController.java - ONLY this method, no overloads
public void sendMotor(int speed, int param2) {
    // Implementation
}
```

#### Rule 5: Constants
Default values and constants belong in `DroneSystem.java`:
- Look for existing constants: `DroneSystem.DEFAULT_*`, `DroneSystem.*_CONSTANT`
- Add new constants if needed (with clear documentation)
- Reference constants in method defaults

### 6. @since Version

All new methods: `@since 1.4.0`

This is the planned next release after v1.3.0.

### 7. Implementation Location

**File paths:**
- **Public API**: `src/main/java/com/otabi/jcodroneedu/Drone.java`
- **Flight Control**: `src/main/java/com/otabi/jcodroneedu/FlightController.java`
- **Link/Controller**: `src/main/java/com/otabi/jcodroneedu/LinkController.java`
- **Telemetry**: `src/main/java/com/otabi/jcodroneedu/TelemetryService.java`
- **Constants**: `src/main/java/com/otabi/jcodroneedu/DroneSystem.java`

## Workflow

### Step 1: Understand the Methods
```bash
# Read the issue description carefully
# Check PYTHON_API_METHODS_TO_PORT.md for any templates
# Review Python docs at:
# https://docs.robolink.com/docs/CoDroneEDU/Python/Function-Documentation
```

### Step 2: Research Existing Code
```bash
# Check if similar methods already exist
grep -r "getGyro\|sendMotor\|controller_draw" src/main/java/

# Look at existing similar implementations for patterns
# Example: If implementing getAngularSpeedX, look at getGyro()
```

### Step 3: Implement Methods
```bash
# Create feature branch
git checkout -b feature/issue-XXX-[description]

# Implement methods in appropriate file(s)
# Follow architecture rules above
# Add all required annotations
```

### Step 4: Verify Implementation
```bash
# Check for compilation errors
./gradlew build

# Run tests
./gradlew test

# Check existing tests still pass
./gradlew integrationTest

# Validate code style
./gradlew checkstyleMain
```

### Step 5: Submit PR
```bash
# Commit with clear message
git commit -m "Implement [method names] for Python API compatibility

- Add [number] new methods
- All methods have @since 1.4.0, @pythonEquivalent, @pythonReference
- Delegation pattern follows existing code
- Tests added/updated as needed"

# Push to feature branch
git push origin feature/issue-XXX-[description]

# Create PR with reference to issue
# Title should match or closely relate to the GitHub issue
```

## Common Patterns

### Pattern 1: Simple Accessor (Delegate to Existing)
```java
public double getAngularSpeedX() {
    int[] gyro = getGyro();
    return gyro[0];
}
```

### Pattern 2: Delegation to Helper Class
```java
public void sendMotor(int motor1, int motor2) {
    flightController.sendMotor(motor1, motor2);
}
```

### Pattern 3: Convenience Overload
```java
// Overload with default
public void sendDisplayDrawRect(int x, int y, int width, int height) {
    sendDisplayDrawRect(x, y, width, height, DroneSystem.DEFAULT_COLOR);
}

// Full implementation delegates
public void sendDisplayDrawRect(int x, int y, int width, int height, int color) {
    flightController.sendDisplayDrawRect(x, y, width, height, color);
}
```

### Pattern 4: Data Structure Aggregation
```java
public MotionData getMotionData() {
    return flightController.getMotionData();
}
```

## Error Handling

### If Python Method Documentation is Missing
1. Search Python source code: `reference/python-venv/lib/python3.12/site-packages/codrone_edu/drone.py`
2. Look for docstrings in the method
3. Check method signature for parameter hints
4. Report in PR if documentation uncertain

### If Method Signature is Unclear
1. Check Python API source for exact parameters
2. Look for similar methods in Java for pattern
3. Ask for clarification in PR comments if needed

### If Implementation Fails Compilation
1. Verify method signature matches Python API
2. Check parameter types and return type
3. Ensure helper class methods exist
4. Look for typos in method names

## Testing

### Unit Tests
If a test file exists for the helper class, add tests for new methods:
- Location: `src/test/java/com/otabi/jcodroneedu/`
- Pattern: Test both direct call and delegation

### Integration Tests
If applicable, add integration tests:
- Location: `src/integrationTest/java/com/otabi/jcodroneedu/`

### Manual Verification
```bash
# Compile and run examples
./gradlew build

# Check for warnings
./gradlew build --warning-mode all
```

## PR Review Checklist

Before submitting PR, verify:

- [ ] All methods from issue are implemented
- [ ] All methods have `@since 1.4.0`
- [ ] All methods have `@pythonEquivalent` annotation
- [ ] All methods have `@pythonReference` annotation with valid anchor
- [ ] Javadoc is complete and accurate
- [ ] Code follows existing style (camelCase, formatting)
- [ ] Methods delegate properly (no repeated logic)
- [ ] Helper classes have NO overloads (only full signature)
- [ ] Drone.java handles all convenience overloads
- [ ] Build passes: `./gradlew build`
- [ ] Tests pass: `./gradlew test`
- [ ] No new warnings introduced
- [ ] Commit message is clear and references issue
- [ ] PR description explains what was implemented

## Common Mistakes to Avoid

1. **❌ Adding overloads to helper classes** → ✅ Only add to Drone.java
2. **❌ Forgetting @pythonReference anchor** → ✅ Always include full link
3. **❌ Using snake_case in Java** → ✅ Use camelCase
4. **❌ Not checking existing methods** → ✅ Search first, reuse if exists
5. **❌ Incomplete Javadoc** → ✅ Copy from Python docs when possible
6. **❌ Using @since 1.3.0** → ✅ Use 1.4.0 for new methods
7. **❌ Adding constants in method** → ✅ Add to DroneSystem.java
8. **❌ Implementing in wrong class** → ✅ Public API always goes to Drone.java

## Getting Help

### Resources
- Python API docs: https://docs.robolink.com/docs/CoDroneEDU/Python/Function-Documentation
- Existing Drone.java: `src/main/java/com/otabi/jcodroneedu/Drone.java`
- Similar method patterns: Search for existing methods in same category
- PYTHON_API_METHODS_TO_PORT.md: Check for specific method templates

### In PR Comments
- Ask for clarification if documentation is unclear
- Ask if Python behavior needs verification
- Ask if implementation approach is correct
- Request review if multiple files are modified

## References

- [GitHub Issue Labels](https://github.com/scerruti/JCoDroneEdu/labels)
- [API Comparison](../API_COMPARISON_vs_2.6.md)
- [AGENT_INSTRUCTIONS.md](./AGENT_INSTRUCTIONS.md) - General agent workflow
- [PYTHON_API_METHODS_TO_PORT.md](../PYTHON_API_METHODS_TO_PORT.md) - Method templates
