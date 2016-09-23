import { Component, Input } from '@angular/core'

import { Article } from '../shared'

@Component({
    selector: 'article',
    templateUrl: './article.component.html'
})
export class ArticleComponent {
    @Input()
    article: Article
}