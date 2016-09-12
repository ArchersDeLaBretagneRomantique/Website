package bzh.abr.album.photo.repository;

import bzh.abr.album.photo.model.Photo;
import org.springframework.data.repository.Repository;

public interface PhotoRepository extends Repository<Photo, Long> {

    Photo save(Photo photo);

}
