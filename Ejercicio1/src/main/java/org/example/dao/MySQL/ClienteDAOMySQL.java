package org.example.dao.MySQL;

import org.example.dao.interfaces.ClienteDAO;
import org.example.entity.Cliente;

import java.util.List;

public class ClienteDAOMySQL implements ClienteDAO {
    @Override
    public List<Cliente> listarTodo() {
        return List.of();
    }

    @Override
    public Cliente obtenerId(Integer id) {
        return null;
    }

    @Override
    public void agregar(Cliente cliente) {

    }

    @Override
    public void eliminar(int id) {

    }
}
