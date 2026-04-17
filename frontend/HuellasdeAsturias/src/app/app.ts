import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { BarraNavegacion } from "./components/barra-navegacion/barra-navegacion";
import { Footer } from "./components/footer/footer";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, BarraNavegacion, Footer],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('Huellas de Asturias');
}
