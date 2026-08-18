# Propuesta PX4/MAVLink ↔ Python ↔ CoDrone EDU

Estado: aprobada; Fase 0 implementada y Fase 1 preparada sin vuelo

Fecha de ingreso: 2026-08-18

Fuente original: `pasted-text.txt`, adjunto a la tarea de Codex

Huella SHA-256 de la fuente: `5a5ff1a65c1cb12926288c33ee51198178c4921fe332be035d8877ef9e96e3a4`

Este documento conserva de forma estructurada el alcance y las restricciones de
la propuesta original. La fuente completa contiene 1710 líneas y debe consultarse
si una decisión futura requiere el texto literal.

## 1. Propósito

Construir progresivamente una capa Python que permita enseñar y aplicar conceptos
de control de vuelo sobre CoDrone EDU y, posteriormente, sobre PX4, manteniendo
separadas la aplicación, la API de vehículo y la comunicación específica de cada
plataforma.

La propuesta no reemplaza el firmware del CoDrone EDU ni pretende ejecutar PX4 en
el dron. Su alcance es una compatibilidad conceptual y, en fases posteriores, un
bridge protocolar MAVLink ↔ CoDrone EDU.

Arquitectura objetivo:

```text
Aplicación o algoritmo
        ↓
Python Flight API
        ↓
Backend abstracto
   ├── CoDroneBackend → codrone_edu → controlador USB → CoDrone EDU
   └── PX4Backend     → MAVSDK      → MAVLink → PX4 SITL/hardware
```

Evolución posterior:

```text
Cliente MAVLink
      ↕
Bridge Python MAVLink ↔ CoDrone
      ↕
CoDroneBackend → codrone_edu → CoDrone EDU
```

## 2. Principios obligatorios

- Seguridad, reproducibilidad y pruebas antes de vuelo.
- Desarrollo incremental; no avanzar de fase sin implementación, evidencia,
  diagnóstico, documentación y validación del responsable.
- No inventar APIs, telemetría, capacidades ni resultados.
- Verificar cada API contra documentación oficial vigente de Robolink, PX4,
  MAVSDK o MAVLink, según corresponda.
- Registrar versiones de software, firmware, hardware y dependencias.
- Separar lógica de aplicación, API de vehículo, backend y hardware.
- Preferir cambios mínimos y diagnósticos comprobables ante fallos.
- No instalar PX4, ROS 2, Gazebo, QGroundControl, MAVSDK o `pymavlink` antes de
  que una fase aprobada los necesite.
- No ejecutar vuelos desde la suite predeterminada de pruebas.
- No realizar commits automáticos sin autorización explícita.
- No borrar entornos, repositorios, configuración, drivers o firmware sin una
  revisión y autorización específica.

## 3. Capacidades conceptuales

La interfaz podría evolucionar hacia operaciones como conexión, desconexión,
telemetría, despegue, aterrizaje, parada de emergencia, velocidad y posición.
Ninguna de ellas se considera soportada hasta comprobar su semántica y seguridad
en el backend correspondiente.

La API debe ser orientada a capacidades: si CoDrone EDU no ofrece una operación
equivalente a PX4, debe declararse como no soportada o modelarse explícitamente;
no se debe simular una equivalencia falsa.

## 4. Fases propuestas

### Fase 0 — Entorno y proyecto

Inspeccionar repositorio, sistema operativo, Python, `pip`, arquitectura, puertos,
controlador, biblioteca `codrone-edu` y sus versiones. Crear solo el entorno y la
estructura mínimos. No instalar aún el ecosistema PX4/MAVLink.

### Fase 1 — Conexión segura, sin vuelo

Detectar el controlador, conectar o emparejar según la API real, consultar la
información y batería disponibles, y cerrar limpiamente. No activar motores ni
despegar.

### Fases 2 y 3 — Exploración y registro de telemetría

Determinar experimentalmente los campos disponibles y sus unidades, frecuencia,
calidad y latencia. Crear un monitor de sensores y un logger CSV que incluya solo
datos realmente soportados.

### Fases 4 y 5 — Vuelo mínimo y ejes elementales

Solo después de validar conexión y sensores: despegue, hover corto y aterrizaje;
luego movimientos pequeños y aislados de roll, pitch, yaw y throttle. Todo vuelo
debe incluir límites, timeout, interrupción, recuperación y cierre seguro.

### Caracterización experimental

Medir de forma reproducible la relación entre comandos y movimiento observado.
Registrar condiciones, valores iniciales/finales, velocidad, error y conclusión.

### Fases 6 y 7 — Modelo común y Vehicle API

Definir telemetría común con campos opcionales, conversiones explícitas y una API
de alto nivel que dependa de un backend, no del enlace físico.

### Fases 8 a 10 — Velocidad, PID y posición

Convertir setpoints de velocidad a ejes de CoDrone mediante modelos verificados;
añadir realimentación solo después. Probar PID primero con señales sintéticas y
límites/anti-windup. Incorporar posición únicamente con tolerancia, velocidad
máxima y timeout.

### Fases 11 y 12 — PX4 SITL y comparación

Cuando el backend CoDrone y la API común estén maduros, incorporar PX4 SITL y
MAVSDK. Ejecutar escenarios equivalentes y comparar trayectorias, errores,
velocidades y tiempos sin asumir paridad perfecta.

### Fases 13 a 15 — MAVLink, bridge y estación terrestre

Implementar un subconjunto educativo y documentado: comenzar por `HEARTBEAT` sin
control de vuelo; después telemetría como batería, actitud, posición local y
distancia; luego comandos con una capa de seguridad intermedia. Probar una GCS
solo con capacidades honestamente anunciadas.

### Fases 16 y 17 — ROS 2 y aplicaciones avanzadas

Considerar ROS 2 únicamente después de estabilizar backends y bridge. Dejar
planificación, visión, AprilTags, navegación, enjambres, SLAM e IA para fases
avanzadas con validación independiente.

## 5. Temas transversales

- Documentar conversiones entre coordenadas CoDrone, PX4 NED y ROS ENU.
- Añadir un `MockBackend` o `SimulatedBackend` para lógica sin hardware.
- Modelar estados y transiciones válidas del vehículo.
- Usar excepciones específicas y nunca ocultarlas con `except: pass`.
- Usar `logging` estructurado y configuración centralizada para límites.
- Separar tests unitarios, de integración, hardware y vuelo.
- Exigir una activación explícita para cualquier test con vuelo real.
- Mantener README, arquitectura, seguridad, mapeo MAVLink y bitácoras de
  experimentos alineados con el código.

## 6. Hito inmediato originalmente solicitado

El primer hito se limita a Fase 0 y Fase 1:

```text
entorno Python verificado
→ biblioteca codrone_edu verificada
→ controlador detectado
→ conexión/emparejamiento comprobado
→ batería leída si la API la ofrece
→ desconexión limpia
```

No incluye vuelo. La implementación se integró en `python/` dentro del monorepo,
con pruebas que usan dobles y no acceden a hardware. La conexión física,
emparejamiento y batería permanecen pendientes hasta disponer del controlador y
un CoDrone EDU bajo supervisión.
