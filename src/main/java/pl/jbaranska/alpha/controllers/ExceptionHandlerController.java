package pl.jbaranska.alpha.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.jbaranska.alpha.exeptions.ExceptionMessage;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public ExceptionMessage handleNotFoundException(
            EntityNotFoundException ex) {
        return new ExceptionMessage(ex.getMessage(), NOT_FOUND.value());
    }
}
