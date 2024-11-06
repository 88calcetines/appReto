import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss']
})
export class LoginPage{
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private router:Router) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      contrasena: ['', [Validators.required]]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      this.authService.login(this.loginForm.value).subscribe(
        response => {
          console.log('Login exitoso', response);
          if (response && response.token) {
            console.log('Token:', response.token);
          } else {
            console.warn('Token no encontrado en la respuesta', response);
            // Handle the case where the token is not present
            // For example, you can navigate to an error page or show a message to the user
          }
          this.router.navigate(['/tabs/tab1']);
        },
        error => {
          console.error('Error en el registro', error);
        }
      );
    }
  }

  onRegister() {
    console.log('Registro');
    this.router.navigate(['/tabs/register']);
  }
}
