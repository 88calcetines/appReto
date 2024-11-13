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

  getFotoUsuario(userId: number): Observable<string> {
    return this.http.get<string>(`${this.apiAlmi}miperfil/${userId}/foto`);
  }
  updateFoto(usuarioId: number, formData: FormData): Observable<any> {
    const url = `${this.apiAlmi}/miperfil/${usuarioId}/foto`;
    return this.http.post<any>(url, formData);
  }

  productosRecientes(): Observable<any[]> {
    return this.http.get<any[]>(this.apiAlmi + 'productosRecientes');
  }

// Método para actualizar la foto de perfil
actualizarFotoPerfil(userId: number, formData: FormData) {
  const url = `${this.apiAlmi}miperfil/${userId}/foto`;
  return this.http.post(url, formData).toPromise();
}

// Otros métodos que necesiten la URL base de `apiAlmi`
// Ejemplo de otro método:
obtenerPerfil(userId: number) {
  const url = `${this.apiAlmi}miperfil/${userId}`;
  return this.http.get(url).toPromise();
}

}