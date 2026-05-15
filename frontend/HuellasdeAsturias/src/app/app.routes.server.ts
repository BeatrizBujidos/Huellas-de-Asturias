import { RenderMode, ServerRoute } from '@angular/ssr';

export const serverRoutes: ServerRoute[] = [
  {
    path: 'artistas',
    renderMode: RenderMode.Server
  },
  {
    path: 'artistas/:id',
    renderMode: RenderMode.Server
  },
  {
    path: 'obras/:id',
    renderMode: RenderMode.Server
  },
  {
    path: 'monumentos/:id',
    renderMode: RenderMode.Server
  },
  {
    path: '**',
    renderMode: RenderMode.Prerender
  }
];
