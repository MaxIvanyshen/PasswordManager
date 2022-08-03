package ua.ivanyshen.passwordmanager.unitTests.interactors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.ivanyshen.passwordmanager.db.PasswordListRepository;
import ua.ivanyshen.passwordmanager.entities.Password;
import ua.ivanyshen.passwordmanager.entities.PasswordEncryptor;
import ua.ivanyshen.passwordmanager.entities.PasswordGenerator;
import ua.ivanyshen.passwordmanager.interactors.PasswordInteractor;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordInteractorTest {

    private PasswordInteractor interactor;
    private PasswordListRepository repo;

    @BeforeEach
    void setUp() {
        repo = new PasswordListRepository();
        interactor = new PasswordInteractor(repo, new PasswordEncryptor("s5v8y/B?E(H+MbQeShVmYq3t6w9z$C&F"), new PasswordGenerator());
    }

    @Test
    public void testGeneratingPasswords() throws Exception {
        String password = interactor.generatePassword();
        assertEquals(15, password.length());
        System.out.println(password);
        assertTrue(password.matches("([a-zA-Z0-9($+#+!+\\.+,+\\!+\\?+\\>+\\<+\\*+\\+" +
                ")]+)+[a-zA-Z0-9($+#+!+\\.+,+\\!+\\?+\\>+\\<+\\*+\\+)]+"));
    }

    @Test
    public void testCreatingPassword_ShouldEncryptPassword() throws Exception {
        Password pw = new Password();
        String rawPassword = "password";
        pw.setPassword(rawPassword);
        Password saved = interactor.createPassword(pw);
        assertNotEquals(rawPassword, saved.getPassword());
        assertEquals(pw, saved);
        assertEquals(1, repo.size());
    }
    
    @Test
    public void testUpdatingPassword() throws Exception {
        Password pw = new Password();
        String url = "https://www.google.com";
        pw.setPassword("password");
        pw.setUrl(url);
        Password saved = interactor.createPassword(pw);
        pw.setUrl("https://apple.com");
        Password updated = interactor.updatePassword(pw.getId(), pw);
        assertNotEquals(url, updated.getUrl());
        assertEquals(1, repo.size());
    }

}
