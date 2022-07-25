package ua.ivanyshen.passwordmanager.unitTests.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.ivanyshen.passwordmanager.entities.User;

import java.util.InvalidPropertiesFormatException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {

    private User u;

    @BeforeEach
    private void setUp() {
        u = new User();
    }

    @Test
    public void testValidatingValidEmail() throws Exception {
        u.setEmail("maxivanyshen@gmail.com");
        assertEquals("maxivanyshen@gmail.com", u.getEmail());
    }

    @Test
    public void testValidatingInvalidEmail() throws Exception {
        assertThrows(InvalidPropertiesFormatException.class, () -> u.setEmail("maxivanyshen"));
    }
}
