# ADR 0002: integración gradual de Python y conceptos PX4/MAVLink

Estado: aceptada para Fase 0 y Fase 1

Fecha: 2026-08-18

## Contexto

El fork ya contiene la biblioteca Java, el portal Astro/Starlight y sus procesos
de CI y despliegue. La iniciativa PX4/MAVLink propone añadir una API educativa en
Python que primero controle CoDrone EDU y, en etapas posteriores, pueda compartir
conceptos con un backend PX4.

La propuesta original parte de un repositorio vacío y utiliza el nombre
provisional `px4-codrone`. Aplicarla literalmente duplicaría infraestructura y
podría sugerir capacidades PX4 que todavía no existen.

## Decisión

La iniciativa se integrará inicialmente en este monorepo, bajo `python/`, con la
distribución provisional `codrone-edu-eit`.

Este primer incremento se limita a:

- registrar un entorno Python reproducible;
- detectar puertos seriales y el controlador mediante su VID oficial `1155`;
- preparar una conexión con `codrone-edu==2.8`;
- consultar estado y batería sin activar motores;
- garantizar el cierre en un bloque `finally`;
- probar toda la lógica en CI mediante dobles, sin hardware.

No se crean aún `Vehicle`, backends abstractos, telemetría común, controladores,
MAVSDK, MAVLink, PX4, ROS 2 ni simuladores.

## Motivos

- Mantiene juntas la biblioteca, la documentación y las rutas docentes mientras
  la iniciativa todavía es experimental.
- Evita prometer compatibilidad antes de medir capacidades reales.
- Permite reutilizar CI, GitHub Pages y las políticas de seguridad existentes.
- Hace explícita la puerta de validación física antes de avanzar de fase.

## Consecuencias

- Gradle, Python y Astro conservan dependencias y directorios separados.
- La suite Python predeterminada no abre puertos seriales.
- El ejemplo de conexión puede terminar con error si falta el controlador, sin
  ocultar la causa y sin ejecutar comandos de vuelo.
- Una separación futura a otro repositorio requerirá un ADR nuevo y evidencia de
  que el paquete ya tiene ciclo de vida independiente.

## Puerta para Fase 2

Se requiere documentar una ejecución física exitosa con controlador y dron:
puerto detectado, estado `Ready`, batería válida y desconexión limpia. Además,
deben registrarse operador, equipo, firmware y condiciones del laboratorio.
