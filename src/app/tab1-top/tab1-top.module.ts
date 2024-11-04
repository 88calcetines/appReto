import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { Tab1TopPageRoutingModule } from './tab1-top-routing.module';

import { Tab1TopPage } from './tab1-top.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    Tab1TopPageRoutingModule
  ],
  declarations: [Tab1TopPage]
})
export class Tab1TopPageModule {}
