import { Component, OnInit, inject, signal, computed } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { forkJoin, of } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { Obra } from '../../model/obra';
import { ObraService } from '../../service/obra-service';
import { ImagenService } from '../../service/imagen-service';
import { TranslateService } from '../../service/translate.service';
import { TranslatePipe } from '../../pipe/translate.pipe';
import { Meta, Title } from '@angular/platform-browser';

interface Epoca {
  id: number;
  nombre: string;
  descripcion?: string;
  fechaInicio?: number;
  fechaFin?: number;
  caracteristicas?: string;
}

type TipoFiltro = 'artista' | 'tecnica' | 'epoca';

@Component({
  selector: 'app-obras',
  imports: [FormsModule, RouterLink, TranslatePipe],
  templateUrl: './obras.html',
  styleUrls: ['./obras.css'],
})
export class Obras implements OnInit {
  // Mejorar SEO
  private readonly title = inject(Title);
  private readonly meta = inject(Meta);

  private readonly todasLasObras = signal<Obra[]>([]);

  // Datos para filtros
  readonly epocas = signal<Epoca[]>([]);
  readonly artistas = signal<string[]>([]);
  readonly tecnicas = signal<string[]>([]);

  // Filtros seleccionados
  readonly tipoFiltro = signal<TipoFiltro>('artista');
  readonly artistaSeleccionado = signal<string | null>(null);
  readonly tecnicaSeleccionada = signal<string | null>(null);
  readonly epocaSeleccionada = signal<number | null>(null);

  readonly imagenesObras = signal<Map<number, string>>(new Map());
  readonly cargando = signal(true);

  readonly obrasFiltradas = computed(() => {
    let filtradas = this.todasLasObras();

    // Filtrar por artista
    const artista = this.artistaSeleccionado();
    if (artista) {
      filtradas = filtradas.filter(o => o.artistaNombre === artista);
    }

    // Filtrar por técnica
    const tecnica = this.tecnicaSeleccionada();
    if (tecnica) {
      filtradas = filtradas.filter(o => o.tecnica === tecnica);
    }

    // Filtrar por época
    const epocaId = this.epocaSeleccionada();
    if (epocaId !== null) {
      filtradas = filtradas.filter(o => o.epocaId === epocaId);
    }

    return filtradas;
  });

  readonly tieneFiltrosActivos = computed(() => {
    return this.artistaSeleccionado() !== null ||
      this.tecnicaSeleccionada() !== null ||
      this.epocaSeleccionada() !== null;
  });

  private readonly obraService = inject(ObraService);
  private readonly imagenService = inject(ImagenService);
  private readonly http = inject(HttpClient);
  readonly translate = inject(TranslateService);

  ngOnInit(): void {
    this.title.setTitle('Obras | Huellas de Asturias');
    this.meta.updateTag(
      { name: 'description', content: 'Catálogo de las obras de arte asturiano: pinturas, esculturas y más.' },
    );
    // Cargar épocas
    this.http.get<Epoca[]>('http://localhost:8080/api/epocas/listado').subscribe({
      next: (epocas) => {
        this.epocas.set(epocas);
      },
      error: (err) => console.warn('Error al cargar épocas:', err),
    });

    this.obraService.getAll().pipe(
      switchMap((obras) => {
        this.todasLasObras.set(obras);

        const artistasUnicos: string[] = [];
        obras.forEach(obra => {
          if (obra.artistaNombre && obra.artistaNombre.trim()) {
            if (!artistasUnicos.includes(obra.artistaNombre)) {
              artistasUnicos.push(obra.artistaNombre);
            }
          }
        });
        this.artistas.set(artistasUnicos.sort());

        const tecnicasUnicas: string[] = [];
        obras.forEach(obra => {
          if (obra.tecnica && obra.tecnica.trim()) {
            if (!tecnicasUnicas.includes(obra.tecnica)) {
              tecnicasUnicas.push(obra.tecnica);
            }
          }
        });
        this.tecnicas.set(tecnicasUnicas.sort());

        this.cargando.set(false);

        if (obras.length === 0) return of([]);
        return forkJoin(obras.map(o => this.imagenService.getUrlPrincipal('OBRA', o.id)));
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
      error: (err) => {
        console.warn('Error al cargar imágenes:', err);
        this.cargando.set(false);
      },
    });
  }

  cambiarTipoFiltro(tipo: TipoFiltro): void {
    this.tipoFiltro.set(tipo);
  }

  seleccionarArtista(event: Event): void {
    const select = event.target as HTMLSelectElement;
    const valor = select.value;
    this.artistaSeleccionado.set(valor || null);
  }

  seleccionarTecnica(event: Event): void {
    const select = event.target as HTMLSelectElement;
    const valor = select.value;
    this.tecnicaSeleccionada.set(valor || null);
  }

  seleccionarEpocaSelect(event: Event): void {
    const select = event.target as HTMLSelectElement;
    const valor = select.value;
    this.epocaSeleccionada.set(valor ? Number(valor) : null);
  }

  getEpocaSeleccionadaNombre(): string | null {
    const id = this.epocaSeleccionada();
    if (id === null) return null;
    const epoca = this.epocas().find(e => e.id === id);
    return epoca?.nombre || null;
  }

  getFiltroActivo(): string | null {
    switch (this.tipoFiltro()) {
      case 'artista':
        return this.artistaSeleccionado();
      case 'tecnica':
        return this.tecnicaSeleccionada();
      case 'epoca':
        return this.getEpocaSeleccionadaNombre();
      default:
        return null;
    }
  }

  getTipoFiltroLabel(): string {
    switch (this.tipoFiltro()) {
      case 'artista': return 'Artista';
      case 'tecnica': return 'Técnica';
      case 'epoca': return 'Época';
      default: return '';
    }
  }

  limpiarFiltroActual(): void {
    switch (this.tipoFiltro()) {
      case 'artista':
        this.artistaSeleccionado.set(null);
        break;
      case 'tecnica':
        this.tecnicaSeleccionada.set(null);
        break;
      case 'epoca':
        this.epocaSeleccionada.set(null);
        break;
    }
  }

  // Limpiar todos los filtros
  limpiarTodosFiltros(): void {
    this.artistaSeleccionado.set(null);
    this.tecnicaSeleccionada.set(null);
    this.epocaSeleccionada.set(null);
  }

  getImagenObra(obraId: number): string {
    return this.imagenesObras().get(obraId) ?? '';
  }
}