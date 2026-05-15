import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, OnInit, OnDestroy, ChangeDetectionStrategy, ChangeDetectorRef, PLATFORM_ID, inject, effect } from '@angular/core';
import { Router } from '@angular/router';
import { isPlatformBrowser } from '@angular/common';
import { TranslateService } from '../../service/translate.service';
import { TranslatePipe } from '../../pipe/translate.pipe';

export interface Monumento {
  id: number;
  idEpoca: number;
  nombre: string;
  fechaConstruccion?: string;
  descripcion: string;
  latitud: number;
  longitud: number;
  imagen: string;
}

export const EPOCAS: Record<number, { label: string; color: string }> = {
  1: { label: 'Alfonso II (791–842)', color: '#00B2FF' },
  2: { label: 'Ramirense (842–866)', color: '#FFF200' },
  3: { label: 'Alfonso III (866–910)', color: '#56AF3F' },
};

export const MONUMENTOS: Monumento[] = [
  {
    id: 1, idEpoca: 1, nombre: 'San Tirso', fechaConstruccion: 'Siglo IX',
    descripcion: 'Fundada por Alfonso II "El Casto" como parte del complejo episcopal ovetense. Aunque sufrió múltiples reformas a lo largo de los siglos (la fachada actual es barroca), conserva de su origen prerrománico el testero de la capilla mayor con su emblemática ventana trigeminada de arcos de medio punto y columnas con capiteles de tradición visigoda.',
    latitud: 43.3627, longitud: -5.8436, imagen: 'assets/monumentos/San_Tirso_01.jpg',
  },
  {
    id: 2, idEpoca: 1, nombre: 'Cámara Santa', fechaConstruccion: 'Siglo IX',
    descripcion: 'Capilla palatina de dos pisos integrada en la Catedral de Oviedo. El nivel superior (San Miguel) custodia las reliquias del Arca Santa y las cruces de la Victoria y los Ángeles, símbolos de Asturias. Destaca el apostolado románico añadido posteriormente en sus columnas.',
    latitud: 43.3625, longitud: -5.8431, imagen: 'assets/monumentos/Camara_Santa_01.jpg',
  },
  {
    id: 3, idEpoca: 1, nombre: 'San Julián de los Prados', fechaConstruccion: '812–842',
    descripcion: 'Conocida popularmente como Santullano, es la iglesia más grande y antigua del periodo asturiano. Mandada construir por Alfonso II, destaca por su gran amplitud, su planta basilical con transepto elevado y su excepcional ciclo de pinturas murales anicónicas.',
    latitud: 43.3670, longitud: -5.8368, imagen: 'assets/monumentos/Santullano_01.jpg',
  },
  {
    id: 4, idEpoca: 1, nombre: 'Santa María de Bendones', fechaConstruccion: 'Siglo IX',
    descripcion: 'Estructura basilical que sigue el modelo de Santullano, aunque con dimensiones más reducidas. Fue reconstruida tras sufrir graves daños en 1936. Destaca su gran nártex o pórtico de entrada y la disposición de sus naves laterales.',
    latitud: 43.3364, longitud: -5.8039, imagen: 'assets/monumentos/Bendones_01.jpg',
  },
  {
    id: 5, idEpoca: 1, nombre: 'San Pedro de Nora', fechaConstruccion: 'Siglo IX',
    descripcion: 'Iglesia de planta basilical con tres naves separadas por arquerías de medio punto sobre pilares cuadrados, situada en un entorno rural junto al río Nora. Mantiene la pureza de líneas y la sobriedad volumétrica características del arte asturiano.',
    latitud: 43.3644, longitud: -5.9525, imagen: 'assets/monumentos/Nora_02.jpg',
  },
  {
    id: 6, idEpoca: 2, nombre: 'Santa María del Naranco', fechaConstruccion: '842',
    descripcion: 'Obra maestra del periodo ramirense. Originalmente concebida como un aula regia o palacio de recreo situado en las afueras de Oviedo, fue transformada en iglesia en el siglo XII. Representa la cúspide de la arquitectura prerrománica europea.',
    latitud: 43.3790, longitud: -5.8659, imagen: 'assets/monumentos/Naranco_02.jpg',
  },
  {
    id: 7, idEpoca: 2, nombre: 'San Miguel de Lillo', fechaConstruccion: '848',
    descripcion: 'Iglesia palatina dedicada a San Miguel Arcángel, situada a escasos metros del Naranco. Actualmente solo se conserva el tercio occidental de la planta original. Es famosa por la esbeltez de sus proporciones y sus celosías de piedra tallada.',
    latitud: 43.3808, longitud: -5.8672, imagen: 'assets/monumentos/Lillo_01.jpg',
  },
  {
    id: 8, idEpoca: 2, nombre: 'Santa Cristina de Lena', fechaConstruccion: '852',
    descripcion: 'Pequeña joya del periodo ramirense situada sobre una colina con vistas panorámicas al valle del Caudal. Su elemento más distintivo es el iconostasio: una triple arquería de piedra que separa el presbiterio de la nave.',
    latitud: 43.1275, longitud: -5.8144, imagen: 'assets/monumentos/Lena_02.jpg',
  },
  {
    id: 9, idEpoca: 3, nombre: 'Foncalada', fechaConstruccion: 'Siglo IX',
    descripcion: 'Única construcción civil de utilidad pública del prerrománico asturiano que se conserva in situ. Se trata de una fuente de agua potable protegida por un templete de piedra con arco de medio punto.',
    latitud: 43.3653, longitud: -5.8455, imagen: 'assets/monumentos/Foncalada_01.jpg',
  },
  {
    id: 10, idEpoca: 3, nombre: 'Santo Adriano de Tuñón', fechaConstruccion: '891',
    descripcion: 'Iglesia de estructura sencilla y planta basilical de tres naves, mandada construir por Alfonso III y su esposa Jimena. Conserva importantes restos de pinturas murales originales en la cabecera.',
    latitud: 43.2925, longitud: -5.9814, imagen: 'assets/monumentos/Tuñon_02.jpg',
  },
  {
    id: 11, idEpoca: 3, nombre: 'San Salvador de Valdediós', fechaConstruccion: '893',
    descripcion: 'Denominada popularmente "El Conventín", representa el apogeo del periodo alfonsino. Destaca por su pórtico lateral abierto (único en el prerrománico asturiano) y los elementos mozárabes de su construcción.',
    latitud: 43.4375, longitud: -5.4686, imagen: 'assets/monumentos/Valdedios_02.jpg',
  },
  {
    id: 12, idEpoca: 3, nombre: 'San Salvador de Priesca', fechaConstruccion: '921',
    descripcion: 'Consagrada en el siglo X, sigue fielmente los esquemas arquitectónicos de Santullano. Conserva una notable parte de su decoración pictórica original con influencias mozárabes.',
    latitud: 43.4897, longitud: -5.2897, imagen: 'assets/monumentos/Priesca_01.jpg',
  },
  {
    id: 13, idEpoca: 3, nombre: 'Santiago de Gobiendes', fechaConstruccion: 'c. 921',
    descripcion: 'Situada cerca de la costa oriental asturiana (Colunga), destaca por la elegancia de sus proporciones. Las restauraciones modernas han recuperado su esencia prerrománica en la cabecera tripartita y los capiteles decorados.',
    latitud: 43.4731, longitud: -5.2289, imagen: 'assets/monumentos/Gobiendes_01.jpg',
  },
];

