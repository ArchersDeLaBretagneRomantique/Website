package bzh.abr.article.web;

import bzh.abr.article.exception.ArticleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ArticleControllerAdvice {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such article")
    @ExceptionHandler(ArticleNotFoundException.class)
    public void handleNotFoundException() {}

}
