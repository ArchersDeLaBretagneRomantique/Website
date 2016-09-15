package bzh.abr.article.web;

import bzh.abr.article.model.Article;
import bzh.abr.article.service.ArticleService;
import bzh.abr.user.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasRole('" + Role.ADMIN + "')")
    @Transactional
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.addArticle(article));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('" + Role.ADMIN + "')")
    @Transactional
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        return ResponseEntity.ok(articleService.updateArticle(id, article));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('" + Role.ADMIN + "')")
    @Transactional
    public ResponseEntity<Void> desactivateArticle(@PathVariable Long id) {
        articleService.desactivateArticle(id);
        return ResponseEntity.noContent().build();
    }

}
