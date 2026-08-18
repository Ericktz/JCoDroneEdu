"""Caso de uso de Fase 1: conectar, leer estado/batería y cerrar sin vuelo."""

from __future__ import annotations

import logging
from dataclasses import dataclass
from importlib.metadata import version
from typing import Callable, Protocol

from .exceptions import PairingError, TelemetryError


class DroneConnection(Protocol):
    """Subset real de codrone-edu 2.8 utilizado en esta fase."""

    def pair(self, portname: str | None = None) -> bool: ...

    def get_flight_state(self) -> object: ...

    def get_battery(self) -> int: ...

    def close(self) -> None: ...


@dataclass(frozen=True, slots=True)
class ConnectionResult:
    """Evidencia mínima obtenida durante una conexión segura."""

    library_version: str
    port: str
    flight_state: str
    battery_percent: int


def _default_drone_factory() -> DroneConnection:
    from codrone_edu.drone import Drone

    return Drone()


def _state_name(state: object) -> str:
    name = getattr(state, "name", None)
    return str(name if name is not None else state)


def run_connection_test(
    port: str,
    *,
    drone_factory: Callable[[], DroneConnection] = _default_drone_factory,
    logger: logging.Logger | None = None,
) -> ConnectionResult:
    """Ejecuta la Fase 1 sin invocar ninguna función de vuelo."""

    log = logger or logging.getLogger(__name__)
    library_version = version("codrone-edu")
    log.info("[INFO] codrone-edu library: %s", library_version)
    log.info("[INFO] Connecting through %s...", port)

    drone = drone_factory()
    close_error: Exception | None = None
    try:
        if drone.pair(portname=port) is not True:
            raise PairingError("pair() no confirmó la apertura del controlador.")

        flight_state = _state_name(drone.get_flight_state())
        if flight_state.casefold() != "ready":
            raise PairingError(
                f"El dron informó el estado {flight_state!r}; se esperaba 'Ready'."
            )
        log.info("[OK] Drone paired; state: %s", flight_state)

        battery = drone.get_battery()
        if isinstance(battery, bool) or not isinstance(battery, int):
            raise TelemetryError(
                f"get_battery() devolvió un tipo inesperado: {type(battery).__name__}."
            )
        if not 0 <= battery <= 100:
            raise TelemetryError(
                f"get_battery() devolvió un porcentaje fuera de rango: {battery}."
            )
        log.info("[INFO] Battery: %d%%", battery)
        log.info("[INFO] Firmware: unavailable through the public Drone 2.8 API")

        return ConnectionResult(
            library_version=library_version,
            port=port,
            flight_state=flight_state,
            battery_percent=battery,
        )
    finally:
        log.info("[INFO] Closing connection")
        try:
            drone.close()
        except Exception as error:
            close_error = error
            log.exception("[ERROR] Clean disconnect failed")
        else:
            log.info("[OK] Clean disconnect")
        if close_error is not None:
            raise close_error
