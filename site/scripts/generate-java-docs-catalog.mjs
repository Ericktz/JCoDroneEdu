import { readdir, readFile, writeFile } from 'node:fs/promises';
import { fileURLToPath } from 'node:url';
import path from 'node:path';

const repositoryRoot = fileURLToPath(new URL('../..', import.meta.url));
const outputPath = path.join(
  repositoryRoot,
  'site/src/content/docs/lenguajes/java-documentacion-heredada.md',
);
const repositoryBlobUrl = 'https://github.com/Ericktz/JCoDroneEdu/blob/main';

const supplementalDocuments = [
  'docs/LOGGING_GUIDE.md',
  'docs/OSSRH_PUBLISHING.md',
  'flight-patterns/README.md',
  'reference/README.md',
  'src/main/java/com/otabi/jcodroneedu/autonomous/README.md',
];

const categoryOrder = [
  'Gobierno, arquitectura y conocimiento',
  'API y compatibilidad Python ↔ Java',
  'Sensores, telemetría y calibración',
  'Controlador, pantalla, buzzer y protocolos',
  'Pruebas, calidad, compilación y releases',
  'Docencia, herramientas y ejemplos',
  'Referencias y otros',
];

function categoryFor(relativePath) {
  const name = relativePath.toUpperCase();

  if (
    /(^|\/)(AGENT_|KNOWLEDGE_|TASK_DIVISION|DESIGN-GUIDE|DEVELOPMENT-HISTORY|DOCUMENTATION_)/.test(
      name,
    ) || name.includes('API_DESIGN_PHILOSOPHY')
  ) {
    return 'Gobierno, arquitectura y conocimiento';
  }
  if (
    /(API_|PYTHON|JAVA_TO_|METHOD_TRACKING|DEPRECATION|APCSA|NON_APCSA|BEST_PRACTICE)/.test(
      name,
    )
  ) {
    return 'API y compatibilidad Python ↔ Java';
  }
  if (
    /(ALTITUDE|ELEVATION|TEMPERATURE|WEATHER|CALIBRATION|OPTICAL_FLOW|INVENTORY|ERROR_DATA|INFO_EXTRACTION|FLIGHT_TIME)/.test(
      name,
    )
  ) {
    return 'Sensores, telemetría y calibración';
  }
  if (/(CONTROLLER|DISPLAY|BUZZER|ENDIANNESS|RESET_AND_TRIM)/.test(name)) {
    return 'Controlador, pantalla, buzzer y protocolos';
  }
  if (
    /(TEST|SMOKE|RELEASE|BUILD|LOGGING|PRINT_AUDIT|LINK_VERIFICATION|OSSRH|CHANGELOG|VERSION_|PRE_RELEASE)/.test(
      name,
    )
  ) {
    return 'Pruebas, calidad, compilación y releases';
  }
  if (/(TEACHER|ACTIVITY|VSCODE|RUNNING_|ERROR_MONITORING|FLIGHT-PATTERNS|AUTONOMOUS)/.test(name)) {
    return 'Docencia, herramientas y ejemplos';
  }
  return 'Referencias y otros';
}

function documentType(relativePath) {
  const name = relativePath.toUpperCase();

  if (/(^|\/)AGENT_|TASK_DIVISION/.test(name)) return 'Instrucciones históricas';
  if (/CHECKLIST|SMOKE_TEST/.test(name)) return 'Lista de validación';
  if (/AUDIT|ANALYSIS|ASSESSMENT|RESEARCH|REPORT|PUNCH_LIST/.test(name)) {
    return 'Auditoría o investigación';
  }
  if (/IMPLEMENTATION|COMPLETE|_FIX|REFACTOR|ENHANCEMENT/.test(name)) {
    return 'Reporte de implementación';
  }
  if (/CHANGELOG|HISTORY|VERSION|SESSION/.test(name)) return 'Historial o versión';
  if (/GUIDE|GUIDANCE|QUICK_REFERENCE|README/.test(name)) return 'Guía o referencia';
  if (/DESIGN|ARCHITECTURE|STRATEGY|PATTERN/.test(name)) return 'Diseño o arquitectura';
  if (/KNOWLEDGE|DOCUMENTATION_SYSTEM|DECISIONS_LOG/.test(name)) {
    return 'Índice o metadocumentación';
  }
  return 'Nota técnica';
}

