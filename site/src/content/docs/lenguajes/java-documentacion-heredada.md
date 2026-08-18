---
title: Archivo documental Java
description: Índice completo de la documentación Markdown heredada de JCoDroneEdu.
---

<!-- Archivo generado por site/scripts/generate-java-docs-catalog.mjs. -->

Este catálogo expone **84 documentos Markdown** técnicos que antes
solo podían encontrarse recorriendo el repositorio. Incluye los Markdown de la
raíz del proyecto Java y cinco referencias técnicas ubicadas en subdirectorios.

:::caution[Documento heredado no significa especificación vigente]
Los reportes registran decisiones, experimentos y estados de distintas fechas.
Antes de aplicar una instrucción se debe contrastar con el código actual, las
pruebas, los ADR de CoDrone EDU EIT y la documentación oficial vigente.
:::

## Principios recuperados para CoDrone EDU EIT

La revisión del archivo permite conservar estas reglas útiles:

- implementar primero la semántica oficial Python y documentar cualquier mejora
  específica de Java sin falsear la equivalencia;
- mantener <code>@pythonEquivalent</code> y <code>@pythonReference</code> solo
  cuando exista una equivalencia comprobada y un enlace oficial vigente; los
  métodos exclusivamente Java no deben forzarse dentro de esa matriz;
- ofrecer, cuando corresponda, acceso compatible, getters simples y objetos Java
  tipados, explicando cuál alternativa es recomendable;
- guiar con JavaDoc y enlaces <code>@see</code> en vez de marcar como obsoleta una API que se
  conserva deliberadamente por compatibilidad docente;
- ejecutar pruebas con <code>MockDrone</code> antes de cualquier prueba con hardware;
- tratar las fechas internas y la evidencia del código como más confiables que un
  estado histórico escrito en un reporte;
- validar versiones, porcentajes de paridad, URLs y procedimientos de publicación
  antes de reutilizarlos en el fork universitario.

