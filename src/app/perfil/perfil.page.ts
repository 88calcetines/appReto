import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Camera, CameraResultType, CameraSource } from '@capacitor/camera';
import { ServiciosService } from '../service/servicios.service';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.page.html',
  styleUrls: ['./perfil.page.scss'],
})
export class PerfilPage implements OnInit {
  user: any;
  nombre: string | undefined;
  email: string | undefined;
  apellido1: string | undefined;
  apellido2: string | undefined;
  imagen: string | undefined;
  userId: any;
  foto: string | undefined;

  constructor(private router: Router, private serviciosService: ServiciosService, private authService: AuthService) { }

  ngOnInit() {
    this.loadUser();
  }

  loadUser() {
    this.user = this.authService.getCurrentUser();
    console.log('Usuario cargado:', this.user);
    if (this.user) {
      this.nombre = this.user.nombre;
      this.email = this.user.email;
      this.apellido1 = this.user.apellido1;
      this.apellido2 = this.user.apellido2;
      this.imagen = this.user.imagen;
      this.userId = this.user.id;

      console.log('Nombre:', this.nombre);
      console.log('Email:', this.email);
      console.log('Apellido1:', this.apellido1);
      console.log('Apellido2:', this.apellido2);
      console.log('Imagen:', this.imagen);
    } else {
      console.error('No user found');
      this.router.navigate(['/tabs/login']);
    }
  }

  async updateFoto() {
    if (!this.user || !this.user.id) {
      console.error('User not loaded');
      return;
    }

    try {
      const image = await Camera.getPhoto({
        source: CameraSource.Camera,
        resultType: CameraResultType.Uri,
        quality: 90
      });

      if (image) {
        const response = await fetch(image.webPath!);
        const blob = await response.blob();
        const file = new File([blob], 'profile.jpg', { type: blob.type });

        const formData = new FormData();
        formData.append('foto', file);

        this.serviciosService.updateFoto(this.user.id, formData).subscribe({
          next: (response) => {
            console.log('Foto actualizada', response);
            this.imagen = image.webPath; // Actualiza la imagen en la vista
          },
          error: (err) => {
            console.error('Error al actualizar la foto', err);
          }
        });
      }
    } catch (error) {
      console.error('Error al tomar la foto', error);
    }
  }

  logout() {
    const token = localStorage.getItem('token');
    if (token) {
      localStorage.removeItem('token');
      console.log('Token removed');
      this.router.navigate(['/tabs/login']);
    } else {
      console.log('No token found');
    }
  }
}