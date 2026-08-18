# CoDrone EDU EIT: acta de proyecto y hoja de ruta inicial

Estado: borrador para validación

Idioma principal: español

Repositorio de trabajo: `Ericktz/JCoDroneEdu`

Repositorio de origen: `scerruti/JCoDroneEdu`

## 1. Propósito

CoDrone EDU EIT convertirá ocho equipos CoDrone EDU en una plataforma docente
reutilizable para cursos universitarios de programación, sistemas, redes, datos,
inteligencia artificial, electrónica y gestión de proyectos.

El producto no será solamente una traducción de la biblioteca Java. Incluirá:

- un portal público en español;
- guías de instalación y seguridad;
- laboratorios reproducibles y evaluables;
- proyectos por asignatura y nivel de dificultad;
- código de referencia en Java y Python cuando corresponda;
- material para docentes, estudiantes y ayudantes;
- una referencia de API generada automáticamente;
- trazabilidad entre resultados de aprendizaje, actividades y evaluación.

## 2. Línea base verificada

La inspección inicial del 17 de agosto de 2026 determinó lo siguiente:

- El fork conserva una biblioteca Java/Gradle orientada a CoDrone EDU.
- La rama principal exige Java 21 según su configuración y documentación.
- El repositorio original publica JavaDoc mediante una rama `gh-pages`.
- El fork contiene solamente `main`; no heredó `gh-pages` y GitHub Pages no está
  habilitado actualmente para `Ericktz/JCoDroneEdu`.
- No existe todavía una aplicación web editorial en la rama principal.
- El equipo local dispone de Node.js 22, pero no tiene un JDK instalado; por ello
  la suite Java todavía no tiene una línea base local certificada.
- Hay deuda técnica heredada que debe corregirse en cambios separados: contenido
  duplicado en `README.md` y `build.gradle.kts`, artefactos compilados versionados y
  documentos de trabajo acumulados en la raíz.

Estos hallazgos describen el estado inicial; no implican que la biblioteca sea
inutilizable. Cada corrección deberá contar con pruebas y una revisión aislada.

## 3. Principios de diseño

1. **Seguridad antes que vuelo.** Todo laboratorio comienza en simulación o con
   pruebas sin hélices cuando sea viable.
2. **Contenido antes que framework.** Las clases se escriben en Markdown/MDX para
   poder migrarlas y versionarlas.
3. **Español como experiencia principal.** Los nombres de clases, métodos y
   protocolos permanecen en su forma técnica original.
4. **No duplicar la API.** JavaDoc se genera desde el código; el portal explica y
   enlaza la API en vez de mantener una copia manual divergente.
5. **Cambios pequeños y comprobables.** Biblioteca, portal y laboratorios tienen
   validaciones independientes.
6. **Compatibilidad con el origen.** Las adaptaciones institucionales se ubican en
   directorios nuevos para reducir conflictos al sincronizar el fork.
7. **Privacidad por diseño.** No se publican nombres, calificaciones ni telemetría
   identificable de estudiantes.
8. **Accesibilidad.** Navegación por teclado, contraste, texto alternativo y una
   alternativa tabular para toda visualización.

## 4. Arquitectura propuesta

Se propone mantener la biblioteca heredada y agregar capas claramente separadas:

```text
JCoDroneEdu/
├── src/                       # Biblioteca Java heredada
├── flight-patterns/           # Patrones Java heredados
├── site/                      # Portal Astro + Starlight
├── curriculum/
│   ├── foundations/           # Programación y algoritmos
│   ├── systems-networks/      # SO, distribuidos y redes
│   ├── data-ai/               # Datos, IA, señales e imágenes
│   └── projects-management/   # Proyectos, innovación y agilidad
├── examples/
│   ├── java/
│   └── python/
├── hardware/                  # Inventario, preparación y seguridad
└── docs/                      # ADR, operación y mantenimiento
```

La incorporación de estos directorios será incremental. No se moverá código
heredado durante la construcción del primer portal.

## 5. Portal y traducción

La opción propuesta es **Astro con Starlight**, generando un sitio completamente
estático. Los motivos y alternativas quedan registrados en
[`adr/0001-plataforma-del-portal.md`](adr/0001-plataforma-del-portal.md).

La traducción tendrá cuatro capas:

