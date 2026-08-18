Te conviene darle a Codex un prompt que no solo describa la idea, sino que también le imponga **arquitectura, fases, metodología de pruebas y criterios para no adelantarse**. Así Codex puede acompañarte durante todo el proyecto y no terminar haciendo una integración monolítica difícil de mantener.

# Proyecto: PX4 / MAVLink to Python for CoDrone EDU

Quiero desarrollar contigo, paso a paso, un proyecto de integración entre **CoDrone EDU, Python y el ecosistema PX4/MAVLink**.

El proyecto debe partir desde pruebas muy básicas de conexión y programación del CoDrone EDU y evolucionar progresivamente hacia una arquitectura que permita utilizar conceptos, comandos e interfaces similares a PX4/MAVLink sobre CoDrone EDU.

No quiero intentar reemplazar el firmware del CoDrone EDU ni instalar PX4 directamente dentro del dron.

La idea central es desarrollar una **capa de abstracción Python** y posteriormente un **bridge MAVLink ↔ CoDrone EDU**.

---

# 1. Objetivo general

El objetivo final es construir una arquitectura como esta:

```text
                    APLICACIÓN / ALGORITMO
                             │
                             │
                    Python Flight API
                             │
               ┌─────────────┴──────────────┐
               │                            │
        Backend CoDrone               Backend PX4
               │                            │
         codrone_edu                    MAVSDK
               │                            │
     USB → Controller → Drone       MAVLink → PX4
```

Posteriormente quiero evolucionar hacia:

```text
                 MAVLink
                    │
                    ▼
          ┌────────────────────┐
          │ MAVLink ↔ CoDrone  │
          │   Python Bridge    │
          └─────────┬──────────┘
                    │
              codrone_edu
                    │
              CoDrone EDU
```

La idea es que un programa escrito utilizando nuestra API pueda ejecutar conceptos similares sobre:

```text
CoDrone EDU
PX4 SITL
PX4 hardware real
```

cambiando idealmente solo el backend.

Ejemplo conceptual:

```python
from px4_codrone import Vehicle

drone = Vehicle("codrone")

# Más adelante:
# drone = Vehicle("px4")

drone.connect()
drone.arm()
drone.takeoff(0.8)
drone.goto(x=1.0, y=0.0, z=0.8)
drone.land()
```

Este ejemplo es solamente conceptual.

No debes inventar métodos que todavía no hayamos implementado.

---

# 2. Filosofía del proyecto

Quiero desarrollar esto como un proyecto serio, modular, documentado y potencialmente utilizable con estudiantes universitarios.

Las prioridades son:

1. Seguridad.
2. Reproducibilidad.
3. Modularidad.
4. Código legible.
5. Documentación.
6. Pruebas antes de vuelo.
7. Separación entre hardware y lógica.
8. Posibilidad de usar simuladores.
9. Compatibilidad futura con PX4/MAVLink.
10. Valor académico y educativo.

No quiero un script monolítico que controle un dron.

Quiero construir progresivamente una pequeña plataforma.

---

# 3. Regla fundamental

**NO avances automáticamente a la fase siguiente.**

Cada fase debe:

1. implementarse;
2. probarse;
3. mostrar resultados;
4. detectar errores;
5. documentarse;
6. validarse conmigo.

Solo después continuaremos con la siguiente fase.

Si una prueba falla:

* no ocultes el error;
* no agregues workarounds arbitrarios;
* diagnostica primero;
* explícame la causa probable;
* propón una prueba para confirmarla;
* realiza el cambio mínimo necesario.

---

# 4. Verificación documental

Antes de utilizar funciones, clases, parámetros o mensajes específicos:

**consulta la documentación oficial actual correspondiente.**

Prioridad de fuentes:

1. documentación oficial de CoDrone EDU / Robolink;
2. documentación oficial PX4;
3. documentación oficial MAVSDK;
4. documentación oficial MAVLink;
5. repositorios oficiales correspondientes.

No inventes APIs.

Si una función cambió entre versiones, indícalo.

Registra las versiones utilizadas en:

```text
README.md
requirements.txt
pyproject.toml
```

según corresponda.

---

# 5. Arquitectura deseada

Quiero que gradualmente construyamos algo similar a:

