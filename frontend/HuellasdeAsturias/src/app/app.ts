import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { BarraNavegacion } from "./components/barra-navegacion/barra-navegacion";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, BarraNavegacion],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('huellas-de-asturias');
}
