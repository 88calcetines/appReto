import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { Tab4TopPageRoutingModule } from './tab4-top-routing.module';

import { Tab4TopPage } from './tab4-top.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    Tab4TopPageRoutingModule
  ],
  declarations: [Tab4TopPage]
})
export class Tab4TopPageModule {}
