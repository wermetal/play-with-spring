package pm.provider.player;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pm.provider.integration.parimatch.PmIntegrationService;
import pm.provider.integration.parimatch.player.PmPlayerInfo;
import pm.provider.player.login.PlayerLoginBadCredentialsException;
import pm.provider.player.repository.PlayerEntity;
import pm.provider.player.repository.PlayerRepository;

import java.util.HashMap;
import java.util.UUID;

@Slf4j
@Service
public class PlayerService {
    private PlayerRepository repository;
    private PmIntegrationService integrationService;
    private HashMap<String, PlayerEntity> sessionsMap = new HashMap<>();
    PlayerService(
            PlayerRepository repository,
            PmIntegrationService integrationService
    ) {
        this.repository = repository;
        this.integrationService = integrationService;
    }

    public String login(String login, String password) {
        PlayerEntity playerExample = new PlayerEntity(login, password);
        PlayerEntity playerEntity = repository
                .findOne(Example.of(playerExample))
                .orElseThrow(() -> new PlayerLoginBadCredentialsException());


        String sessionId = this.generateSessionId();
        this.sessionsMap.put(sessionId, playerEntity);
        return sessionId;
    }

    public boolean isLoggedIn(String sessionId) {
        return sessionsMap.get(sessionId) != null;
    }

    public PmPlayerInfo getPlayerInfo(String sessionId) {

        boolean isLogged = isLoggedIn(sessionId);
        if (!isLogged) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return this.integrationService.getPlayerInfo(sessionId);
    }


    private String generateSessionId() {
        return UUID.randomUUID().toString();
    }



}
