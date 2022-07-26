package ua.ivanyshen.passwordmanager.db;

public interface Repository<T> {
    T insert(T element);
    void delete(Object o);
    int size();

    T findById(String id);
}
