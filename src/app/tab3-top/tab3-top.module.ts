import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { Tab3TopPageRoutingModule } from './tab3-top-routing.module';

import { Tab3TopPage } from './tab3-top.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    Tab3TopPageRoutingModule
  ],
  declarations: [Tab3TopPage]
})
export class Tab3TopPageModule {}
