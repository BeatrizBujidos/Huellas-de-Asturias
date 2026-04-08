import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { Artista } from '../../model/artista';
import { ArtistaService } from '../../service/artista-service';
import { Obra } from '../../model/obra';
import { ObraService } from '../../service/obra-service';

@Component({
  selector: 'app-artistas',
  imports: [FormsModule, RouterLink, RouterLinkActive],
  templateUrl: './artistas.html',
  styleUrl: './artistas.css',
})
export class Artistas implements OnInit {
  artista: Artista | null = null;
  obras: Obra[] = [];
  busqueda: string = '';
  private idArtista: number = 1;
  route: any;

  constructor(private artistaService: ArtistaService, private obraService: ObraService) { }

  ngOnInit(): void {

    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.idArtista = id;

    this.artistaService.getById(this.idArtista).subscribe({
      next: (data) => (this.artista = data),
      error: (error) => console.error('Error al cargar artista:', error),
    });
    
    this.obraService.getAll().subscribe({
      next: (data) => (this.obras = data),
      error: (error) => console.error('Error al cargar obras:', error),
    });
  }
}