1. **Interfaz:** menús, buscador, navegación, avisos y metadatos en español.
2. **Inicio rápido:** instalación, conexión, primer programa y solución de fallas.
3. **Material docente:** laboratorios, rúbricas, resultados de aprendizaje y
   guías para ayudantes.
4. **Referencia técnica:** JavaDoc automático y un glosario español-inglés. No se
   traducirán identificadores de código.

Cada página traducida deberá registrar la fuente, la versión revisada y la fecha de
última verificación. Se evitará traducir material externo cuya licencia no lo
permita.

## 6. Mapa docente inicial

La prioridad indica qué tan directa es la integración con los drones: A es uso
central, B es un laboratorio complementario y C es un caso de estudio o proyecto.

| Área o asignatura | Prioridad | Primer proyecto viable |
| --- | --- | --- |
| Programación | A | Secuencias, decisiones y bucles para una ruta segura |
| Programación avanzada | A | API orientada a objetos y controlador de misión |
| Estructuras de datos y algoritmos | A | Cola de comandos, historial y búsqueda de rutas |
| Grafos y algoritmos | A | Grafo de waypoints y ruta de menor costo |
| Bases de datos | B | Modelo relacional para vuelos, drones y sensores |
| Bases de datos avanzadas | B | Series temporales, particionado y consultas analíticas |
| Desarrollo web y móvil | A | Tablero de telemetría y planificador de misiones |
| Sistemas operativos | A | Puerto serial, concurrencia, temporización y recursos |
| Sistemas distribuidos | A | Coordinación tolerante a fallas de múltiples drones |
| Ingeniería de software | A | Requisitos, pruebas, CI y mantenibilidad de una misión |
| Arquitectura de software | A | Separación entre dominio, hardware y telemetría |
| Arquitecturas emergentes | B | Gemelo digital y procesamiento en el borde |
| Inteligencia artificial | A | Agente reactivo para evitar obstáculos |
| Deep learning | B | Clasificación visual en estación terrestre o simulador |
| Reconocimiento de patrones en imágenes | A | Detección y clasificación de marcadores de color |
| Data science | A | Limpieza, exploración y modelado de telemetría |
| Procesamiento para big data | B | Ingesta y agregación de vuelos simulados a escala |
| Probabilidades y estadísticas | A | Incertidumbre, calibración y repetibilidad de sensores |
| Señales y sistemas | A | Respuesta temporal, muestreo y filtros de sensores |
| Procesamiento digital de señales | A | Filtrado de IMU, ruido y detección de eventos |
| Electrónica y electrotecnia | A | Energía, motores, sensores y presupuesto de potencia |
| Arquitectura y organización de computadores | B | Representación binaria, tramas y costo de procesamiento |
| Comunicaciones digitales | A | Codificación, errores, latencia y pérdida de paquetes |
| Tecnologías inalámbricas | A | RSSI, interferencia, alcance y diseño experimental |
| Redes de datos | B | Telemetría sobre una pasarela y análisis de tráfico |
| Taller de redes y servicios | A | API local segura para misiones y telemetría |
| Redes móviles celulares | C | Pasarela terrestre y comparación de latencia de acceso |
| Redes ópticas de datos | C | Caso de estudio de backhaul para telemetría masiva |
| Comunicación por luz visible | B | Canal experimental con LED y sensor de color |
| Algoritmos de ruteo y redes resilientes | A | Replanificación ante nodos o enlaces no disponibles |
| Criptografía y seguridad en redes | A | Autenticidad, replay y protección de comandos |
| Proyecto en TIC I | A | Descubrimiento, prototipo y validación con usuarios |
| Proyecto en TIC II | A | Producto integrado, evaluación y transferencia |
| Evaluación de proyectos TIC | B | Costos, riesgos, beneficios e indicadores del laboratorio |
| Procesos ágiles | A | Backlog, iteraciones y retrospectivas con evidencia de vuelo |
| Gestión organizacional | C | Operación del laboratorio y gestión de activos |
| Gestión en innovación tecnológica | B | Portafolio de casos, propiedad intelectual y adopción |

## 7. Operación con ocho equipos

### Identidad e inventario

- Asignar identificadores físicos `DRONE-01` a `DRONE-08` y
  `CTRL-01` a `CTRL-08`.
- Mantener una ficha por equipo: serie, firmware, baterías, hélices, reparaciones,
  incidentes, ciclos aproximados y responsable de revisión.
