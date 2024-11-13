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
    return this.http.get<any[]>(this.apiAlmi + 'productosMejoresValorados');
  }

  getFotoUsuario(userId: number) {
    return this.http.get<{ foto: string | null }>(`${this.apiAlmi}api/usuario/foto/${userId}`);
  }

  updateFoto(userId: number, formData: FormData): Observable<any> {
    return this.http.post<any>(`${this.apiAlmi}/usuarios/${userId}/foto`, formData);
  }
  
  productosRecientes(): Observable<any[]> {
    return this.http.get<any[]>(this.apiAlmi + 'productosRecientes');
  }

  actualizarFotoPerfil(userId: number, formData: FormData) {
    const url = `${this.apiAlmi}miperfil/${userId}/foto`;
    return this.http.post(url, formData).toPromise();
  }

  obtenerPerfil(userId: number) {
    const url = `${this.apiAlmi}miperfil/${userId}`;
    return this.http.get(url).toPromise();
  }

  actualizarStock(productId: number, cantidad: number): Observable<any> {
    return this.http.put<any>(`${this.apiAlmi}/productos/${productId}/stock`, { cantidad });
  }

}