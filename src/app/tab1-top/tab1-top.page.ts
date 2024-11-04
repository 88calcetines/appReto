import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiciosService } from '../service/servicios.service';

@Component({
  selector: 'app-tab1-top',
  templateUrl: './tab1-top.page.html',
  styleUrls: ['./tab1-top.page.scss'],
})
export class Tab1TopPage implements OnInit {
  productos: any[] = [];

  constructor(private router: Router, private serviciosService: ServiciosService) { }

  ngOnInit() {
    this.cargarVideoconsolas();
  }

  cargarVideoconsolas() {
    const nombre = 'Videoconsolas';
    this.serviciosService.getProductosPorNombre(nombre).subscribe(
      res => {
        if (Array.isArray(res)) {
          this.productos = res;
        } else {
          console.error('La respuesta no es un array:', res);
        }
        console.log(res);
      },
      err => {
        console.error('Error al obtener las videoconsolas:', err);
      }
    );
  }
}