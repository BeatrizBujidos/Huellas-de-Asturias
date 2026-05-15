import { CommonModule } from '@angular/common';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { TranslatePipe } from '../../pipe/translate.pipe';

interface Noticia {
  id: number;
  titulo: string;
  descripcion: string;
  imagen: string;
  enlace: string;
}

@Component({
  selector: 'app-carrusel',
  imports: [CommonModule, TranslatePipe],
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
      },
      {
        id: 5,
        titulo: 'Agua',
        descripcion: 'Exposición de Edward Burtynsky.',
        imagen: 'assets/imagenes/museos/centroNiemeyer.jpg',
        enlace: 'https://www.centroniemeyer.es/events/event/edward-burtynsky-agua/'
      },
      {
        id: 6,
        titulo: 'Santa María del Naranco en el siglo IX',
        descripcion: 'Viaje inmersivo al prerrománico asturiano con realidad virtual.',
        imagen: 'assets/imagenes/museos/centroInterpretacion.jpg',
        enlace: 'https://www.centroprerromanicoasturiano.com/es/agenda/-/calendarsuite/event/santa-maria-de-naranco-en-el-siglo-ix-viaje-inmersivo-al-prerromanico-asturiano-con-realidad-virtual/15083475/mO9xSINzVJeP'
      },
      {
        id: 7,
        titulo: 'Alejandro Sirio. La caligrafía del dibujo',
        descripcion: 'Hasta el 14 de junio de 2026.',
        imagen: 'assets/imagenes/museos/museoPinole.jpg',
        enlace: 'https://www.gijon.es/es/eventos/alejandro-sirio-la-caligrafia-del-dibujo-l-exposicion'
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
