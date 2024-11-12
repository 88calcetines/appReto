import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { AlertController } from '@ionic/angular';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss']
})
export class LoginPage {
  loginForm: FormGroup;
  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router, private alertController: AlertController) {
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
        async response => {
          console.log('Login exitoso', response);
          if (response && response.token) {
            console.log('Token JWT:', response.token);
            localStorage.setItem('jwtToken', response.token);
            this.router.navigate(['/tabs/tab1']);
          } else {
            console.warn('Token no encontrado en la respuesta', response);
            const alert = await this.alertController.create({
              header: 'Error',
              subHeader: 'Error al iniciar sesión',
              message: 'No se ha podido iniciar sesión. Por favor, revise tus credenciales e inténtalo de nuevo.',
              buttons: ['OK'],
              cssClass: 'error-alert',
            });
            await alert.present();
          }
        },
        async error => {
          console.error('Error en el login', error);
          const alert = await this.alertController.create({
            header: 'Error',
            subHeader: 'Error al iniciar sesión',
            message: 'Ha ocurrido un error al intentar iniciar sesión. Por favor, inténtelo de nuevo más tarde.',
            buttons: ['OK'],
            cssClass: 'error-alert',
          });
          await alert.present();
        }
      );
    }
  }

  onRegister() {
    console.log('Registro');
    this.router.navigate(['/tabs/register']);
  }
}