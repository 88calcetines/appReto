import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiciosService } from '../service/servicios.service';
import { CestaService } from '../service/cesta.service';

@Component({
  selector: 'app-tab1-top',
  templateUrl: './tab1-top.page.html',
  styleUrls: ['./tab1-top.page.scss'],
})
export class Tab1TopPage implements OnInit {
  productos: any[] = [];
  cantidadProductos: number = 0;

  constructor(private router: Router, private serviciosService: ServiciosService, private cestaService:CestaService) { }

  ngOnInit() {
    this.cargarVideoconsolas();
  }

  cargarVideoconsolas() {
    const nombre = 'Videojuegos';
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

  agregarACesta(producto: any) {
    this.cestaService.addItem(producto);
    console.log('Producto añadido a la cesta:', producto);
  }

  actualizarCantidadProductos() {
    this.cantidadProductos = this.cestaService.getItems().length;
  }
}