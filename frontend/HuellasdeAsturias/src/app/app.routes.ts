import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        loadComponent: () => import('./pages/home/home').then(m => m.Home)
    },
    {
        path: 'artistas',
        loadComponent: () => import('./pages/artistas/artistas').then(m => m.Artistas)
    },
    {
        path: 'artistas/:id',
        loadComponent: () => import('./pages/artistas/artistas').then(m => m.Artistas)
    },
    {
        path: 'obras',
        loadComponent: () => import('./pages/obras/obras').then(m => m.Obras)
    },
    {
        path: 'obras/:id',
        loadComponent: () => import('./pages/obras/obra-detalle/obra-detalle').then(m => m.ObraDetalle)
    },
    {
        path: 'monumentos',
        loadComponent: () => import('./pages/monumentos/monumentos').then(m => m.Monumentos)
    },
    {
        path: 'monumentos/:id',
        loadComponent: () => import('./pages/monumentos/monumentos').then(m => m.Monumentos)
    }
];
