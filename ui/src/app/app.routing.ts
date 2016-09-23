import { ModuleWithProviders } from '@angular/core'
import { Routes, RouterModule } from '@angular/router'

import { AlbumsComponent, PhotosComponent } from './albums'
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
        path: 'albums/:id',
        component: PhotosComponent
    },
    {
        path: '',
        redirectTo: '/articles',
        pathMatch: 'full'
    },  
]

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes)
