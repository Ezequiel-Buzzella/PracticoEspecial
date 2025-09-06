package org.example.dao.MySQL;

import org.example.dao.interfaces.ProductoDAO;
import org.example.entity.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOMySQL implements ProductoDAO {

    private Connection conn;

    public ProductoDAOMySQL(Connection conn) {
        this.conn = conn;
        createTable();
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
    public Producto obtenerId(Integer id) {
        String query = "SELECT * FROM Producto WHERE id = ?";
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

    public void update(Producto p){
        String update = "UPDATE Producto SET nombre = ?, valor = ? WHERE id = ?";
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
        String insert = "INSERT INTO Producto VALUES (?, ?, ?)";
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
    public void eliminar(int id) {
        String delete = "DELETE FROM Producto WHERE idProducto = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(delete);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createTable() {
        String table = "CREATE TABLE IF NOT EXISTS Producto(" +
                "idProducto INT, " +
                "nombre VARCHAR(50), " +
                "valor FLOAT, " +
                "PRIMARY KEY(idProducto))";
        try {
            PreparedStatement ps = conn.prepareStatement(table);
            ps.executeUpdate();
            conn.commit();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}