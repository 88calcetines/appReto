import { Component, OnInit } from '@angular/core';
import { ServiciosService } from '../service/servicios.service';
import { CestaService } from '../service/cesta.service';
import { LoadingController } from '@ionic/angular';
import { AlertController } from '@ionic/angular';

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})
export class Tab1Page implements OnInit {
  productosMejoresValorados: any[] = [];
  token: string | null = null;
  cantidadProductos: number = 0;

  constructor(private serviciosService:ServiciosService, private cestaService:CestaService, private loadingController:LoadingController, private alertController:AlertController) {}

  ngOnInit() {
    this.token = localStorage.getItem('jwtToken');
    console.log('Token JWT:', this.token);
    this.cargarProductosMejoresValorados();
    this.actualizarCantidadProductos();
    this.cargarProductosMejoresValorados();
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
      cssClass: 'success-alert',  // Clase CSS personalizada
    });
  
    await alert.present();
  }
  

  actualizarCantidadProductos() {
    this.cantidadProductos = this.cestaService.getItems().length;
  }

  async cargarProductosMejoresValorados() {
    const loading = await this.loadingController.create({
      message: 'Cargando productos...',
      spinner: 'crescent'
    });
    await loading.present();

    this.serviciosService.getProductosMejoresValorados().subscribe(
      productos => {
        this.productosMejoresValorados = productos;
        loading.dismiss();
      },
      error => {
        console.error('Error al cargar productos:', error);
        loading.dismiss();
      }
    );
  }

}