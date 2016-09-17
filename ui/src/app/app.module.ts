import { NgModule } from '@angular/core'
import { BrowserModule }  from '@angular/platform-browser'

import { routing } from './app.routing'
import { AppComponent } from './app.component'
import { ArticleComponent, ArticleListComponent, ArticlesComponent  } from './articles'

@NgModule({
  imports: [
    BrowserModule,
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
