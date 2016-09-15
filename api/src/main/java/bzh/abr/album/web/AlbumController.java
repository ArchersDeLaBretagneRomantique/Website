package bzh.abr.album.web;

import bzh.abr.album.exception.AlbumNotFoundException;
import bzh.abr.album.model.Album;
import bzh.abr.album.service.AlbumService;
import bzh.abr.user.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping(method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public ResponseEntity<List<Album>> getAlbums() {
        List<Album> albums = albumService.getAlbums();

        if (albums.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(albums);
        }

        return ResponseEntity.ok(albums);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public ResponseEntity<Album> getAlbum(@PathVariable Long id) {
        return albumService.getAlbum(id)
                .map(ResponseEntity::ok)
                .orElseThrow(AlbumNotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + Role.ADMIN + "')")
    @Transactional
    public ResponseEntity<Album> addAlbum(@Validated @RequestBody Album album) {
        return ResponseEntity.status(HttpStatus.CREATED).body(albumService.addAlbum(album));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('" + Role.ADMIN + "')")
    @Transactional
    public ResponseEntity<Void> desactivateAlbum(@RequestParam Long id) {
        albumService.desactivateAlbum(id);
        return ResponseEntity.noContent().build();
    }

}
