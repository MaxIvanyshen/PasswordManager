package ua.ivanyshen.passwordmanager.db;

import ua.ivanyshen.passwordmanager.entities.User;

import java.util.ArrayList;

public class UserListRepository implements Repository<User> {

    private ArrayList<User> list;

    public UserListRepository() {
        list = new ArrayList<>();
    }

    @Override
    public User insert(User element) {
        list.add(element);
        return element;
    }

    @Override
    public void delete(Object o) {
        String id = (String) o;
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getId().equals(id))
                list.remove(i);
        }
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public User findById(String id) {
        for(User u : list) {
            if(u.getId().equals(id))
                return u;
        }
        return null;
    }
}
