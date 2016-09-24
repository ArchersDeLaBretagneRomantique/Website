import { Component, Input } from '@angular/core'

import { Article } from '../shared'

@Component({
    selector: 'abr-article',
    templateUrl: './article.component.html',
    styleUrls: ['./article.component.css']
})
export class ArticleComponent {
    @Input()
    article: Article
}