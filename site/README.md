# Portal CoDrone EDU EIT

Portal en español construido con Astro y Starlight. La salida es estática y se
publica en GitHub Pages bajo `https://ericktz.github.io/JCoDroneEdu/`.

## Desarrollo local

```bash
cd site
npm ci
npm run dev
```

## Validación

```bash
npm run build
```

Para incorporar la referencia Java antes de compilar el portal:

```bash
./gradlew javadoc
mkdir -p site/public/api/java/latest
cp -R build/docs/javadoc/. site/public/api/java/latest/
cd site && npm run build
```

`site/public/api/` es un artefacto temporal del build y no debe mantenerse a mano.
