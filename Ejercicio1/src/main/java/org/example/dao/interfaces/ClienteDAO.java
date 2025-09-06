package org.example.dao.interfaces;

import org.example.entity.Cliente;

import java.util.List;

public interface ClienteDAO extends DAO<Cliente> {
    public List<Cliente> getClientesCOnMayorFacturacion() throws  Exception;
}
