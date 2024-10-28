import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { Tab1TopPage } from './tab1-top.page';

const routes: Routes = [
  {
    path: '',
    component: Tab1TopPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class Tab1TopPageRoutingModule {}
