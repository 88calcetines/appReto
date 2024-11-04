import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ServiciosService {

  private apiAlmi = "https://calcetines88.duckdns.org/";

  constructor(private http: HttpClient) { }

  getProductos(): Observable<any[]> {
    return this.http.get<any[]>(this.apiAlmi + 'productos').pipe(
      catchError(this.handleError)
    );
  }

  getProductosPorNombre(nombre: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiAlmi}productos/${nombre}`).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    console.error('An error occurred:', error);
    return throwError('Something bad happened; please try again later.');
  }
}