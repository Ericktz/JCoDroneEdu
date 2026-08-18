import { defineConfig } from 'astro/config';
import starlight from '@astrojs/starlight';

const repositoryUrl = 'https://github.com/Ericktz/JCoDroneEdu';

export default defineConfig({
  site: 'https://ericktz.github.io',
  base: '/JCoDroneEdu',
  trailingSlash: 'always',
  integrations: [
    starlight({
      title: 'CoDrone EDU EIT',
      description:
        'Plataforma docente universitaria en español para aprender con CoDrone EDU.',
      favicon: '/favicon.svg',
      locales: {
        root: {
          label: 'Español',
          lang: 'es',
        },
      },
      customCss: ['./src/styles/custom.css'],
      editLink: {
        baseUrl: `${repositoryUrl}/edit/main/site/`,
      },
      social: [
        {
          icon: 'github',
          label: 'Repositorio en GitHub',
          href: repositoryUrl,
        },
      ],
      sidebar: [
        {
          label: 'Comenzar',
          items: [
            { label: 'Visión general', slug: 'inicio/vision-general' },
            { label: 'Preparar el entorno', slug: 'inicio/instalacion' },
            { label: 'Seguridad', slug: 'inicio/seguridad' },
          ],
        },
        {
          label: 'Lenguajes',
          items: [
            { label: 'Python', slug: 'lenguajes/python' },
            { label: 'Java', slug: 'lenguajes/java' },
            {
              label: 'Archivo documental Java',
              slug: 'lenguajes/java-documentacion-heredada',
            },
          ],
        },
        {
          label: 'Ejemplos',
          items: [
            { label: 'Catálogo', slug: 'ejemplos' },
            {
              label: 'Python: conexión sin vuelo',
              slug: 'ejemplos/python/conexion-sin-vuelo',
            },
          ],
        },
        {
          label: 'Docencia',
          items: [
            { label: 'Asignaturas', slug: 'docencia/asignaturas' },
            { label: 'Operación de 8 equipos', slug: 'docencia/ocho-equipos' },
          ],
        },
        {
          label: 'Proyecto',
          items: [
            { label: 'Hoja de ruta', slug: 'proyecto/hoja-de-ruta' },
            { label: 'Fuentes y versiones', slug: 'proyecto/fuentes' },
            {
              label: 'API Java generada',
              link: '/api/java/latest/',
              attrs: { target: '_blank' },
            },
          ],
        },
      ],
    }),
  ],
});
