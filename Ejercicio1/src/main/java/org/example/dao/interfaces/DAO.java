package org.example.dao.interfaces;

import java.util.List;

public interface DAO<T> {
    List<T> listarTodo();
    T listarPorId(Integer id);
    void  agregar(T t);
    void eliminar(T t);

}
