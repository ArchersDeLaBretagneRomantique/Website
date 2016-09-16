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
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ArticleTest {

    @Value("${local.server.port}")
    private int serverPort;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @DirtiesContext
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
        Assert.assertTrue(storedArticle.get().isEnabled());
    }

    @Test
    @DirtiesContext
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
        Assert.assertFalse(storedArticle.get().isEnabled());
    }

    @Test
    @DirtiesContext
    public void shouldGetArticles() {
        Article article = new Article();
        article.setTitle("Title");
        article.setContent("Content");
        ResponseEntity<Article> postResp = UserEnum.ADMIN.getRestTemplate(serverPort)
                .postForEntity("http://localhost:" + serverPort + "/api/articles", article, Article.class);

        Assert.assertEquals(HttpStatus.CREATED, postResp.getStatusCode());

        RestTemplate template = new RestTemplate();
        ResponseEntity<Article[]> getResp = template.getForEntity("http://localhost:" + serverPort + "/api/articles", Article[].class);

        Assert.assertEquals(HttpStatus.OK, getResp.getStatusCode());

        Assert.assertEquals(1, getResp.getBody().length);
        Assert.assertEquals(article.getTitle(), getResp.getBody()[0].getTitle());
        Assert.assertEquals(article.getContent(), getResp.getBody()[0].getContent());
        Assert.assertNotNull(getResp.getBody()[0].getCreationDate());

        UserEnum.ADMIN.getRestTemplate(serverPort)
                .delete("http://localhost:" + serverPort + "/api/articles/" + postResp.getBody().getId());

        getResp = template.getForEntity("http://localhost:" + serverPort + "/api/articles", Article[].class);

        Assert.assertEquals(HttpStatus.NO_CONTENT, getResp.getStatusCode());
    }

}
