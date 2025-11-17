
# Changelog

All notable changes to this project will be documented in this file.

## v1.4.0 - 2025-11-16

### Added
- Image data retrieval: `getImageData(String imageFileName)` method for saving images from the drone camera
- Sensor reset: `resetSensor()` method for resetting all sensors to initial state
- Individual sensor axis accessor methods for full Python API compatibility:
  - `getAngularSpeedX()`, `getAngularSpeedY()`, `getAngularSpeedZ()`
  - `getAccelX()`, `getAccelY()`, `getAccelZ()`
  - `getAngleX()`, `getAngleY()`, `getAngleZ()`
  - `getGyroX()`, `getGyroY()`, `getGyroZ()` (deprecated)
- API comparison tooling improvements and documentation updates

### Changed
- Deprecated legacy sensor axis methods in favor of new accessors
- Improved API documentation and Python compatibility annotations

### Fixed
- Resolved merge conflicts and ensured all tests pass after major API changes

### Documentation
- Updated API documentation for new and deprecated methods
- Updated API comparison and compatibility reports

## v1.3.0 - 2025-11-16

### Added
- Display batch protocol implementation (0x88 DisplayDrawImage) with interleaved transmission for efficient canvas updates
- Integration test reorganization with dedicated `integrationtests` folder and `IntegrationTestMenu`
- Buzzer frequency-based control method overloads for more flexible audio control
- Enhanced receiver handling for both standard and echo Ack formats
- Canvas parameter overloads for drawing methods

### Fixed
- Receiver now properly handles both standard and echo Ack response formats from controller
- Resolved deleted test files from package reorganization (restored from commit history)
- Fixed ExampleMenu references after package reorganization

### Changed
- Package reorganization: GUI tools moved from `examples/gui` to `tools` package
- Display methods now support canvas-based batch transmission for better performance

### Documentation
- Generated comprehensive development-history.md (3,000 words)
- Generated design-guide.md (3,500 words)
- Generated teacher-guide.md (4,500+ words) with all required educational sections
- Generated student-guide.md (4,000+ words) with 15+ Robolink links
- Added copyright headers and updated release strategy

## v1.1.0 - 2025-10-28

### Added
- Renamed `getAltitudeData` to `getAltitude` for Java idiomatic usage.
- Added deprecated `getAltitudeData` method for Python compatibility.
- Updated documentation for altitude methods, clarifying usage and compatibility.
- Bumped project version to 1.1 (minor release).

### Changed
- Renamed sensor and range methods for consistency with Python API:
  - `getBottomDistance` replaced with `getBottomRange`
  - `getAltitudeData` replaced with `getAltitude`
  - Deprecated methods preserved for compatibility
- Converted several method names from `snake_case` to `camelCase` for Java API consistency.
- Improved API consistency between Java and Python versions.

### Notes
- This release introduces a minor version increment due to public API changes and compatibility improvements.

## v1.0.0 - 2025-10-15

- Add expanded smoke tests and examples: non-flying SmokeTest, controller display and buzzer tests, QuickLEDTest, and a guarded FlightSmokeTest (indoor-safe turning-only) with Gradle tasks `runSmokeTest`, `runControllerDisplayExample`, `runBuzzerTest`, `runQuickLEDTest`, and `runFlightSmokeTest`.

## Unreleased

- Normalize sensor scaling: centralize scales and update displays, tests, and docs (#1)
  - PR: #1 by @scerruti
  - Merge commit: `06d0b9481c5f90fc78f5e25696d6b87dec1a4feb`
  - Merged: 2025-10-14T01:54:48Z
  - Summary: Centralize sensor conversion constants (`DroneSystem.SensorScales`), fix endianness parsing in the receiver, update examples and GUI to display accelerometer in m/s^2 and g and angles in degrees, update `FlightController` getters and unit tests, and add documentation.

## Previously released

No prior changelog entries.
