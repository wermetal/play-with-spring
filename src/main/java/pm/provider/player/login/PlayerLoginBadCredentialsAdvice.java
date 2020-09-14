package pm.provider.player.login;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class PlayerLoginBadCredentialsAdvice {
    @ResponseBody
    @ExceptionHandler(PlayerLoginBadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String badCredentialsHandler(PlayerLoginBadCredentialsException ex) {
        return ex.getMessage();
    }
}
