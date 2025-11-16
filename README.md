# CoDrone EDU Java API

A concise, classroom-focused Java library for programming the CoDrone EDU hardware. This repository contains the Java API (package `com.otabi.jcodroneedu`), example programs, documentation, and build tooling used to produce student and teacher JARs.

## Summary

CoDrone EDU Java is intended for educators and students. It exposes a simple, well-documented API to control the drone (flight, sensors, LEDs, buzzer), includes classroom-ready examples, and provides teacher utilities in a separate teacher JAR. The project aims for behavioral parity with the reference Python API while adapting to Java idioms and safety.

---

## 🎯 Project Vision

The CoDrone EDU Java API aims to provide educators and students with a robust, classroom-ready programming interface that matches the functionality and ease of use of the official Python API while leveraging Java's educational benefits for AP Computer Science A and university-level courses.

## 🧪 Testing & Quality Assurance

The project maintains high code quality standards with:
- **Comprehensive Test Coverage**: 134+ automated test cases
- **Educational Focus Testing**: Validates classroom usability and student experience
- **Python API Compatibility**: Ensures method parity and behavior consistency
- **Continuous Integration**: Automated testing on code changes

## 📚 Educational Use Cases

This API will support:

### **K-12 STEM Education**
- Introduction to programming concepts through drone control
- Physics demonstrations (motion, acceleration, color theory)
- Problem-solving through robotics challenges

### **AP Computer Science A**
- Object-oriented programming with real hardware
- Array and data structure manipulation with sensor data

# CoDrone EDU Java API

🎓 Educational Java API for CoDrone EDU — a classroom-focused Java library for teaching drone programming, aligned with common K–12 and AP Computer Science A learning objectives.

## Quick overview
CoDrone EDU Java provides a simple, well-documented interface to fly and program CoDrone EDU hardware from Java. The library exposes core flight control, basic sensors, LED control, and curated student examples so classrooms and instructors can teach programming and robotics with hands-on activities.

Artifacts produced by the build:
- Main JAR (core library for classroom/student use)
- Sources JAR and Javadoc JAR
- Published to Maven Central for easy dependency management

## Development status (classroom-ready)
The CoDrone EDU Java API is classroom-ready for student and educator use. Core flight controls, sensor access, LED control, and the student examples are implemented and tested. We continue to refine advanced features and documentation; check the release notes for details.

## Latest Release: v1.3.1

Latest release now available on Maven Central! This release includes simplified Maven publication for easier integration with Gradle and Maven-based student projects.

Highlights:
- Core flight controls: user-friendly movement commands for classroom exercises.
- Sensors: accelerometer, gyroscope, distance sensors, and camera/vision APIs.
- LED control: full support for drone and controller LEDs, example patterns, and guided activities.
- Student examples: curated example projects suitable for K–12 and AP Computer Science lessons.
- Published on Maven Central: easy dependency integration for Gradle and Maven projects.

For past releases and detailed changelog, see [CHANGELOG.md](CHANGELOG.md).

## Why use the Java API
- Designed for educators: simple, consistent APIs and examples aligned to classroom exercises.
- AP CSA-friendly: encourages object-oriented thinking and standard Java programming patterns.
- Portable: packaged JARs, sources, and Javadoc make it easy to integrate into existing Java curricula.

## ⚙️ API Compatibility

This Java API maintains behavioral parity with the official CoDrone EDU Python API. The current release is compatible with:

| Component | Version |
|-----------|---------|
| **Java Runtime** | 21+ |
| **Python API** | 2.6.0+ |
| **CoDrone EDU Firmware** | 25.2.1+ |

### Version Alignment

- Java API v1.3.0 → Python API v2.6.0 (target compatibility)
- Naming conventions adapted for Java idioms (camelCase methods, Java-style class structure)
- Method naming follows Java conventions while maintaining semantic equivalence with Python
- See [API_COMPARISON_vs_2.6.md](API_COMPARISON_vs_2.6.md) for detailed method mapping with latest version
- Legacy comparisons available: [API_COMPARISON_2.3.md](API_COMPARISON_2.3.md), [API_COMPARISON_2.5.md](API_COMPARISON_2.5.md)

### Checking Compatibility

To verify your environment supports this library:

```bash
# Check Java version
java -version  # Requires 21 or higher

# Check Python compatibility
pip show codrone-edu  # Should be v2.6.0 or compatible version
```

### Reporting Incompatibilities

If you encounter compatibility issues:
1. Check the [Compatibility Matrix](#-api-compatibility) above
2. Review [API_COMPARISON_vs_2.6.md](API_COMPARISON_vs_2.6.md) for method availability
3. Open an issue with your Java/Python versions and the specific problem

## Getting started (student)

### Adding to your project

The library is published to Maven Central. Add it to your project:

**Gradle:**
```gradle
dependencies {
    implementation 'com.otabi:codrone-edu-java:1.3.1'
}
```

**Maven:**
```xml
<dependency>
  <groupId>com.otabi</groupId>
  <artifactId>codrone-edu-java</artifactId>
  <version>1.3.1</version>
</dependency>
```

### Running examples

1. See the `src/main/java/com/otabi/jcodroneedu/examples` directory for hands-on sample programs and lesson starters.
2. API documentation (Javadoc) is included with each Maven Central release.
3. For detailed implementation patterns, see [docs/LOGGING_GUIDE.md](docs/LOGGING_GUIDE.md)

## Teacher edition
The teacher edition JAR includes extra utilities for instructors such as testing helpers and test harnesses used in classroom assessments. The teacher JAR is distributed via the Release assets for instructors and course maintainers.

## Documentation & examples
- **API documentation (Javadoc)**: Included in Maven Central releases and available via the `-javadoc.jar` artifact
- **Example programs**: See `src/main/java/com/otabi/jcodroneedu/examples` for hands-on sample projects
- **Implementation guides**: 
  - [Logging Guide](docs/LOGGING_GUIDE.md) - Using the logging framework
  - [Teacher's Guide: GitHub Copilot for Test Creation](TEACHER_COPILOT_GUIDE.md) - AI-assisted test development
- **Implementation notes and design documents**: See the `docs/` and `reference/` directories

## Testing & quality
- We run automated tests as part of the build pipeline and maintain a suite of unit tests focused on classroom behaviors.
- Before adopting the library for production curricula, instructors should run the examples and tests in their environment to verify behavior with their hardware.

## Installing and building locally (developer)
To build artifacts locally:
```bash
# Build everything and run tests
./gradlew build

# Generate sources and javadoc JARs
./gradlew sourcesJar javadocJar
```

To publish to Maven Local (for local testing):
```bash
./gradlew publishMavenJavaPublicationToMavenLocal -Pversion=1.4.0-SNAPSHOT
```

## Contributing
We welcome contributions once the core API is stabilized. For now:
- Open issues for bugs or feature requests.
- Contribute educational examples or documentation improvements.
- If you'd like to help with teacher tooling or advanced features, open an issue and we’ll coordinate.

## License
This project is available under the MIT License. See the `LICENSE` file in the repository for details.

## Changelog & releases
See [CHANGELOG.md](CHANGELOG.md) for past release notes. Visit the [GitHub Releases page](https://github.com/scerruti/JCoDroneEdu/releases) for packaged artifacts and Javadoc.

The latest release is automatically published to [Maven Central](https://central.sonatype.com/artifact/com.otabi/codrone-edu-java).

## Contact & support
For timeline questions, classroom integration, or bug reports, open an issue on this repository. For private instructor access to teacher tooling, check the release assets or contact the maintainers through the project issues.
