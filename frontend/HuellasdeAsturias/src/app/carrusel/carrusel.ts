import { CommonModule } from '@angular/common';
import { Component, OnInit, OnDestroy } from '@angular/core';

interface Noticia {
  id: number;
  titulo: string;
  descripcion: string;
  imagen: string;
  enlace: string;
}

@Component({
  selector: 'app-carrusel',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './carrusel.html',
  styleUrls: ['./carrusel.css']
})

export class Carrusel implements OnInit, OnDestroy {
  noticias: Noticia[] = [];
  currentIndex = 0;
  intervalId: any;

  ngOnInit() {
    this.loadNoticias();
    this.startAutoSlide();
  }

  ngOnDestroy() {
    if (this.intervalId) {
      clearInterval(this.intervalId);
    }
  }

  loadNoticias() {
    this.noticias = [
      {
        id: 1,
        titulo: 'Nueva exposición en el Museo de Bellas Artes',
        descripcion: 'Descubre "Asturias a través de los siglos", una muestra que recoge obras desde el medievo hasta la actualidad. Hasta el 15 de diciembre.',
        imagen: 'assets/imagenes/museos/museoBellasArtes.jpg',
        enlace: 'https://www.museobbaa.com/'
      },
      {
        id: 2,
        titulo: 'Noche de los museos',
        descripcion: 'Apertura especial nocturna con visitas guiadas, conciertos y actividades para todos los públicos.',
        imagen: 'assets/imagenes/museos/museoJovellanos.jpg',
        enlace: 'https://www.gijon.es/es/directorio/museo-casa-natal-de-jovellanos'
      },
      {
        id: 3,
        titulo: 'Exposición de Arte Contemporáneo',
        descripcion: 'Descubre las últimas tendencias del arte contemporáneo en una exposición que reúne a artistas de toda la región.',
        imagen: 'assets/imagenes/museos/laboral',
        enlace: 'https://laboralcentrodearte.org/es/'
      },
      {
        id: 4,
        titulo: 'Reapertura del Museo de la Minería',
        descripcion: 'Tras las obras de renovación, el museo reabre sus puertas con nuevas salas interactivas y una exposición sobre la historia minera de la región.',
        imagen: 'https://images.unsplash.com/photo-1582555172866-f73bb12a2ab3?ixlib=rb-4.0.3&auto=format&fit=crop&w=1350&q=80',
        enlace: '#'
      }
    ];
  }

  startAutoSlide() {
    this.intervalId = setInterval(() => {
      this.next();
    }, 6000);
  }

  goToSlide(index: number) {
    this.currentIndex = index;
  }

  prev() {
    this.currentIndex = this.currentIndex === 0 
      ? this.noticias.length - 1 
      : this.currentIndex - 1;
  }

  next() {
    this.currentIndex = this.currentIndex === this.noticias.length - 1 
      ? 0 
      : this.currentIndex + 1;
  }

  pauseCarousel() {
    if (this.intervalId) {
      clearInterval(this.intervalId);
    }
  }

  resumeCarousel() {
    this.startAutoSlide();
  }
}
