package ua.golubenko.slots.web;

import org.springframework.web.bind.annotation.*;
import ua.golubenko.slots.domain.client.model.player.PmPlayerInfo;
import ua.golubenko.slots.domain.PlayerService;
import ua.golubenko.slots.web.model.LoginData;

@RestController
public class PlayerController {
    private PlayerService playerService;

    PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("player/login")
    String login(@RequestBody LoginData loginData) {
        String sessionId = this.playerService.login(loginData.getLogin(), loginData.getPassword());

        return sessionId;
    }

    @GetMapping("player/info")
    PmPlayerInfo info( @RequestHeader("Provider-Session-Id") String sessionId) {
        return this.playerService.getPlayerInfo(sessionId);
    }
}
