import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { ServiciosService } from '../service/servicios.service';

@Component({
  selector: 'app-tab2',
  templateUrl: './tab2.page.html',
  styleUrls: ['./tab2.page.scss'],
})
export class Tab2Page implements OnInit {
  center: google.maps.LatLngLiteral ={ lat: 43.246246885490976, lng: -2.892240076465803};  //{ lat: 43.27192290021221, lng: -2.948695787239455 };  // Coordenadas iniciales
  zoom = 15;  // Nivel de zoom
  markerOptions: google.maps.MarkerOptions = { position: this.center, title: 'Almi Bilbao' };

  constructor(private authService: AuthService, private serviciosService: ServiciosService) { }

  ngOnInit() {
    // Inicialmente obtenemos la ubicación del usuario
    this.obtenerUbicacionActual();
  }

  // Método para obtener la ubicación actual del usuario
  obtenerUbicacionActual(): void {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          // Actualizamos el centro del mapa y el marcador con la ubicación actual
          this.center = { lat: position.coords.latitude, lng: position.coords.longitude };
          this.markerOptions = {
            position: this.center,
            title: 'Tu ubicación'
          };
          this.zoom = 15;  // Ajustamos el nivel de zoom
        },
        (error) => {
          console.error("Error al obtener la ubicación: ", error);
          // Aquí puedes mostrar un mensaje si no se puede obtener la ubicación
        }
      );
    } else {
      console.error("La geolocalización no es soportada por este navegador.");
    }
  }

  // Método que puede ser llamado para actualizar la ubicación manualmente
  actualizarUbicacion(): void {
    this.obtenerUbicacionActual();
  }
}
