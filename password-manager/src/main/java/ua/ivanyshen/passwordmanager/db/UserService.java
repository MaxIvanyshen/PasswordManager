package ua.ivanyshen.passwordmanager.db;

import ua.ivanyshen.passwordmanager.entities.User;

public class UserService {
    private Repository<User> repo;

    public UserService() {
        repo = new UserListRepository();
    }

    public User insert(User u) {
        return repo.insert(u);
    }

    public int size() {
        return repo.size();
    }

    public User findUserById(String id) {
        return repo.findById(id);
    }

    public void delete(String id) {
        repo.delete(id);
    }
}