Los puntos de entrada más útiles son [Knowledge Index](https://github.com/Ericktz/JCoDroneEdu/blob/main/KNOWLEDGE_INDEX.md),
[API Design Philosophy](https://github.com/Ericktz/JCoDroneEdu/blob/main/API_DESIGN_PHILOSOPHY.md),
[Testing Guide](https://github.com/Ericktz/JCoDroneEdu/blob/main/TESTING_GUIDE.md),
[Best Practice Guidance](https://github.com/Ericktz/JCoDroneEdu/blob/main/BEST_PRACTICE_GUIDANCE.md) y
[Agent Decisions Log](https://github.com/Ericktz/JCoDroneEdu/blob/main/AGENT_DECISIONS_LOG.md).

## Cómo se mantiene

La página se genera mediante <code>npm run catalog:java</code>. El build comprueba que esté
sincronizada; si se agrega, elimina o renombra un Markdown técnico, el catálogo
debe regenerarse y revisarse en el mismo PR.

## Gobierno, arquitectura y conocimiento (11)

| Documento | Archivo | Tipo |
| --- | --- | --- |
| [Agent Decisions Log](https://github.com/Ericktz/JCoDroneEdu/blob/main/AGENT_DECISIONS_LOG.md) | `AGENT_DECISIONS_LOG.md` | Instrucciones históricas |
| [Comprehensive Documentation System - Agent Instructions](https://github.com/Ericktz/JCoDroneEdu/blob/main/AGENT_INSTRUCTIONS_DOCUMENTATION_SYSTEM.md) | `AGENT_INSTRUCTIONS_DOCUMENTATION_SYSTEM.md` | Instrucciones históricas |
| [AGENT INSTRUCTIONS: Add @pythonEquivalent and @pythonReference Annotations](https://github.com/Ericktz/JCoDroneEdu/blob/main/AGENT_INSTRUCTIONS_PYTHON_EQUIVALENT.md) | `AGENT_INSTRUCTIONS_PYTHON_EQUIVALENT.md` | Instrucciones históricas |
| [Agent Project: Comprehensive Documentation System](https://github.com/Ericktz/JCoDroneEdu/blob/main/AGENT_PROJECT_DOCUMENTATION_SYSTEM.md) | `AGENT_PROJECT_DOCUMENTATION_SYSTEM.md` | Instrucciones históricas |
| [JCoDroneEdu API Design Philosophy](https://github.com/Ericktz/JCoDroneEdu/blob/main/API_DESIGN_PHILOSOPHY.md) | `API_DESIGN_PHILOSOPHY.md` | Diseño o arquitectura |
| [CoDrone EDU Java Library - Design Guide](https://github.com/Ericktz/JCoDroneEdu/blob/main/design-guide.md) | `design-guide.md` | Guía o referencia |
| [CoDrone EDU Java Library - Development History](https://github.com/Ericktz/JCoDroneEdu/blob/main/development-history.md) | `development-history.md` | Historial o versión |
| [Documentation Generation Project - COMPLETE](https://github.com/Ericktz/JCoDroneEdu/blob/main/DOCUMENTATION_GENERATION_COMPLETE.md) | `DOCUMENTATION_GENERATION_COMPLETE.md` | Reporte de implementación |
| [Documentation Generation Project - Quick Reference](https://github.com/Ericktz/JCoDroneEdu/blob/main/DOCUMENTATION_PROJECT_QUICK_REFERENCE.md) | `DOCUMENTATION_PROJECT_QUICK_REFERENCE.md` | Guía o referencia |
| [Knowledge Index - CoDroneEdu Documentation](https://github.com/Ericktz/JCoDroneEdu/blob/main/KNOWLEDGE_INDEX.md) | `KNOWLEDGE_INDEX.md` | Índice o metadocumentación |
| [Task Division: Pre-Release Validation](https://github.com/Ericktz/JCoDroneEdu/blob/main/TASK_DIVISION.md) | `TASK_DIVISION.md` | Instrucciones históricas |

## API y compatibilidad Python ↔ Java (19)

| Documento | Archivo | Tipo |
| --- | --- | --- |
| [CoDrone EDU Java API: AP CSA Compliant Features](https://github.com/Ericktz/JCoDroneEdu/blob/main/APCSA_COMPLIANT_API_DOCUMENTATION.md) | `APCSA_COMPLIANT_API_DOCUMENTATION.md` | Nota técnica |
| [API Comparison Summary](https://github.com/Ericktz/JCoDroneEdu/blob/main/API_COMPARISON_SUMMARY.md) | `API_COMPARISON_SUMMARY.md` | Nota técnica |
| [API Comparison Report](https://github.com/Ericktz/JCoDroneEdu/blob/main/API_COMPARISON_vs_2.6.md) | `API_COMPARISON_vs_2.6.md` | Nota técnica |
| [API Comparison Report](https://github.com/Ericktz/JCoDroneEdu/blob/main/API_COMPARISON.md) | `API_COMPARISON.md` | Nota técnica |
| [Best Practice Guidance for Inventory Methods](https://github.com/Ericktz/JCoDroneEdu/blob/main/BEST_PRACTICE_GUIDANCE.md) | `BEST_PRACTICE_GUIDANCE.md` | Guía o referencia |
| [CoDrone EDU Method Implementation Tracking](https://github.com/Ericktz/JCoDroneEdu/blob/main/CODRONE_EDU_METHOD_TRACKING.md) | `CODRONE_EDU_METHOD_TRACKING.md` | Nota técnica |
| [Deprecation Analysis Report - JCoDroneEdu v1.3.0](https://github.com/Ericktz/JCoDroneEdu/blob/main/DEPRECATION_REPORT.md) | `DEPRECATION_REPORT.md` | Auditoría o investigación |
| [Elevation API Implementation](https://github.com/Ericktz/JCoDroneEdu/blob/main/ELEVATION_API_IMPLEMENTATION.md) | `ELEVATION_API_IMPLEMENTATION.md` | Reporte de implementación |
| [Elevation API - Quick Reference Card](https://github.com/Ericktz/JCoDroneEdu/blob/main/ELEVATION_API_QUICK_REFERENCE.md) | `ELEVATION_API_QUICK_REFERENCE.md` | Guía o referencia |
| [Error Data API Summary](https://github.com/Ericktz/JCoDroneEdu/blob/main/ERROR_DATA_API_SUMMARY.md) | `ERROR_DATA_API_SUMMARY.md` | Nota técnica |
| [Java-to-Python Alignment Assessment](https://github.com/Ericktz/JCoDroneEdu/blob/main/JAVA_TO_PYTHON_ALIGNMENT_ASSESSMENT.md) | `JAVA_TO_PYTHON_ALIGNMENT_ASSESSMENT.md` | Auditoría o investigación |
| [Java-to-Python Alignment: Audit Complete ✅](https://github.com/Ericktz/JCoDroneEdu/blob/main/JAVA_TO_PYTHON_ALIGNMENT_COMPLETE.md) | `JAVA_TO_PYTHON_ALIGNMENT_COMPLETE.md` | Reporte de implementación |
| [CoDrone EDU Java API: Non-AP CSA Features Documentation](https://github.com/Ericktz/JCoDroneEdu/blob/main/NON_APCSA_API_DOCUMENTATION.md) | `NON_APCSA_API_DOCUMENTATION.md` | Nota técnica |
| [@pythonEquivalent Annotation Audit](https://github.com/Ericktz/JCoDroneEdu/blob/main/PYTHON_EQUIVALENT_AUDIT.md) | `PYTHON_EQUIVALENT_AUDIT.md` | Auditoría o investigación |
| [CoDrone EDU Python Library Management](https://github.com/Ericktz/JCoDroneEdu/blob/main/PYTHON_MANAGEMENT.md) | `PYTHON_MANAGEMENT.md` | Nota técnica |
| [Phase 4: Python-to-Java Logging Audit](https://github.com/Ericktz/JCoDroneEdu/blob/main/PYTHON_TO_JAVA_AUDIT.md) | `PYTHON_TO_JAVA_AUDIT.md` | Auditoría o investigación |
| [Python-to-Java Logging Audit (v2.3)](https://github.com/Ericktz/JCoDroneEdu/blob/main/PYTHON_TO_JAVA_LOGGING_AUDIT_V2_3.md) | `PYTHON_TO_JAVA_LOGGING_AUDIT_V2_3.md` | Auditoría o investigación |
| [CoDrone EDU Java API: Teacher Quick Reference Guide](https://github.com/Ericktz/JCoDroneEdu/blob/main/TEACHER_API_QUICK_REFERENCE.md) | `TEACHER_API_QUICK_REFERENCE.md` | Guía o referencia |
| [Temperature API Enhancement - Implementation Complete ✅](https://github.com/Ericktz/JCoDroneEdu/blob/main/TEMPERATURE_API_ENHANCEMENT.md) | `TEMPERATURE_API_ENHANCEMENT.md` | Reporte de implementación |

## Sensores, telemetría y calibración (16)

| Documento | Archivo | Tipo |
| --- | --- | --- |
| [Python vs Java Altitude/Height/Pressure API Audit](https://github.com/Ericktz/JCoDroneEdu/blob/main/ALTITUDE_HEIGHT_AUDIT.md) | `ALTITUDE_HEIGHT_AUDIT.md` | Auditoría o investigación |
| [`getAutomaticElevation()` – Classroom Activity Design](https://github.com/Ericktz/JCoDroneEdu/blob/main/AUTOMATIC_ELEVATION_ACTIVITY.md) | `AUTOMATIC_ELEVATION_ACTIVITY.md` | Nota técnica |
| [Automatic Elevation Detection - Implementation Summary](https://github.com/Ericktz/JCoDroneEdu/blob/main/AUTOMATIC_ELEVATION_SUMMARY.md) | `AUTOMATIC_ELEVATION_SUMMARY.md` | Nota técnica |
| [Gyroscope Calibration Enhancement](https://github.com/Ericktz/JCoDroneEdu/blob/main/CALIBRATION_ENHANCEMENT.md) | `CALIBRATION_ENHANCEMENT.md` | Reporte de implementación |
| [Calibration Timeout Issue - Resolution](https://github.com/Ericktz/JCoDroneEdu/blob/main/CALIBRATION_TIMEOUT_FIX.md) | `CALIBRATION_TIMEOUT_FIX.md` | Reporte de implementación |
| [ElevationService Refactor Summary](https://github.com/Ericktz/JCoDroneEdu/blob/main/ELEVATION_SERVICE_REFACTOR.md) | `ELEVATION_SERVICE_REFACTOR.md` | Reporte de implementación |
| [Error Data Handler Fix](https://github.com/Ericktz/JCoDroneEdu/blob/main/ERROR_DATA_HANDLER_FIX.md) | `ERROR_DATA_HANDLER_FIX.md` | Reporte de implementación |
| [CoDrone EDU Flight Time Constraints](https://github.com/Ericktz/JCoDroneEdu/blob/main/FLIGHT_TIME_CONSTRAINTS.md) | `FLIGHT_TIME_CONSTRAINTS.md` | Nota técnica |
| [Information Extraction Summary - Phase 1](https://github.com/Ericktz/JCoDroneEdu/blob/main/INFO_EXTRACTION_SUMMARY.md) | `INFO_EXTRACTION_SUMMARY.md` | Nota técnica |
| [Inventory Data Access Patterns](https://github.com/Ericktz/JCoDroneEdu/blob/main/INVENTORY_DATA_ACCESS_PATTERNS.md) | `INVENTORY_DATA_ACCESS_PATTERNS.md` | Diseño o arquitectura |
| [Inventory Methods Implementation Summary](https://github.com/Ericktz/JCoDroneEdu/blob/main/INVENTORY_METHODS_IMPLEMENTATION.md) | `INVENTORY_METHODS_IMPLEMENTATION.md` | Reporte de implementación |
| [Optical Flow Sensor Implementation - COMPLETE](https://github.com/Ericktz/JCoDroneEdu/blob/main/OPTICAL_FLOW_IMPLEMENTATION_COMPLETE.md) | `OPTICAL_FLOW_IMPLEMENTATION_COMPLETE.md` | Reporte de implementación |
| [Quick Reference: Temperature Calibration Factors](https://github.com/Ericktz/JCoDroneEdu/blob/main/TEMPERATURE_CALIBRATION_FACTORS.md) | `TEMPERATURE_CALIBRATION_FACTORS.md` | Nota técnica |
| [Temperature Calibration Research Guide](https://github.com/Ericktz/JCoDroneEdu/blob/main/TEMPERATURE_CALIBRATION_RESEARCH.md) | `TEMPERATURE_CALIBRATION_RESEARCH.md` | Auditoría o investigación |
| [Temperature Sensor Information](https://github.com/Ericktz/JCoDroneEdu/blob/main/TEMPERATURE_SENSOR_INFO.md) | `TEMPERATURE_SENSOR_INFO.md` | Nota técnica |
| [Weather-Calibrated Elevation API](https://github.com/Ericktz/JCoDroneEdu/blob/main/WEATHER_CALIBRATED_ELEVATION.md) | `WEATHER_CALIBRATED_ELEVATION.md` | Nota técnica |

## Controlador, pantalla, buzzer y protocolos (10)

| Documento | Archivo | Tipo |
| --- | --- | --- |
| [CoDrone EDU Buzzer API Implementation - COMPLETE](https://github.com/Ericktz/JCoDroneEdu/blob/main/BUZZER_IMPLEMENTATION_COMPLETE.md) | `BUZZER_IMPLEMENTATION_COMPLETE.md` | Reporte de implementación |
| [Controller Display & Buzzer Architecture Audit](https://github.com/Ericktz/JCoDroneEdu/blob/main/CONTROLLER_DISPLAY_BUZZER_ARCHITECTURE_AUDIT.md) | `CONTROLLER_DISPLAY_BUZZER_ARCHITECTURE_AUDIT.md` | Auditoría o investigación |
| [Controller Display Implementation Complete](https://github.com/Ericktz/JCoDroneEdu/blob/main/CONTROLLER_DISPLAY_IMPLEMENTATION_COMPLETE.md) | `CONTROLLER_DISPLAY_IMPLEMENTATION_COMPLETE.md` | Reporte de implementación |
| [CoDrone EDU Controller Input API Implementation - COMPLETE](https://github.com/Ericktz/JCoDroneEdu/blob/main/CONTROLLER_INPUT_IMPLEMENTATION_COMPLETE.md) | `CONTROLLER_INPUT_IMPLEMENTATION_COMPLETE.md` | Reporte de implementación |
| [Controller Input Refactoring](https://github.com/Ericktz/JCoDroneEdu/blob/main/CONTROLLER_INPUT_REFACTORING.md) | `CONTROLLER_INPUT_REFACTORING.md` | Reporte de implementación |
| [DisplayDrawImage (0x88) Protocol Guide](https://github.com/Ericktz/JCoDroneEdu/blob/main/DISPLAY_0x88_PROTOCOL_GUIDE.md) | `DISPLAY_0x88_PROTOCOL_GUIDE.md` | Guía o referencia |
| [Display Batch Protocol Research](https://github.com/Ericktz/JCoDroneEdu/blob/main/DISPLAY_BATCH_PROTOCOL_RESEARCH.md) | `DISPLAY_BATCH_PROTOCOL_RESEARCH.md` | Auditoría o investigación |
| [Display Canvas Optimization Strategy](https://github.com/Ericktz/JCoDroneEdu/blob/main/DISPLAY_OPTIMIZATION_STRATEGY.md) | `DISPLAY_OPTIMIZATION_STRATEGY.md` | Diseño o arquitectura |
| [Endianness Audit Report](https://github.com/Ericktz/JCoDroneEdu/blob/main/ENDIANNESS_AUDIT_REPORT.md) | `ENDIANNESS_AUDIT_REPORT.md` | Auditoría o investigación |
| [CoDrone EDU Reset and Trim API Implementation - COMPLETE](https://github.com/Ericktz/JCoDroneEdu/blob/main/RESET_AND_TRIM_IMPLEMENTATION_COMPLETE.md) | `RESET_AND_TRIM_IMPLEMENTATION_COMPLETE.md` | Reporte de implementación |

## Pruebas, calidad, compilación y releases (19)

| Documento | Archivo | Tipo |
| --- | --- | --- |
| [JCoDroneEdu Gradle Build Script Recovery Plan](https://github.com/Ericktz/JCoDroneEdu/blob/main/BUILD_RECOVERY_PLAN.md) | `BUILD_RECOVERY_PLAN.md` | Nota técnica |
| [Changelog](https://github.com/Ericktz/JCoDroneEdu/blob/main/CHANGELOG.md) | `CHANGELOG.md` | Historial o versión |
| [Phase 5: Logging Documentation & Educational Patterns](https://github.com/Ericktz/JCoDroneEdu/blob/main/docs/LOGGING_GUIDE.md) | `docs/LOGGING_GUIDE.md` | Guía o referencia |
| [OSSRH / Maven Central Publishing Guide](https://github.com/Ericktz/JCoDroneEdu/blob/main/docs/OSSRH_PUBLISHING.md) | `docs/OSSRH_PUBLISHING.md` | Nota técnica |
| [Link Verification Report](https://github.com/Ericktz/JCoDroneEdu/blob/main/LINK_VERIFICATION_REPORT.md) | `LINK_VERIFICATION_REPORT.md` | Auditoría o investigación |
| [Logging Inconsistency Analysis & Enhancement](https://github.com/Ericktz/JCoDroneEdu/blob/main/LOGGING_ENHANCEMENT_ANALYSIS.md) | `LOGGING_ENHANCEMENT_ANALYSIS.md` | Auditoría o investigación |
| [Logging Implementation Summary](https://github.com/Ericktz/JCoDroneEdu/blob/main/LOGGING_IMPLEMENTATION.md) | `LOGGING_IMPLEMENTATION.md` | Reporte de implementación |
| [Phase 4: Complete Print Statement Audit](https://github.com/Ericktz/JCoDroneEdu/blob/main/PHASE_4_PRINT_AUDIT_COMPLETE.md) | `PHASE_4_PRINT_AUDIT_COMPLETE.md` | Auditoría o investigación |
| [Phase 4 Logging Enhancement - Print Statement Audit Index](https://github.com/Ericktz/JCoDroneEdu/blob/main/PHASE_4_PRINT_AUDIT_INDEX.md) | `PHASE_4_PRINT_AUDIT_INDEX.md` | Auditoría o investigación |
| [Phase 4 Audit Summary - Quick Reference](https://github.com/Ericktz/JCoDroneEdu/blob/main/PHASE_4_PRINT_AUDIT_QUICK_REFERENCE.md) | `PHASE_4_PRINT_AUDIT_QUICK_REFERENCE.md` | Auditoría o investigación |
| [Pre-Release Validation Checklist](https://github.com/Ericktz/JCoDroneEdu/blob/main/PRE_RELEASE_CHECKLIST.md) | `PRE_RELEASE_CHECKLIST.md` | Lista de validación |
| [📋 Release Checklist & Procedures](https://github.com/Ericktz/JCoDroneEdu/blob/main/RELEASE_CHECKLIST.md) | `RELEASE_CHECKLIST.md` | Lista de validación |
| [📦 Release Strategy](https://github.com/Ericktz/JCoDroneEdu/blob/main/RELEASE_STRATEGY.md) | `RELEASE_STRATEGY.md` | Diseño o arquitectura |
| [Build the project](https://github.com/Ericktz/JCoDroneEdu/blob/main/SMOKE_TEST.md) | `SMOKE_TEST.md` | Lista de validación |
| [JCoDroneEdu Unit Test Framework](https://github.com/Ericktz/JCoDroneEdu/blob/main/TESTING_GUIDE.md) | `TESTING_GUIDE.md` | Guía o referencia |
| [Testing Implementation Summary](https://github.com/Ericktz/JCoDroneEdu/blob/main/TESTING_IMPLEMENTATION_SUMMARY.md) | `TESTING_IMPLEMENTATION_SUMMARY.md` | Reporte de implementación |
| [Python Library Version 2.3 vs 2.4 Detailed Comparison](https://github.com/Ericktz/JCoDroneEdu/blob/main/VERSION_2.3_VS_2.4_ANALYSIS.md) | `VERSION_2.3_VS_2.4_ANALYSIS.md` | Auditoría o investigación |
| [CoDrone EDU Python Library Version History](https://github.com/Ericktz/JCoDroneEdu/blob/main/VERSION_HISTORY.md) | `VERSION_HISTORY.md` | Historial o versión |
| [Python Version History Research - Summary](https://github.com/Ericktz/JCoDroneEdu/blob/main/VERSION_RESEARCH_SUMMARY.md) | `VERSION_RESEARCH_SUMMARY.md` | Auditoría o investigación |

## Docencia, herramientas y ejemplos (5)

| Documento | Archivo | Tipo |
| --- | --- | --- |
| [Error Monitoring Example - Enhanced with Flight Modes](https://github.com/Ericktz/JCoDroneEdu/blob/main/ERROR_MONITORING_EXAMPLE_MODES.md) | `ERROR_MONITORING_EXAMPLE_MODES.md` | Nota técnica |
| [JCoDroneEdu Flight Patterns](https://github.com/Ericktz/JCoDroneEdu/blob/main/flight-patterns/README.md) | `flight-patterns/README.md` | Guía o referencia |
| [Running the Error Monitoring Example](https://github.com/Ericktz/JCoDroneEdu/blob/main/RUNNING_ERROR_MONITORING_EXAMPLE.md) | `RUNNING_ERROR_MONITORING_EXAMPLE.md` | Nota técnica |
| [Autonomous Method Framework](https://github.com/Ericktz/JCoDroneEdu/blob/main/src/main/java/com/otabi/jcodroneedu/autonomous/README.md) | `src/main/java/com/otabi/jcodroneedu/autonomous/README.md` | Guía o referencia |
| [VS Code Extension for CoDrone EDU Control & Safety Override](https://github.com/Ericktz/JCoDroneEdu/blob/main/VSCODE_EXTENSION_RESEARCH.md) | `VSCODE_EXTENSION_RESEARCH.md` | Auditoría o investigación |

## Referencias y otros (4)

| Documento | Archivo | Tipo |
| --- | --- | --- |
| [Java Drone Class Audit - Prioritized Punch List](https://github.com/Ericktz/JCoDroneEdu/blob/main/DRONE_AUDIT_PUNCH_LIST.md) | `DRONE_AUDIT_PUNCH_LIST.md` | Auditoría o investigación |
| [CoDrone EDU Java API](https://github.com/Ericktz/JCoDroneEdu/blob/main/README.md) | `README.md` | Guía o referencia |
| [Reference Materials](https://github.com/Ericktz/JCoDroneEdu/blob/main/reference/README.md) | `reference/README.md` | Guía o referencia |
| [Session Completion Summary - October 15, 2025](https://github.com/Ericktz/JCoDroneEdu/blob/main/SESSION_COMPLETION_SUMMARY.md) | `SESSION_COMPLETION_SUMMARY.md` | Historial o versión |
