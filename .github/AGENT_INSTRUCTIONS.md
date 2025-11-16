# Agent Instructions for JCoDroneEdu

This document provides guidance for coding agents working on JCoDroneEdu issues.

## Workflow Overview

When you are assigned an issue, GitHub Actions will automatically:
1. Create a feature or bugfix branch (based on issue labels)
2. Bump the version number appropriately
3. Post a comment on the issue with instructions

**You will receive a comment on the issue with the branch name and next steps.**

---

## Step-by-Step Development Process

### 1. Check Out the Prepared Branch

When assigned to an issue, look for a comment posted by `github-actions[bot]` containing:
- The branch name (e.g., `feature/issue-38-release-automations`)
- The new version (e.g., `1.4.0-SNAPSHOT`)

Checkout the branch:
```bash
git fetch origin <branch-name>
git checkout <branch-name>
```

Example:
```bash
git fetch origin feature/issue-38-release-automations
git checkout feature/issue-38-release-automations
```

### 2. Understand the Version Bump

The version has already been updated in the branch:

| Branch Type | Version Change | Example |
|-------------|----------------|---------|
| `feature/*` | Minor bump | `1.3.0` → `1.4.0-SNAPSHOT` |
| `bugfix/*` | Patch bump | `1.3.0` → `1.3.1-SNAPSHOT` |

**Important**: Do NOT manually change the version. It's already set correctly.

### 3. Implement the Feature/Fix

Develop your solution following the project conventions:

#### For New Public Methods:
- Add `@since <VERSION>` JavaDoc tag with the version from the branch (e.g., `@since 1.4.0`)
- Add comprehensive JavaDoc with examples
- Add unit tests

```java
/**
 * Retrieves drone temperature in Celsius.
 * 
 * @return temperature in degrees Celsius
 * @since 1.4.0
 */
public double getDroneTemperature() {
    // implementation
}
```

#### For Bug Fixes:
- Reference the issue in your commit: `Fixes #42`
- Update CHANGELOG.md if it's a significant fix
- Add regression tests

#### For Features:
- Update CHANGELOG.md with the new feature
- Add examples or demo code if appropriate
- Update README.md if relevant

### 4. Update CHANGELOG.md (if needed)

For significant changes, add an entry under the `## Unreleased` section:

```markdown
## Unreleased

### Added
- New method: `validateSinceTags()` for @since annotation validation

### Fixed
- Fixed altitude offset calculation in elevation API

### Changed
- Refactored display protocol handling for better performance
```

The exact version will be set during release.

### 5. Commit Guidelines

Write clear, concise commit messages:
```bash
git commit -m "Add validateSinceTags Gradle task for @since annotation checking"
git commit -m "Fix altitude offset calculation - closes #42"
git commit -m "Add comprehensive JavaDoc examples to Drone class"
```

**Always include issue reference if fixing a specific issue:**
```bash
git commit -m "Implement feature: closes #38"
```

### 6. Pre-Release Validation (Before Opening PR)

Run the validation tasks to catch issues early:

```bash
# Run all validations
./gradlew preReleaseCheck

# Or run individual checks
./gradlew validateSinceTags
./gradlew validateVersionConsistency
./gradlew validateChangelog
./gradlew test
```

**Address any failures before opening a PR.**

### 7. Push and Open a Pull Request

Push your branch:
```bash
git push origin <branch-name>
```

Open a PR on GitHub with:
- **Title**: Clear description of changes (e.g., "Add missing release automation tasks")
- **Description**: 
  - Reference the issue: "Closes #38"
  - Summary of changes
  - Testing performed
  - Any breaking changes (if applicable)

Example PR description:
```
Closes #38

## Summary
Implements 7 missing release automation tasks to reduce manual errors.

## Changes
- Added `updateCopyright` Gradle task
- Added `validateSinceTags` task
- Added `validateChangelog` task
- Added `validateVersionConsistency` task

## Testing
- All new tasks tested independently
- Integrated into preReleaseCheck
- Existing tests still pass

## Notes
No breaking changes. All tasks are backward compatible.
```

---

## Important Conventions

### Code Style
- Follow existing project code style
- Use meaningful variable/method names
- Add comments for complex logic
- Keep lines under 100 characters where practical

### Testing
- Write tests for new functionality
- Ensure all tests pass: `./gradlew test`
- Test with real scenarios when applicable

### Documentation
- Add JavaDoc to all public methods
- Include `@since` tags for new public APIs
- Add examples to complex methods
- Update README.md if adding major features

### Git Practices
- One feature per commit (logically)
- Use conventional commit format when possible
- Reference issues: `Closes #38`
- Squash trivial commits before PR

---

## Build and Test Commands

```bash
# Run all tests
./gradlew test

# Run specific test class
./gradlew test --tests ClassName

# Build the project
./gradlew build

# Run release validation
./gradlew preReleaseCheck

# Generate JavaDoc
./gradlew javadoc

# Run specific example/integration test
./gradlew runSmokeTest
./gradlew runMultiSensorTest
```

---

## Release Automation Tasks

These tasks are used during the release process. You may encounter them:

| Task | Purpose |
|------|---------|
| `updateCopyright` | Updates copyright headers in source files |
| `validateSinceTags` | Checks all public methods have `@since` tags |
| `validateChangelog` | Validates CHANGELOG.md format and content |
| `validateVersionConsistency` | Ensures version consistency across files |
| `validateArtifacts` | Verifies JAR artifacts before publishing |
| `checkDeprecations` | Warns about long-deprecated methods |
| `preReleaseCheck` | Runs all validation tasks |

---

## Common Issues and Solutions

### Issue: "Branch already exists"
The branch may have been created in a previous attempt.
```bash
git fetch origin
git checkout <branch-name>
git pull origin <branch-name>
```

### Issue: Version shows as SNAPSHOT in your code
This is correct! SNAPSHOT indicates development version. It will be removed during release.
```gradle
// ✅ Correct during development
version = "1.4.0-SNAPSHOT"

// ❌ This becomes the release version
version = "1.4.0"
```

### Issue: Validation tasks failing
Check the error output carefully. Common issues:
- Missing `@since` tags on new public methods
- CHANGELOG.md format incorrect
- Version inconsistency between files

Run individual tasks to debug:
```bash
./gradlew validateSinceTags --info
```

---

## Questions or Issues?

If you encounter problems:
1. Check this file for guidance
2. Review the GitHub issue for context
3. Check the RELEASE_CHECKLIST.md for more details
4. Look at recent commits for examples

---

## Summary: Your Workflow

```
1. Get assigned to issue → Receive GitHub comment
2. Checkout prepared branch with version bump
3. Implement feature/fix
4. Update CHANGELOG.md if needed
5. Run: ./gradlew preReleaseCheck
6. Commit and push
7. Open PR with clear description
8. Request review
```

**That's it!** The automation handles the rest.

---

**Last Updated**: November 16, 2025  
**Version**: 1.0
