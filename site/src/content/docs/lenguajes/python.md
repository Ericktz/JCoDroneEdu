---
title: Trayecto Python
description: Integración del paquete oficial codrone-edu y estado de compatibilidad.
---

## Estado de versión

<div class="version-status">

- **Paquete oficial:** `codrone-edu` 2.8
- **Import verificado:** sí, sin hardware
- **Pruebas de conexión y vuelo:** pendientes
- **Paridad declarada por la biblioteca Java:** Python 2.6

</div>

La diferencia entre 2.8 y 2.6 se tratará como una auditoría de compatibilidad, no
como una equivalencia supuesta. El changelog de Robolink y el paquete instalado
serán las fuentes para construir una matriz método por método.

## Primer programa seguro

Este ejemplo solo crea el objeto. No empareja ni activa motores:

```python
from codrone_edu.drone import Drone


def main() -> None:
    drone = Drone()
    try:
        print("Objeto CoDrone EDU creado; hardware aún no conectado")
    finally:
        drone.close()


if __name__ == "__main__":
    main()
```

El primer vuelo se publicará después de validar puertos, firmware y parada de
emergencia con los equipos de la universidad.

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
