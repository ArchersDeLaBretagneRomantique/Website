package bzh.abr.album;

import bzh.abr.Application;
import bzh.abr.album.model.Album;
import bzh.abr.album.repository.AlbumRepository;
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
public class AlbumTest {

    @Value("${local.server.port}")
    private int serverPort;

    @Autowired
    private AlbumRepository albumRepository;

    @Test
    @DirtiesContext
    public void shouldAddAnAlbum() {
        Album album = new Album();
        album.setTitle("Title");

        ResponseEntity<Album> resp = UserEnum.ADMIN.getRestTemplate(serverPort)
                .postForEntity("http://localhost:" + serverPort + "/api/albums", album, Album.class);

        Assert.assertEquals(HttpStatus.CREATED, resp.getStatusCode());

        Optional<Album> storedAlbum = albumRepository.findOne(resp.getBody().getId());
        Assert.assertTrue(storedAlbum.isPresent());
        Assert.assertEquals(album.getTitle(), storedAlbum.get().getTitle());
        Assert.assertNotNull(storedAlbum.get().getCreationDate());
        Assert.assertTrue(storedAlbum.get().isEnabled());
    }

    @Test
    @DirtiesContext
    public void shouldDesactivateAlbum() {
        Album album = new Album();
        album.setTitle("Title");

        ResponseEntity<Album> postResp = UserEnum.ADMIN.getRestTemplate(serverPort)
                .postForEntity("http://localhost:" + serverPort + "/api/albums", album, Album.class);

        Assert.assertEquals(HttpStatus.CREATED, postResp.getStatusCode());

        UserEnum.ADMIN.getRestTemplate(serverPort)
                .delete("http://localhost:" + serverPort + "/api/albums/" + postResp.getBody().getId());

        Optional<Album> storedAlbum = albumRepository.findOne(postResp.getBody().getId());
        Assert.assertTrue(storedAlbum.isPresent());
        Assert.assertEquals(album.getTitle(), storedAlbum.get().getTitle());
        Assert.assertNotNull(storedAlbum.get().getCreationDate());
        Assert.assertFalse(storedAlbum.get().isEnabled());
    }

    @Test
    @DirtiesContext
    public void shouldGetAlbums() {
        Album album = new Album();
        album.setTitle("Title");

        ResponseEntity<Album> postResp = UserEnum.ADMIN.getRestTemplate(serverPort)
                .postForEntity("http://localhost:" + serverPort + "/api/albums", album, Album.class);

        Assert.assertEquals(HttpStatus.CREATED, postResp.getStatusCode());

        RestTemplate template = new RestTemplate();
        ResponseEntity<Album[]> getResp = template.getForEntity("http://localhost:" + serverPort + "/api/albums", Album[].class);

        Assert.assertEquals(HttpStatus.OK, getResp.getStatusCode());

        Assert.assertEquals(1, getResp.getBody().length);
        Assert.assertEquals(album.getTitle(), getResp.getBody()[0].getTitle());
        Assert.assertNotNull(getResp.getBody()[0].getCreationDate());

        UserEnum.ADMIN.getRestTemplate(serverPort)
                .delete("http://localhost:" + serverPort + "/api/albums/" + postResp.getBody().getId());

        getResp = template.getForEntity("http://localhost:" + serverPort + "/api/albums", Album[].class);

        Assert.assertEquals(HttpStatus.NO_CONTENT, getResp.getStatusCode());
    }
}
