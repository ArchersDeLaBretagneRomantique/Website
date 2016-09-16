package bzh.abr.album.web;

import bzh.abr.album.exception.AlbumNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AlbumControllerAdvice {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such album")
    @ExceptionHandler(AlbumNotFoundException.class)
    public void handleNotFoundException() {}

}