```text
px4-codrone/
│
├── README.md
├── LICENSE
├── pyproject.toml
├── requirements.txt
├── .gitignore
│
├── docs/
│   ├── architecture.md
│   ├── hardware.md
│   ├── telemetry.md
│   ├── safety.md
│   └── mavlink_mapping.md
│
├── examples/
│   ├── 00_connect.py
│   ├── 01_telemetry.py
│   ├── 02_takeoff_land.py
│   ├── 03_manual_axes.py
│   ├── 04_position.py
│   └── ...
│
├── src/
│   └── px4_codrone/
│       ├── __init__.py
│       │
│       ├── vehicle.py
│       │
│       ├── telemetry.py
│       │
│       ├── types.py
│       │
│       ├── exceptions.py
│       │
│       ├── config.py
│       │
│       ├── backends/
│       │   ├── __init__.py
│       │   ├── base.py
│       │   ├── codrone.py
│       │   └── px4.py
│       │
│       ├── control/
│       │   ├── __init__.py
│       │   ├── pid.py
│       │   ├── position.py
│       │   └── velocity.py
│       │
│       └── mavlink/
│           ├── __init__.py
│           ├── bridge.py
│           ├── mapping.py
│           └── messages.py
│
├── tests/
│   ├── test_vehicle.py
│   ├── test_telemetry.py
│   ├── test_codrone_backend.py
│   └── ...
│
└── tools/
    ├── telemetry_logger.py
    └── sensor_monitor.py
```

No es obligatorio crear todos esos archivos inmediatamente.

La estructura debe crecer a medida que se necesite.

---

# 6. Separación de responsabilidades

Quiero separar claramente:

```text
Aplicación
    ↓
Vehicle API
    ↓
Backend interface
    ↓
Backend específico
```

Por ejemplo:

```text
Vehicle
 ├── CoDroneBackend
 └── PX4Backend
```

La aplicación principal no debería necesitar saber cómo se comunica físicamente con el dron.

---

# 7. Backend abstracto

Más adelante quiero tener una interfaz conceptual parecida a:

```python
class VehicleBackend:

    def connect(self):
        ...

    def disconnect(self):
        ...

    def arm(self):
        ...

    def takeoff(self, altitude=None):
        ...

    def land(self):
        ...

    def emergency_stop(self):
        ...

    def get_telemetry(self):
        ...
```

Pero:

**no implementes todavía métodos que no podamos soportar correctamente.**

La API debe evolucionar junto con nuestras pruebas.

---

# 8. FASE 0 — Preparación del proyecto

Primero debemos:

1. revisar el entorno actual;
2. comprobar versión de Python;
3. comprobar sistema operativo;
4. determinar cómo está conectado el controlador CoDrone EDU;
5. crear entorno virtual;
6. instalar librerías necesarias;
7. verificar que Python puede importar la biblioteca del CoDrone;
8. determinar versión de la biblioteca;
9. crear repositorio;
10. crear estructura inicial.

Antes de instalar algo innecesario, explícame qué necesitas.

No instalar todavía:

```text
ROS 2
Gazebo
PX4
QGroundControl
MAVSDK
pymavlink
```

salvo que sean necesarios para una fase concreta.

Primero CoDrone.

---

# 9. FASE 1 — Comunicación básica con CoDrone

La primera prueba debe ser completamente segura.

**NO volar.**

Crear:

```text
examples/00_connect.py
```

El objetivo será:

```text
PC
 ↓ USB
CoDrone Controller
 ↓
CoDrone EDU
```

La prueba debe intentar:

```text
crear objeto
conectar
parear si corresponde
consultar información básica
consultar batería
cerrar conexión
```

Mostrar claramente en consola:

```text
[INFO] Library version:
[INFO] Connecting...
[OK] Controller detected
[OK] Drone paired
[INFO] Battery:
[INFO] Firmware:
[INFO] Closing connection
[OK] Test completed
```

No inventar información si la API no la entrega.

---

# 10. FASE 2 — Sensor Explorer

Antes de volar quiero conocer qué telemetría obtenemos realmente.

Crear:

```text
examples/01_telemetry.py
```

y posiblemente:

```text
tools/sensor_monitor.py
```

Explorar, según disponibilidad real de la API:

```text
battery
roll
pitch
yaw
height
x
y
z
velocity_x
velocity_y
velocity_z
range_front
range_bottom
optical_flow
temperature
state
```

No asumir que todos existen.

Determinar experimentalmente qué entrega realmente el CoDrone EDU.

Mostrar los valores aproximadamente a:

```text
5 Hz
```

o una frecuencia razonable que no sature la comunicación.

---

