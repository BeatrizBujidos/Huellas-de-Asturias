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
        titulo: 'Nueva obra de Zuloaga adquirida por el Museo de Bellas Artes de Asturias',
        descripcion: 'El lienzo ingresó en el Museo a principios de enero de 2026 y quedará expuesto de manera permanente en la sala 18.',
        imagen: 'assets/imagenes/museos/museoBellasArtes.jpg',
        enlace: 'https://www.museobbaa.com/noticia/una-nueva-obra-zuloaga-adquirida-museo-bellas-artes-asturias/'
      },
      {
        id: 2,
        titulo: 'Los conciertos del museo',
        descripcion: 'Nueva sesión de Los Conciertos del Museo organizado por Juventudes Musicales de Gijón.',
        imagen: 'assets/imagenes/museos/museoJovellanos.jpg',
        enlace: 'https://www.gijon.es/es/directorio/museo-casa-natal-de-jovellanos'
      },
      {
        id: 3,
        titulo: 'Exposición De rerum natura',
        descripcion: 'De la naturaleza de las cosas.',
        imagen: 'assets/imagenes/museos/laboral.jpg',
        enlace: 'https://laboralcentrodearte.org/es/exposiciones/de-rerum-natura/'
      },
      {
        id: 4,
        titulo: 'Los findes al museo',
        descripcion: 'Visitas guiadas abril 2026.',
        imagen: 'assets/imagenes/museos/museoEvaristoValle.jpg',
        enlace: 'https://evaristovalle.com/los-findes-al-museo-visitas-guiadas-abril-2026/'
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
