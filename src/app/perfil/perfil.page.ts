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
  imagen: string | null = null;
  user: any;
  nombre: string | undefined;
  email: string | undefined;
  apellido1: string | undefined;
  apellido2: string | undefined;

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
    console.log('Nombre:', this.nombre);
    console.log('Email:', this.email);
    console.log('Apellido1:', this.apellido1);
    console.log('Apellido2:', this.apellido2);
  } else {
    console.error('No user found');
    this.router.navigate(['/tabs/login']);
  }
}


async tomarFoto() {
  const photo = await Camera.getPhoto({
    resultType: CameraResultType.DataUrl,
    source: CameraSource.Camera,
    quality: 90,
  });

  if (photo?.dataUrl) {
    this.imagen = photo.dataUrl;

    const formData = new FormData();
    formData.append('foto', this.dataUrlToBlob(photo.dataUrl), 'perfil.jpg');

    const userId = 1; // Cambia esto por el ID real del usuario
    this.serviciosService.updateFoto(userId, formData).subscribe(
      (response) => {
        console.log('Foto actualizada:', response);
      },
      (error) => {
        console.error('Error al actualizar la foto:', error);
      }
    );
  }
}

private dataUrlToBlob(dataUrl: string): Blob {
  const byteString = atob(dataUrl.split(',')[1]);
  const mimeString = dataUrl.split(',')[0].split(':')[1].split(';')[0];
  const ab = new ArrayBuffer(byteString.length);
  const ia = new Uint8Array(ab);
  for (let i = 0; i < byteString.length; i++) {
    ia[i] = byteString.charCodeAt(i);
  }
  return new Blob([ab], { type: mimeString });
}

  

  /*cargarimagen() {
    if (this.user && this.user.id) {
      this.serviciosService.getFotoUsuario(this.user.id).subscribe(
        res => {
          this.foto = res;
        },
        err => {
          console.error('Error al cargar la foto del usuario:', err);
        }
      );
    }
  }*/

  /*async updateFoto() {
    if (!this.user || !this.user.id) {
      console.error('User not loaded');
      return;
    }

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
          this.user.foto = response.foto;
          this.foto = response.foto; // Actualiza la foto en la vista
        },
        error: (err) => {
          console.error('Error al actualizar la foto', err);
        }
      });
    }
  }*/

  // Función para cerrar sesión
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