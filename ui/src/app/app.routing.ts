import { ModuleWithProviders } from '@angular/core'
import { Routes, RouterModule } from '@angular/router'

import { AlbumsComponent } from './albums'
import { ArticlesComponent } from './articles'

const appRoutes: Routes = [
  {
    path: 'articles',
    component: ArticlesComponent
  },
  {
    path: 'albums',
    component: AlbumsComponent
  },
  {
    path: '',
    redirectTo: '/articles',
    pathMatch: 'full'
  },  
]

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes)
