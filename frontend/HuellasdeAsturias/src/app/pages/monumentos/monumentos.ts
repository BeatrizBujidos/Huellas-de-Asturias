import { Component, OnInit, OnDestroy, inject, signal, computed, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { Subscription } from 'rxjs';
import { ImagenService } from '../../service/imagen-service';
import { Imagen } from '../../model/imagen';
import { MONUMENTOS, EPOCAS, Monumento } from '../../components/mapa-monumentos/mapa-monumentos';
import { TranslateService } from '../../service/translate.service';
import { TranslatePipe } from '../../pipe/translate.pipe';
import { Meta, Title } from '@angular/platform-browser';

@Component({
  selector: 'app-monumentos',
  imports: [CommonModule, TranslatePipe],
  templateUrl: './monumentos.html',
  styleUrl: './monumentos.css',
})
export class Monumentos implements OnInit, OnDestroy {
  // Mejorar SEO
  private readonly title = inject(Title);
  private readonly meta = inject(Meta);

  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);
  private readonly imagenService = inject(ImagenService);
  private readonly platformId = inject(PLATFORM_ID);
  private readonly cdr = inject(ChangeDetectorRef);

  readonly monumento = signal<Monumento | null>(null);
  readonly imagenes = signal<Imagen[]>([]);
  readonly imagenActiva = signal<string | null>(null);
  readonly cargandoImagenes = signal(true);
  readonly translate = inject(TranslateService);

  // Filtro épocas
  readonly epocaActiva = signal<number | null>(null);
  readonly epocasLista = Object.entries(EPOCAS).map(([id, info]) => ({
    id: Number(id), ...info,
  }));
  readonly monumentosDeEpoca = computed(() =>
    this.epocaActiva() === null
      ? []
      : MONUMENTOS.filter(m => m.idEpoca === this.epocaActiva())
  );

  readonly lightboxAbierto = signal(false);
  readonly lightboxIndice = signal(0);
  readonly lightboxUrl = signal<string>('');

  private mapa: any;
  private routeSub?: Subscription;
  private keydownListener?: (e: KeyboardEvent) => void;

  constructor() {
    this.title.setTitle('Monumentos | Huellas de Asturias');
    this.meta.updateTag(
      { name: 'description', content: 'Monumentos prerrománicos de Asturias: iglesias, palacios y otros edificios históricos.' });

    if (isPlatformBrowser(this.platformId)) {
      this.keydownListener = (e: KeyboardEvent) => {
        if (!this.lightboxAbierto()) return;
        if (e.key === 'Escape') this.cerrarLightbox();
        if (e.key === 'ArrowRight') this.navLightbox(1);
        if (e.key === 'ArrowLeft') this.navLightbox(-1);
      };
      window.addEventListener('keydown', this.keydownListener);
    }
  }

  ngOnInit(): void {
    // paramMap.subscribe detecta cambios de :id sin destruir el componente,
    // lo que permite navegar entre monumentos desde el filtro de épocas.
    this.routeSub = this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      const encontrado = MONUMENTOS.find(m => m.id === id) ?? null;

      setTimeout(() => {
        this.monumento.set(encontrado);
        this.imagenActiva.set(null);
        this.imagenes.set([]);
        this.cargandoImagenes.set(true);
        this.lightboxAbierto.set(false);
        this.cdr.markForCheck();

        if (encontrado) {
          this.cargarImagenes(encontrado.id);
          if (isPlatformBrowser(this.platformId)) {
            setTimeout(() => this.iniciarMapa(encontrado), 0);
          }
        }
      }, 0);
    });
  }

  ngOnDestroy(): void {
    this.routeSub?.unsubscribe();
    if (this.mapa) this.mapa.remove();
    if (isPlatformBrowser(this.platformId) && this.keydownListener) {
      window.removeEventListener('keydown', this.keydownListener);
    }
  }

  private cargarImagenes(id: number): void {
    this.imagenService.getByEntidad('MONUMENTO', id).subscribe({
      next: (imgs) => {
        const ordenadas = [...imgs].sort((a, b) => (a.orden ?? 0) - (b.orden ?? 0));
        this.imagenes.set(ordenadas);
        this.cargandoImagenes.set(false);
      },
      error: () => this.cargandoImagenes.set(false),
    });
  }
  // Crear mapa de Leaflet importandolo dinámicamente
  private async iniciarMapa(monumento: Monumento): Promise<void> {
    if (!document.querySelector('link[href*="leaflet"]')) {
      const link = document.createElement('link');
      link.rel = 'stylesheet';
      link.href = 'https://unpkg.com/leaflet@1.9.4/dist/leaflet.css';
      document.head.appendChild(link);
    }

    const leaflet = await import('leaflet');
    const L = (leaflet as any).default ?? leaflet;
    if (this.mapa) this.mapa.remove();

    this.mapa = L.map('mapa-detalle', {
      center: [monumento.latitud, monumento.longitud],
      zoom: 14,
      zoomControl: true,
      scrollWheelZoom: false,
    });

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
      maxZoom: 18,
    }).addTo(this.mapa);

    const epoca = EPOCAS[monumento.idEpoca];
    L.circleMarker([monumento.latitud, monumento.longitud], {
      radius: 10,
      fillColor: epoca.color,
      color: '#fff',
      weight: 3,
      opacity: 1,
      fillOpacity: 0.9,
    }).addTo(this.mapa)
      .bindPopup(`<strong>${monumento.nombre}</strong>`, { closeButton: false })
      .openPopup();
  }

  // Épocas 
  toggleEpoca(id: number): void {
    this.epocaActiva.set(this.epocaActiva() === id ? null : id);
  }

  navegarA(id: number): void {
    this.router.navigate(['/monumentos', id]);
  }

  // Imagen activa 
  seleccionarImagen(url: string, indice: number): void {
    this.imagenActiva.set(url);
    this.abrirLightbox(url, indice);
  }

  getImagenActiva(): string {
    return this.imagenActiva()
      ?? this.imagenes()[0]?.url
      ?? this.monumento()?.imagen
      ?? '';
  }

  // Lightbox (Imágenes en pantalla completa)
  abrirLightbox(url: string, indice?: number): void {
    const idx = indice ?? this.imagenes().findIndex(i => i.url === url);
    this.lightboxIndice.set(idx >= 0 ? idx : 0);
    this.lightboxUrl.set(url);
    this.lightboxAbierto.set(true);
    if (isPlatformBrowser(this.platformId)) document.body.style.overflow = 'hidden';
  }

  cerrarLightbox(): void {
    this.lightboxAbierto.set(false);
    if (isPlatformBrowser(this.platformId)) document.body.style.overflow = '';
  }

  navLightbox(dir: 1 | -1): void {
    const imgs = this.imagenes();
    if (imgs.length === 0) return;
    const nuevo = (this.lightboxIndice() + dir + imgs.length) % imgs.length;
    this.lightboxIndice.set(nuevo);
    this.lightboxUrl.set(imgs[nuevo].url);
  }

  // Época 
  getColorEpoca(): string {
    const m = this.monumento();
    return m ? (EPOCAS[m.idEpoca]?.color ?? '#125700') : '#125700';
  }

  getLabelEpoca(): string {
    const m = this.monumento();
    return m ? (EPOCAS[m.idEpoca]?.label ?? '') : '';
  }
  // Descripción traducida (si existe) o original
  getDescripcionMonumento(): string {
    const m = this.monumento();
    if (!m) return '';
    const traducido = this.translate.t(`MONUMENTOS_DATA.${m.id}.descripcion`);
    return traducido !== `MONUMENTOS_DATA.${m.id}.descripcion` ? traducido : m.descripcion;
  }
}