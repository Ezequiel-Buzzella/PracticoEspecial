package org.example.dao.interfaces;

import org.example.entity.Cliente;

import java.util.List;

public interface ClienteDAO extends DAO<Cliente> {
    List<Cliente> getClientesCOnMayorFacturacion() throws Exception;
    
}
