---
title: Catálogo de ejemplos
description: Código docente versionado, seguro y verificable para CoDrone EDU EIT.
---

Los ejemplos se publican por fases. Cada uno identifica si requiere hardware, si
puede activar motores y qué evidencia falta. El código visible en este portal se
carga desde el mismo archivo que se prueba en CI.

| Código | Fase | Hardware | Vuelo | Estado |
| --- | --- | --- | --- | --- |
| `environment_report.py` | 0 | No | No | Verificado |
| [`00_connect.py`](/JCoDroneEdu/ejemplos/python/conexion-sin-vuelo/) | 1 | Sí | No | Implementado; validación física pendiente |

:::caution[Puerta de fase]
No se publicarán ejemplos de telemetría continua ni vuelo hasta completar la
conexión, batería y cierre con un equipo real y validar el resultado con el
responsable del laboratorio.
:::

## Convenciones

- `pytest` no abre puertos ni activa motores.
- Los ejemplos con hardware lo indican expresamente.
- Un ejemplo de vuelo futuro requerirá ejecución manual y autorización.
- Las funciones se verifican primero contra la documentación oficial vigente.

Consulta el [trayecto Python](/JCoDroneEdu/lenguajes/python/) y las
[reglas de seguridad](/JCoDroneEdu/inicio/seguridad/) antes de usar hardware.
