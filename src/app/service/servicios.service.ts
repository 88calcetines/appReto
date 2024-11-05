import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServiciosService {

  private apiAlmi = "https://calcetines88.duckdns.org/";

  constructor(private http: HttpClient) { }

  getProductos(): Observable<any[]> {
    return this.http.get<any[]>(this.apiAlmi + 'productos');
  }

  getProductosPorNombre(nombre: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiAlmi}productos/${nombre}`);
  }

}