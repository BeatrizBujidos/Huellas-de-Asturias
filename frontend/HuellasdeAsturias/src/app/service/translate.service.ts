import { Injectable, signal, computed, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export type Lang = 'es' | 'en';

//Fallback con traducciones en español para evitar errores si no se carga el JSON
const ES_FALLBACK: Record<string, any> = {
   "NAV": {
    "BRAND": "Huellas de Asturias",
    "ARTISTAS": "Artistas",
    "OBRAS": "Obras",
    "MAPA": "Mapa",
    "LANG": "EN"
  },
  "CARRUSEL": {
    "TITULO": "Noticias de los Museos Asturianos",
    "LEER_MAS": "Leer más",
    "ANTERIOR": "Anterior",
    "SIGUIENTE": "Siguiente"
  },
  "ARTISTAS_SECCION": {
    "TITULO": "¿Quién es quién?",
    "SUBTITULO": "Descúbrelo",
    "VER_MAS": "Ver más",
    "PAGINACION": "{{desde}}–{{hasta}} de {{total}}",
    "ARIA_ANTERIOR": "Anteriores artistas",
    "ARIA_SIGUIENTE": "Siguientes artistas"
  },
  "ARTISTAS": {
    "BUSCAR_PLACEHOLDER": "Buscar artista por nombre...",
    "BIOGRAFIA": "Biografía",
    "ESTILO": "Estilo:",
    "OBRAS_DESTACADAS": "Obras Destacadas",
    "VER_TODAS": "Ver todas",
    "VER_DETALLES": "Ver detalles",
    "CARGANDO": "Cargando artista...",
    "CARGANDO_OBRAS": "Cargando obras..."
  },
  "OBRAS": {
    "TITULO": "Galería de Obras",
    "SUBTITULO": "Explora nuestra colección de arte por artista, técnica o época",
    "FILTRO_ARTISTA": "Artista",
    "FILTRO_TECNICA": "Técnica",
    "FILTRO_EPOCA": "Época",
    "TODOS_ARTISTAS": "Todos los artistas",
    "TODAS_TECNICAS": "Todas las técnicas",
    "TODAS_EPOCAS": "Todas las épocas",
    "LIMPIAR_FILTROS": "Limpiar todos los filtros",
    "LIMPIAR": "Limpiar filtros",
    "CARGANDO": "Cargando obras...",
    "RESULTADO": "{{count}} obra(s) encontrada(s)",
    "FILTRO_ARTISTA_LABEL": "Artista",
    "FILTRO_TECNICA_LABEL": "Técnica",
    "FILTRO_EPOCA_LABEL": "Época",
    "SIN_RESULTADOS": "No se encontraron obras con los filtros aplicados.",
    "VER_DETALLES": "Ver detalles"
  },
  "OBRA_DETALLE": {
    "GALERIA": "Galería",
    "DESCRIPCION": "Descripción",
    "FICHA_TECNICA": "Ficha Técnica",
    "TECNICA": "Técnica",
    "DIMENSIONES": "Dimensiones",
    "FECHA": "Fecha",
    "MUSEO": "Museo",
    "EPOCA": "Época",
    "VER_ARTISTA": "Ver artista",
    "CARGANDO": "Cargando obra..."
  },
  "MONUMENTOS": {
    "TITULO": "Mapa del Prerrománico",
    "SUBTITULO": "Explora las joyas del arte asturiano. Haz clic en cualquier marcador para descubrir el monumento.",
    "EXPLORAR_EPOCA": "Explorar por época:",
    "CONSTRUCCION": "Construcción",
    "ESTILO": "Estilo",
    "UBICACION": "Ubicación",
    "DESCRIPCION": "Descripción",
    "GALERIA": "Galería fotográfica",
    "COMO_LLEGAR": "Cómo llegar",
    "VER_DETALLES": "Ver más detalles",
    "CARGANDO": "Cargando imágenes...",
    "SIN_SELECCION": "Haz clic en un marcador del mapa para ver la información del monumento.",
    "NO_ENCONTRADO": "Monumento no encontrado."
  },
  "MAPA": {
    "VER_INFO": "Ver más información",
    "EPOCAS": {
      "1": "Alfonso II (791–842)",
      "2": "Ramirense (842–866)",
      "3": "Alfonso III (866–910)"
    }
  },
  "COMUN": {
    "CARGANDO": "Cargando...",
    "RETRATO_DE": "Retrato de",
    "LIGHTBOX_CERRAR": "Cerrar",
    "LIGHTBOX_ANTERIOR": "Imagen anterior",
    "LIGHTBOX_SIGUIENTE": "Imagen siguiente"
  }
};

@Injectable({ providedIn: 'root' })
export class TranslateService {

  private readonly http = inject(HttpClient);
  private translations: Record<string, any> = ES_FALLBACK;
  readonly lang = signal<Lang>('es');

  // Carga el JSON del idioma activo
  load(lang: Lang): Promise<void> {
    return new Promise(resolve => {
      this.http.get<Record<string, any>>(`assets/i18n/${lang}.json`).subscribe({
        next: data => {
          this.translations = data;
          this.lang.set(lang);
          resolve();
        },
        error: () => {
          // Si falla la carga, se mantiene el idioma anterior
          this.lang.set(lang);
          resolve();
        },
      });
    });
  }

  // Traduce una clave con interpolación opcional: t('OBRAS.RESULTADO', { count: 5 })
  t(key: string, params?: Record<string, string | number>): string {
    const value = key.split('.').reduce<any>((obj, k) => obj?.[k], this.translations);
    if (typeof value !== 'string') return key;
    if (!params) return value;
    return Object.entries(params).reduce(
      (str, [k, v]) => str.replace(new RegExp(`{{${k}}}`, 'g'), String(v)),
      value
    );
  }

  toggle(): void {
    const next: Lang = this.lang() === 'es' ? 'en' : 'es';
    this.load(next);
  }

  isEs(): boolean { return this.lang() === 'es'; }
  isEn(): boolean { return this.lang() === 'en'; }
}
