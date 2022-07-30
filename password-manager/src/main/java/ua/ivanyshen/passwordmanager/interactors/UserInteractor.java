package ua.ivanyshen.passwordmanager.interactors;

import org.springframework.stereotype.Component;
import ua.ivanyshen.passwordmanager.db.Repository;
import ua.ivanyshen.passwordmanager.entities.PasswordEncryptor;
import ua.ivanyshen.passwordmanager.entities.User;

@Component
public class UserInteractor {

    private Repository<User> repo;
    private PasswordEncryptor encryptor;

    public UserInteractor(Repository<User> repo, PasswordEncryptor encryptor) {
        this.encryptor = encryptor;
        this.repo = repo;
    }

    public User createUser(User u) {
        u.setMasterPassword(encryptor.encrypt(u.getMasterPassword()));
        repo.insert(u);
        return u;
    }

    public void deleteUser(String id) {
        repo.delete(id);
    }

    public User updateUser(String id, User user) {
        User found = repo.findById(id);
        found.setUsername(user.getUsername());
        found.setEmail(user.getEmail());
        found.setMasterPassword(user.getMasterPassword());
        return repo.insert(found);
    }

    public User find(String id) {
        return repo.findById(id);
    }
}