# 11. FASE 3 — Telemetry Logger

Crear un logger independiente de vuelo.

Formato inicial:

```csv
timestamp,
battery,
roll,
pitch,
yaw,
x,
y,
z,
vx,
vy,
vz,
front_range,
bottom_range
```

Solo incluir campos disponibles.

Guardar en:

```text
logs/
```

Ejemplo:

```text
logs/telemetry_2026-08-17_235500.csv
```

Utilizar timestamps reales.

---

# 12. FASE 4 — Primer vuelo

Solamente después de validar comunicación y sensores.

Primer vuelo:

```text
takeoff
↓
hover
↓
esperar
↓
land
```

Crear:

```text
examples/02_takeoff_land.py
```

Debe existir manejo de excepciones.

Conceptualmente:

```python
try:
    connect()
    takeoff()
    hover()
    land()

except KeyboardInterrupt:
    land()

except Exception:
    emergency_stop_if_safe()

finally:
    close()
```

Implementar esto según las capacidades REALES de la API.

---

# 13. Seguridad

Todos los scripts que vuelen deben considerar:

* timeout;
* KeyboardInterrupt;
* pérdida de comunicación;
* aterrizaje seguro cuando sea posible;
* emergency stop;
* cierre de conexión;
* límites de duración;
* límites de comandos.

Nunca realizar vuelos largos durante desarrollo inicial.

Preferir inicialmente:

```text
0.5 - 1 metro
```

de desplazamiento máximo.

Nada de vuelos autónomos complejos hasta validar control básico.

---

# 14. FASE 5 — Control elemental de ejes

Investigar y probar individualmente:

```text
roll
pitch
yaw
throttle
```

Crear:

```text
examples/03_manual_axes.py
```

Realizar movimientos pequeños.

Ejemplo conceptual:

```text
pitch + pequeño
hover

pitch - pequeño
hover

roll + pequeño
hover

roll - pequeño
hover

yaw + pequeño
hover

yaw - pequeño
hover
```

Registrar telemetría durante las pruebas.

---

# 15. Caracterización experimental

Quiero comenzar a caracterizar el dron.

Por ejemplo:

```text
command_pitch → velocidad observada
command_roll  → velocidad observada
throttle      → velocidad vertical
yaw           → yaw_rate
```

Crear experimentos reproducibles.

Ejemplo:

```text
pitch command = 10
duration = 1 s

registrar:
posición inicial
posición final
velocidad media
```

Repetir para:

```text
10
20
30
40
```

si es seguro.

Esto permitirá construir posteriormente nuestra capa de control.

---

# 16. FASE 6 — Modelo de telemetría común

Crear una estructura propia.

Ejemplo conceptual:

```python
@dataclass
class Telemetry:
    timestamp: float

    battery: float | None

    roll: float | None
    pitch: float | None
    yaw: float | None

    x: float | None
    y: float | None
    z: float | None

    vx: float | None
    vy: float | None
    vz: float | None

    front_range: float | None
    bottom_range: float | None
```

El backend CoDrone deberá transformar sus datos internos a este modelo.

Después PX4 utilizará el mismo modelo.

---

# 17. Sistemas de coordenadas

Este punto será muy importante.

Documentar cuidadosamente:

```text
CoDrone coordinate system
PX4 NED
ROS ENU
```

No asumir que:

```text
+x
+y
+z
```

significan lo mismo.

Crear:

```text
docs/coordinate_systems.md
```

Definir conversiones explícitas.

---

# 18. FASE 7 — API Vehicle común

Cuando el backend CoDrone esté estable, crear una API de alto nivel.

Objetivo conceptual:

```python
vehicle = Vehicle(backend="codrone")

vehicle.connect()

vehicle.takeoff()

vehicle.set_velocity(
    vx=0.3,
    vy=0,
    vz=0
)

vehicle.land()
```

Pero solo implementar lo que sea técnicamente posible.

---

# 19. FASE 8 — Control de velocidad

Convertir:

```text
vx
vy
vz
yaw_rate
```

en:

```text
pitch
roll
throttle
yaw
```

Inicialmente mediante una relación experimental simple.

Posteriormente usar realimentación.

Arquitectura:

```text
velocity setpoint
       ↓
velocity controller
       ↓
roll / pitch / throttle
       ↓
CoDrone
       ↓
optical flow
       ↓
velocity estimate
       └──────── feedback
```

---

# 20. FASE 9 — PID

Crear:

