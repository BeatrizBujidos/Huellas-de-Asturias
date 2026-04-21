import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Imagen } from '../model/imagen';

@Injectable({
  providedIn: 'root',
})
export class ImagenService {

  private readonly apiUrl = 'http://localhost:8080/api/imagenes';
  private readonly http = inject(HttpClient);

  // Todas las imágenes de una entidad ordenadas
  getByEntidad(tipoEntidad: 'OBRA' | 'MONUMENTO', idEntidad: number): Observable<Imagen[]> {
    return this.http.get<Imagen[]>(`${this.apiUrl}/entidad/${tipoEntidad}/${idEntidad}`).pipe(
      catchError(() => of([]))
    );
  }
 
  // Imagen principal de una entidad
  getImagenPrincipal(tipoEntidad: 'OBRA' | 'MONUMENTO', idEntidad: number): Observable<Imagen | null> {
    return this.http.get<Imagen>(`${this.apiUrl}/principal/${tipoEntidad}/${idEntidad}`).pipe(
      catchError(() => of(null))
    );
  }
 
  // Solo la URL de la imagen principal
  getUrlPrincipal(tipoEntidad: 'OBRA' | 'MONUMENTO', idEntidad: number): Observable<string | null> {
    return this.getImagenPrincipal(tipoEntidad, idEntidad).pipe(
      map(img => img ? img.url : null)
    );
  }
    
}
  
