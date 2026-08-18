from enum import Enum
from importlib.metadata import version

import pytest

from codrone_eit.exceptions import PairingError, TelemetryError
from codrone_eit.phase1 import run_connection_test


class FlightState(Enum):
    Ready = 1
    Error = 2


class FakeDrone:
    def __init__(
        self,
        *,
        pair_result: bool = True,
        state: FlightState = FlightState.Ready,
        battery: object = 73,
    ) -> None:
        self.pair_result = pair_result
        self.state = state
        self.battery = battery
        self.calls: list[object] = []

    def pair(self, portname: str | None = None) -> bool:
        self.calls.append(("pair", portname))
        return self.pair_result

    def get_flight_state(self) -> FlightState:
        self.calls.append("get_flight_state")
        return self.state

    def get_battery(self) -> object:
        self.calls.append("get_battery")
        return self.battery

    def close(self) -> None:
        self.calls.append("close")


def test_connection_reads_only_safe_state_and_closes() -> None:
    drone = FakeDrone()

    result = run_connection_test("test-port", drone_factory=lambda: drone)

    assert result.library_version == version("codrone-edu")
    assert result.flight_state == "Ready"
    assert result.battery_percent == 73
    assert drone.calls == [
        ("pair", "test-port"),
        "get_flight_state",
        "get_battery",
        "close",
    ]


def test_unready_drone_fails_and_still_closes() -> None:
    drone = FakeDrone(state=FlightState.Error)

    with pytest.raises(PairingError, match="Ready"):
        run_connection_test("test-port", drone_factory=lambda: drone)

    assert drone.calls[-1] == "close"


@pytest.mark.parametrize("battery", [-1, 101, 72.5, True, None])
def test_invalid_battery_fails_and_still_closes(battery: object) -> None:
    drone = FakeDrone(battery=battery)

    with pytest.raises(TelemetryError):
        run_connection_test("test-port", drone_factory=lambda: drone)

    assert drone.calls[-1] == "close"
