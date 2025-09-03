package org.example.dao.MySQL;

import org.example.dao.interfaces.FacturaDAO;
import org.example.entity.Factura;

import java.util.List;

public class FacturaDAOMySQL implements FacturaDAO {
    @Override
    public List<Factura> listarTodo() {
        return List.of();
    }

    @Override
    public Factura listarPorId(Integer id) {
        return null;
    }

    @Override
    public void agregar(Factura factura) {

    }

    @Override
    public void eliminar(Factura factura) {

    }
}
