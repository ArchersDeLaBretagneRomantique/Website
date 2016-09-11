package bzh.abr.article.web;

import bzh.abr.article.model.Article;
import bzh.abr.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
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
    @PreAuthorize("permitAll")
    public ResponseEntity<List<Article>> getArticles() {
        return ResponseEntity.ok(articleService.getArticles());
    }

}
