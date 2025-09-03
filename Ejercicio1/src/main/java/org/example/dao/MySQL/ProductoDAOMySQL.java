package org.example.dao.MySQL;

import org.example.dao.interfaces.ProductoDAO;
import org.example.entity.Producto;

import java.util.List;

public class ProductoDAOMySQL implements ProductoDAO {

    @Override
    public List<Producto> listarTodo() {
        return List.of();
    }

    @Override
    public Producto listarPorId(Integer id) {
        return null;
    }

    @Override
    public void agregar(Producto producto) {

    }

    @Override
    public void eliminar(Producto producto) {

    }
}
