---
title: Preparar el entorno
description: Requisitos iniciales para trabajar con CoDrone EDU desde Python o Java.
---

Elige un trayecto y valida primero el software sin iniciar un vuelo. Para el uso en
laboratorio se fijarán versiones por semestre y se probarán con el firmware real de
los ocho equipos.

## Python oficial

Robolink ofrece dos modalidades: un entorno de Python en el navegador y un entorno
de escritorio. La documentación oficial recomienda el navegador cuando no se puede
instalar software y un IDE de escritorio para funciones avanzadas.

Para un proyecto local aislado:

```bash
python3 -m venv .venv
source .venv/bin/activate
python -m pip install "codrone-edu==2.8"
```

Importación explícita verificada para `codrone-edu` 2.8:

```python
from codrone_edu.drone import Drone

drone = Drone()
```

:::note[Alcance de la verificación]
El import se comprobó sin conectar hardware. La comunicación serial, el emparejado
y el vuelo se validarán por separado en los computadores del laboratorio.
:::

Consulta la [guía oficial de instalación de Robolink](https://docs.robolink.com/docs/CoDroneEDU/Python/Setup-and-Installation/)
y el [paquete oficial en PyPI](https://pypi.org/project/codrone-edu/).

## Java del fork

La biblioteca requiere JDK 21. En macOS con Homebrew:

```bash
brew install openjdk@21
export JAVA_HOME="/opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home"
export PATH="/opt/homebrew/opt/openjdk@21/bin:$PATH"
./gradlew test
```

La referencia completa se genera con:

```bash
./gradlew javadoc
```

## Antes de conectar un controlador

1. Identifica el kit asignado y registra su estado.
2. Verifica que el cable USB transporte datos.
3. Retira las hélices para las primeras pruebas seriales cuando el procedimiento lo permita.
4. Ejecuta una prueba de conexión sin comandos de vuelo.
5. Continúa con la [guía de seguridad](/JCoDroneEdu/inicio/seguridad/).
