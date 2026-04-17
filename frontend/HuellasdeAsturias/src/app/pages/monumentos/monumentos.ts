import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { Monumento } from '../../model/monumento';
import { MonumentoService } from '../../service/monumento-service';

@Component({
  selector: 'app-monumentos',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './monumentos.html',
  styleUrl: './monumentos.css',
})
export class Monumentos implements OnInit {

  monumentos: Monumento[] = [];
  monumentoSeleccionado: Monumento | null = null;
  busqueda: string = '';

  constructor(private monumentoService: MonumentoService) { }

  ngOnInit(): void {
    this.monumentoService.getAll().subscribe({
      next: (data) => {
        this.monumentos = data;
        if (data.length > 0) this.monumentoSeleccionado = data[0];
      },
      error: (error) => console.error('Error al cargar monumentos:', error)
    });
  }

  seleccionarMonumento(monumento: Monumento): void {
    this.monumentoSeleccionado = monumento;
  }
}
