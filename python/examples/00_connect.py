"""Fase 1: conexión, estado y batería de CoDrone EDU, sin vuelo."""

from __future__ import annotations

import argparse
import logging

from codrone_eit.exceptions import CoDroneEITError
from codrone_eit.phase1 import run_connection_test
from codrone_eit.ports import list_serial_ports, select_controller_port


def build_parser() -> argparse.ArgumentParser:
    parser = argparse.ArgumentParser(
        description="Prueba segura de conexión con CoDrone EDU; no activa motores."
    )
    parser.add_argument("--port", help="Puerto serial detectado, por ejemplo /dev/cu.*")
    parser.add_argument(
        "--list-ports",
        action="store_true",
        help="Solo enumera puertos y termina sin conectarse.",
    )
    return parser


def main() -> int:
    logging.basicConfig(level=logging.INFO, format="%(message)s")
    log = logging.getLogger("codrone_eit.connect")
    args = build_parser().parse_args()
    ports = list_serial_ports()

    if args.list_ports:
        if not ports:
            log.info("[INFO] No serial ports detected")
        for port in ports:
            log.info(
                "[INFO] %s | %s | VID=%s PID=%s",
                port.device,
                port.description,
                port.vid,
                port.pid,
            )
        return 0

    try:
        controller = select_controller_port(args.port, ports)
        log.info("[OK] Controller detected: %s", controller.device)
        run_connection_test(controller.device, logger=log)
    except CoDroneEITError as error:
        log.error("[ERROR] %s", error)
        return 2
    except (KeyboardInterrupt, SystemExit):
        log.error("[ERROR] Connection interrupted")
        return 130

    log.info("[OK] Test completed without flight")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
