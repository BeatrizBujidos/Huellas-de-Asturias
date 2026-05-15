import { Component, inject } from '@angular/core';
import { Carrusel } from "../../components/carrusel/carrusel";
import { SeccionArtistas } from "../../components/seccion-artistas/seccion-artistas";
import { MapaMonumentos } from '../../components/mapa-monumentos/mapa-monumentos';
import { Meta, Title } from '@angular/platform-browser';

@Component({
  selector: 'app-home',
  imports: [Carrusel, SeccionArtistas, MapaMonumentos],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  //Mejorar SEO
  private readonly meta = inject(Meta);
  private readonly title = inject(Title);

  constructor() {
    this.title.setTitle('Huellas de Asturias');
    this.meta.updateTag(
      { name: 'description', content: 'Descubre el arte y los monumentos prerrmánicos de Asturias. Artistas, obras y monumentos del patrimonio asturiano.' },
    );
  }

}
