package ua.ivanyshen.passwordmanager.integrationTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import ua.ivanyshen.passwordmanager.controllers.UserController;
import ua.ivanyshen.passwordmanager.db.UserListRepository;
import ua.ivanyshen.passwordmanager.db.UserMongoRepository;
import ua.ivanyshen.passwordmanager.entities.PasswordEncryptor;
import ua.ivanyshen.passwordmanager.entities.User;
import ua.ivanyshen.passwordmanager.interactors.UserInteractor;

@SpringJUnitConfig
public class UserControllerTest {
    private WebTestClient client;
    private User u;

    @BeforeEach
    void setUp() {
        client = WebTestClient.bindToController(new UserController(new UserInteractor(new UserMongoRepository("mongodb://localhost:27017"), new PasswordEncryptor("s5v8y/B?E(H+MbQeShVmYq3t6w9z$C&F"))))
                .configureClient()
                .baseUrl("/api/v1/users")
                .build();
    }

    private void createUser() {
        u = new User("maxivanyshen@gmail.com", "Frozr", "nastya2016");
        client.post().uri("/new")
                .body(BodyInserters.fromValue(u))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testCreatingUser() throws Exception {
        User u = new User("maxivanyshen@gmail.com", "Frozr", "nastya2016");
        client.post().uri("/new")
                .body(BodyInserters.fromValue(u))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("email").isEqualTo("maxivanyshen@gmail.com")
                .jsonPath("username").isEqualTo("Frozr")
                .jsonPath("id").isEqualTo(u.getId());

    }

    @Test
    public void testGettingUser() throws Exception {
        createUser();

        client.get().uri("/" + u.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("email").isEqualTo("maxivanyshen@gmail.com")
                .jsonPath("username").isEqualTo("Frozr")
                .jsonPath("id").isEqualTo(u.getId());
    }

    @Test
    public void testDeletingUser() throws Exception {
        createUser();

        client.delete().uri("/" + u.getId())
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    public void testUpdatingUser() throws Exception {
        createUser();
        client.put().uri("/" + u.getId())
                .body(BodyInserters.fromValue(new User("umax2006@outlook.com", "Frozr617", "newPassword")))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("email").isEqualTo("umax2006@outlook.com")
                .jsonPath("username").isEqualTo("Frozr617")
                .jsonPath("id").isEqualTo(u.getId());
    }

    @Test
    public void testGettingUserPasswords() throws Exception {
        createUser();
        client.get().uri("/" + u.getId() + "/passwords")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json("[]");
    }

    @Test
    public void testAddingValidPasswordToUser() throws Exception {
        createUser();
        client.post().uri("/" + u.getId() + "/passwords/" + "3AUpIhfiRT")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testAddingInvalidPasswordToUser() throws Exception {
        createUser();
        client.post().uri("/" + u.getId() + "/passwords/" + "3AUpashfiRT")
                .exchange()
                .expectStatus().isNoContent();
    }
}
