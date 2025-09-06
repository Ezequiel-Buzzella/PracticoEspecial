package org.example.dao.MySQL;

import org.example.dao.interfaces.Factura_ProductoDAO;
import org.example.entity.Factura_Producto;

import java.util.List;

public class Factura_ProductoDAOMySQL implements Factura_ProductoDAO {
    @Override
    public List<Factura_Producto> listarTodo() {
        return List.of();
    }

    @Override
    public Factura_Producto obtenerId(Integer id) {
        return null;
    }

    @Override
    public void agregar(Factura_Producto facturaProducto) {

    }

    @Override
    public void eliminar(int facturaProducto) {

    }
}
