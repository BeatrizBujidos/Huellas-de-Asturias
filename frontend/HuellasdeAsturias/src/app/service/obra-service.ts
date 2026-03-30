import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Obra } from '../model/obra';

@Injectable({
  providedIn: 'root',
})
export class ObraService {

  private readonly apiUrl = 'http://localhost:8080/api/obras';
  
  constructor(private http: HttpClient) {}

  getAll(): Observable<Obra[]> {
    return this.http.get<Obra[]>(this.apiUrl);
  } 

  getById(id: number): Observable<Obra> {
    return this.http.get<Obra>(`${this.apiUrl}/${id}`);
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
