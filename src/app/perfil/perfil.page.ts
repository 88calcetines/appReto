import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Camera, CameraResultType, CameraSource } from '@capacitor/camera';
import { ServiciosService } from '../service/servicios.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.page.html',
  styleUrls: ['./perfil.page.scss'],
})
export class PerfilPage implements OnInit {
  foto: string | undefined;
  user: any; // Define la propiedad user
  nombre: string | undefined; // Define la propiedad nombre

  constructor(private router: Router, private serviciosService: ServiciosService) { }

  ngOnInit() {
    this.loadUser();
  }

  loadUser() {
    // Aquí debes implementar la lógica para obtener el usuario actual
    // Por ejemplo, podrías obtenerlo desde el localStorage o desde un servicio de autenticación
    const userData = localStorage.getItem('user');
    if (userData) {
      this.user = JSON.parse(userData);
      this.nombre = this.user.nombre; // Asigna el nombre del usuario a la propiedad nombre
      this.cargarFoto(); // Mueve la llamada a cargarFoto aquí
    } else {
      // Manejar el caso en que no haya un usuario logueado
      console.error('No user found');
      this.router.navigate(['/tabs/login']);
    }
  }

  cargarFoto() {
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
  }

  async updateFoto() {
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
  }

  // Función para cerrar sesión
  logout() {
    const token = localStorage.getItem('jwtToken');
    if (token) {
      localStorage.removeItem('jwtToken');
      console.log('Token removed');
      this.router.navigate(['/tabs/login']);
    } else {
      console.log('No token found');
    }
  }
}
