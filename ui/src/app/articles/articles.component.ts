import { Component, OnInit } from '@angular/core'

import { Article, ArticleService } from './shared'

@Component({
  selector: 'articles',
  providers: [ArticleService],
  templateUrl: './articles.component.html'
})
export class ArticlesComponent implements OnInit {
  articles: Article[] = []

  constructor(
    private articleService: ArticleService
  ) {}

  getArticles(): void {
    this.articleService.getArticles()
      .then(articles => this.articles = articles)
  }

  ngOnInit(): void {
    this.getArticles()
  }
}