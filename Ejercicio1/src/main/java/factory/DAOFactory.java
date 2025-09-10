package factory;


import daos.interfaces.DAO;

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

    public abstract DAO getProductoDAO() throws SQLException;
    public abstract DAO getClienteDAO() throws SQLException;
    public abstract DAO getFacturaDAO() throws SQLException;
    public abstract DAO getFactura_ProductoDAO() throws SQLException;
}
