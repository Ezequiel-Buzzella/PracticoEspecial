package org.example.dao.interfaces;

import org.example.entity.Producto;

import java.util.List;

public interface ProductoDAO extends DAO<Producto> {
    List<Producto> listarTodo();
    Producto obtenerId(Integer id);
    void agregar(Producto p);
    void eliminar(int id);
    void update(Producto p);
}
