package ua.golubenko.slots.web;

public class PlayerLoginBadCredentialsException extends RuntimeException {

    public PlayerLoginBadCredentialsException() {
        super("Can't find user with such login and password");
    }
}
