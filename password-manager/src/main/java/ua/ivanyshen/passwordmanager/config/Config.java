package ua.ivanyshen.passwordmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import ua.ivanyshen.passwordmanager.db.PasswordListRepository;
import ua.ivanyshen.passwordmanager.db.Repository;
import ua.ivanyshen.passwordmanager.db.UserListRepository;
import ua.ivanyshen.passwordmanager.entities.Password;
import ua.ivanyshen.passwordmanager.entities.PasswordGenerator;
import ua.ivanyshen.passwordmanager.entities.User;

@Configuration
public class Config {

    @Bean
    public Repository<User> userRepository() {
        return new UserListRepository();
    }

    @Bean
    public Repository<Password> passwordRepository() {return new PasswordListRepository();}

    @Bean
    public PasswordGenerator passwordGenerator() {return new PasswordGenerator();}
}
