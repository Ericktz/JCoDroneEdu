# Experimento: inspección de entorno para Fase 0

- **Fecha:** 2026-08-18
- **Equipo:** estación de desarrollo local
- **Sistema:** macOS 15.0.1, Darwin 24.0.0, ARM64
- **Python:** CPython 3.14.2
- **Biblioteca:** `codrone-edu==2.8`
- **Firmware:** no consultado; la API pública `Drone` 2.8 no expone un getter de
  firmware
- **Hardware CoDrone:** no detectado durante la inspección

## Objetivo

Confirmar el entorno de desarrollo, la importación de la biblioteca oficial y la
disponibilidad del controlador antes de preparar una prueba sin vuelo.

## Procedimiento

1. Consultar sistema operativo, arquitectura, Python y `pip`.
2. Importar `Drone` desde `codrone_edu.drone`.
3. Inspeccionar las firmas de `pair`, `get_battery` y `close`.
4. Enumerar dispositivos USB y puertos seriales disponibles.

## Resultado

- `Drone()` pudo crearse y cerrarse sin hardware.
- Las firmas verificadas son `pair(portname=None)`, `get_battery()` y `close()`.
- Solo se encontraron puertos internos de macOS; no apareció un dispositivo USB
  con VID decimal `1155`.
- No se intentó `pair()` porque el controlador no estaba conectado.
- No se ejecutó ninguna función de vuelo.

## Conclusión

La parte reproducible de Fase 0 queda preparada. Fase 1 está implementada como
prueba segura, pero sus criterios físicos continúan pendientes.
