"""Informe reproducible de Fase 0; no abre puertos ni conecta hardware."""

from __future__ import annotations

import logging
import platform
import sys
from importlib.metadata import PackageNotFoundError, version

from codrone_eit.ports import CODRONE_EDU_CONTROLLER_VID, controller_ports, list_serial_ports


def main() -> int:
    logging.basicConfig(level=logging.INFO, format="%(message)s")
    log = logging.getLogger("codrone_eit.environment")

    try:
        library_version = version("codrone-edu")
    except PackageNotFoundError:
        log.error("[ERROR] codrone-edu is not installed")
        return 1

    ports = list_serial_ports()
    controllers = controller_ports(ports)

    log.info("[OK] Python environment: %s", sys.version.split()[0])
    log.info("[INFO] Operating system: %s", platform.platform())
    log.info("[INFO] Machine: %s", platform.machine())
    log.info("[OK] codrone_edu library: %s", library_version)
    log.info("[INFO] Serial ports detected: %d", len(ports))
    log.info(
        "[%s] CoDrone EDU controllers (VID %d): %d",
        "OK" if controllers else "INFO",
        CODRONE_EDU_CONTROLLER_VID,
        len(controllers),
    )
    for controller in controllers:
        log.info("[OK] Controller: %s", controller.device)

    return 0


if __name__ == "__main__":
    raise SystemExit(main())
