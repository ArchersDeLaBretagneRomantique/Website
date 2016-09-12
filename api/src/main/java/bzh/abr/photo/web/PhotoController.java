package bzh.abr.photo.web;

import bzh.abr.photo.exception.PhotoUploadException;
import bzh.abr.photo.service.PhotoService;
import bzh.abr.user.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/api/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + Role.ADMIN + "')")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file) throws PhotoUploadException {
        if (!file.isEmpty()) {
            return ResponseEntity.ok(photoService.uploadPhoto(file).toString());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
