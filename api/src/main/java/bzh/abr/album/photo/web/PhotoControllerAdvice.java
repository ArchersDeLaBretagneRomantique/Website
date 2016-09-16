package bzh.abr.album.photo.web;

import bzh.abr.album.photo.exception.PhotoUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PhotoControllerAdvice {

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "An error occurred when uploading the file")
    @ExceptionHandler(PhotoUploadException.class)
    public void handleException() {}

}
