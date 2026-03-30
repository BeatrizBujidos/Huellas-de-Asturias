import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Artista } from '../model/artista.ts';

@Component({
  selector: 'app-seccion-artistas',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './seccion-artistas.html',
  styleUrl: './seccion-artistas.css',
})
export class SeccionArtistas {
  artistas: Artista[] = [
    {
      id: 1,
      nombre: 'Artista 1',
      descripcion: 'Descripción del artista 1',
      imagen: '/assets/imagenes/artistas/artista1.jpg'
    },
    {
      id: 2,
      nombre: 'Artista 2',
      descripcion: 'Descripción del artista 2',
      imagen: '/assets/imagenes/artistas/artista2.jpg'
    }
  ];
}