@Component({
  selector: 'app-mapa-monumentos',
  imports: [CommonModule, TranslatePipe],
  templateUrl: './mapa-monumentos.html',
  styleUrl: './mapa-monumentos.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class MapaMonumentos implements OnInit, AfterViewInit, OnDestroy {

  private readonly platformId = inject(PLATFORM_ID);
  private readonly cdr = inject(ChangeDetectorRef);
  private readonly router = inject(Router);
  readonly translate = inject(TranslateService);


  monumentos: Monumento[] = MONUMENTOS;
  monumentoSeleccionado: Monumento | null = null;
  readonly epocas = EPOCAS;

  private mapa: any;
  private marcadores: Map<number, any> = new Map();

  constructor() {
    // Actualizar popups cuando cambie el idioma
    effect(() => {
      this.translate.lang();
      if (this.mapa) {
        this.marcadores.forEach((marcador, id) => {
          const mon = this.monumentos.find(m => m.id === id);
          if (mon) marcador.setPopupContent(this.crearContenido(mon));
        });
      }
    });
  }

  ngOnInit(): void { }

  async ngAfterViewInit(): Promise<void> {
    if (isPlatformBrowser(this.platformId)) {
      requestAnimationFrame(async () => {
        const link = document.createElement('link');
        link.rel = 'stylesheet';
        link.href = 'https://unpkg.com/leaflet@1.9.4/dist/leaflet.css';
        document.head.appendChild(link);

        const leaflet = await import('leaflet');
        const L = (leaflet as any).default ?? leaflet;
        this.iniciarMapa(L);
      });
    }
  }

  ngOnDestroy(): void {
    if (this.mapa) {
      this.mapa.remove();
    }
  }

  private iniciarMapa(L: any): void {
    this.mapa = L.map('mapa-preromanico', {
      center: [43.35, -5.75],
      zoom: 8,
      zoomControl: true,
    });

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
      maxZoom: 18,
    }).addTo(this.mapa);

    this.monumentos.forEach(monumento => {
      const epoca = EPOCAS[monumento.idEpoca];
      const marcador = L.circleMarker([monumento.latitud, monumento.longitud], {
        radius: 8,
        fillColor: epoca.color,
        color: '#fff',
        weight: 2,
        opacity: 1,
        fillOpacity: 0.8
      }).addTo(this.mapa)
        .bindPopup(this.crearContenido(monumento), {
          maxWidth: 250,
          className: 'popup-monumento'
        });

      marcador.on('click', () => {
        this.seleccionarMonumento(monumento);
      });

      this.marcadores.set(monumento.id, marcador);
    });

    const leyenda = new L.control({ position: 'bottomleft' });
    leyenda.onAdd = () => {
      const div = L.DomUtil.create('div', 'leyenda');
      div.innerHTML = Object.entries(EPOCAS).map(([, e]) => ` <span style="background:${e.color}"></span> ${e.label} `).join('');
      return div;
    };
    leyenda.addTo(this.mapa);
  }

  private crearContenido(monumento: Monumento): string {
    const epoca = EPOCAS[monumento.idEpoca];
    const desc = this.translate.t(`MONUMENTOS_DATA.${monumento.id}.descripcion`) || monumento.descripcion;
    return `
      <div class="popup-contenido">
        <div style="display:flex;align-items:center;gap:8px;margin-bottom:8px;">
          <span style="display:inline-block;width:12px;height:12px;border-radius:50%;background:${epoca.color};flex-shrink:0;"></span>
          <span style="font-size:0.7rem;color:${epoca.color};font-weight:600;text-transform:uppercase;">${epoca.label}</span>
        </div>
        <h6 style="font-family:'Playfair Display',serif;margin:0 0 4px;font-size:0.95rem;">${monumento.nombre}</h6>
        <p style="font-size:0.78rem;color:#6c757d;margin:0 0 6px;">
          <i class="bi bi-calendar3"></i> ${monumento.fechaConstruccion}
        </p>
        <p style="font-size:0.78rem;color:#495057;margin:0;line-height:1.4;">${desc.substring(0, 120)}…</p>
      </div>
    `;
  }

  seleccionarMonumento(monumento: Monumento): void {
    this.monumentoSeleccionado = monumento;
    this.cdr.markForCheck();
    this.mapa.flyTo([monumento.latitud, monumento.longitud], 13, {
      animate: true,
      duration: 1,
    });
    const marcador = this.marcadores.get(monumento.id);
    if (marcador) marcador.openPopup();

  }

  irADetalle(): void {
    if (this.monumentoSeleccionado) {
      this.router.navigate(['/monumentos', this.monumentoSeleccionado.id]);
    }
  }

  getDescripcion(monumento: Monumento): string {
    this.translate.lang();
    const traducido = this.translate.t(`MONUMENTOS_DATA.${monumento.id}.descripcion`);
    return traducido !== `MONUMENTOS_DATA.${monumento.id}.descripcion` ? traducido : monumento.descripcion;
  }
}