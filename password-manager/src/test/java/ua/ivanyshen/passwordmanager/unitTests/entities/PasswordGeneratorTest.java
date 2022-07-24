package ua.ivanyshen.passwordmanager.unitTests.entities;

import org.junit.jupiter.api.Test;
import ua.ivanyshen.passwordmanager.entities.PasswordGenerator;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordGeneratorTest {

    @Test
    public void testGeneratingPassword() throws Exception {
        String password = new PasswordGenerator().generate();
        System.out.println(password);
        assertEquals(15, password.length());
        assertTrue(password.matches("([a-zA-Z0-9($+#+!+\\.+,+\\!+\\?+\\>+\\<+\\*+\\+" +
                                            ")]+)+[a-zA-Z0-9($+#+!+\\.+,+\\!+\\?+\\>+\\<+\\*+\\+)]+"));
    }
    
    @Test
    public void testSettingOptionsManually() throws Exception {
        PasswordGenerator generator = new PasswordGenerator();
        generator.setLength(20);
        String password = generator.generate();
        assertEquals(20, password.length());

        generator.setSpecialChars(false);
        password = generator.generate();

        assertFalse(password.contains("#") || password.contains("!") || password.contains("$")
                || password.contains(".") || password.contains(",") || password.contains("?")
                || password.contains("<") || password.contains(">") || password.contains("*")
                || password.contains("+"));

        generator.setSpecialChars(true);
        password = generator.generate();

        assertTrue(password.contains("#") || password.contains("!") || password.contains("$")
                || password.contains(".") || password.contains(",") || password.contains("?")
                || password.contains("<") || password.contains(">") || password.contains("*")
                || password.contains("+"));
    }

    @Test
    public void testSettingOptionsInConstructor() throws Exception {
        PasswordGenerator generator = new PasswordGenerator(22, true);
        String password = generator.generate();
        assertEquals(22, password.length());

        assertTrue(password.contains("#") || password.contains("!") || password.contains("$")
                || password.contains(".") || password.contains(",") || password.contains("?")
                || password.contains("<") || password.contains(">") || password.contains("*")
                || password.contains("+"));
    }
}
