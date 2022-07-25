package ua.ivanyshen.passwordmanager.unitTests.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.ivanyshen.passwordmanager.entities.Password;

import java.util.InvalidPropertiesFormatException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PasswordTest {

    private Password p;

    @BeforeEach
    private void setUp() {
        p = new Password();
    }

    @Test
    public void testValidatingValidUrl() throws Exception {
        String url = "https://www.google.com";
        p.setUrl(url);
        assertEquals(url, p.getUrl());
    }

    @Test
    public void testValidatingInvalidUrl() throws Exception {
        String url = "https:/ww.goog";
        assertThrows(InvalidPropertiesFormatException.class, () -> p.setUrl(url));
    }
}
