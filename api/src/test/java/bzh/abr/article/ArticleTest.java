package bzh.abr.article;

import bzh.abr.Application;
import bzh.abr.article.model.Article;
import bzh.abr.article.repository.ArticleRepository;
import bzh.abr.user.repository.UserRepository;
import bzh.abr.util.UserEnum;
import org.junit.Assert;
import org.junit.Before;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@ActiveProfiles("test")
public class ArticleTest {

    @Value("${local.server.port}")
    private int serverPort;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setup() {

    }

    @Test
    public void shouldAddAnArticle() {
        Article article = new Article();
        article.setTitle("Title");
        article.setContent("Content");
        ResponseEntity<Article> resp = UserEnum.ADMIN.getRestTemplate(serverPort)
                .postForEntity("http://localhost:" + serverPort + "/api/articles", article, Article.class);

        Assert.assertEquals(HttpStatus.CREATED, resp.getStatusCode());
    }

}
