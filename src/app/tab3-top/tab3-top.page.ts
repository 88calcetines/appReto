import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiciosService } from '../service/servicios.service';
import { CestaService } from '../service/cesta.service';

@Component({
  selector: 'app-tab3-top',
  templateUrl: './tab3-top.page.html',
  styleUrls: ['./tab3-top.page.scss'],
})
export class Tab3TopPage implements OnInit {
  productos: any[] = [];
  cantidadProductos: number = 0;

  constructor(private router: Router, private serviciosService: ServiciosService, private cestaService:CestaService) { }

  ngOnInit() {
    this.cargarTabletsySmartphones();
  }

  cargarTabletsySmartphones() {
    const nombre = 'Telefonia';
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
        console.error('Error al obtener las TABLETS Y SMARTPHONES:', err);
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
