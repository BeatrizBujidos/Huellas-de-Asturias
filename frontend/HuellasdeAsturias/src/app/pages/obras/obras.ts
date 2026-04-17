import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { Obra } from '../../model/obra';
import { ObraService } from '../../service/obra-service';

@Component({
  selector: 'app-obras',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './obras.html',
  styleUrl: './obras.css',
})
export class Obras {

  obra: Obra | null = null;
  obrasMismoArtista: Obra[] = [];
  private obraId: number = 1;

  constructor(private obraService: ObraService) { }

  ngOnInit(): void {
    this.obraService.getById(this.obraId).subscribe({
      next: (data) => (this.obra = data),
      error: (error) => console.error('Error al cargar obra:', error)
    });

    this.obraService.getAll().subscribe({
      next: (data) => (this.obrasMismoArtista = data.filter(o => o.id !== this.obraId).slice(0, 3)),
      error: (error) => console.error('Error al cargar obras:', error)
    });
  }
}
