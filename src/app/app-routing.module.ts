import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./tabs/tabs.module').then(m => m.TabsPageModule)
  },
  {
    path: 'tab1-top',
    loadChildren: () => import('./tab1-top/tab1-top.module').then( m => m.Tab1TopPageModule)
  },
  {
    path: 'tab2-top',
    loadChildren: () => import('./tab2-top/tab2-top.module').then( m => m.Tab2TopPageModule)
  },
  {
    path: 'tab3-top',
    loadChildren: () => import('./tab3-top/tab3-top.module').then( m => m.Tab3TopPageModule)
  }
];
@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
