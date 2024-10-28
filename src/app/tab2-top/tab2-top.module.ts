import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { Tab2TopPageRoutingModule } from './tab2-top-routing.module';

import { Tab2TopPage } from './tab2-top.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    Tab2TopPageRoutingModule
  ],
  declarations: [Tab2TopPage]
})
export class Tab2TopPageModule {}
