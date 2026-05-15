import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Obra } from '../model/obra';

@Injectable({
  providedIn: 'root',
})
export class ObraService {
  private readonly apiUrl = '/api/obras';
  private readonly http = inject(HttpClient);

  getAll(): Observable<Obra[]> {
    return this.http.get<Obra[]>(this.apiUrl);
  }

  getById(id: number): Observable<Obra> {
    return this.http.get<Obra>(`${this.apiUrl}/${id}`);
  }

  // Filtra todas las obras por artistaId en el frontend
  // ya que el backend filtra por nombre, usamos getAll + filter por artistaId
  getByArtistaId(artistaId: number): Observable<Obra[]> {
    return this.getAll().pipe(
      map(obras => obras.filter(o => o.artistaId === artistaId)),
      catchError(() => of([]))
    );
  }

  getByArtistaNombre(nombre: string): Observable<Obra[]> {
    return this.http.get<Obra[]>(`${this.apiUrl}/artista/nombre/${nombre}`).pipe(
      catchError(() => of([]))
    );
  }

  create(obra: Obra): Observable<Obra> {
    return this.http.post<Obra>(this.apiUrl, obra);
  }

  update(id: number, obra: Obra): Observable<Obra> {
    return this.http.put<Obra>(`${this.apiUrl}/${id}`, obra);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}