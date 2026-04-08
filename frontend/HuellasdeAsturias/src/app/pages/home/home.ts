import { Component } from '@angular/core';
import { Carrusel } from "../../components/carrusel/carrusel";
import { SeccionArtistas } from "../../components/seccion-artistas/seccion-artistas";
import { MapaMonumentos } from '../../components/mapa-monumentos/mapa-monumentos';

@Component({
  selector: 'app-home',
  imports: [Carrusel, SeccionArtistas, MapaMonumentos],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {

}