```text
src/px4_codrone/control/pid.py
```

Implementar PID genérico con:

```text
Kp
Ki
Kd
setpoint
measurement
dt
output limits
integral limits
anti-windup
```

Con tests unitarios.

No probar inmediatamente en vuelo.

Primero probar PID con señales sintéticas.

---

# 21. FASE 10 — Position Controller

Objetivo:

```python
vehicle.goto(
    x=1.0,
    y=0.0,
    z=0.8
)
```

Arquitectura conceptual:

```text
position setpoint
       ↓
position controller
       ↓
velocity setpoint
       ↓
velocity controller
       ↓
roll/pitch/throttle
       ↓
CoDrone
       ↓
position sensors
       └──────── feedback
```

Añadir:

```text
position tolerance
velocity limit
timeout
```

Nunca permitir comandos ilimitados.

---

# 22. FASE 11 — PX4 SITL

Solo cuando la API del CoDrone esté madura.

Instalar y configurar:

```text
PX4 SITL
MAVSDK
```

Crear:

```text
PX4Backend
```

El objetivo es ejecutar aproximadamente:

```python
vehicle = Vehicle(backend="px4")

vehicle.connect()
vehicle.arm()
vehicle.takeoff()
vehicle.goto(...)
vehicle.land()
```

sin modificar la aplicación.

---

# 23. PX4Backend

Internamente utilizar inicialmente:

```text
MAVSDK
```

antes que ROS 2.

Investigar las APIs actuales oficiales para:

```text
connect
arm
takeoff
land
position
velocity
yaw
offboard
telemetry
```

No copiar APIs antiguas sin comprobar versión.

---

# 24. FASE 12 — Comparación CoDrone vs PX4

Crear tests equivalentes.

Ejemplo:

```text
takeoff
goto 1 m forward
hover
return
land
```

Comparar:

```text
CoDrone real
PX4 SITL
```

Registrar:

```text
posición
velocidad
error
tiempo
trayectoria
```

---

# 25. FASE 13 — MAVLink

Después estudiar el protocolo MAVLink.

No intentar implementar todo MAVLink.

Crear un subset educativo.

Inicialmente considerar:

```text
HEARTBEAT
SYS_STATUS
BATTERY_STATUS
ATTITUDE
LOCAL_POSITION_NED
DISTANCE_SENSOR
```

y posteriormente comandos:

```text
COMMAND_LONG
SET_POSITION_TARGET_LOCAL_NED
SET_ATTITUDE_TARGET
```

Verificar los mensajes y campos contra la especificación MAVLink vigente.

---

# 26. FASE 14 — MAVLink Bridge

Crear:

```text
src/px4_codrone/mavlink/bridge.py
```

Arquitectura:

```text
MAVLink Client
      │
      ▼
Python MAVLink Bridge
      │
      ▼
CoDroneBackend
      │
      ▼
CoDrone EDU
```

En dirección contraria:

```text
CoDrone sensors
      │
      ▼
Telemetry model
      │
      ▼
MAVLink encoder
      │
      ▼
Ground station / client
```

---

# 27. HEARTBEAT

Primer experimento MAVLink:

hacer que nuestro bridge se presente como un vehículo MAVLink.

Objetivo inicial:

```text
Bridge
  ↓
HEARTBEAT
  ↓
MAVLink client
```

Sin controlar el dron.

Primero solamente comunicación.

---

# 28. Telemetría MAVLink

Posteriormente mapear:

```text
CoDrone battery
        ↓
BATTERY_STATUS

CoDrone attitude
        ↓
ATTITUDE

CoDrone position
        ↓
LOCAL_POSITION_NED

CoDrone range
        ↓
DISTANCE_SENSOR
```

Documentar todas las conversiones.

Crear:

```text
docs/mavlink_mapping.md
```

con tabla:

| CoDrone    | Internal API | MAVLink              | Conversion | Estado |
| ---------- | ------------ | -------------------- | ---------- | ------ |
| Battery    | battery      | BATTERY_STATUS       | ...        |        |
| Roll       | roll         | ATTITUDE.roll        | deg→rad    |        |
| Pitch      | pitch        | ATTITUDE.pitch       | deg→rad    |        |
| Yaw        | yaw          | ATTITUDE.yaw         | deg→rad    |        |
| Position X | x            | LOCAL_POSITION_NED.x | ...        |        |
| Range      | front        | DISTANCE_SENSOR      | ...        |        |

---

# 29. Comandos MAVLink

