import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Monumento } from '../model/monumento';

@Injectable({
  providedIn: 'root',
})
export class MonumentoService {

  private readonly apiUrl = '/api/monumentos';

  constructor(private http: HttpClient) {}
  
  getAll(): Observable<Monumento[]> {
    return this.http.get<Monumento[]>(this.apiUrl);
  }

  getById(id: number): Observable<Monumento> {
    return this.http.get<Monumento>(`${this.apiUrl}/${id}`);
  }

  create(monumento: Monumento): Observable<Monumento> {
    return this.http.post<Monumento>(this.apiUrl, monumento);
  }

  update(id: number, monumento: Monumento): Observable<Monumento> {
    return this.http.put<Monumento>(`${this.apiUrl}/${id}`, monumento);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
