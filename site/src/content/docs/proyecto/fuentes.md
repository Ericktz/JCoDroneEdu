---
title: Fuentes y versiones
description: Referencias primarias y política de actualización del portal.
---

## Política

Las afirmaciones sobre hardware, firmware y API se contrastan primero con fuentes
del fabricante o del mantenedor del paquete. Cada guía local indica qué se verificó
con software y qué se verificó con hardware.

No se copia ni traduce de manera extensa contenido externo. El portal resume,
atribuye y enlaza la fuente; nuestros laboratorios y explicaciones son contenido
propio.

## Fuentes primarias

| Fuente | Uso |
| --- | --- |
| [Documentación CoDrone EDU](https://docs.robolink.com/docs/CoDroneEDU/) | Punto de entrada oficial del producto |
| [Configuración Python](https://docs.robolink.com/docs/CoDroneEDU/Python/Setup-and-Installation/) | Entornos, cable y preparación |
| [API Python](https://docs.robolink.com/docs/CoDroneEDU/Python/Drone-Function-Documentation/) | Contratos y ejemplos oficiales |
| [Changelog Python](https://docs.robolink.com/docs/CoDroneEDU/Python/Python-Changelog/) | Cambios entre versiones |
| [Swarm](https://docs.robolink.com/docs/CoDroneEDU/Python/Swarm-Function-Documentation/) | Requisitos y límites del enjambre |
| [Sensor Guide](https://docs.robolink.com/docs/CoDroneEDU/Resources/Sensor-Guide/) | Ejes, sensores y aplicaciones |
| [Manual v3.2](https://docs.robolink.com/files/co-drone-edu-manual-v-3-2.pdf) | Operación y seguridad del equipo |
| [codrone-edu en PyPI](https://pypi.org/project/codrone-edu/) | Artefacto y versión publicada |
| [Ejemplos oficiales de Robolink](https://github.com/RobolinkInc/codrone-edu-python-examples) | Casos ejecutables de Python |

## Estado registrado

| Componente | Versión observada | Estado local |
| --- | --- | --- |
| `codrone-edu` Python | 2.8 | Import verificado sin hardware |
| JCoDroneEdu | 1.5.0-SNAPSHOT | Suite automatizada verificada con JDK 21 |
| Paridad Java declarada | Python 2.6 | Requiere auditoría contra 2.8 |
| Firmware de los 8 drones | Por inventariar | Bloquea validación de vuelo |

## Cadencia

- Revisar PyPI y el changelog de Robolink antes de cada semestre.
- Fijar versiones durante el semestre; no actualizar en medio de una evaluación.
- Reejecutar pruebas y laboratorios antes de aceptar una versión nueva.
- Registrar divergencias Python/Java en una matriz versionada.
