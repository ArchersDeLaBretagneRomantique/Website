import { NgModule } from '@angular/core'
import { BrowserModule }  from '@angular/platform-browser'
import { HttpModule }    from '@angular/http'

import { InMemoryWebApiModule } from 'angular2-in-memory-web-api'
import { InMemoryDataService }  from './in-memory-data.service'

import { routing } from './app.routing'
import { AppComponent } from './app.component'
import { AlbumComponent, AlbumsComponent, PhotoComponent, PhotosComponent } from './albums'
import { ArticleComponent, ArticlesComponent  } from './articles'

@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        InMemoryWebApiModule.forRoot(InMemoryDataService),
        routing
    ],
    declarations: [
        AppComponent,
        AlbumComponent,
        AlbumsComponent,
        ArticleComponent,
        ArticlesComponent,
        PhotoComponent,
        PhotosComponent
    ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }
