package factory;


import daos.interfaces.ClienteDAO;
import daos.interfaces.DAO;
import daos.interfaces.ProductoDAO;

import java.sql.SQLException;

public abstract class DAOFactory {

    public static DAOFactory getDAOFactory(int database) throws ClassNotFoundException {
        switch(database){
            case 1:
                return MysqlDAOFactory.getInstance();
            default:
                throw new IllegalArgumentException("Fábrica no soportada: " + database);
        }
    }

    public abstract ProductoDAO getProductoDAO() throws SQLException;
    public abstract ClienteDAO getClienteDAO() throws SQLException;
    public abstract DAO getFacturaDAO() throws SQLException;
    public abstract DAO getFactura_ProductoDAO() throws SQLException;
}
