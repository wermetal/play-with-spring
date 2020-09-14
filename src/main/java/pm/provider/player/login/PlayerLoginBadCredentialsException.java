package pm.provider.player.login;

public class PlayerLoginBadCredentialsException extends RuntimeException {
    public PlayerLoginBadCredentialsException() {
        super("Can't find user with such login and password");
    }
}
