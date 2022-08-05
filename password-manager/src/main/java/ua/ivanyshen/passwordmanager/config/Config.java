package ua.ivanyshen.passwordmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import ua.ivanyshen.passwordmanager.db.*;
import ua.ivanyshen.passwordmanager.entities.Password;
import ua.ivanyshen.passwordmanager.entities.PasswordGenerator;
import ua.ivanyshen.passwordmanager.entities.User;

@Configuration
public class Config {

    @Value("${db.uri}")
    private String uri;
    @Bean
    public Repository<User> userRepository() {
        return new UserMongoRepository(uri.substring(1, uri.length()-1));
    }

    @Bean
    public Repository<Password> passwordRepository() {return new PasswordMongoRepository(uri.substring(1, uri.length()-1));}

    @Bean
    public PasswordGenerator passwordGenerator() {return new PasswordGenerator();}
}
