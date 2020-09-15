package ua.golubenko.slots.web.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginData {
    @NotNull
    String login;

    @NotNull
    String password;


    public LoginData() {}
    public LoginData(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
