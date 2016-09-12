package bzh.abr.article.service;

import bzh.abr.article.exception.ArticleNotFoundException;
import bzh.abr.article.model.Article;
import bzh.abr.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Article addArticle(Article article) {
        article.setId(null);
        article.setActivated(true);
        article.setCreationDate(new Date());
        return articleRepository.save(article);
    }

    public Article updateArticle(Long id, Article article) {
        Optional<Article> a = articleRepository.findOne(id);

        if (!a.isPresent()) throw new ArticleNotFoundException();

        a.get().setTitle(article.getTitle());
        a.get().setContent(article.getContent());

        return articleRepository.save(a.get());
    }

    public void desactivateArticle(Long id) {
        Optional<Article> article = articleRepository.findOne(id);

        if (!article.isPresent()) throw new ArticleNotFoundException();

        article.get().setActivated(false);
        articleRepository.save(article.get());
    }

}
