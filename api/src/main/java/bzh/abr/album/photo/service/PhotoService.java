package bzh.abr.album.photo.service;

import bzh.abr.album.exception.AlbumNotFoundException;
import bzh.abr.album.model.Album;
import bzh.abr.album.photo.exception.PhotoUploadException;
import bzh.abr.album.photo.model.Photo;
import bzh.abr.album.photo.model.PhotoBuilder;
import bzh.abr.album.photo.repository.PhotoRepository;
import bzh.abr.album.repository.AlbumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Optional;
import java.util.UUID;

@Service
public class PhotoService {

    private static final Logger logger = LoggerFactory.getLogger(PhotoService.class);

    @Value("${photo.path}")
    private String path;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PhotoRepository photoRepository;

    public String uploadPhoto(Long albumId, MultipartFile file) {
        UUID uuid = UUID.randomUUID();
        String filePath = path + uuid;

        Optional<Album> album = albumRepository.findOne(albumId);

        if (!album.isPresent()) throw new AlbumNotFoundException();

        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(filePath)))) {
            FileCopyUtils.copy(file.getInputStream(), outputStream);

            Photo photo = PhotoBuilder.newBuilder()
                    .setFile(uuid.toString())
                    .setAlbum(album.get())
                    .build();

            photoRepository.save(photo);

            return uuid.toString();
        } catch (IOException e) {
            logger.error("Error uploading album", e);
            throw new PhotoUploadException(e);
        }
    }

}
