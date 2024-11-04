import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { Tab2TopPage } from './tab2-top.page';

const routes: Routes = [
  {
    path: '',
    component: Tab2TopPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class Tab2TopPageRoutingModule {}
