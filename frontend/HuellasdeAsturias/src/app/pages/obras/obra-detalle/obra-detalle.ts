import { Component, OnInit, inject, signal } from '@angular/core';
import { RouterLink, ActivatedRoute } from '@angular/router';
import { forkJoin, of } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Obra } from '../../../model/obra';
import { ObraService } from '../../../service/obra-service';
import { ImagenService } from '../../../service/imagen-service';

@Component({
  selector: 'app-obra-detalle',
  imports: [RouterLink],
  templateUrl: './obra-detalle.html',
  styleUrl: './obra-detalle.css',
})
export class ObraDetalle implements OnInit {

  readonly obra = signal<Obra | null>(null);
  readonly obrasMismoArtista = signal<Obra[]>([]);
  readonly imagenesObras = signal<Map<number, string>>(new Map());

  private readonly route = inject(ActivatedRoute);
  private readonly obraService = inject(ObraService);
  private readonly imagenService = inject(ImagenService);

  ngOnInit(): void {
    const obraId = Number(this.route.snapshot.paramMap.get('id')) || 1;

    this.obraService.getById(obraId).subscribe({
      next: (data) => {
        this.obra.set(data);

        // Imagen de la obra principal
        this.imagenService.getUrlPrincipal('OBRA', data.id).subscribe({
          next: (url) => {
            if (url) {
              const m = new Map(this.imagenesObras());
              m.set(data.id, url);
              this.imagenesObras.set(m);
            }
          }
        });

        // Obras relacionadas del mismo artista
        this.obraService.getAll().pipe(
          switchMap((todas) => {
            const relacionadas = todas
              .filter(o => o.artistaId === data.artistaId && o.id !== data.id)
              .slice(0, 3);
            this.obrasMismoArtista.set(relacionadas);
            if (relacionadas.length === 0) return of([]);
            return forkJoin(
              relacionadas.map(o => this.imagenService.getUrlPrincipal('OBRA', o.id))
            );
          })
        ).subscribe({
          next: (urls) => {
            const m = new Map(this.imagenesObras());
            (urls as (string | null)[]).forEach((url, i) => {
              if (url) m.set(this.obrasMismoArtista()[i].id, url);
            });
            this.imagenesObras.set(m);
          },
          error: (err) => console.warn('Error al cargar obras relacionadas:', err)
        });
      },
      error: (err) => console.error('Error al cargar obra:', err)
    });
  }

  getImagenObra(obraId: number): string {
    return this.imagenesObras().get(obraId) ?? '';
  }
}