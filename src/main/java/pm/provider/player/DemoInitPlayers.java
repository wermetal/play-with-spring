package pm.provider.player;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pm.provider.player.repository.PlayerEntity;
import pm.provider.player.repository.PlayerRepository;

@Configuration
@Slf4j
class DemoInitPlayers {
    @Bean
    CommandLineRunner initDatabase(PlayerRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new PlayerEntity("player1", "111")));
            log.info("Preloading " + repository.save(new PlayerEntity("player2", "222")));
        };
    }
}