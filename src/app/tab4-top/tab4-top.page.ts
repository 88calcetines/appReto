import { Component, OnInit } from '@angular/core';
import { CestaService } from '../service/cesta.service';
import { AlertController, LoadingController } from '@ionic/angular';
import { ServiciosService } from '../service/servicios.service';

@Component({
  selector: 'app-tab4-top',
  templateUrl: 'tab4-top.page.html',
  styleUrls: ['tab4-top.page.scss']
})
export class Tab4TopPage implements OnInit {
  items: any[] = [];
  total: number = 0;
  cantidadProductos: number = 0;
  productosCesta: any[] = [];

  constructor(private cestaService:CestaService, private alertController:AlertController, private loadingController:LoadingController, private serviciosService:ServiciosService) {}

  ngOnInit() {
    this.loadItems();
    this.actualizarCantidadProductos();
  }

  loadItems() {
    this.items = this.cestaService.getItems();
    this.calculateTotal();
  }

  async eliminar(index: number) {
    const alert = await this.alertController.create({
      header: 'Confirmación',
      message: '¿Desea eliminar este producto de la cesta?',
      buttons: [
        {
          text: 'CANCELAR',
          role: 'cancel',
          cssClass: 'secondary',
        },
        {
          text: 'OK',
          handler: () => {
            this.cestaService.removeItem(index);
            this.loadItems();
            this.actualizarCantidadProductos();
          }
        }
      ],
    });
    await alert.present();
  }

  async comprar() {
    let alertOptions;
  
    if (this.items.length === 0) {
      alertOptions = {
        header: 'Error',
        message: 'No hay productos en la cesta.',
        buttons: ['OK'],
      };
    } else {
      alertOptions = {
        header: 'Confirmación de compra',
        message: `¿Desea confirmar la compra de ${this.items.length} productos por un total de ${this.total} €?`,
        buttons: [
          {
            text: 'CANCELAR',
            role: 'cancel',
            cssClass: 'secondary',
          },
          {
            text: 'CONFIRMAR',
            handler: async () => {
              const loading = await this.loadingController.create({
                message: 'Procesando compra...',
                spinner: 'crescent'
              });
              await loading.present();

              for(const item of this.items) {
                await this.serviciosService.actualizarStock(item.id, -1).toPromise();
              }
  
                loading.dismiss();
                this.cestaService.clearCesta();
                this.loadItems();
                this.actualizarCantidadProductos();
  
                const successAlert = await this.alertController.create({
                  header: 'Compra exitosa',
                  message: 'Su compra se ha realizado con éxito.',
                  buttons: ['OK'],
                });
                await successAlert.present();
            }
          }
        ],
      };
    }
    const alert = await this.alertController.create(alertOptions);
    await alert.present();
  }

  calculateTotal() {
    this.total = parseFloat(this.items.reduce((acc, item) => acc + item.precio, 0).toFixed(2));
  }

  actualizarCantidadProductos() {
    this.cantidadProductos = this.cestaService.getItems().length;
  }

  ionViewWillEnter() {
    this.actualizarCantidadProductos();
  }
}