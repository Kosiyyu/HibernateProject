package org.example.dao;

import java.util.List;

public interface DAO<T> {

    T get(long id);

    List<T> getAll();

    void save(T t);

    void update(T t, T tUpdate);

    void delete(T t);

    void delete(long id);
}
