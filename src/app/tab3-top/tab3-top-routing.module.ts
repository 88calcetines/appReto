import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { Tab3TopPage } from './tab3-top.page';

const routes: Routes = [
  {
    path: '',
    component: Tab3TopPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class Tab3TopPageRoutingModule {}
