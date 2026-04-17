import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Imagen } from '../model/imagen';

@Injectable({
  providedIn: 'root',
})
export class ImagenService {
  private readonly apiUrl = 'http://localhost:8080/api/imagenes';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Imagen[]> {
    return this.http.get<Imagen[]>(this.apiUrl);
  }
    
}
  
