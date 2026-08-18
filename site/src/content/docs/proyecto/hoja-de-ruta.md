---
title: Hoja de ruta
description: Fases de construcción del proyecto CoDrone EDU EIT.
---

## 0. Reproducibilidad

- Certificar JDK 21 y suite Java.
- Registrar versiones oficiales de Python y firmware.
- Separar artefactos generados del código fuente.
- Mantener `origin` y `upstream` claramente documentados.

## 1. Portal mínimo viable

- Portal español con Astro/Starlight.
- Despliegue automático en GitHub Pages.
- Instalación, seguridad, trayectos Java/Python y fuentes.
- JavaDoc integrado como artefacto generado.

## 2. Laboratorios fundamentales

- Programación, programación avanzada y estructuras de datos.
- Plantilla común, pruebas y rúbricas.
- Niveles guiado, desafío y extensión.

## Iniciativa Python → PX4/MAVLink

Esta línea mantiene su propia puerta de fases:

1. **Fase 0 — completada:** entorno reproducible y diagnóstico.
2. **Fase 1 — implementada, pendiente de hardware:** conexión, estado, batería y
   cierre sin vuelo.
3. **Fases 2+ — bloqueadas por validación:** telemetría, logger, vuelo mínimo,
   abstracción y, mucho después, PX4/MAVLink.

Consulta el [catálogo de ejemplos](/JCoDroneEdu/ejemplos/) para ver únicamente las
capacidades ya implementadas.

## 3. Datos, IA, sistemas y redes

- Telemetría y conjuntos de datos anonimizados.
- Señales, estadística, visión e inteligencia artificial.
- Pasarela web, almacenamiento y coordinación distribuida.
- Seguridad de comandos y servicios.

## 4. Proyectos integradores

- Catálogo de problemas y criterios de selección.
- Hitos, riesgos, pruebas y demostración final.
- Evidencia de aprendizaje y mejora semestral.
