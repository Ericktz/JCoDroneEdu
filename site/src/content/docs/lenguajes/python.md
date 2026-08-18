---
title: Trayecto Python
description: Integración del paquete oficial codrone-edu y estado de compatibilidad.
---

## Estado de versión

<div class="version-status">

- **Paquete oficial:** `codrone-edu` 2.8
- **Import verificado:** sí, sin hardware
- **Flujo de conexión:** probado con dobles; hardware real pendiente
- **Pruebas de vuelo:** no iniciadas
- **Paridad declarada por la biblioteca Java:** Python 2.6

</div>

La diferencia entre 2.8 y 2.6 se tratará como una auditoría de compatibilidad, no
como una equivalencia supuesta. El changelog de Robolink y el paquete instalado
serán las fuentes para construir una matriz método por método.

## Primer programa seguro

El repositorio ya contiene un diagnóstico de entorno y una prueba de conexión
segura. Ninguno activa motores:

- [`environment_report.py`](https://github.com/Ericktz/JCoDroneEdu/blob/main/python/tools/environment_report.py)
- [`00_connect.py`](https://github.com/Ericktz/JCoDroneEdu/blob/main/python/examples/00_connect.py)
- [Guía ejecutable en el portal](/JCoDroneEdu/ejemplos/python/conexion-sin-vuelo/)

El primer vuelo se publicará después de validar conexión, telemetría, firmware y
procedimientos de seguridad con los equipos de la universidad.

## Iniciativa PX4/MAVLink

Se aprobó una evolución gradual hacia una API educativa con backends CoDrone y,
en fases posteriores, PX4. CoDrone EDU no ejecuta PX4. En el estado actual no se
han instalado MAVSDK, MAVLink, PX4, ROS 2 ni QGroundControl.

La [propuesta integrada](https://github.com/Ericktz/JCoDroneEdu/blob/main/docs/plans/PX4_CODRONE_PROPOSAL.md)
obliga a validar cada fase antes de avanzar. La Fase 1 sigue abierta hasta probar
controlador, estado `Ready`, batería y desconexión sobre hardware real.

## Enjambres

Robolink recomienda no conectar más de cuatro drones por computador para su módulo
`swarm`. Para utilizar los ocho equipos se planificarán al menos dos estaciones y
una capa explícita de coordinación entre ellas.

Consulta la [documentación oficial de swarm](https://docs.robolink.com/docs/CoDroneEDU/Python/Swarm-Function-Documentation/).

## Auditoría Python ↔ Java

La matriz considerará:

- existencia y nombre de cada método;
- parámetros, unidades y valores predeterminados;
- tipo y semántica del retorno;
- bloqueo, temporización y manejo de errores;
- métodos obsoletos o no disponibles;
- resultado con hardware real.
