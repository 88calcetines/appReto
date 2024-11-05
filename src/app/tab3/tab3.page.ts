import { Component } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { ImageDialogComponent } from '../image-dialog/image-dialog.component'; // Asegúrate de importar tu componente de diálogo

@Component({
  selector: 'app-tab3',
  templateUrl: 'tab3.page.html',
  styleUrls: ['tab3.page.scss']
})
export class Tab3Page {
  images: string[];

  constructor(private modalController: ModalController) {
    this.images = [
      'assets/images/almi1.jpg',
      'assets/images/almi2.jpg',
      'assets/images/almi3.jpg',
      'assets/images/almi4.jpg',
      'assets/images/almi5.jpg',
      'assets/images/almi6.jpg',
      'assets/images/almi7.jpg',
      'assets/images/almi8.jpg',
      'assets/images/almi9.jpg',
      'assets/images/almi10.jpg'
    ];
  }

  async openImageDialog(image: string) {
    const modal = await this.modalController.create({
      component: ImageDialogComponent,
      componentProps: { image }
    });
    return await modal.present();
  }
}
