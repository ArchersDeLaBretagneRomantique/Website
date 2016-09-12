package bzh.abr.album.repository;

import bzh.abr.album.model.Album;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends Repository<Album, Long> {

    List<Album> findAll();

    Optional<Album> findOne(Long id);

    Album save(Album album);

}
