import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, catchError, of} from 'rxjs';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'https://calcetines88.duckdns.org';
  private loggedIn = new BehaviorSubject<boolean>(false);
  private currentUserSubject = new BehaviorSubject<any>(null);
  currentUser$ = this.currentUserSubject.asObservable();

  constructor(private http: HttpClient, private router: Router) {
    const token = localStorage.getItem('token');
    if (token) {
      this.loggedIn.next(true);
    }
  }

  login(credentials: { email: string; contrasena: string }) {
    return this.http.post<any>(`${this.apiUrl}/login`, credentials).pipe(
      tap((response: any) => {
        console.log('Respuesta completa del servidor:', response);
  
        if (response && response.token) {
          this.storeUserData(response.token, response.user);
          console.log('Usuario:', response.user, 'Token:', response.token);
          this.loggedIn.next(true);
        } else {
          console.error('Token no encontrado en la respuesta', response);
        }
      }),
      catchError((error) => {
        console.error('Error en el inicio de sesión', error);
        return of({ error: 'Error en el inicio de sesión' });
      })
    );
  }

  register(credentials: {nombre: string, apellido1: string, apellido2: string, email: string; contrasena: string }) {
    return this.http.post<any>(`${this.apiUrl}/register`, credentials);
  }

  private storeUserData(token: string, user: any) {
    localStorage.setItem('token', token);
    this.currentUserSubject.next(user);
  }

  logout() {
    localStorage.removeItem('token');
    this.currentUserSubject.next(null);
    this.loggedIn.next(false);
    this.router.navigate(['/login']);
  }

  isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  getToken() {
    return localStorage.getItem('token');
  }

  getCurrentUser() {
    return this.currentUserSubject.getValue();
  }
}
