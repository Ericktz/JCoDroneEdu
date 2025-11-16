# 📋 Release Checklist & Procedures

## Timeline: When to Do What

This document clarifies the **exact sequence** and **timing** for all release tasks.

---

## Phase 1: Pre-Release Development (Before Release Branch)

### 1.1 Update `@since` Annotations on New Methods
**When**: As you add/modify public API methods during development  
**How**: Add `@since VERSION` JavaDoc tag to each new public method

```java
/**
 * Retrieves drone temperature in Celsius.
 * 
 * @return temperature in degrees Celsius
 * @since 1.0.0
 */
public double getDroneTemperature() {
    // implementation
}
```

**Why**: So developers know which release introduced each method
**Note**: Use the **upcoming** version number (e.g., if next release is 1.1.0, use `@since 1.1.0`)

---

## Phase 2: Release Preparation (Before Tag Creation)

### 2.1 Update Copyright Year (If MIT License)
**When**: At the start of release preparation  
**What**: Update year in all source files if year has changed  
**Example**:
```
Copyright (c) 2024-2025 Otabi  
Licensed under the MIT License
```

**Which files**:
- [ ] All `.java` files in `src/main/java/` - add copyright header
- [ ] All `.java` files in `src/test/java/` - add copyright header
- [ ] `LICENSE` file - update year range
- [ ] `README.md` - update if it contains copyright

**Format** (add to top of each file):
```java
/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * See LICENSE file in project root
 */
package com.otabi.jcodroneedu;
```

**Tool**: You can create a Gradle task to automate this

### 2.2 Update Version Number
**When**: After copyright updates  
**Where**: `build.gradle.kts`

```kotlin
version = "1.1.0"  // Update this
```

**Versioning Strategy** (Semantic Versioning):
- `MAJOR.MINOR.PATCH` (e.g., 1.0.0, 1.1.0, 2.0.0)
- **MAJOR**: Breaking changes
- **MINOR**: New features (backward compatible)
- **PATCH**: Bug fixes (backward compatible)

### 2.3 Update CHANGELOG.md
**When**: Before creating the release tag  
**What**: Add entry for this release at the TOP

**Format**:
```markdown
# Changelog

## [1.1.0] - 2025-11-16

### Added
- New method: `getDroneTemperature()` for temperature in Celsius
- New method: `getCalibratedTemperature()` for ambient temperature estimation
- MockDrone support for temperature sensor simulation

### Changed
- Improved altitude calibration accuracy (±2m)
- Updated Python reference library to v2.4

### Fixed
- Fixed optical flow reset issue after firmware update
- Fixed color sensor inconsistency

### Deprecated
- `getTemperature()` → use `getDroneTemperature()` instead

## [1.0.0] - 2025-10-15
- Initial release
```

**Include**:
- [ ] All new features (with method names)
- [ ] Bug fixes
- [ ] Breaking changes (if any)
- [ ] Deprecations
- [ ] Firmware compatibility notes
- [ ] Known issues

### 2.4 Update README.md
**When**: Same time as CHANGELOG.md  
**What**: Update any version-specific information

- [ ] Update installation instructions with new version
- [ ] Update firmware compatibility section
- [ ] Update any deprecated method references
- [ ] Update feature list if new major features added

### 2.5 Verify All Tests Pass
**When**: Before committing version changes

```bash
./gradlew clean test
```

- [ ] All unit tests pass
- [ ] No compilation warnings
- [ ] Javadoc generates without errors

---

## Phase 3: Commit & Tag (Create the Release)

### 3.1 Commit Version Changes
**When**: After all documentation is updated  
**Command**:
```bash
git add build.gradle.kts CHANGELOG.md README.md src/
git commit -m "Release v1.1.0"
```

**What's included**:
- Version number change
- Copyright year updates
- CHANGELOG.md updates
- README.md updates
- `@since` annotations on new methods

### 3.2 Create Release Tag
**When**: Immediately after commit  
**Command**:
```bash
git tag v1.1.0
```

**Format**: 
- Must start with `v` (e.g., `v1.1.0`, not `1.1.0`)
- Must exactly match version in `build.gradle.kts`
- This triggers the GitHub Actions release workflow

