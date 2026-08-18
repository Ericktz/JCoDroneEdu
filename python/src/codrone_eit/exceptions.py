"""Errores específicos del hito de conexión sin vuelo."""


class CoDroneEITError(RuntimeError):
    """Error base de la integración CoDrone EDU EIT."""


class ControllerNotFoundError(CoDroneEITError):
    """No se encontró un controlador CoDrone EDU entre los puertos seriales."""


class AmbiguousControllerError(CoDroneEITError):
    """Hay varios controladores y se debe elegir uno explícitamente."""


class PairingError(CoDroneEITError):
    """El controlador abrió, pero el dron no confirmó el estado Ready."""


class TelemetryError(CoDroneEITError):
    """La lectura inicial de telemetría no es válida."""
