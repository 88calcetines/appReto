import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, catchError, of } from 'rxjs';
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
    // Verificar si ya hay un token en localStorage al iniciar el servicio
    const token = localStorage.getItem('token');
    if (token) {
      this.loggedIn.next(true);
      // Aquí podrías recuperar el usuario actual si lo tienes
    }
  }

  login(credentials: { email: string; contrasena: string }) {
    return this.http.post<any>(`${this.apiUrl}/login`, credentials).pipe(
      catchError((error) => {
        console.error('Error en el inicio de sesión', error);
        return of(null); // Manejar el error y retornar null
      })
    ).subscribe(response => {
      if (response && response.token) {
        this.storeUserData(response.token, response.user);
        this.loggedIn.next(true);
        console.log('Inicio de sesión exitoso');
      }
    });
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
