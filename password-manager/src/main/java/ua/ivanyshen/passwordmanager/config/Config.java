package ua.ivanyshen.passwordmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.ivanyshen.passwordmanager.db.Repository;
import ua.ivanyshen.passwordmanager.db.UserListRepository;
import ua.ivanyshen.passwordmanager.entities.User;

@Configuration
public class Config {

    @Bean
    public Repository<User> userRepository() {
        return new UserListRepository();
    }
}