Después:

```text
MAVLink command
        ↓
bridge
        ↓
setpoint
        ↓
controller
        ↓
CoDrone
```

Nunca mapear directamente un mensaje MAVLink a motores sin capa de seguridad.

---

# 30. FASE 15 — Ground Control Station

Una meta posterior es investigar si nuestro bridge puede aparecer ante:

```text
QGroundControl
```

como un vehículo MAVLink básico.

Inicialmente únicamente:

```text
heartbeat
battery
attitude
position
```

No intentar engañar a QGroundControl implementando funciones que no soportamos.

---

# 31. FASE 16 — ROS 2

ROS 2 viene después.

Arquitectura potencial:

```text
ROS 2
  │
  ├── PX4
  │
  └── CoDrone bridge
```

Investigar solamente cuando:

```text
CoDrone backend
PX4 backend
MAVLink bridge
```

sean estables.

---

# 32. FASE 17 — Aplicaciones avanzadas

Luego podremos experimentar con:

```text
path planning
waypoints
computer vision
object tracking
AprilTags
autonomous navigation
formation control
multi-drone
swarm
collision avoidance
SLAM
AI
```

Siempre incrementalmente.

---

# 33. Modo simulación

Quiero poder desarrollar componentes sin tener el dron encendido.

Crear eventualmente:

```text
MockBackend
```

o:

```text
SimulatedBackend
```

para pruebas.

Ejemplo:

```python
vehicle = Vehicle("mock")
```

Esto debe permitir probar:

```text
state machine
PID
goto
timeouts
exceptions
mission logic
```

sin hardware.

---

# 34. State machine

Más adelante quiero representar estados de vehículo.

Por ejemplo:

```text
DISCONNECTED
CONNECTED
READY
ARMED
TAKING_OFF
FLYING
LANDING
EMERGENCY
ERROR
```

No permitir operaciones inconsistentes.

Ejemplo:

```text
goto()
```

no debería ejecutarse si el vehículo está:

```text
DISCONNECTED
```

---

# 35. Manejo de errores

Crear excepciones propias cuando sea necesario:

```text
ConnectionError
PairingError
TelemetryError
FlightError
TimeoutError
UnsupportedCommandError
EmergencyError
```

Evitar:

```python
except:
    pass
```

No ocultar excepciones.

---

# 36. Logging

Utilizar:

```python
logging
```

en lugar de llenar el código de `print()`.

Ejemplo:

```text
INFO
WARNING
ERROR
DEBUG
```

Formato útil:

```text
2026-08-17 23:45:21 INFO CoDroneBackend Connected
```

---

# 37. Configuración

Evitar constantes distribuidas por el código.

Más adelante usar configuración para:

```text
max_velocity
max_altitude
max_pitch
max_roll
telemetry_rate
command_timeout
position_tolerance
```

Ejemplo:

```yaml
safety:
  max_altitude_m: 1.5
  max_velocity_m_s: 0.5
```

---

# 38. Tests

Utilizar:

```text
pytest
```

cuando corresponda.

Separar:

```text
unit tests
integration tests
hardware tests
flight tests
```

Los tests que necesitan dron físico deben quedar claramente marcados.

Ejemplo:

```text
@pytest.mark.hardware
```

---

# 39. Regla de seguridad para tests

Un simple:

```bash
pytest
```

**jamás debe hacer despegar el dron.**

Los vuelos reales deben requerir una acción explícita.

Por ejemplo:

```bash
python examples/02_takeoff_land.py
```

o una flag:

```bash
pytest --hardware-flight
```

---

# 40. Git

Trabajar de forma incremental.

Después de cada hito estable:

```text
git diff
git status
tests
commit
```

Commits pequeños.

Ejemplos:

```text
feat: add CoDrone connection test
feat: add telemetry logger
feat: add backend abstraction
feat: add PX4 SITL backend
feat: add MAVLink heartbeat bridge
```

No realizar commits automáticos sin mostrarme antes los cambios, salvo que expresamente te lo autorice.

---

# 41. README

Mantener actualizado:

```text
README.md
```

Debe incluir:

```text
objetivo
arquitectura
hardware
instalación
primer test
estado actual
roadmap
limitaciones
seguridad
```

---

# 42. Documentación de experimentos

Cada experimento importante debe registrar:

```text
fecha
hardware
firmware
software version
objetivo
procedimiento
resultado
problemas
conclusiones
```

Podemos usar:

