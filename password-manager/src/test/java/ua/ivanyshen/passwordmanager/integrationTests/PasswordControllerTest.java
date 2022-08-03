package ua.ivanyshen.passwordmanager.integrationTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import ua.ivanyshen.passwordmanager.controllers.PasswordController;
import ua.ivanyshen.passwordmanager.db.PasswordListRepository;
import ua.ivanyshen.passwordmanager.entities.Password;
import ua.ivanyshen.passwordmanager.entities.PasswordEncryptor;
import ua.ivanyshen.passwordmanager.entities.PasswordGenerator;
import ua.ivanyshen.passwordmanager.interactors.PasswordInteractor;

@SpringJUnitConfig
public class PasswordControllerTest {

    private WebTestClient client;

    private Password password;

    @BeforeEach
    void setUp() {
        client = WebTestClient.bindToController(new PasswordController(new PasswordInteractor(new PasswordListRepository(), new PasswordEncryptor("s5v8y/B?E(H+MbQeShVmYq3t6w9z$C&F"), new PasswordGenerator())))
                .configureClient()
                .baseUrl("/api/v1/passwords")
                .build();

        password = new Password("https://www.google.com", "nastya2016");
    }



    private void createPassword(Password password) {
        client.post().uri("/new")
                .body(BodyInserters.fromValue(password))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testGeneratingPassword() throws Exception {
        client.get().uri("/new/" + 15)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class);
    }

    @Test
    public void testCreatingPassword() throws Exception {
        client.post().uri("/new")
                .body(BodyInserters.fromValue(password))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("id").isEqualTo(password.getId())
                .jsonPath("url").isEqualTo(password.getUrl())
                .jsonPath("password").isNotEmpty()
                .jsonPath("notes").isEqualTo(password.getNotes());
    }

    @Test
    public void testGettingPasswordById() throws Exception {
        createPassword(password);
        client.get().uri("/" + password.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("id").isEqualTo(password.getId())
                .jsonPath("url").isEqualTo(password.getUrl())
                .jsonPath("password").isNotEmpty()
                .jsonPath("notes").isEqualTo(password.getNotes());
    }

    @Test
    public void testDeletingUser() throws Exception {
        createPassword(password);
        client.delete().uri("/" + password.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty();
    }

    @Test
    public void testUpdatingPassword() throws Exception {
        createPassword(password);
        password.setUrl("https://www.apple.com");
        password.setPassword("akldjalkdjas");
        client.put().uri("/" + password.getId())
                .body(BodyInserters.fromValue(password))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("id").isEqualTo(password.getId())
                .jsonPath("url").isEqualTo(password.getUrl())
                .jsonPath("password").isNotEmpty()
                .jsonPath("notes").isEqualTo(password.getNotes());
    }
}