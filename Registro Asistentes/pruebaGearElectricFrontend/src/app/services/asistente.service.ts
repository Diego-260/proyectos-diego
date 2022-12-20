import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Asistente } from '../models/asistente';

@Injectable({
  providedIn: 'root'
})
export class AsistenteService {
  url = 'http://localhost:4000/api/asistentes/'

  constructor(private http: HttpClient) { }

  getAsistentes(): Observable<any>{
    return this.http.get(this.url);
  }

  deleteAsistente(id: String): Observable<any>{
    return this.http.delete(this.url + id);
  }

  createAsistente(asistente: Asistente): Observable<any>{
    return this.http.post(this.url, asistente);
  }

  getAsistente(id: String): Observable<any>{
    return this.http.get(this.url + id);
  }

  editAsistente(id: String, asistente: Asistente): Observable<any>{
    return this.http.put(this.url + id, asistente);
  }

}
