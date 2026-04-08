import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-barra-navegacion',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './barra-navegacion.html',  
  styleUrl: './barra-navegacion.css'
})
export class BarraNavegacion {
  isNavbarCollapsed = true;

  toggleMenu(){
    this.isNavbarCollapsed = !this.isNavbarCollapsed;
  }
}
