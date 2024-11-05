import { Component, Input } from '@angular/core';
import { ModalController } from '@ionic/angular';

@Component({
  selector: 'app-image-dialog',
  templateUrl: './image-dialog.component.html',
  styleUrls: ['./image-dialog.component.scss']
})
export class ImageDialogComponent {
  @Input() image: string = '';

  constructor(private modalController: ModalController) {}

  close() {
    this.modalController.dismiss();
  }
}