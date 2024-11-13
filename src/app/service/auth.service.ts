import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';

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
      this.setCurrentUserFromToken(token);
    }
  }

  login(credentials: { email: string; contrasena: string }): Observable<any> {
    const payload = {
      email: credentials.email,
      password: credentials.contrasena
    };
    return this.http.post<any>(`${this.apiUrl}/login`, payload).pipe(
      tap((response: any) => {
        if (response && response.token) {
          this.storeUserData(response.token);
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

  register(credentials: { nombre: string, apellido1: string, apellido2: string, email: string, contrasena: string }): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/register`, credentials).pipe(
      tap((response: any) => {
        if (response && response.token) {
          this.storeUserData(response.token);
          this.loggedIn.next(true);
        } else {
          console.error('Token no encontrado en la respuesta', response);
        }
      }),
      catchError((error) => {
        console.error('Error en el registro', error);
        return of({ error: 'Error en el registro' });
      })
    );
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    this.currentUserSubject.next(null);
    this.loggedIn.next(false);
    this.router.navigate(['/login']);
  }

  isLoggedIn(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  private storeUserData(token: string) {
    localStorage.setItem('token', token);
    this.setCurrentUserFromToken(token);
  }

  private setCurrentUserFromToken(token: string) {
    const decodedToken: any = jwtDecode(token);
    console.log('Token decodificado:', decodedToken);
    const user = {
      email: decodedToken.username
    };
    localStorage.setItem('user', JSON.stringify(user));
    this.currentUserSubject.next(user);
    this.getPerfil().subscribe({
      next: (perfil) => {
        console.log('Perfil recibido:', perfil);
        if (perfil) {
          const updatedUser = {
            ...user,
            nombre: perfil.nombre,
            apellido1: perfil.apellido1,
            apellido2: perfil.apellido2,
            imagen: perfil.imagen
          };
          localStorage.setItem('user', JSON.stringify(updatedUser));
          this.currentUserSubject.next(updatedUser);
        }
      },
      error: (error) => {
        console.error('Error al obtener el perfil', error);
      }
    });
  }

  getCurrentUser() {
    const user = localStorage.getItem('user');
    if(user) {
      const parsedUser = JSON.parse(user);
      return {
        id: parsedUser.id,
        nombre: parsedUser.nombre,
        apellido1: parsedUser.apellido1,
        apellido2: parsedUser.apellido2,
        email: parsedUser.email,
        imagen: parsedUser.imagen
      }
    }
    return null;
  }

  

  getPerfil(): Observable<any> {
    const token = this.getToken();
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any>(`${this.apiUrl}/miperfil`, { headers }).pipe(
      catchError((error) => {
        console.error('Error al obtener el perfil', error);
        return of(null);
      })
    );
  }
  
  
}