import { Component, OnInit, inject, signal, computed } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink, ActivatedRoute } from '@angular/router';
import { forkJoin, of } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Artista } from '../../model/artista';
import { ArtistaService } from '../../service/artista-service';
import { Obra } from '../../model/obra';
import { ObraService } from '../../service/obra-service';
import { ImagenService } from '../../service/imagen-service';

@Component({
  selector: 'app-artistas',
  imports: [FormsModule, RouterLink],
  templateUrl: './artistas.html',
  styleUrl: './artistas.css',
})
export class Artistas implements OnInit {
  artista: Artista | null = null;
  busqueda = signal('');

  private readonly todasLasObras = signal<Obra[]>([]);
  readonly imagenesObras = signal<Map<number, string>>(new Map());

  readonly obrasFiltradas = computed(() => {
    const texto = this.busqueda().toLowerCase().trim();
    if (!texto) return this.todasLasObras();
    return this.todasLasObras().filter(o =>
      o.titulo.toLowerCase().includes(texto) ||
      o.tecnica?.toLowerCase().includes(texto) ||
      o.descripcion?.toLowerCase().includes(texto)
    );
  });

  private readonly route = inject(ActivatedRoute);
  private readonly artistaService = inject(ArtistaService);
  private readonly obraService = inject(ObraService);
  private readonly imagenService = inject(ImagenService);

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id')) || 1;

    this.artistaService.getById(id).subscribe({
      next: (artista) => {
        this.artista = artista;
        this.cargarObrasDelArtista(artista);
      },
      error: (err) => console.error('Error al cargar artista:', err),
    });
  }

  private cargarObrasDelArtista(artista: Artista): void {
    this.obraService.getByArtistaId(artista.id).pipe(
      switchMap((obras) => {
        this.todasLasObras.set(obras);
        if (obras.length === 0) return of([]);
        return forkJoin(
          obras.map(obra => this.imagenService.getUrlPrincipal('OBRA', obra.id))
        );
      })
    ).subscribe({
      next: (urls) => {
        const obras = this.todasLasObras();
        const nuevoMap = new Map<number, string>();
        (urls as (string | null)[]).forEach((url, i) => {
          if (url) nuevoMap.set(obras[i].id, url);
        });
        this.imagenesObras.set(nuevoMap);
      },
      error: (err) => console.warn('Error al cargar imágenes:', err),
    });
  }

  getImagenArtista(): string {
    const img = this.artista?.imagen;
    if (!img) return '';
    return img.startsWith('/') || img.startsWith('http') ? img : `assets/imagenes/artistas/${img}`;
  }

  getImagenObra(obraId: number): string {
    return this.imagenesObras().get(obraId) ?? '';
  }
}