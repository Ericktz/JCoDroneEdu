import pytest

from codrone_eit.exceptions import AmbiguousControllerError, ControllerNotFoundError
from codrone_eit.ports import SerialPortInfo, controller_ports, select_controller_port


def port(device: str, vid: int | None) -> SerialPortInfo:
    return SerialPortInfo(device, "Test port", vid, 1)


def test_controller_ports_filter_official_vid() -> None:
    ports = [port("internal", None), port("controller", 1155)]

    assert controller_ports(ports) == [ports[1]]


def test_select_controller_requires_detected_hardware() -> None:
    with pytest.raises(ControllerNotFoundError, match="VID 1155"):
        select_controller_port(None, [port("internal", None)])


def test_select_controller_rejects_ambiguous_detection() -> None:
    with pytest.raises(AmbiguousControllerError, match="usa --port"):
        select_controller_port(None, [port("one", 1155), port("two", 1155)])


def test_requested_port_must_match_controller_vid() -> None:
    with pytest.raises(ControllerNotFoundError, match="no corresponde"):
        select_controller_port("other", [port("other", 999)])
