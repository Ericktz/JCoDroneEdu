# 📦 Release Strategy

## 🎯 Single Release Approach

This project provides **one unified release** with comprehensive library functionality for the educational ecosystem:

### 📚 **JCoDroneEdu Release**
- **Target Audience**: Students and instructors learning/teaching drone programming
- **Content**: Core CoDrone EDU library with testing framework and documentation
- **Distribution**: Maven Central + GitHub releases
- **Maven Coordinates**: `com.otabi:codrone-edu-java:VERSION`
- **Documentation**: Published to [gh-pages](https://scerruti.github.io/JCoDroneEdu/)

## 🚀 Using the Release

### Installation

#### Option 1: Maven/Gradle (Recommended)
```xml
<!-- Maven -->
<dependency>
    <groupId>com.otabi</groupId>
    <artifactId>codrone-edu-java</artifactId>
    <version>1.0.0</version>
</dependency>
```

```kotlin
// Gradle
implementation("com.otabi:codrone-edu-java:1.0.0")
```

#### Option 2: Direct JAR Download
1. Go to [Releases](https://github.com/scerruti/JCoDroneEdu/releases)
2. Download `codrone-edu-java-VERSION.jar`
3. Add to your project classpath

### Testing Framework Included

The library includes MockDrone and DroneTest for comprehensive testing:

```java
// In src/test/java/
import com.otabi.jcodroneedu.DroneTest;

public class SquareFlightTest extends DroneTest {
    @Test
    public void testSquarePattern() {
        executeStudentDroneOperations();
        assertTrue(mockDrone.wasSquarePatternUsed());
    }
}
```

## 📖 Resources Included

Each release includes:
- **Core Library**: Full CoDrone EDU API
- **MockDrone**: Full drone simulation for testing
- **DroneTest**: Base class for creating tests
- **API Documentation**: Complete JavaDoc published to gh-pages
- **Examples**: Runnable demo applications
- **Testing Guide**: Comprehensive testing documentation

## 🔄 Release Process

### Automated Releases

Releases are automated via GitHub Actions when you:
1. **Push a version tag**: `git tag v1.0.0 && git push origin v1.0.0`
2. **Trigger manual release**: Via GitHub Actions UI

The GitHub Actions workflow will automatically:
- Build the JAR and source distributions
- Generate and publish JavaDoc to gh-pages
- Create a GitHub release with artifacts
- Publish to Maven Central (if configured)

### What Gets Published

| Artifact | Maven Central | GitHub Releases | gh-pages |
|----------|---------------|-----------------|----------|
| JAR | ✅ | ✅ | - |
| Sources JAR | ✅ | ✅ | - |
| Javadoc | ✅ | ✅ | ✅ |
| Release Notes | - | ✅ | - |

### Version Strategy
- **Semantic Versioning**: MAJOR.MINOR.PATCH (e.g., 1.0.0, 1.1.0, 2.0.0)
- **SNAPSHOT**: Development versions for pre-release testing
- **Latest**: Always points to the most recent release on gh-pages

## 🎓 Educational Benefits

- **Complete Library**: All functionality in one unified release
- **Testing Framework**: MockDrone for comprehensive assignment validation
- **Professional Practice**: Learn proper dependency management and semantic versioning
- **Full Documentation**: Complete Javadoc for learning API usage
- **GitHub Pages**: Hosted API documentation always available online

## 🛠️ Development Workflow

### Making a Release

1. **Update version** in `build.gradle.kts`
2. **Run final tests**: `./gradlew test`
3. **Update CHANGELOG.md** with new features and fixes
4. **Commit changes**: `git commit -am "Release v1.0.0"`
5. **Create tag**: `git tag v1.0.0`
6. **Push to GitHub**: `git push origin main && git push origin v1.0.0`
7. **GitHub Actions automatically**:
   - Builds the JAR and source distributions
   - Generates JavaDoc
   - Publishes JavaDoc to gh-pages under `/docs/v1.0.0/`
   - Updates `/docs/latest/` symlink
   - Creates GitHub release with artifacts
   - (Optional) Publishes to Maven Central

### Local Testing Before Release

```bash
# Build and test locally
./gradlew clean build

# Test examples
./gradlew runSmokeTest
./gradlew runMultiSensorTest
./gradlew runAltitudePressureTest

# Generate JavaDoc locally
./gradlew javadoc

# View generated docs
open build/docs/javadoc/index.html
```

### JavaDoc Publication

JavaDoc is automatically published to gh-pages with:
- **Versioned docs**: `https://scerruti.github.io/JCoDroneEdu/docs/v1.0.0/`
- **Latest docs**: `https://scerruti.github.io/JCoDroneEdu/docs/latest/` (always points to most recent)
- **Full method index**: Searchable across all releases
- **Educational examples**: JavaDoc includes code samples and best practices
