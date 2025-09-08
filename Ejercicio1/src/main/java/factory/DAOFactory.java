package factory;


import org.example.dao.interfaces.ClienteDAO;
import org.example.dao.interfaces.FacturaDAO;
import org.example.dao.interfaces.Factura_ProductoDAO;
import org.example.dao.interfaces.ProductoDAO;

public abstract class DAOFactory {


    public static DAOFactory getDAOFactory(int database) {
        switch(database){
            case 1:
                return new MysqlDAOFactory();
            default:
                throw new IllegalArgumentException("Fábrica no soportada: " + database);
        }
    }

    /*public abstract ProductoDAO getProductoDAO();
    public abstract ClienteDAO getClienteDAO();
    public abstract FacturaDAO getFacturaDAO();
    public abstract Factura_ProductoDAO getFactura_ProductoDAO();*/

}
