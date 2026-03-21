import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { BarraNavegacion } from "./barra-navegacion/barra-navegacion";
import { Carrusel } from "./carrusel/carrusel";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, BarraNavegacion, Carrusel],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('huellas-de-asturias');
}
