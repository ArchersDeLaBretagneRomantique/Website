import { Component, OnInit } from '@angular/core'

import { Article, ArticleService } from '../shared'

@Component({
  selector: 'article-list',
  providers: [ArticleService],
  templateUrl: './article-list.component.html'
})
export class ArticleListComponent implements OnInit {
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