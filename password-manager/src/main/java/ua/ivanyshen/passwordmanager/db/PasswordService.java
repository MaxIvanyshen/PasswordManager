package ua.ivanyshen.passwordmanager.db;

import ua.ivanyshen.passwordmanager.entities.Password;
import ua.ivanyshen.passwordmanager.entities.User;

public class PasswordService {

    private Repository<Password> repo;

    public PasswordService(Repository<Password> repo) {
        this.repo = repo;
    }

    public PasswordService() {repo = new PasswordListRepository();}

    public Password insert(Password p) {
        return repo.insert(p);
    }

    public int size() {
        return repo.size();
    }

    public Password findPasswordById(String id) {
        return repo.findById(id);
    }

    public void delete(String id) {
        repo.delete(id);
    }
}
