import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { Tab4TopPage } from './tab4-top.page';

const routes: Routes = [
  {
    path: '',
    component: Tab4TopPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class Tab4TopPageRoutingModule {}
