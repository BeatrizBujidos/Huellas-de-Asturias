import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { BarraNavegacion } from "./components/barra-navegacion/barra-navegacion";
import { Carrusel } from "./components/carrusel/carrusel";
import { SeccionArtistas } from "./components/seccion-artistas/seccion-artistas";
import { MapaMonumentos } from "./components/mapa-monumentos/mapa-monumentos";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, BarraNavegacion, Carrusel, SeccionArtistas, MapaMonumentos],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('huellas-de-asturias');
}
