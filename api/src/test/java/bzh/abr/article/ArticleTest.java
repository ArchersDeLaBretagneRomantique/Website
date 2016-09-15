package bzh.abr.article;

import bzh.abr.Application;
import bzh.abr.article.model.Article;
import bzh.abr.article.repository.ArticleRepository;
import bzh.abr.util.UserEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@ActiveProfiles("test")
public class ArticleTest {

    @Value("${local.server.port}")
    private int serverPort;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void shouldAddAnArticle() {
        Article article = new Article();
        article.setTitle("Title");
        article.setContent("Content");
        ResponseEntity<Article> resp = UserEnum.ADMIN.getRestTemplate(serverPort)
                .postForEntity("http://localhost:" + serverPort + "/api/articles", article, Article.class);

        Assert.assertEquals(HttpStatus.CREATED, resp.getStatusCode());

        Optional<Article> storedArticle = articleRepository.findOne(resp.getBody().getId());
        Assert.assertTrue(storedArticle.isPresent());
        Assert.assertEquals(article.getTitle(), storedArticle.get().getTitle());
        Assert.assertEquals(article.getContent(), storedArticle.get().getContent());
        Assert.assertNotNull(storedArticle.get().getCreationDate());
        Assert.assertTrue(storedArticle.get().isActivated());
    }

    @Test
    public void shouldDesactivateArticle() {
        Article article = new Article();
        article.setTitle("Title");
        article.setContent("Content");
        ResponseEntity<Article> postResp = UserEnum.ADMIN.getRestTemplate(serverPort)
                .postForEntity("http://localhost:" + serverPort + "/api/articles", article, Article.class);

        Assert.assertEquals(HttpStatus.CREATED, postResp.getStatusCode());

        UserEnum.ADMIN.getRestTemplate(serverPort)
                .delete("http://localhost:" + serverPort + "/api/articles/" + postResp.getBody().getId());

        Optional<Article> storedArticle = articleRepository.findOne(postResp.getBody().getId());
        Assert.assertTrue(storedArticle.isPresent());
        Assert.assertEquals(article.getTitle(), storedArticle.get().getTitle());
        Assert.assertEquals(article.getContent(), storedArticle.get().getContent());
        Assert.assertNotNull(storedArticle.get().getCreationDate());
        Assert.assertFalse(storedArticle.get().isActivated());
    }

}
