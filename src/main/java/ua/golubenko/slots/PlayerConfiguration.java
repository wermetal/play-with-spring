package ua.golubenko.slots;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.golubenko.slots.domain.dao.PlayerEntity;
import ua.golubenko.slots.domain.dao.PlayerRepository;

@Configuration
@Slf4j
class PlayerConfiguration {

    @Bean
    CommandLineRunner initDatabase(PlayerRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new PlayerEntity("player1", "111")));
            log.info("Preloading " + repository.save(new PlayerEntity("player2", "222")));
        };
    }
}