"""Descubrimiento de controladores sin abrir el puerto serial."""

from __future__ import annotations

from dataclasses import dataclass
from typing import Iterable

from serial.tools.list_ports import comports

from .exceptions import AmbiguousControllerError, ControllerNotFoundError

CODRONE_EDU_CONTROLLER_VID = 1155


@dataclass(frozen=True, slots=True)
class SerialPortInfo:
    """Información mínima y serializable de un puerto detectado."""

    device: str
    description: str
    vid: int | None
    pid: int | None


def list_serial_ports() -> list[SerialPortInfo]:
    """Enumera puertos sin conectarse a ninguno."""

    return [
        SerialPortInfo(
            device=port.device,
            description=port.description or "Sin descripción",
            vid=port.vid,
            pid=port.pid,
        )
        for port in comports()
    ]


def controller_ports(
    ports: Iterable[SerialPortInfo] | None = None,
) -> list[SerialPortInfo]:
    """Filtra controladores CoDrone EDU por el VID usado por la API oficial."""

    candidates = list_serial_ports() if ports is None else list(ports)
    return [port for port in candidates if port.vid == CODRONE_EDU_CONTROLLER_VID]


def select_controller_port(
    requested_port: str | None,
    ports: Iterable[SerialPortInfo] | None = None,
) -> SerialPortInfo:
    """Selecciona un controlador detectado o produce un error accionable."""

    available = list_serial_ports() if ports is None else list(ports)

    if requested_port is not None:
        for port in available:
            if port.device == requested_port:
                if port.vid != CODRONE_EDU_CONTROLLER_VID:
                    raise ControllerNotFoundError(
                        f"{requested_port} existe, pero su VID no corresponde al "
                        "controlador CoDrone EDU."
                    )
                return port
        raise ControllerNotFoundError(
            f"El puerto solicitado {requested_port} no está disponible."
        )

    detected = controller_ports(available)
    if not detected:
        raise ControllerNotFoundError(
            "No se detectó un controlador CoDrone EDU (VID 1155). "
            "Conecta el cable de datos y vuelve a ejecutar --list-ports."
        )
    if len(detected) > 1:
        names = ", ".join(port.device for port in detected)
        raise AmbiguousControllerError(
            f"Se detectaron varios controladores ({names}); usa --port."
        )
    return detected[0]