function firstHeading(markdown, relativePath) {
  const match = markdown.match(/^#\s+(.+)$/m);
  return match?.[1]?.trim() ?? path.basename(relativePath, '.md');
}

function escapeMarkdown(text) {
  return text.replaceAll('[', '\\[').replaceAll(']', '\\]');
}

async function discoverDocuments() {
  const rootEntries = await readdir(repositoryRoot, { withFileTypes: true });
  const rootDocuments = rootEntries
    .filter((entry) => entry.isFile() && entry.name.toLowerCase().endsWith('.md'))
    .map((entry) => entry.name);
  const paths = [...rootDocuments, ...supplementalDocuments].sort((a, b) =>
    a.localeCompare(b, 'es'),
  );

  return Promise.all(
    paths.map(async (relativePath) => {
      const markdown = await readFile(path.join(repositoryRoot, relativePath), 'utf8');
      return {
        relativePath,
        title: firstHeading(markdown, relativePath),
        category: categoryFor(relativePath),
        type: documentType(relativePath),
      };
    }),
  );
}

function renderCatalog(documents) {
  const sections = categoryOrder
    .map((category) => {
      const categoryDocuments = documents.filter((document) => document.category === category);
      if (categoryDocuments.length === 0) return '';

      const rows = categoryDocuments
        .map((document) => {
          const url = `${repositoryBlobUrl}/${document.relativePath}`;
          return `| [${escapeMarkdown(document.title)}](${url}) | \`${document.relativePath}\` | ${document.type} |`;
        })
        .join('\n');

      return `## ${category} (${categoryDocuments.length})\n\n| Documento | Archivo | Tipo |\n| --- | --- | --- |\n${rows}`;
    })
    .filter(Boolean)
    .join('\n\n');

  return `---
title: Archivo documental Java
description: Índice completo de la documentación Markdown heredada de JCoDroneEdu.
---

<!-- Archivo generado por site/scripts/generate-java-docs-catalog.mjs. -->

Este catálogo expone **${documents.length} documentos Markdown** técnicos que antes
solo podían encontrarse recorriendo el repositorio. Incluye los Markdown de la
raíz del proyecto Java y cinco referencias técnicas ubicadas en subdirectorios.

:::caution[Documento heredado no significa especificación vigente]
Los reportes registran decisiones, experimentos y estados de distintas fechas.
Antes de aplicar una instrucción se debe contrastar con el código actual, las
pruebas, los ADR de CoDrone EDU EIT y la documentación oficial vigente.
:::

## Principios recuperados para CoDrone EDU EIT

La revisión del archivo permite conservar estas reglas útiles:

- implementar primero la semántica oficial Python y documentar cualquier mejora
  específica de Java sin falsear la equivalencia;
- mantener <code>@pythonEquivalent</code> y <code>@pythonReference</code> solo
  cuando exista una equivalencia comprobada y un enlace oficial vigente; los
  métodos exclusivamente Java no deben forzarse dentro de esa matriz;
- ofrecer, cuando corresponda, acceso compatible, getters simples y objetos Java
  tipados, explicando cuál alternativa es recomendable;
- guiar con JavaDoc y enlaces <code>@see</code> en vez de marcar como obsoleta una API que se
  conserva deliberadamente por compatibilidad docente;
- ejecutar pruebas con <code>MockDrone</code> antes de cualquier prueba con hardware;
- tratar las fechas internas y la evidencia del código como más confiables que un
  estado histórico escrito en un reporte;
- validar versiones, porcentajes de paridad, URLs y procedimientos de publicación
  antes de reutilizarlos en el fork universitario.

Los puntos de entrada más útiles son [Knowledge Index](${repositoryBlobUrl}/KNOWLEDGE_INDEX.md),
[API Design Philosophy](${repositoryBlobUrl}/API_DESIGN_PHILOSOPHY.md),
[Testing Guide](${repositoryBlobUrl}/TESTING_GUIDE.md),
[Best Practice Guidance](${repositoryBlobUrl}/BEST_PRACTICE_GUIDANCE.md) y
[Agent Decisions Log](${repositoryBlobUrl}/AGENT_DECISIONS_LOG.md).

## Cómo se mantiene

La página se genera mediante <code>npm run catalog:java</code>. El build comprueba que esté
sincronizada; si se agrega, elimina o renombra un Markdown técnico, el catálogo
debe regenerarse y revisarse en el mismo PR.

${sections}
`;
}

const documents = await discoverDocuments();
const generated = renderCatalog(documents);
const checkOnly = process.argv.includes('--check');

if (checkOnly) {
  const current = await readFile(outputPath, 'utf8').catch(() => '');
  if (current !== generated) {
    console.error('El catálogo Java está desactualizado. Ejecuta: npm run catalog:java');
    process.exitCode = 1;
  } else {
    console.log(`Catálogo Java verificado: ${documents.length} documentos.`);
  }
} else {
  await writeFile(outputPath, generated, 'utf8');
  console.log(`Catálogo Java generado: ${documents.length} documentos.`);
}
