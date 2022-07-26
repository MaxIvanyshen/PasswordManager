package ua.ivanyshen.passwordmanager.unitTests.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.ivanyshen.passwordmanager.db.PasswordService;
import ua.ivanyshen.passwordmanager.entities.Password;
import ua.ivanyshen.passwordmanager.entities.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordDAOTest {

    private PasswordService service;

    @BeforeEach
    void setUp() {
        service = new PasswordService();
    }

    @Test
    public void testInsertingData() throws Exception {
        Password p = new Password();
        service.insert(p);

        assertEquals(1, service.size());
    }

    @Test
    public void testFindingExistingData() throws Exception {
        Password p = new Password("https://www.google.com", "hello world");
        service.insert(p);

        assertEquals(p, service.findPasswordById(p.getId()));
    }

    @Test
    public void testFindingUnExistingData() throws Exception {
        assertEquals(null, service.findPasswordById("pjk6aJr"));
    }

    @Test
    public void testDeletingData() throws Exception {
        Password p = new Password();
        service.insert(p);

        assertEquals(p, service.findPasswordById(p.getId()));

        service.delete(p.getId());

        assertEquals(null, service.findPasswordById(p.getId()));
    }
}
