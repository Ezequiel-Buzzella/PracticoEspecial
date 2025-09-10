package org.example.dao.MySQL;

import org.example.dao.interfaces.DAO;
import org.example.dao.interfaces.ProductoDAO;
import org.example.entity.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOMySQL implements ProductoDAO {

    private static ProductoDAOMySQL instancia;

    private Connection conn;

    public static ProductoDAOMySQL obtenerInstancia(Connection conexion) throws SQLException {
        if(instancia == null) {
            instancia = new ProductoDAOMySQL(conexion);
        }
        return instancia;
    }

    private ProductoDAOMySQL(Connection conn) throws SQLException {
        this.conn = conn;
        this.createTable();
    }

    @Override
    public void createTable() throws SQLException {
        String create = "CREATE TABLE IF NOT EXISTS Producto(idProducto INT, " +
                "nombre VARCHAR(44), " +
                "valor FLOAT, " +
                "PRIMARY KEY (idProdutco))";
        this.conn.prepareStatement(create).executeUpdate();
        conn.commit();
    }

    @Override
    public List<Producto> listarTodo() {
        String query = "SELECT * FROM Producto";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<Producto> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(new Producto(rs.getInt(1), rs.getString(2), rs.getFloat(3)));
            }
            ps.executeUpdate();
            rs.close();
            ps.close();
            conn.commit();
            conn.close();
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Producto obtenerPorId(Integer id) {
        String query = "SELECT * FROM Producto WHERE idProducto = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            Producto p  =new  Producto(rs.getInt(1), rs.getString(2), rs.getFloat(3));
            ps.executeUpdate();
            rs.close();
            ps.close();
            conn.commit();
            conn.close();
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void actualizar(Producto p){
        String update = "UPDATE Producto SET nombre = ?, valor = ? WHERE idProducto = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(update);
            ps.setString(1, p.getNombre());
            ps.setFloat(2, p.getValor());
            ps.executeUpdate();
            ps.close();
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void agregar(Producto producto) {
        String insert = "INSERT INTO Producto (idProducto,nombre, valor) VALUES (?,?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getValor());
            ps.executeUpdate();
            ps.close();
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(Producto producto) throws SQLException {
        String query = "DELETE FROM Producto WHERE idProducto = ?";
        try(PreparedStatement ps = conn.prepareStatement(query);){
            ps.setInt(1, producto.getIdProducto());
            int rowsAffected = ps.executeUpdate();

            if(rowsAffected==0){
                throw new RuntimeException();
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void actualizar(int id, Producto nuevo) throws SQLException {
        String query= "UPDATE Producto SET nombre = ?, valor = ? WHERE idProducto = ?";
        try(PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();){
            if(rs.next()){
                ps.setString(1, nuevo.getNombre());
                ps.setFloat(2, nuevo.getValor());
                ps.executeUpdate();

            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public Producto productoQueMasRecaudo() throws Exception {
        return null;
    }
}