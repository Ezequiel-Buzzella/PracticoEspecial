package factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDAOFactory extends DAOFactory {

    String driver =  "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3307/Entregable1";
    String password = "root";
    String user = "root";


    public Connection getConnection() throws ClassNotFoundException{
        try {
            Class.forName(this.driver).getDeclaredConstructor().newInstance();
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
             conexion = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conexion;
    }

}
