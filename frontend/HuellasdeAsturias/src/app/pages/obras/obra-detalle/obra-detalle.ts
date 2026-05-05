import { Component, OnInit, inject, signal, computed, HostListener } from '@angular/core';
import { RouterLink, ActivatedRoute } from '@angular/router';
import { Obra } from '../../../model/obra';
import { ObraService } from '../../../service/obra-service';
import { ImagenService } from '../../../service/imagen-service';
import { Imagen } from '../../../model/imagen';
import { TranslateService } from '../../../service/translate.service';
import { TranslatePipe } from '../../../pipe/translate.pipe';

@Component({
  selector: 'app-obra-detalle',
  imports: [RouterLink, TranslatePipe],
  templateUrl: './obra-detalle.html',
  styleUrl: './obra-detalle.css',
})
export class ObraDetalle implements OnInit {

  readonly obra = signal<Obra | null>(null);
  readonly imagenes = signal<Imagen[]>([]);
  readonly imagenSeleccionada = signal<string>('');
  readonly imagenIndice = signal<number>(0);

  // Lightbox
  readonly lightboxAbierto = signal<boolean>(false);
  readonly lightboxIndice = signal<number>(0);

  private readonly route = inject(ActivatedRoute);
  private readonly obraService = inject(ObraService);
  private readonly imagenService = inject(ImagenService);
  readonly translate = inject(TranslateService);

  ngOnInit(): void {
    const obraId = Number(this.route.snapshot.paramMap.get('id')) || 1;

    this.obraService.getById(obraId).subscribe({
      next: (data) => {
        this.obra.set(data);

        this.imagenService.getByEntidad('OBRA', data.id).subscribe({
          next: (imagenes) => {
            this.imagenes.set(imagenes);
            if (imagenes.length > 0) {
              this.imagenSeleccionada.set(imagenes[0].url);
              this.imagenIndice.set(0);
            }
          }
        });
      },
      error: (err) => console.error('Error al cargar obra:', err)
    });
  }

  seleccionarImagen(url: string, indice: number): void {
    this.imagenSeleccionada.set(url);
    this.imagenIndice.set(indice);
  }

  // ── Lightbox ──────────────────────────────────────────────

  abrirLightbox(indice: number): void {
    this.lightboxIndice.set(indice);
    this.lightboxAbierto.set(true);
    document.body.style.overflow = 'hidden';
  }

  cerrarLightbox(): void {
    this.lightboxAbierto.set(false);
    document.body.style.overflow = '';
  }

  navLightbox(delta: number): void {
    const total = this.imagenes().length;
    const nuevo = (this.lightboxIndice() + delta + total) % total;
    this.lightboxIndice.set(nuevo);
  }

  @HostListener('document:keydown', ['$event'])
  onKeydown(event: KeyboardEvent): void {
    if (!this.lightboxAbierto()) return;
    if (event.key === 'Escape') this.cerrarLightbox();
    if (event.key === 'ArrowLeft') this.navLightbox(-1);
    if (event.key === 'ArrowRight') this.navLightbox(1);
  }
}