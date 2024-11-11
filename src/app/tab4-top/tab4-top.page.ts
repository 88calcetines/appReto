import { Component, OnInit } from '@angular/core';
import { CestaService } from '../service/cesta.service';
import { LoadingController } from '@ionic/angular';

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

  constructor(private cestaService:CestaService, private loadingController:LoadingController) {}

  ngOnInit() {
    this.loadItems();
  }

  loadItems() {
    this.items = this.cestaService.getItems();
    this.calculateTotal();
  }

  eliminar(index: number) {
    this.cestaService.removeItem(index);
    this.loadItems();
    console.log('Producto eliminado de la cesta:', index);
  }

  calculateTotal() {
    this.total = parseFloat(this.items.reduce((acc, item) => acc + item.precio, 0).toFixed(2));
  }

  actualizarCantidadProductos() {
    this.cantidadProductos = this.cestaService.getItems().length;
  }
}