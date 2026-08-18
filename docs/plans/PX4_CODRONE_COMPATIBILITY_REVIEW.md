# Revisión de compatibilidad: propuesta PX4/Python y CoDrone EDU EIT

Estado: aceptada para Fase 0 y Fase 1 sin vuelo

Fecha de revisión: 2026-08-18

Propuesta relacionada: [`PX4_CODRONE_PROPOSAL.md`](PX4_CODRONE_PROPOSAL.md)

## Dictamen

La propuesta es compatible con el proyecto CoDrone EDU EIT y complementa el
portal académico y la biblioteca Java existentes. No debe ejecutarse literalmente
como si la carpeta estuviera vacía: el repositorio ya tiene historia, CI, portal,
JavaDoc y decisiones arquitectónicas que deben preservarse.

Compatibilidad estimada: **alta, con adaptación de integración obligatoria**.

## Estado verificado al terminar el trabajo anterior

- Rama local y remota: `main` en `df4b2cf`.
- Portal Astro/Starlight en español publicado mediante GitHub Pages.
- CI y despliegue aprobados.
- 545 pruebas Java aprobadas por el trabajo anterior.
- `codrone-edu==2.8` importado y `Drone()` creado/cerrado sin hardware en un
  entorno temporal.
- Conexión física, emparejamiento, batería y vuelo siguen pendientes.
- El proyecto ya contempla Java y Python, telemetría, señales, IA, redes,
  sistemas distribuidos y proyectos docentes.

## Elementos directamente compatibles

| Propuesta | Proyecto actual | Evaluación |
| --- | --- | --- |
| Seguridad antes de vuelo | Principio central del acta y portal | Compatible |
| Separar hardware y lógica | Arquitectura docente y futura capa Python | Compatible |
| Telemetría reproducible | Hoja de ruta de datos, señales e IA | Compatible |
| Pruebas incrementales | CI, criterios de calidad y laboratorios | Compatible |
| Python oficial de Robolink | Trayecto Python ya publicado | Compatible |
| Simulación y PX4 SITL | Extensión para control, sistemas y robótica | Compatible |
| MAVLink y bridge | Extensión académica de protocolos y redes | Compatible |
| Registro de experimentos | Plantilla docente y trazabilidad | Compatible |

## Ajustes necesarios antes de implementar

### 1. No crear otro repositorio automáticamente

La instrucción original parte de “crear repositorio” y propone una raíz
`px4-codrone/`. Esta carpeta ya es el fork `Ericktz/JCoDroneEdu`. Primero debe
decidirse mediante ADR si la capa Python vivirá:

- dentro de este monorepo, por ejemplo en `python/px4_codrone/`; o
- en un repositorio independiente enlazado desde el portal.

La opción recomendada para el primer experimento es mantenerlo en este monorepo,
aislado de Gradle y Astro, hasta disponer de evidencia suficiente para separar un
producto independiente.

### 2. No sustituir la biblioteca Java ni el portal

La nueva capa debe convivir con:

- `src/` y la compilación Gradle de JCoDroneEdu;
- `site/` y su pipeline de GitHub Pages;
- `docs/` y los ADR actuales;
- la ruta Python ya documentada en el portal.

Las dependencias Python no deben mezclarse con `build.gradle.kts` ni con
`site/package.json`.

### 3. Tratar “compatibilidad PX4” como una afirmación verificable

El nombre provisional `px4-codrone` puede inducir a creer que CoDrone EDU ejecuta
PX4 o ofrece todas sus capacidades. Hasta definir criterios y una matriz de
capacidades, la documentación debe hablar de “API educativa inspirada en conceptos
PX4/MAVLink” y marcar todas las diferencias.

### 4. Usar capacidades, no una interfaz rígida prematura

Operaciones como `arm`, `goto`, posición local, velocidad o emergencia pueden no
tener equivalencia directa en `codrone-edu`. Antes de fijar `VehicleBackend`, cada
método debe clasificarse como:

- soportado de forma nativa;
- adaptado con una semántica documentada;
- estimado mediante control externo;
- no soportado.

### 5. Separar pruebas sin hardware, con hardware y con vuelo

El CI predeterminado nunca debe abrir puertos reales ni despegar. Las pruebas deben
usar marcadores y activaciones explícitas; los vuelos requieren además lista de
verificación física, operador responsable, zona segura y límites configurados.

### 6. Reutilizar el trabajo ya completado

La Fase 0 no comienza desde cero. Ya están confirmados macOS, el repositorio, la
rama principal, el paquete oficial `codrone-edu==2.8` y una prueba sin hardware de
creación/cierre. Debe repetirse en un entorno reproducible del proyecto, pero no
volver a auditar o reestructurar innecesariamente el portal.

## Riesgos principales

1. **Semántica falsa:** presentar comandos CoDrone como equivalentes a PX4 sin
   medir unidades, marcos, latencia y límites.
2. **Seguridad física:** ejecutar ejemplos o tests de vuelo por defecto.
3. **Acoplamiento del monorepo:** mezclar Gradle, Python y Astro sin fronteras de
   dependencias y CI.
4. **Alcance excesivo:** intentar PX4, MAVLink, ROS 2, GCS e IA antes de validar
   conexión y telemetría básica.
5. **Datos poco confiables:** asumir que todos los campos propuestos existen o
   tienen la precisión necesaria para control cerrado.
6. **Nombre del producto:** prometer “compatibilidad PX4” antes de definir el
   subconjunto soportado.

## Secuencia recomendada después de aprobar la iniciativa

1. Crear una rama dedicada desde `main` limpio.
2. Escribir un ADR corto para ubicación, nombre y límites del paquete Python.
3. Crear un entorno Python reproducible sin instalar PX4/MAVLink/ROS 2.
4. Registrar versión de Python, `codrone-edu`, sistema y puertos disponibles.
5. Implementar solo una prueba de conexión sin vuelo y con cierre garantizado.
6. Validarla primero sin controlador y después con el hardware bajo supervisión.
7. Documentar resultado, errores y capacidades observadas.
8. Solicitar validación antes de iniciar el explorador de telemetría.

## Archivos que no deben modificarse en el primer incremento

- `src/` Java, salvo que una incompatibilidad demostrada lo exija.
- `build.gradle.kts`.
- workflow de GitHub Pages.
- contenido del portal no relacionado con el trayecto Python.
- configuración de PX4, ROS 2 o MAVLink, porque aún no corresponde.

## Decisión adoptada

Se confirmó:

- **Ubicación:** monorepo, aislada en `python/`.
- **Nombre inicial:** distribución `codrone-edu-eit`; no se presenta todavía como
  una implementación PX4-compatible.
- **Autorización:** concedida para crear rama, archivos, commits y despliegue.
- **Seguridad:** Fase 1 no contiene llamadas de vuelo y la suite predeterminada
  usa dobles de prueba.

El controlador no estaba conectado durante la inspección del 2026-08-18. Por
ello, detectar controlador, emparejar, leer batería y cerrar sobre hardware real
siguen siendo criterios pendientes. No se autoriza avanzar a Fase 2 hasta
registrar esa evidencia y obtener validación del responsable.
