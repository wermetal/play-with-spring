package ua.golubenko.slots.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ErrorHandler {

    @ResponseBody
    @ExceptionHandler( PlayerLoginBadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String badCredentialsHandler(PlayerLoginBadCredentialsException ex) {
        return ex.getMessage();
    }
}
