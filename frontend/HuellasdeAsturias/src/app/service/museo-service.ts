import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Museo } from '../model/museo'; 

@Injectable({
  providedIn: 'root',
})
export class MuseoService {

  private readonly apiUrl = 'http://localhost:8080/api/museos';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Museo[]> {
    return this.http.get<Museo[]>(this.apiUrl);
  }

  getById(id: number): Observable<Museo> {
    return this.http.get<Museo>(`${this.apiUrl}/${id}`);
  }

  create(museo: Museo): Observable<Museo> {
    return this.http.post<Museo>(this.apiUrl, museo);
  }

  update(id: number, museo: Museo): Observable<Museo> {
    return this.http.put<Museo>(`${this.apiUrl}/${id}`, museo);
  } 

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  } 
}
