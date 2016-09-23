import { Injectable } from '@angular/core'
import { Http } from '@angular/http'

import { Remarkable } from 'remarkable'

import 'rxjs/add/operator/toPromise';

import { Article } from './article.model'

@Injectable()
export class ArticleService {
    md = new Remarkable()

    constructor(
        private http: Http
    ) {}

    private handleError(error: any): Promise<any> {
        return Promise.reject(error.message || error);
    }

    getArticles(): Promise<Article[]> {
        return this.http.get('/api/articles')
            .toPromise()
            .then(response => response.json().data as Article[])
            .then(articles => articles.map(article => new Article(article.id, article.title, this.md.render(article.content))))
            .catch(this.handleError)
    }
}
