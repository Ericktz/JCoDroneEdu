# JCoDroneEdu Gradle Build Script Recovery Plan

## Situation
- The build is failing due to unresolved references to Java classes (e.g., `Base64`, `ByteArrayOutputStream`, `LocalDateTime`, `ZipFile`) in `build.gradle.kts`.
- These references are not supported in Gradle Kotlin DSL scripts without special handling.
- The main goal is to ensure the build works, the main jar is named `codrone-edu-java-<version>.jar`, and the project is maintainable and safe for future changes.

## Best Practices Plan

### 1. Restore Build Health
- **Comment out or remove** all custom scripting in `build.gradle.kts` that references Java classes directly (e.g., for signing, changelog, or validation tasks).
- **Keep only** the essential configuration for:
  - Plugins
  - Java toolchain
  - Main jar naming
  - Publishing (using nmcp, maven-publish, signing if possible with Gradle-native DSL)
- **Rebuild** to confirm the project compiles and produces the correct artifacts.

### 2. Refactor Advanced Logic
- **Move complex scripting** (e.g., changelog fetching, Python venv management, artifact validation) into:
  - A custom Gradle plugin (in `buildSrc` or as a separate module)
  - Or, external scripts (e.g., Python, Bash) called from Gradle tasks
- **Use only Gradle/Kotlin-native APIs** in `build.gradle.kts` for maximum compatibility and maintainability.

### 3. Version Control Safety
- **Commit and push** after each successful build step.
- **Avoid force pushes** unless absolutely necessary; prefer `git pull --rebase` and resolve conflicts.
- **Tag** known-good states (e.g., `git tag v1.4.1-good`).

### 4. Documentation & Safety
- **Document** all custom tasks and publishing steps in a `BUILDING.md` or similar file.
- **Add comments** in `build.gradle.kts` explaining any non-standard configuration.
- **Review** all CI/CD scripts to ensure they match the new artifact names and publishing flow.

### 5. Gradle Best Practices
- Use the latest stable Gradle and plugin versions.
- Prefer configuration over scripting in `build.gradle.kts`.
- Use `tasks.named<Jar>("jar") { archiveBaseName.set("codrone-edu-java") }` for jar naming.
- Use the `nmcp` plugin for Maven Central publishing, but only with supported Gradle/Kotlin DSL features.

## Next Steps
1. Backup your current `build.gradle.kts`.
2. Comment out or remove all problematic scripting blocks.
3. Rebuild and verify artifact naming and publishing.
4. Gradually reintroduce advanced features via plugins or external scripts.
5. Update documentation and CI/CD as needed.

---

**If you need step-by-step code edits or want to see a minimal working `build.gradle.kts`, just ask.**

Stay safe and take breaks as needed. You can recover this build!
