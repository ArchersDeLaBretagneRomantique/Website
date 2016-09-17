import { Injectable } from '@angular/core'

import { Article } from './article.model'
import { ARTICLES } from './mock-articles'

@Injectable()
export class ArticleService {
    getArticles(): Promise<Article[]> {
        return Promise.resolve(ARTICLES)
    }
}
