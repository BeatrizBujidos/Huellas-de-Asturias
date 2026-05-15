import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Artista } from '../model/artista';

@Injectable({
  providedIn: 'root',
})
export class ArtistaService {
  private readonly apiUrl = '/api/artistas';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Artista[]> {
    return this.http.get<Artista[]>(this.apiUrl);
  }

  getById(id: number): Observable<Artista> {
    return this.http.get<Artista>(`${this.apiUrl}/${id}`);
  }
  
  create(artista: Artista): Observable<Artista> {
    return this.http.post<Artista>(this.apiUrl, artista);
  }

  update(id: number, artista: Artista): Observable<Artista> {
    return this.http.put<Artista>(`${this.apiUrl}/${id}`, artista);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
