package ua.ivanyshen.passwordmanager.db;

import org.springframework.stereotype.Component;

import ua.ivanyshen.passwordmanager.entities.Password;

import java.util.ArrayList;

public class PasswordListRepository implements Repository<Password>{

    private ArrayList<Password> list;

    public PasswordListRepository()  {
        list = new ArrayList<>();
    }

    @Override
    public Password insert(Password element) {
        if(findById(element.getId()) == null)
            list.add(element);
        else {
            for(int i = 0; i < list.size(); i++)
                if(list.get(i).getId().equals(element.getId()))
                    list.remove(i);
            list.add(element);
        }
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
    public Password findById(String id) {
        for(Password p : list) {
            if(p.getId().equals(id))
                return p;
        }
        return null;
    }
}
