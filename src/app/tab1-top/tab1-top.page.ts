import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiciosService } from '../service/servicios.service';
import { CestaService } from '../service/cesta.service';
import { AlertController, LoadingController } from '@ionic/angular';

@Component({
  selector: 'app-tab1-top',
  templateUrl: './tab1-top.page.html',
  styleUrls: ['./tab1-top.page.scss'],
})
export class Tab1TopPage implements OnInit {
  productos: any[] = [];
  cantidadProductos: number = 0;

  constructor(private router: Router, private serviciosService: ServiciosService, private cestaService:CestaService, private alertController:AlertController, private loadingController:LoadingController) { }

  ngOnInit() {
    this.cargarVideoconsolas();
    this.actualizarCantidadProductos();
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

  async agregarACesta(producto: any) {
    this.cestaService.addItem(producto);
    console.log('Producto añadido a la cesta:', producto);
    this.actualizarCantidadProductos();
    const alert = await this.alertController.create({
      header: '¡Éxito!',
      subHeader: 'Producto añadido a la cesta',
      message: 'El producto se ha añadido correctamente a tu cesta de compras.',
      buttons: ['OK'],
      cssClass: 'success-alert',
    });
    await alert.present();
  }

  actualizarCantidadProductos() {
    this.cantidadProductos = this.cestaService.getItems().length;
  }

  ionViewWillEnter() {
    this.actualizarCantidadProductos();
  }
}