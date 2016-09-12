package bzh.abr.album.service;

import bzh.abr.album.exception.AlbumNotFoundException;
import bzh.abr.album.model.Album;
import bzh.abr.album.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public List<Album> getAlbums() {
        return albumRepository.findAll();
    }

    public Optional<Album> getAlbum(Long id) {
        return albumRepository.findOne(id);
    }

    public Album addAlbum(Album album) {
        album.setId(null);
        album.setCreationDate(new Date());
        album.setActivated(true);
        return albumRepository.save(album);
    }

    public void desactivateAlbum(Long id) {
        Optional<Album> album = albumRepository.findOne(id);

        if (!album.isPresent()) throw new AlbumNotFoundException();

        album.get().setActivated(false);
        albumRepository.save(album.get());
    }

}
