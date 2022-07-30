package ua.ivanyshen.passwordmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.ivanyshen.passwordmanager.db.Repository;
import ua.ivanyshen.passwordmanager.db.UserListRepository;
import ua.ivanyshen.passwordmanager.entities.User;

@SpringBootApplication
public class PasswordManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasswordManagerApplication.class, args);
	}

}
