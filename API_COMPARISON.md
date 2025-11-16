# API Comparison Report

**Generated:** 2025-11-16T13:21:53.214911
**Java Version:** 1.3.0
**Python API Version:** 2.6.0

## Summary

- **Python Methods:** 127
- **Java Methods:** 188
- **Matched Methods:** 105
  - Documented (@pythonEquivalent): 60
  - Inferred (by name): 45
- **In Python, Not Java:** 31
- **In Java, Not Python:** 83

**Note:** Java methods use @pythonEquivalent annotations to document their Python API mapping.

## Methods in Python but NOT in Java

⚠️ Consider implementing these methods:

- `append_color_data()`
- `avoid_wall()`
- `controller_LED_off()`
- `controller_draw_arc()`
- `controller_draw_chord()`
- `controller_draw_ellipse()`
- `controller_draw_line()`
- `controller_draw_point()`
- `controller_draw_polygon()`
- `controller_draw_rectangle()`
- `controller_draw_square()`
- `controller_draw_string_align()`
- `controller_preview_canvas()`
- `get_angular_speed_x()`
- `get_angular_speed_y()`
- `get_angular_speed_z()`
- `get_image_data()`
- `get_x_accel()`
- `get_x_angle()`
- `get_y_accel()`
- `get_y_angle()`
- `get_z_accel()`
- `get_z_angle()`
- `keep_distance()`
- `load_classifier()`
- `load_color_data()`
- `move_forward()`
- `new_color_data()`
- `predict_colors()`
- `reset_move()`
- `reset_sensor()`

## Methods in Java but NOT in Python

ℹ️ Java-specific methods (expected):

- `autoConnect) throws DroneNotFoundException()`
- `autoConnect, String portName) throws DroneNotFoundException()`
- `avoidWall()`
- `changeSpeed()`
- `circleTurn()`
- `clearBias()`
- `clearCounter()`
- `connect()`
- `controllerBuzzerSequence()`
- `controllerClearArea()`
- `controllerDrawCircle()`
- `controllerDrawLine()`
- `controllerDrawPoint()`
- `controllerDrawRectangle()`
- `controllerInvertArea()`
- `controllerLEDOff()`
- `droneBuzzerSequence()`
- `getAccel()`
- `getAccidentCount()`
- `getAddressData()`
- `getAddressDataObject()`
- `getAltitude()`
- `getAltitudeData()`
- `getAngle()`
- `getButtonDataObject()`
- `getCalculatedAltitude()`
- `getCalibratedTemperature()`
- `getCorrectedElevation()`
- `getCountData()`
- `getCountDataObject()`
- `getCpuIdData()`
- `getCpuIdDataObject()`
- `getDroneStatus()`
- `getElevation()`
- `getErrors()`
- `getFlightController()`
- `getFlightTime()`
- `getFlowData()`
- `getGyro()`
- `getInformationData()`
- `getInformationDataObject()`
- `getJoystickDataObject()`
- `getLandingCount()`
- `getLinkController()`
- `getLinkManager()`
- `getPositionX()`
- `getPositionY()`
- `getPositionZ()`
- `getReceiver()`
- `getSettingsController()`
- `getTakeoffCount()`
- `getTelemetryService()`
- `getUncalibratedTemperature()`
- `getUncorrectedElevation()`
- `go()`
- `isConnected()`
- `isOpen()`
- `keepDistance()`
- `loadColorClassifier()`
- `moveForward()`
- `ping()`
- `registerBuzzerSequence()`
- `sendControl()`
- `sendControlPosition()`
- `sendControlWhile()`
- `sendRequest()`
- `sendRequestWait()`
- `setBacklight()`
- `setControllerLEDMode()`
- `setControllerMode()`
- `setDefault()`
- `setDroneLEDMode()`
- `setHeadlessMode()`
- `setLinkMode()`
- `square()`
- `triangleTurn()`
- `triggerFlightEvent()`
- `unloadColorClassifier()`
- `updateButtonData()`
- `updateJoystickData()`
- `useCalibratedTemperature()`
- `useCorrectedElevation()`
- `{()`