- Usar nombres lógicos y colores consistentes en software, cajas y documentación.

### Modelo de clase

- Hasta ocho equipos de estudiantes trabajan con un kit asignado.
- Los roles rotan: piloto de seguridad, programador, observador y analista de datos.
- La primera ejecución es simulada o sin vuelo; la segunda es una prueba acotada;
  la tercera puede ejecutar la misión completa.
- Las baterías se rotan y cargan fuera de la zona de vuelo siguiendo las
  instrucciones del fabricante y las normas de la universidad.

### Seguridad mínima

- Zona de vuelo delimitada, aforo controlado y protección ocular cuando proceda.
- Inspección previa de hélices, protectores, batería y enlace con el controlador.
- Comando de aterrizaje de emergencia ensayado antes de cada actividad.
- Altura, velocidad, duración y número de drones simultáneos limitados por guía.
- Registro de incidentes y retiro inmediato de cualquier equipo dañado.
- Ningún laboratorio de vuelo se publica como listo hasta ser validado por una
  persona responsable con el hardware real.

## 8. Hoja de ruta

### Fase 0: saneamiento y reproducibilidad

- Instalar y fijar JDK 21.
- Ejecutar la suite y registrar la línea base.
- Añadir el remoto `upstream` y documentar el procedimiento de sincronización.
- Separar artefactos generados del código fuente.
- Corregir duplicaciones y enlaces que apuntan al repositorio original.
- Definir ramas, revisión y convenciones de commits.

### Fase 1: portal mínimo viable

- Crear `site/` con Astro y Starlight.
- Publicar inicio, visión, seguridad, instalación y primer vuelo.
- Integrar JavaDoc generado bajo una ruta estable.
- Validar enlaces, accesibilidad básica, formato y compilación.
- Desplegar el artefacto estático con GitHub Actions.

### Fase 2: trayecto fundamental

- Programación, programación avanzada, estructuras de datos y algoritmos.
- Plantilla común de laboratorio, rúbrica y conjunto de pruebas.
- Tres niveles por actividad: guiado, desafío y extensión.

### Fase 3: datos, IA, sistemas y redes

- Telemetría reproducible y conjuntos de datos anonimizados.
- Laboratorios de señales, estadística, IA, imágenes y data science.
- Pasarela de telemetría para web, bases de datos y sistemas distribuidos.
- Amenazas y controles para comunicaciones y servicios.

### Fase 4: proyectos integradores

- Catálogo de problemas y criterios de selección.
- Proyectos TIC I y II con hitos, riesgos, pruebas y demostración final.
- Evidencias de aprendizaje y proceso de mejora semestral.

## 9. Plantilla obligatoria para cada laboratorio

Cada laboratorio nuevo deberá incluir:

1. asignatura, nivel, duración y prerrequisitos;
2. resultados de aprendizaje observables;
3. materiales, versiones y preparación del equipo;
4. riesgos, límites y procedimiento de emergencia;
5. actividad previa sin hardware;
6. instrucciones para estudiantes;
7. notas privadas o separadas para docentes;
8. código inicial y solución de referencia;
9. pruebas automáticas o evidencia reproducible;
10. rúbrica, preguntas de reflexión y extensiones;
11. procedimiento de limpieza, apagado y devolución;
12. fecha y hardware con que fue validado.

## 10. Criterios de calidad

Un cambio se considera terminado cuando:

- compila en un entorno limpio;
- supera las pruebas automáticas aplicables;
- no contiene secretos, datos personales ni artefactos locales;
- sus enlaces internos son válidos;
- la documentación y el código coinciden;
- declara versiones de Java, Python, firmware y dependencias pertinentes;
- incluye instrucciones de reversión o recuperación cuando cambia despliegues;
- un laboratorio de vuelo fue revisado con el hardware indicado;
- las decisiones arquitectónicas relevantes se registran mediante ADR.

## 11. Decisiones pendientes

Antes del primer despliegue deben confirmarse:

- nombre oficial de la universidad, escuela y proyecto;
- logotipos, paleta, tipografías y reglas institucionales de accesibilidad;
- licencia de los nuevos contenidos docentes, código y conjuntos de datos;
- versión real de firmware de los ocho drones y sistemas operativos del laboratorio;
- tamaño de los equipos de estudiantes y duración habitual de cada sesión;
- alcance de la primera asignatura piloto.
