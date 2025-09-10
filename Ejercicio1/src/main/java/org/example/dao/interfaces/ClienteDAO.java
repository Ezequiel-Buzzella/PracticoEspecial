package org.example.dao.interfaces;

import org.example.DTO.ClienteDTO;
import org.example.entity.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO extends DAO<Cliente, Integer> {
    List<ClienteDTO> getClientesConMayorFacturacion() throws SQLException;
}
