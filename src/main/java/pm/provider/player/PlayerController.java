package pm.provider.player;

import org.springframework.web.bind.annotation.*;
import pm.provider.integration.parimatch.player.PmPlayerInfo;
import pm.provider.player.login.LoginData;

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
    PmPlayerInfo info(@RequestHeader("Provider-Session-Id") String sessionId) {
        return this.playerService.getPlayerInfo(sessionId);
    }
}
