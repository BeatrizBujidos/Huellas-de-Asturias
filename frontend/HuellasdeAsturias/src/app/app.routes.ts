import { Routes } from '@angular/router';
import { Artistas } from './pages/artistas/artistas';

export const routes: Routes = [
    {
        path: '',
        loadComponent:() => import('./pages/home/home').then(m => m.Home)
    },
    {
        path: 'artistas',
        component: Artistas
    }
];
