package bzh.abr.album.photo.web;

import bzh.abr.album.photo.service.PhotoService;
import bzh.abr.user.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/api/albums/{albumId}/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + Role.ADMIN + "')")
    @Transactional
    public ResponseEntity<String> uploadPhoto(@PathVariable Long albumId, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            return ResponseEntity.ok(photoService.uploadPhoto(albumId, file));
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
