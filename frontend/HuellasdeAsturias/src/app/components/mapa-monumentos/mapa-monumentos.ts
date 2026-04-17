import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, OnInit, OnDestroy, ChangeDetectionStrategy, ChangeDetectorRef, PLATFORM_ID, inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { max } from 'rxjs';

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
  1: { label: 'Alfonso II (791–842)', color: '#1a4a7a' },
  2: { label: 'Ramirense (842–866)', color: '#dfd225' },
  3: { label: 'Alfonso III (866–910)', color: '#1a7a4a' },
};

@Component({
  selector: 'app-mapa-monumentos',
  imports: [CommonModule],
  templateUrl: './mapa-monumentos.html',
  styleUrl: './mapa-monumentos.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})

export class MapaMonumentos implements OnInit, AfterViewInit, OnDestroy {

  private readonly platformId = inject(PLATFORM_ID);
  private readonly cdr = inject(ChangeDetectorRef);

  monumentos: Monumento[] = [
    {
      id: 1, idEpoca: 1,
      nombre: 'San Tirso',
      fechaConstruccion: 'Siglo IX',
      descripcion: 'Fundada por Alfonso II "El Casto" como parte del complejo episcopal ovetense. Aunque sufrió múltiples reformas a lo largo de los siglos (la fachada actual es barroca), conserva de su origen prerrománico el testero de la capilla mayor con su emblemática ventana trigeminada de arcos de medio punto y columnas con capiteles de tradición visigoda. Esta ventana tripartita se ha convertido en uno de los iconos visuales del arte asturiano.',
      latitud: 43.3627, longitud: -5.8436, imagen: 'assets/monumentos/San_Tirso_01.jpg',
    },
    {
      id: 2, idEpoca: 1,
      nombre: 'Cámara Santa',
      fechaConstruccion: 'Siglo IX',
      descripcion: 'Capilla palatina de dos pisos integrada en la Catedral de Oviedo. El nivel superior (San Miguel) custodia las reliquias del Arca Santa y las cruces de la Victoria y los Ángeles, símbolos de Asturias. El espacio inferior (Santa Leocadia) presenta bóveda de cañón. Destaca el apostolado románico añadido posteriormente en sus columnas, siendo uno de los conjuntos escultóricos más importantes del siglo XII en España.',
      latitud: 43.3625, longitud: -5.8431, imagen: 'assets/monumentos/Camara_Santa_01.jpg',
    },
    {
      id: 3, idEpoca: 1,
      nombre: 'San Julián de los Prados',
      fechaConstruccion: '812–842',
      descripcion: 'Conocida popularmente como Santullano, es la iglesia más grande y antigua del periodo asturiano. Mandada construir por Alfonso II, destaca por su gran amplitud, su planta basilical con transepto elevado y, sobre todo, por su excepcional ciclo de pinturas murales anicónicas con motivos geométricos y arquitectónicos que cubrían originalmente todos sus muros interiores.',
      latitud: 43.3670, longitud: -5.8368, imagen: 'assets/monumentos/Santullano_01.jpg',
    },
    {
      id: 4, idEpoca: 1,
      nombre: 'Santa María de Bendones',
      fechaConstruccion: 'Siglo IX',
      descripcion: 'Estructura basilical que sigue el modelo de Santullano, aunque con dimensiones más reducidas. Fue reconstruida tras sufrir graves daños en 1936 durante la Guerra Civil española. Destaca su gran nártex o pórtico de entrada y la disposición de sus naves laterales, siendo un ejemplo clave del estilo bajo el reinado de Alfonso II.',
      latitud: 43.3364, longitud: -5.8039, imagen: 'assets/monumentos/Bendones_01.jpg',
    },
    {
      id: 5, idEpoca: 1,
      nombre: 'San Pedro de Nora',
      fechaConstruccion: 'Siglo IX',
      descripcion: 'Iglesia de planta basilical con tres naves separadas por arquerías de medio punto sobre pilares cuadrados, situada en un entorno rural junto al río Nora. Presenta una cabecera tripartita característica del arte asturiano. A pesar de su reconstrucción casi completa tras la Guerra Civil, mantiene la pureza de líneas y la sobriedad volumétrica características del arte asturiano.',
      latitud: 43.3644, longitud: -5.9525, imagen: 'assets/monumentos/Nora_02.jpg',
    },
    {
      id: 6, idEpoca: 2,
      nombre: 'Santa María del Naranco',
      fechaConstruccion: '842',
      descripcion: 'Obra maestra del periodo ramirense. Originalmente concebida como un aula regia o palacio de recreo situado en las afueras de Oviedo, fue transformada en iglesia en el siglo XII. Destaca por su arquitectura innovadora: estructura rectangular de dos pisos con miradores laterales, bóveda de cañón reforzada con arcos fajones y exquisita decoración escultórica en medallones y capiteles. Representa la cúspide de la arquitectura prerrománica europea.',
      latitud: 43.3790, longitud: -5.8659, imagen: 'assets/monumentos/Naranco_02.jpg',
    },
    {
      id: 7, idEpoca: 2,
      nombre: 'San Miguel de Lillo',
      fechaConstruccion: '848',
      descripcion: 'Iglesia palatina dedicada a San Miguel Arcángel, situada a escasos metros del Naranco. Actualmente solo se conserva el tercio occidental de la planta original tras un derrumbe en el siglo XIII. Es famosa por la esbeltez de sus proporciones, sus celosías de piedra tallada con motivos geométricos y los relieves de sus jambas inspirados en un díptero consular romano.',
      latitud: 43.3808, longitud: -5.8672, imagen: 'assets/monumentos/Lillo_01.jpg',
    },
    {
      id: 8, idEpoca: 2,
      nombre: 'Santa Cristina de Lena',
      fechaConstruccion: '852',
      descripcion: 'Pequeña joya del periodo ramirense situada sobre una colina con vistas panorámicas al valle del Caudal. Su elemento más distintivo es el iconostasio: una triple arquería de piedra que separa el presbiterio de la nave, decorada con piezas visigodas reutilizadas. Su planta de cruz griega y la calidad de su sillería evidencian la madurez del estilo ramirense.',
      latitud: 43.1275, longitud: -5.8144, imagen: 'assets/monumentos/Lena_02.jpg',
    },
    {
      id: 9, idEpoca: 3,
      nombre: 'Foncalada',
      fechaConstruccion: 'Siglo IX',
      descripcion: 'Única construcción civil de utilidad pública del prerrománico asturiano que se conserva in situ. Se trata de una fuente de agua potable protegida por un templete de piedra con arco de medio punto. En el frontón luce la Cruz de la Victoria junto con su inscripción. Es un testimonio excepcional de la ingeniería hidráulica altomedieval.',
      latitud: 43.3653, longitud: -5.8455, imagen: 'assets/monumentos/Foncalada_01.jpg',
    },
    {
      id: 10, idEpoca: 3,
      nombre: 'Santo Adriano de Tuñón',
      fechaConstruccion: '891',
      descripcion: 'Iglesia de estructura sencilla y planta basilical de tres naves, mandada construir por Alfonso III y su esposa Jimena. Conserva importantes restos de pinturas murales originales en la cabecera con motivos geométricos y vegetales. Es un testimonio fundamental de la expansión del reino asturiano hacia los valles interiores de la cordillera Cantábrica.',
      latitud: 43.2925, longitud: -5.9814, imagen: 'assets/monumentos/Tuñon_02.jpg',
    },
    {
      id: 11, idEpoca: 3,
      nombre: 'San Salvador de Valdediós',
      fechaConstruccion: '893',
      descripcion: 'Denominada popularmente "El Conventín", representa el apogeo del periodo alfonsino. Situada en un entorno natural exuberante del valle de Boides. Destaca por su pórtico lateral abierto (único en el prerrománico asturiano) y por mantener un equilibrio perfecto entre la influencia asturiana tradicional y los nuevos elementos mozárabes introducidos por artesanos procedentes de Al-Ándalus.',
      latitud: 43.4375, longitud: -5.4686, imagen: 'assets/monumentos/Valdedios_02.jpg',
    },
    {
      id: 12, idEpoca: 3,
      nombre: 'San Salvador de Priesca',
      fechaConstruccion: '921',
      descripcion: 'Consagrada en el siglo X, sigue fielmente los esquemas arquitectónicos de Santullano aunque en una época más tardía. Conserva una notable parte de su decoración pictórica original en los muros interiores y arcos triunfales, con influencias mozárabes. Sirve de eslabón de transición entre el estilo asturiano puro y el primer románico.',
      latitud: 43.4897, longitud: -5.2897, imagen: 'assets/monumentos/Priesca_01.jpg',
    },
    {
      id: 13, idEpoca: 3,
      nombre: 'Santiago de Gobiendes',
      fechaConstruccion: 'c. 921',
      descripcion: 'Situada cerca de la costa oriental asturiana (Colunga), esta iglesia destaca por la elegancia de sus proporciones y la calidad de su fábrica de sillarejo. Fue profundamente reformada en el siglo XVIII, pero las restauraciones modernas han permitido recuperar su esencia prerrománica, especialmente visible en la cabecera tripartita y los capiteles decorados.',
      latitud: 43.4731, longitud: -5.2289, imagen: 'assets/monumentos/Gobiendes_01.jpg',
    },
  ];

  monumentoSeleccionado: Monumento | null = null;
  readonly epocas = EPOCAS;

  private mapa: any;
  private marcadores: Map<number, any> = new Map();

  ngOnInit(): void { }

  async ngAfterViewInit(): Promise<void> {
    if (isPlatformBrowser(this.platformId)) {
      setTimeout(async () => {
        const leaflet = await import('leaflet');
        const L = (leaflet as any).default ?? leaflet;
        this.iniciarMapa(L);
      }, 0);
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

    //Marcadores de color según época
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
    //Leyenda de épocas
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
        <p style="font-size:0.78rem;color:#495057;margin:0;line-height:1.4;">${monumento.descripcion.substring(0, 120)}…</p>
      </div>
    `;
  }

  seleccionarMonumento(monumento: Monumento): void {
    this.monumentoSeleccionado = monumento;
    this.cdr.markForCheck();
    // Centrar el mapa en el monumento seleccionado
    this.mapa.flyTo([monumento.latitud, monumento.longitud], 13, {
      animate: true,
      duration: 1,
    });
    // Abrir el popup del marcador
    const marcador = this.marcadores.get(monumento.id);
    if (marcador) {
      marcador.openPopup();
    }
  }
}
