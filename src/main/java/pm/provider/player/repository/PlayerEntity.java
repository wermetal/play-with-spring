package pm.provider.player.repository;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class PlayerEntity {
    private @Id @GeneratedValue Long id;
    private String login;
    private String password;


    PlayerEntity() {}

    public PlayerEntity(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
