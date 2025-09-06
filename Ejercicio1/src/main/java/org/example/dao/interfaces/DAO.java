package org.example.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    List<T> listarTodo() throws SQLException;
    T obtenerPorId(Integer id) throws SQLException;
    void  agregar(T t) throws SQLException;
    void eliminar(T t) throws SQLException;
    void actualizar(int id, T nuevo) throws SQLException;

}
