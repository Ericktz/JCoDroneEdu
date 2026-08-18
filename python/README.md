# CoDrone EDU EIT — trayecto Python

Este directorio contiene la integración Python incremental del monorepo. El
estado actual cubre **Fase 0** y prepara **Fase 1** sin vuelo. No implementa PX4,
MAVLink, MAVSDK, ROS 2 ni una API `Vehicle`.

## Versiones

- Python: 3.11 o posterior; validado localmente con 3.14.2.
- `codrone-edu`: 2.8, fijada en `pyproject.toml` y `requirements.txt`.
- `pytest`: 9.1.1, dependencia opcional de desarrollo.

## Instalación

Desde la raíz del repositorio:

```bash
python3 -m venv python/.venv
source python/.venv/bin/activate
python -m pip install --upgrade pip
python -m pip install -e './python[dev]'
```

No se instalan componentes PX4/MAVLink en esta fase.

## Diagnóstico seguro

```bash
python python/tools/environment_report.py
python python/examples/00_connect.py --list-ports
```

Estos comandos no conectan ni activan motores.

## Prueba de conexión sin vuelo

Con el controlador USB y el dron encendido y emparejado:

```bash
python python/examples/00_connect.py
```

También se puede indicar un puerto detectado previamente:

```bash
python python/examples/00_connect.py --port /dev/cu.usbmodemXXXX
```

El script solo usa `pair()`, `get_flight_state()`, `get_battery()` y `close()`.
Siempre intenta cerrar la conexión. No contiene `takeoff()`, `move()` ni comandos
de ejes.

## Pruebas automáticas

```bash
python -m pytest python/tests
```

La suite usa objetos simulados. Un `pytest` normal jamás accede al controlador ni
hace volar el dron.

## Estado de validación

- [x] Entorno e importación.
- [x] Detección de puertos implementada.
- [x] Flujo de conexión probado con dobles.
- [ ] Controlador detectado físicamente.
- [ ] Estado del dron confirmado como `Ready`.
- [ ] Batería real leída.
- [ ] Desconexión real confirmada.

No se avanzará al explorador de telemetría hasta completar los cuatro criterios
físicos pendientes y registrar la evidencia.
