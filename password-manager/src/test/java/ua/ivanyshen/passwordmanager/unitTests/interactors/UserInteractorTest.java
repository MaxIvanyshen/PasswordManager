package ua.ivanyshen.passwordmanager.unitTests.interactors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.ivanyshen.passwordmanager.db.UserListRepository;
import ua.ivanyshen.passwordmanager.entities.PasswordEncryptor;
import ua.ivanyshen.passwordmanager.entities.User;
import ua.ivanyshen.passwordmanager.interactors.UserInteractor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserInteractorTest {

    private UserListRepository repo;
    private UserInteractor interactor;

    @BeforeEach
    void setUp() {
        repo = new UserListRepository();
        interactor = new UserInteractor(repo, new PasswordEncryptor("s5v8y/B?E(H+MbQeShVmYq3t6w9z$C&F"));
    }

    @Test
    public void testCreatingUser_ShouldEncryptPassword() throws Exception {
        User u = new User("maxivanyshen@gmail.com", "Frozr", "nastya2016");
        assertEquals(u, interactor.createUser(u));
        assertEquals(1, repo.size());
    }

    @Test
    public void testDeletingUser() throws Exception {
        User u = new User("maxivanyshen@gmail.com", "Frozr", "nastya2016");
        assertEquals(u, interactor.createUser(u));
        assertEquals(1, repo.size());
        interactor.deleteUser(u.getId());
        assertEquals(0, repo.size());
    }

    @Test
    public void testUpdatingUser() throws Exception {
        User u = new User("maxivanyshen@gmail.com", "Frozr", "nastya2016");
        interactor.createUser(u);
        User edited = interactor.updateUser(u.getId(), new User("umax2006@outlook.com", "Frozr", "nastya2016"));
        assertEquals(u.getId(), edited.getId());
        assertEquals(u.getUsername(), edited.getUsername());
        assertEquals(1, repo.size());
    }
}
