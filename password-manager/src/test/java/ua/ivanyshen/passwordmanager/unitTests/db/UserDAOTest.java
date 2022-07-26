package ua.ivanyshen.passwordmanager.unitTests.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.ivanyshen.passwordmanager.db.UserService;
import ua.ivanyshen.passwordmanager.entities.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDAOTest {

    private UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService();
    }

    @Test
    public void testInsertingData() throws Exception {
        User u = new User();
        service.insert(u);

        assertEquals(1, service.size());
    }

    @Test
    public void testFindingExistingData() throws Exception {
        User u = new User("maxivanyshen@gmail.com", "Frozr", null);
        service.insert(u);

        assertEquals(u, service.findUserById(u.getId()));
    }

    @Test
    public void testFindingUnExistingData() throws Exception {
        assertEquals(null, service.findUserById("pjk6aJr"));
    }

    @Test
    public void testDeletingData() throws Exception {
        User u = new User("maxivanyshen@gmail.com", "Frozr", null);
        service.insert(u);

        assertEquals(u, service.findUserById(u.getId()));

        service.delete(u.getId());

        assertEquals(null, service.findUserById(u.getId()));
    }
}
