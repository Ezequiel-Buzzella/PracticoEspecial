package org.example.dao.interfaces;

import org.example.entity.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T, Y> {
    List<T> listarTodo() throws SQLException;
    T obtenerPorId(Y id) throws SQLException;
    void  agregar(T t) throws SQLException;
    void eliminar(T t) throws SQLException;
    void actualizar(int id, T nuevo) throws SQLException;
    void createTable() throws SQLException;
}
