package bzh.abr.photo.service;

import bzh.abr.photo.exception.PhotoUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Service
public class PhotoService {

    private static final Logger logger = LoggerFactory.getLogger(PhotoService.class);

    @Value("${photo.path}")
    private String path;

    public String uploadPhoto(MultipartFile file) throws PhotoUploadException {
        UUID uuid = UUID.randomUUID();
        String filePath = path + uuid;
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(filePath)))) {
            FileCopyUtils.copy(file.getInputStream(), outputStream);
            return uuid.toString();
        } catch (IOException e) {
            logger.error("Error uploading photo", e);
            throw new PhotoUploadException(e);
        }
    }

}
