import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss']
})
export class LoginPage {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      contrasena: ['', [Validators.required]]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const loginData = this.loginForm.value;
      console.log('Datos de login:', loginData);

      this.authService.login(loginData).subscribe(
        response => {
          console.log('Login exitoso', response);
          if (response && response.token) {
            console.log('Token JWT:', response.token);
            localStorage.setItem('jwtToken', response.token); // Guarda el token en localStorage
            this.router.navigate(['/tabs/tab1']);
          } else {
            console.warn('Token no encontrado en la respuesta', response);
          }
        },
        error => {
          console.error('Error en el login', error);
        }
      );
    }
  }

  onRegister() {
    console.log('Registro');
    this.router.navigate(['/tabs/register']);
  }
}