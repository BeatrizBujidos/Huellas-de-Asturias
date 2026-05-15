import { Component, OnInit, inject, signal, computed, ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink, ActivatedRoute, Router } from '@angular/router';
import { forkJoin, of } from 'rxjs';
import { switchMap, finalize } from 'rxjs/operators';
import { Artista } from '../../model/artista';
import { ArtistaService } from '../../service/artista-service';
import { Obra } from '../../model/obra';
import { ObraService } from '../../service/obra-service';
import { ImagenService } from '../../service/imagen-service';
import { TranslateService } from '../../service/translate.service';
import { TranslatePipe } from '../../pipe/translate.pipe';
import { Meta, Title } from '@angular/platform-browser';

@Component({
  selector: 'app-artistas',
  imports: [FormsModule, RouterLink, TranslatePipe],
  templateUrl: './artistas.html',
  styleUrl: './artistas.css',
})
export class Artistas implements OnInit {
  // Mejorar SEO
  private readonly title = inject(Title);
  private readonly meta = inject(Meta);

  readonly translate = inject(TranslateService);
  artista = signal<Artista | null>(null);
  busqueda = signal('');
  artistasDisponibles = signal<Artista[]>([]);
  cargandoObras = signal(false);
  mostrarSugerencias = signal(false);

  readonly artistasFiltrados = computed(() => {
    const texto = this.busqueda().toLowerCase().trim();
    if (!texto) return this.artistasDisponibles();
    return this.artistasDisponibles().filter(a =>
      a.nombre.toLowerCase().includes(texto) ||
      a.apellidos.toLowerCase().includes(texto) ||
      `${a.nombre} ${a.apellidos}`.toLowerCase().includes(texto)
    );
  });

  private readonly todasLasObras = signal<Obra[]>([]);
  readonly imagenesObras = signal<Map<number, string>>(new Map());

  // Las obras no se filtran por búsqueda cuando hay un artista seleccionado,
  // ya que búsqueda contiene el nombre del artista, no un filtro de obras.
  readonly obrasFiltradas = computed(() => {
    return this.todasLasObras();
  });

  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);
  private readonly artistaService = inject(ArtistaService);
  private readonly obraService = inject(ObraService);
  private readonly imagenService = inject(ImagenService);
  private readonly cdr = inject(ChangeDetectorRef);

  ngOnInit(): void {

    this.title.setTitle('Artistas | Huellas de Asturias');
    this.meta.updateTag(
      { name: 'description', content: 'Explora los artistas más destacados, sus biografías y sus obras.' },
    );

    this.artistaService.getAll().subscribe({
      next: (artistas) => {
        this.artistasDisponibles.set(artistas);
        this.cdr.detectChanges();
      },
      error: (err) => console.error('Error al cargar artistas:', err),
    });

    const id = Number(this.route.snapshot.paramMap.get('id')) || 1;
    this.cargarArtista(id);
  }

  cargarArtista(id: number): void {
    this.todasLasObras.set([]);
    this.imagenesObras.set(new Map());
    this.cargandoObras.set(true);
    this.cdr.detectChanges();

    this.artistaService.getById(id).subscribe({
      next: (artista) => {
        this.artista.set(artista);
        this.cdr.detectChanges();

        this.router.navigate(['/artistas', id], { replaceUrl: true });

        this.cargarObrasDelArtista(artista);
      },
      error: (err) => {
        console.error('Error al cargar artista:', err);
        this.cargandoObras.set(false);
        this.cdr.detectChanges();
      },
    });
  }

  seleccionarArtista(artista: Artista): void {
    this.busqueda.set(`${artista.nombre} ${artista.apellidos}`);
    this.mostrarSugerencias.set(false);
    this.cargarArtista(artista.id);
  }

  onBlurBusqueda(): void {
    setTimeout(() => {
      this.mostrarSugerencias.set(false);
      this.cdr.detectChanges();
    }, 200);
  }

  onFocusBusqueda(): void {
    if (this.busqueda().length > 0) {
      this.mostrarSugerencias.set(true);
      this.cdr.detectChanges();
    }
  }

  onInputBusqueda(): void {
    this.mostrarSugerencias.set(true);
    this.cdr.detectChanges();
  }

  private cargarObrasDelArtista(artista: Artista): void {
    this.obraService.getByArtistaId(artista.id).pipe(
      switchMap((obras) => {
        console.log('Obras recibidas:', obras.length);
        this.todasLasObras.set(obras);
        this.cdr.detectChanges();

        if (obras.length === 0) {
          this.cargandoObras.set(false);
          this.cdr.detectChanges();
          return of([]);
        }
        // Para cada obra, se carga la URL de su imagen principal
        return forkJoin(
          obras.map(obra => this.imagenService.getUrlPrincipal('OBRA', obra.id))
        );
      }),
      // finalize se ejecuta al completar el observable
      finalize(() => {
        this.cargandoObras.set(false);
        this.cdr.detectChanges();
      })
    ).subscribe({
      next: (urls) => {
        const obras = this.todasLasObras();
        const nuevoMap = new Map<number, string>();

        if (urls && urls.length > 0) {
          (urls as (string | null)[]).forEach((url, i) => {
            if (url && obras[i]) {
              nuevoMap.set(obras[i].id, url);
            }
          });
        }

        this.imagenesObras.set(nuevoMap);
        console.log('Imágenes cargadas:', nuevoMap.size);
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.warn('Error al cargar imágenes:', err);
        this.cargandoObras.set(false);
        this.cdr.detectChanges();
      },
    });
  }

  getImagenArtista(): string {
    const img = this.artista()?.imagen;
    if (!img) return '';
    return img.startsWith('/') || img.startsWith('http') ? img : `assets/imagenes/artistas/${img}`;
  }

  getImagenObra(obraId: number): string {
    return this.imagenesObras().get(obraId) ?? '';
  }
  // Formato de la fecha en español
  formatearFecha(fecha: string | null | undefined): string {
    if (!fecha) return '';
    const [year, month, day] = fecha.split('-');
    return `${day}-${month}-${year}`;
  }

  getRangoFechas(): string {
    const artistaActual = this.artista();
    if (!artistaActual) return '';
    const nacimiento = this.formatearFecha(artistaActual.fechaNacimiento);
    const muerte = artistaActual.fechaMuerte
      ? this.formatearFecha(artistaActual.fechaMuerte)
      : 'Actualidad';
    return `Nacimiento: ${nacimiento} - Muerte: ${muerte}`;
  }

  getBiografia(): string {
    // Leer el signal lang() hace que Angular re-evalúe cuando cambia el idioma
    const isEn = this.translate.lang() === 'en';
    const a = this.artista();
    if (!a) return '';
    return isEn && a.biografiaEn ? a.biografiaEn : a.biografia;
  }
}