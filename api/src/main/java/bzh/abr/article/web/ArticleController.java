package bzh.abr.article.web;

import bzh.abr.article.model.Article;
import bzh.abr.article.service.ArticleService;
import bzh.abr.user.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public ResponseEntity<List<Article>> getArticles() {
        List<Article> articles = articleService.getArticles();

        if (articles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(articles);
        }

        return ResponseEntity.ok(articles);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    @PreAuthorize("hasRole('" + Role.ADMIN + "')")
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.addArticle(article));
    }

}
