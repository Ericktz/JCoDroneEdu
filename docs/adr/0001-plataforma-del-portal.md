# ADR 0001: plataforma del portal CoDrone EDU EIT

Estado: aceptada

Fecha: 2026-08-18

## Contexto

El fork contiene una biblioteca Java y un proceso de publicación de JavaDoc. No
contiene un portal editorial. El nuevo sitio debe servir material universitario en
español, mantener referencias técnicas, admitir crecimiento por asignatura y poder
publicarse como GitHub Pages o en Vercel.

## Decisión propuesta

Construir el portal en `site/` con **Astro + Starlight**, en modo estático, y
mantener el contenido en Markdown/MDX.

La URL canónica de producción es
`https://ericktz.github.io/JCoDroneEdu/`:

- **GitHub Pages** es el destino canónico para el recurso público, estable, sin
  backend y alojado junto al código.
- **Vercel** queda fuera del primer alcance y podrá evaluarse en el futuro para
  previsualizaciones si aporta una ventaja concreta.

El sitio no dependerá de funciones exclusivas de Vercel durante el primer hito, de
modo que ambos destinos consuman el mismo resultado de compilación.

## Motivos

- Starlight está orientado a documentación y proporciona navegación, búsqueda,
  estructura de contenido e internacionalización.
- Su interfaz dispone de traducciones al español.
- Astro genera HTML estático apropiado para GitHub Pages y Vercel.
- Markdown/MDX reduce el acoplamiento y facilita revisiones de contenido docente.
- El sitio puede incorporar JavaDoc como un artefacto generado sin copiarlo a
  mano ni traducir identificadores técnicos.

## Alternativas consideradas

### Mantener solamente JavaDoc en `gh-pages`

Rechazada como portal principal. JavaDoc es apropiado para referencia de API, pero
no para rutas de aprendizaje, rúbricas, laboratorios y contenido transversal.

### Jekyll nativo de GitHub Pages

Viable, pero menos conveniente para una experiencia documental multilingüe y para
previsualizaciones portables. No ofrece una ventaja suficiente frente a Starlight.

### Next.js en Vercel

Viable si aparecen autenticación, contenido por usuario o funciones de servidor.
Para el primer portal agrega complejidad operativa innecesaria y mayor dependencia
del proveedor.

### VitePress o Docusaurus

Ambas son opciones válidas. Starlight se prefiere inicialmente por su generación
estática, soporte documental e internacionalización con una base pequeña.

## Consecuencias

### Positivas

- Un solo contenido se puede desplegar en dos proveedores.
- El portal y la biblioteca Java se prueban de manera independiente.
- La traducción puede avanzar por página y conservar el inglés como respaldo.
- Es posible añadir un dominio propio sin rediseñar el sitio.

### Costos y riesgos

- Se incorpora Node.js al conjunto de herramientas del repositorio.
- El JavaDoc heredado necesita integrarse al nuevo artefacto de despliegue.
- GitHub Pages sirve un sitio de proyecto bajo un prefijo de ruta, por lo que la
  configuración debe conocer el nombre final del repositorio.
- Dos despliegues simultáneos requieren definir cuál es la URL canónica para evitar
  contenido duplicado en buscadores.

## Validación requerida

La decisión se aceptó al confirmar GitHub Pages. Todavía deben definirse:

1. identidad visual institucional;
2. licencia del contenido docente nuevo;
3. asignatura piloto.
