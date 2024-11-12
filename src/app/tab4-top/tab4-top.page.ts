import { Component, OnInit } from '@angular/core';
import { CestaService } from '../service/cesta.service';
import { AlertController } from '@ionic/angular';

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

  constructor(private cestaService:CestaService, private alertController:AlertController) {}

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