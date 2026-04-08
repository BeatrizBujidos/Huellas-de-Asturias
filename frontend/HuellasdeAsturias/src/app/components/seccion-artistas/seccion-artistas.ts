import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Artista } from '../../model/artista';
import { ArtistaService } from '../../service/artista-service';

@Component({
  selector: 'app-seccion-artistas',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './seccion-artistas.html',
  styleUrl:'./seccion-artistas.css',
})
export class SeccionArtistas implements OnInit {

  artistas: Artista[] = [];

  constructor(private artistaService: ArtistaService) { }

  ngOnInit(): void {
    this.artistaService.getAll().subscribe({
      next: (data) => {
        this.artistas = data;
      },
      error: (error) => {
        console.error('Error al cargar artistas:', error);
      }
    });
  }
}