### 3.3 Push to GitHub
**When**: Immediately after tag creation  
**Command**:
```bash
git push origin main
git push origin v1.1.0
```

---

## Phase 4: Automated Release (GitHub Actions)

**When**: Automatically triggered by tag push  
**What GitHub Actions Does**:

1. **Builds artifacts**:
   - `codrone-edu-java-1.1.0.jar`
   - `codrone-edu-java-1.1.0-sources.jar`
   - `codrone-edu-java-1.1.0-javadoc.jar`

2. **Generates and publishes JavaDoc**:
   - Extracts javadoc from JAR
   - Publishes to `https://scerruti.github.io/JCoDroneEdu/docs/v1.1.0/`
   - Updates `/docs/latest/` symlink

3. **Creates GitHub Release**:
   - Title: `CoDrone EDU Java v1.1.0`
   - Body: Pre-filled template with links to docs
   - Attaches artifacts (JAR, sources, javadoc)

4. **Publishes to Maven Central**:
   - Uploads signed artifacts
   - Makes available at `com.otabi:codrone-edu-java:1.1.0`

### 4.1 Create Release Notes (AFTER Tag)
**When**: After tag is pushed and GitHub Actions starts  
**Where**: GitHub Release page (auto-created)  
**What to do**:

1. Go to [Releases](https://github.com/scerruti/JCoDroneEdu/releases)
2. Find the auto-created release for your tag
3. Edit the release notes body
4. Copy content from `CHANGELOG.md` for this version
5. Add any additional notes specific to release

**Template** (in GitHub Release body):
```markdown
## 📚 CoDrone EDU Java v1.1.0

### 📖 API Documentation
[View Full API Docs](https://scerruti.github.io/JCoDroneEdu/docs/v1.1.0/)

### 📦 Installation

#### Maven
```xml
<dependency>
    <groupId>com.otabi</groupId>
    <artifactId>codrone-edu-java</artifactId>
    <version>1.1.0</version>
</dependency>
```

#### Gradle
```kotlin
implementation("com.otabi:codrone-edu-java:1.1.0")
```

### ✨ What's New
- Feature 1
- Feature 2
- Bug fix 1

### 🔗 Resources
- [API Documentation](https://scerruti.github.io/JCoDroneEdu/docs/v1.1.0/)
- [CHANGELOG](./CHANGELOG.md)
- [Repository](https://github.com/scerruti/JCoDroneEdu)
```

---

## Summary: The Complete Release Process

```
┌─────────────────────────────────────────────────────────────┐
│ PHASE 1: DEVELOPMENT                                        │
├─────────────────────────────────────────────────────────────┤
│ ✓ Add @since VERSION to new public methods                  │
│ ✓ Write tests for new functionality                         │
│ ✓ Update MockDrone as needed                                │
└─────────────────────────────────────────────────────────────┘
                           ↓
┌─────────────────────────────────────────────────────────────┐
│ PHASE 2: PREPARE RELEASE                                    │
├─────────────────────────────────────────────────────────────┤
│ ✓ Update copyright year in all files (if needed)            │
│ ✓ Update version in build.gradle.kts                        │
│ ✓ Update CHANGELOG.md with all changes                      │
│ ✓ Update README.md with new version info                    │
│ ✓ Run full test suite                                       │
│ ✓ Commit all changes: git commit -m "Release v1.1.0"       │
└─────────────────────────────────────────────────────────────┘
                           ↓
┌─────────────────────────────────────────────────────────────┐
│ PHASE 3: CREATE RELEASE                                     │
├─────────────────────────────────────────────────────────────┤
│ ✓ Create tag: git tag v1.1.0                               │
│ ✓ Push to GitHub: git push origin main && git push v1.1.0  │
└─────────────────────────────────────────────────────────────┘
                           ↓
┌─────────────────────────────────────────────────────────────┐
│ PHASE 4: GITHUB ACTIONS (AUTOMATIC)                         │
├─────────────────────────────────────────────────────────────┤
│ ✓ Builds JAR, sources, javadoc                              │
│ ✓ Publishes JavaDoc to gh-pages                             │
│ ✓ Creates GitHub Release (auto-filled)                      │
│ ✓ Publishes to Maven Central                                │
└─────────────────────────────────────────────────────────────┘
                           ↓
┌─────────────────────────────────────────────────────────────┐
│ PHASE 5: FINALIZE RELEASE                                   │
├─────────────────────────────────────────────────────────────┤
│ ✓ Edit GitHub Release notes (add CHANGELOG content)         │
│ ✓ Verify Maven Central publication (takes ~10min)           │
│ ✓ Test Maven dependency: mvn clean install                  │
│ ✓ Announce release on relevant channels                     │
└─────────────────────────────────────────────────────────────┘
```

---

## @since Annotation Rules

### When to Add `@since`
- ✅ New public methods (first appearance in release)
- ✅ New public classes
- ✅ Significant API changes to public methods

### When NOT to Add `@since`
- ❌ Bug fixes (method already existed)
- ❌ Internal/private methods
- ❌ Documentation-only changes

### Example Timeline
```
v1.0.0 release:
  getDroneTemperature() - @since 1.0.0

v1.0.1 release (bug fix):
  getDroneTemperature() - @since 1.0.0 (unchanged)

v1.1.0 release (new method):
  getCalibratedTemperature() - @since 1.1.0
  getDroneTemperature() - @since 1.0.0 (unchanged)

v2.0.0 release (breaking change):
  getDroneTemperature() - @since 1.0.0, Modified in 2.0.0
```

---

## Copyright Notice Strategy (MIT License)

### When to Update
- [ ] At the start of each calendar year (if not already done)
- [ ] When releasing in a new year

### What to Update
- [ ] All Java source files (`.java`)
- [ ] Test files
- [ ] Build scripts
- [ ] LICENSE file
- [ ] README.md

### MIT License Header Format
```
/*
 * CoDrone EDU Java Library
 * Copyright (c) 2024-2025 Stephen P. Cerruti
 * Licensed under the MIT License
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * See LICENSE file for full license text.
 */
```

### Automated Copyright Update (Gradle Task - Optional)
You could add a task to automate this:

```kotlin
tasks.register("updateCopyright") {
    group = "maintenance"
    description = "Updates copyright year in all source files"
    
    doLast {
        val currentYear = LocalDateTime.now().year
        val pattern = Regex("""Copyright \(c\) (\d{4})-?(\d{4})?""")
        val replacement = "Copyright (c) 2024-$currentYear"
        
        fileTree("src") {
            include("**/*.java")
        }.forEach { file ->
            file.writeText(file.readText().replace(pattern, replacement))
        }
        
        println("✅ Updated copyright year to $currentYear")
    }
}
```

---

## Quick Reference: Release Day Steps

```bash
# 1. Update version and documentation
vim build.gradle.kts          # Update version
vim CHANGELOG.md              # Add release notes
vim README.md                 # Update version references
vim src/**/*.java             # Update @since on new methods
git add . && git commit -m "Release v1.1.0"

# 2. Create and push release
git tag v1.1.0
git push origin main && git push origin v1.1.0

# 3. Wait for GitHub Actions (2-5 minutes)
# - Check GitHub Actions tab for workflow status
# - Verify JavaDoc published to gh-pages
# - Verify release created in Releases tab

# 4. Finalize release
# - Edit GitHub Release body with CHANGELOG content
# - Wait ~10 minutes for Maven Central publication
# - Test: mvn dependency:get -Dartifact=com.otabi:codrone-edu-java:1.1.0

# 5. Verify everything
curl https://repo1.maven.org/maven2/com/otabi/codrone-edu-java/1.1.0/
# Should return 200 OK with artifacts listed
```

---

## Troubleshooting

### Release Not Appearing in Maven Central
- Wait 10-15 minutes (synchronization delay)
- Check GitHub Actions workflow completed successfully
- Verify signing credentials are configured

### JavaDoc Not Published to gh-pages
- Check GitHub Actions workflow for `Publish JavaDoc to gh-pages` step
- Verify `pages: write` permission in workflow
- Check `https://scerruti.github.io/JCoDroneEdu/docs/v1.1.0/` directly

### Tag Already Exists
```bash
# Delete local tag
git tag -d v1.1.0

# Delete remote tag
git push origin --delete v1.1.0

# Recreate and push
git tag v1.1.0
git push origin v1.1.0
```

---

**Last Updated**: November 16, 2025  
**License**: MIT (as per project)
