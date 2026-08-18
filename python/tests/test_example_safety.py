import ast
from pathlib import Path


FORBIDDEN_FLIGHT_CALLS = {
    "emergency_stop",
    "go",
    "hover",
    "land",
    "move",
    "set_pitch",
    "set_roll",
    "set_throttle",
    "set_yaw",
    "takeoff",
}


def test_phase_0_and_1_code_contains_no_flight_calls() -> None:
    python_root = Path(__file__).parents[1]
    safe_files = [
        *sorted((python_root / "examples").glob("*.py")),
        *sorted((python_root / "tools").glob("*.py")),
        *sorted((python_root / "src" / "codrone_eit").glob("*.py")),
    ]
    called_methods: set[str] = set()

    for source_file in safe_files:
        tree = ast.parse(source_file.read_text(encoding="utf-8"))
        called_methods.update(
            node.func.attr
            for node in ast.walk(tree)
            if isinstance(node, ast.Call) and isinstance(node.func, ast.Attribute)
        )

    assert called_methods.isdisjoint(FORBIDDEN_FLIGHT_CALLS)
