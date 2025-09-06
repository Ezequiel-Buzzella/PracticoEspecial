package org.example.dao.interfaces;

import java.util.List;

public interface DAO<T> {
    List<T> listarTodo();
    T obtenerId(Integer id);
    void  agregar(T t);
    void eliminar(int id);
    void createTable();
    void update(T t);
}
