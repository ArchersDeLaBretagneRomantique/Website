package bzh.abr.album;

import bzh.abr.Application;
import bzh.abr.album.model.Album;
import bzh.abr.util.UserEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class AlbumTest {

    @Value("${local.server.port}")
    private int serverPort;

    @Test
    public void shouldAddAlbum() {
        Album album = new Album();
        album.setTitle("Title");

        ResponseEntity<Album> resp = UserEnum.ADMIN.getRestTemplate(serverPort)
                .postForEntity("http://localhost:" + serverPort + "/api/albums", album, Album.class);

        Assert.assertEquals(HttpStatus.CREATED, resp.getStatusCode());
    }
}
