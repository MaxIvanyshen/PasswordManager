package ua.ivanyshen.passwordmanager.unitTests.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import ua.ivanyshen.passwordmanager.entities.PasswordEncryptor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordEncryptorTest {

    @Test
    public void testEncrypting() throws Exception {
        String hello = "hello";
        String helloEncrypted = "oJIuq367QfU7eNc7/Tm6KQ==";

        PasswordEncryptor encryptor = new PasswordEncryptor("s5v8y/B?E(H+MbQeShVmYq3t6w9z$C&F");

        assertEquals(helloEncrypted, encryptor.encrypt(hello));
    }

    @Test
    public void testDecrypting() throws Exception {
        String hello = "hello";
        String helloEncrypted = "oJIuq367QfU7eNc7/Tm6KQ==";

        PasswordEncryptor encryptor = new PasswordEncryptor("s5v8y/B?E(H+MbQeShVmYq3t6w9z$C&F");

        assertEquals(hello, encryptor.decrypt(helloEncrypted));

    }
}
