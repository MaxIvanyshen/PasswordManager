package ua.ivanyshen.passwordmanager.interactors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.ivanyshen.passwordmanager.db.Repository;
import ua.ivanyshen.passwordmanager.entities.Password;
import ua.ivanyshen.passwordmanager.entities.PasswordEncryptor;
import ua.ivanyshen.passwordmanager.entities.PasswordGenerator;

@NoArgsConstructor
@AllArgsConstructor
public class PasswordInteractor {

    @Setter
    private Repository<Password> repo;

    @Setter
    private PasswordEncryptor encryptor;
    @Setter
    private PasswordGenerator generator;

    public PasswordInteractor(Repository<Password> repo) {
        this.repo = repo;
    }

    public PasswordInteractor(Repository<Password> repo, PasswordGenerator generator) {
        this.repo = repo;
        this.generator = generator;
    }

    public PasswordInteractor(Repository<Password> repo, PasswordEncryptor encryptor) {
        this.repo = repo;
        this.encryptor = encryptor;
    }

    public String generatePassword(int length, boolean specialChars) {
        this.generator = new PasswordGenerator(length, specialChars);
        return generatePassword();
    }

    public String generatePassword() {
        return generator.generate();
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
}
