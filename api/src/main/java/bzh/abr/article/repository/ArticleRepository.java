package bzh.abr.article.repository;

import bzh.abr.article.model.Article;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends Repository<Article, Long> {

    Optional<Article> findOne(Long id);

    List<Article> findByEnabled(boolean enabled);

    Article save(Article article);

}
