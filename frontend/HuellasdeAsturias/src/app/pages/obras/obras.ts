import { Component, OnInit, inject } from '@angular/core';
import { RouterLink, ActivatedRoute } from '@angular/router';
import { Obra } from '../../model/obra';
import { ObraService } from '../../service/obra-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-obras',
  imports: [FormsModule, RouterLink],
  templateUrl: './obras.html',
  styleUrl: './obras.css',
})
export class Obras {

  obra: Obra | null = null;
  obrasMismoArtista: Obra[] = [];

  private readonly route = inject(ActivatedRoute);
  private readonly obraService = inject(ObraService);

  ngOnInit(): void {

    const obraId = Number(this.route.snapshot.paramMap.get('id')) || 1;

    this.obraService.getById(obraId).subscribe({
      next: (data) => (this.obra = data),
      error: (error) => console.error('Error al cargar obra:', error)
    });

    this.obraService.getAll().subscribe({
      next: (data) => (this.obrasMismoArtista = data.filter(o => o.id !== obraId).slice(0, 3)),
      error: (error) => console.error('Error al cargar obras:', error)
    });
  }
}
