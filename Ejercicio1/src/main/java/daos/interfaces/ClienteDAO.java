package daos.interfaces;

import dtos.ClienteDTO;
import entity.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO extends DAO<Cliente, Integer> {
    List<ClienteDTO> getClientesConMayorFacturacion() throws SQLException;
}
