import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServiciosService {

  private apiAlmi = "https://calcetines88.duckdns.org/";

  constructor(private http: HttpClient) { }

  getProductos(): Observable<any[]> {
    return this.http.get<any[]>(this.apiAlmi + 'productoCategoria');
  }

  getProductosPorNombre(nombre: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiAlmi}productoCategoria/${nombre}`);
  }

  getProductosMejoresValorados(): Observable<any[]> {
    return this.http.get<any[]>(this.apiAlmi + 'productoMejoresValorados');
  }


  getFotoUsuario(userId: number): Observable<string> {
    return this.http.get<string>(`${this.apiAlmi}miperfil/${userId}/foto`);
  }
  updateFoto(usuarioId: number, formData: FormData): Observable<any> {
    const url = `${this.apiAlmi}/miperfil/${usuarioId}/foto`;
    return this.http.post<any>(url, formData);
  }

}