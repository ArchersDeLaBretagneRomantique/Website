import { NgModule } from '@angular/core'
import { BrowserModule }  from '@angular/platform-browser'
import { HttpModule }    from '@angular/http'

import { InMemoryWebApiModule } from 'angular2-in-memory-web-api'
import { InMemoryDataService }  from './in-memory-data.service'

import { routing } from './app.routing'
import { AppComponent } from './app.component'
import { ArticleComponent, ArticleListComponent, ArticlesComponent  } from './articles'

@NgModule({
  imports: [
    BrowserModule,
    HttpModule,
    InMemoryWebApiModule.forRoot(InMemoryDataService),
    routing
  ],
  declarations: [
    AppComponent,
    ArticleComponent,
    ArticleListComponent,
    ArticlesComponent
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
