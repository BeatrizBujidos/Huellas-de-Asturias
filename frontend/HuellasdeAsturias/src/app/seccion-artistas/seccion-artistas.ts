import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-seccion-artistas',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './seccion-artistas.html',
  styleUrl: './seccion-artistas.css',
})
export class SeccionArtistas {
  
}
