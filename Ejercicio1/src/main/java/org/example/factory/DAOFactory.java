package org.example.factory;

import org.example.dao.interfaces.ClienteDAO;
import org.example.dao.interfaces.ProductoDAO;

public interface DAOFactory {

    ClienteDAO getClienteDAO();
    ProductoDAO getProductoDAO();

}
