package factory;

import daos.interfaces.ClienteDAO;
import daos.interfaces.ProductoDAO;
import daos.mysql.ClienteDAOMySQL;
import daos.mysql.FacturaDAOMySQL;
import daos.mysql.Factura_ProductoDAOMySQL;
import daos.mysql.ProductoDAOMySQL;
import daos.interfaces.DAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDAOFactory extends DAOFactory {

    private static MysqlDAOFactory instance;

    private Connection conn;

    private final String DRIVER =  "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3307/Entregable1";
    private final String PASSWORD = "root";
    private final String USER = "root";

    public static MysqlDAOFactory getInstance() throws ClassNotFoundException {
        if(instance == null) {
            instance = new MysqlDAOFactory();
        }
        return instance;
    }

    private MysqlDAOFactory() throws ClassNotFoundException {
        this.conn = getConnection();
    }

    public Connection getConnection() throws ClassNotFoundException{
        try {
            Class.forName(this.DRIVER).getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        Connection conexion;
        try {
             conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            conexion.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conexion;
    }

    @Override
    public ClienteDAO getClienteDAO() throws SQLException {
        return ClienteDAOMySQL.obtenerInstancia(this.conn);
    }
    @Override
    public ProductoDAO getProductoDAO() throws SQLException {
        return ProductoDAOMySQL.obtenerInstancia(this.conn);
    }

    @Override
    public DAO getFacturaDAO() throws SQLException {
        return FacturaDAOMySQL.obtenerInstancia(this.conn);
    }

    @Override
    public DAO getFactura_ProductoDAO() throws SQLException {
        return Factura_ProductoDAOMySQL.obtenerInstancia(this.conn);
    }
}
