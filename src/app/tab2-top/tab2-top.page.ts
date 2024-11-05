import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiciosService } from '../service/servicios.service';

@Component({
  selector: 'app-tab2-top',
  templateUrl: './tab2-top.page.html',
  styleUrls: ['./tab2-top.page.scss'],
})
export class Tab2TopPage implements OnInit {
  productos: any[] = [];

  constructor(private router: Router, private serviciosService: ServiciosService) { }

  ngOnInit() {
    this.cargarConsolas();
  }

  cargarConsolas() {
    const nombre = 'Consolas';
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
        console.error('Error al obtener las Videoconsolas:', err);
      }
    );
  }

}
