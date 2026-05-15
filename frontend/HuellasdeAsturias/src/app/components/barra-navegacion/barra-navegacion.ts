import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { TranslateService } from '../../service/translate.service';
import { TranslatePipe } from '../../pipe/translate.pipe';


@Component({
  selector: 'app-barra-navegacion',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive, TranslatePipe],
  templateUrl: './barra-navegacion.html',
  styleUrl: './barra-navegacion.css'
})
export class BarraNavegacion {
  readonly translate = inject(TranslateService);
  isNavbarCollapsed = true;

  toggleMenu() {
    this.isNavbarCollapsed = !this.isNavbarCollapsed;
  }

  toggleLang(): void {
    this.translate.toggle();
  }

  get currentFlag(): string {
    return this.translate.isEs() ? 'assets/imagenes/gb.webp' : 'assets/imagenes/sp.webp';
  }
}