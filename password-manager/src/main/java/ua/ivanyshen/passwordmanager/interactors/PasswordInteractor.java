package ua.ivanyshen.passwordmanager.interactors;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ua.ivanyshen.passwordmanager.db.PasswordListRepository;
import ua.ivanyshen.passwordmanager.db.Repository;
import ua.ivanyshen.passwordmanager.entities.Password;
import ua.ivanyshen.passwordmanager.entities.PasswordEncryptor;
import ua.ivanyshen.passwordmanager.entities.PasswordGenerator;
import ua.ivanyshen.passwordmanager.entities.User;

@Component
@AllArgsConstructor
public class PasswordInteractor {
    private Repository<Password> repo;

    private PasswordEncryptor encryptor;

    private PasswordGenerator generator;

    public String generatePassword() {
        return generator.generate();
    }

    public String generatePassword(int length, boolean specialChars) {
        generator.setLength(length);
        generator.setSpecialChars(specialChars);
        return generatePassword();
    }

    public Password createPassword(Password pw) {
        repo.insert(encryptPassword(pw));
        return pw;
    }

    public Password updatePassword(String id, Password pw) {
        pw = encryptPassword(pw);
        Password found = repo.findById(id);
        found.setUrl(pw.getUrl());
        found.setPassword(pw.getPassword());
        found.setNotes(pw.getNotes());
        repo.insert(pw);
        return pw;
    }

    private Password encryptPassword(Password pw) {
        pw.setPassword(encryptor.encrypt(pw.getPassword()));
        return pw;
    }

    public Password find(String id) {
        return repo.findById(id);
    }

    public void deletePassword(String id) {
        repo.delete(id);
    }
}