```text
docs/experiments/
```

---

# 43. Diseño pensando en docencia

Este proyecto eventualmente podría utilizarse para enseñar:

```text
Python
robótica
control automático
sistemas embebidos
telemetría
protocolos
MAVLink
PX4
ROS 2
visión artificial
navegación autónoma
```

Por ello el código debe ser comprensible.

No sobreingenierizar innecesariamente.

---

# 44. Objetivo académico

Una de las ideas centrales es que un estudiante pueda aprender inicialmente con:

```python
Vehicle("codrone")
```

y posteriormente pasar a:

```python
Vehicle("px4")
```

manteniendo conceptos similares.

El CoDrone serviría como plataforma educativa accesible y PX4 como transición hacia una arquitectura profesional.

---

# 45. Nombre provisional

Nombre técnico provisional:

```text
px4-codrone
```

Descripción:

```text
Python compatibility and MAVLink bridge layer between CoDrone EDU and PX4-style vehicle control.
```

También puede describirse conceptualmente como:

```text
PX4-compatible Python Flight Interface for CoDrone EDU
```

o:

```text
MAVLink/PX4 Educational Bridge for CoDrone EDU
```

No afirmar que CoDrone EDU ejecuta PX4.

---

# 46. Muy importante: no confundir conceptos

Mantener siempre diferenciados:

```text
CoDrone firmware
CoDrone Python API
Nuestra Flight API
MAVLink protocol
MAVSDK
PX4 autopilot
PX4 SITL
ROS 2
QGroundControl
```

Nuestra capa proporciona compatibilidad conceptual y eventualmente protocolar.

No significa que PX4 esté ejecutándose dentro del CoDrone.

---

# 47. Forma en que debes trabajar conmigo

En cada etapa quiero que respondas con esta estructura:

```text
FASE X — nombre

Objetivo
Qué vamos a modificar
Archivos involucrados
Código
Comandos que debo ejecutar
Resultado esperado
Qué observar
Posibles errores
Cómo validar
```

Después de que yo te entregue la salida de terminal:

1. analízala;
2. confirma qué funcionó;
3. identifica qué falló;
4. no supongas resultados;
5. entrega el siguiente paso.

---

# 48. No asumir mi entorno

Antes de entregar comandos que dependan del sistema, determina:

```text
SO
Python
pip
arquitectura
puerto serial
hardware conectado
```

No asumir:

```text
COM3
/dev/ttyUSB0
/dev/ttyACM0
```

Detectarlo primero.

---

# 49. Evitar cambios destructivos

No borrar:

```text
entornos
repositorios
configuración
drivers
firmware
```

sin explicarlo previamente.

Antes de cambios importantes:

```text
mostrar estado actual
hacer backup cuando corresponda
aplicar cambio
validar
```

---

# 50. Primer objetivo inmediato

Ahora quiero empezar exclusivamente por:

# FASE 0 + FASE 1

Es decir:

```text
preparar entorno
↓
crear proyecto
↓
instalar biblioteca CoDrone
↓
detectar controlador
↓
conectar
↓
parear
↓
leer información
↓
leer batería
↓
cerrar conexión
```

**SIN hacer volar todavía el dron.**

---

# 51. Tu primera respuesta

No escribas todavía toda la plataforma.

Primero:

1. revisa si ya existe un repositorio o directorio de trabajo;
2. revisa los archivos existentes;
3. determina SO y versión de Python;
4. verifica la documentación oficial actual de `codrone-edu`;
5. determina la versión recomendada;
6. dime exactamente qué vamos a hacer en Fase 0;
7. crea solamente la estructura mínima necesaria;
8. prepara la primera prueba de conexión sin vuelo.

Si tienes acceso al terminal, ejecuta primero comandos **solo de lectura** para determinar el estado del entorno.

Ejemplos:

```bash
pwd
ls -la
git status
python --version
python3 --version
pip --version
```

adapta los comandos al sistema operativo real.

No instales ni modifiques nada hasta haber inspeccionado primero el entorno.

Después comienza la implementación de:

```text
FASE 0 — Entorno y proyecto
FASE 1 — CoDrone connection test
```

El primer hito del proyecto será conseguir:

```text
[OK] Python environment
[OK] codrone_edu library
[OK] Controller detected
[OK] Drone connection/pairing
[OK] Battery telemetry
[OK] Clean disconnect
```

sin despegar el CoDrone.

Empieza ahora con la inspección del entorno y la Fase 0.
