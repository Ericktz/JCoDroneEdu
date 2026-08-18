---
title: Trayecto Java
description: Estado y uso de la biblioteca JCoDroneEdu.
---

## Estado actual

El fork contiene una biblioteca Java orientada al aula, construida con Gradle y
JDK 21. La línea base local supera su suite automatizada; las pruebas que requieren
hardware se mantienen separadas.

La versión de desarrollo es `1.5.0-SNAPSHOT` y el README declara compatibilidad
objetivo con la API Python 2.6. La versión oficial actual de Python es 2.8, por lo
que esa brecha está registrada como trabajo pendiente.

## Comandos de desarrollo

```bash
./gradlew test --no-daemon --console=plain
./gradlew javadoc
./gradlew build
```

## Referencia

La [API Java generada](/JCoDroneEdu/api/java/latest/) se construye desde los
comentarios JavaDoc del código en cada despliegue del portal.

:::tip[Idioma y código]
Las explicaciones docentes se escriben en español, pero nombres como `Drone`,
`takeoff()` o `getHeight()` no se traducen. Así los ejemplos siguen siendo válidos
y comparables con la documentación oficial.
:::
