package ua.ivanyshen.passwordmanager.db;

import org.springframework.stereotype.Component;

@Component
public interface Repository<T> {
    T insert(T element);
    void delete(Object o);
    int size();

    T findById(String id);
}
