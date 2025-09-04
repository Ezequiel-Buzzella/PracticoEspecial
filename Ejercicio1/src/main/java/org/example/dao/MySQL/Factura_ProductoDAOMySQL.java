package org.example.dao.MySQL;

import org.example.dao.interfaces.Factura_ProductoDAO;
import org.example.entity.Factura_Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class Factura_ProductoDAOMySQL implements Factura_ProductoDAO {
    private Connection conexion;

    @Override
    public List<Factura_Producto> listarTodo() throws SQLException {
        List<Factura_Producto> lista = new ArrayList<>();
        String query = "SELECT * FROM Factura_Producto";
        PreparedStatement ps = null;
        try {
            ps = this.conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Factura_Producto facturaProducto = new Factura_Producto(
                rs.getInt("idFactura"),
                rs.getInt("idProducto"),
                rs.getInt("cantidad")
                );
                lista.add(facturaProducto);
            }
        } catch(SQLException e) {
            throw new SQLException("Error al listar todos los registros de Factura_Producto", e);
        } finally {
            if (ps != null) try {ps.close();} catch (Exception e) { /*Agregar manejo de excepciones */ };
        }
        return lista;
    }

    @Override
    public Factura_Producto listarPorId(Integer id) throws SQLException {
        String query = "SELECT * FROM Factura_Producto WHERE idFactura = ?";
        PreparedStatement ps = null;
        try {
            ps = this.conexion.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Factura_Producto(
                        rs.getInt("idFactura"),
                        rs.getInt("idProducto"),
                        rs.getInt("cantidad")
                );
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener un registro de Factura_Producto por id", e);
        } finally {
            if (ps != null) try {ps.close();} catch (Exception e) { /*Agregar manejo de excepcion };*/}
        }
        return null;
    }

    @Override
    public void agregar(Factura_Producto facturaProducto) throws SQLException {
        String insert = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = this.conexion.prepareStatement(insert);
            ps.setInt(1, facturaProducto.getIdFactura());
            ps.setInt(2, facturaProducto.getIdProducto());
            ps.setInt(3, facturaProducto.getCantidad());
            ps.execute();
            this.conexion.commit();
        } catch(SQLException e) {
            throw new SQLException("Error al agregar un registro a Factura_Producto", e);
        } finally {
            if (ps != null) try { ps.close(); } catch (Exception e) {/* Agregar manejo de excepciones */ }
        }
    }

    @Override
    public void eliminar(Factura_Producto facturaProducto) throws SQLException {
        String delete = "DELETE FROM Factura_Producto WHERE idFactura = ?";
        PreparedStatement ps = null;
        try {
            ps = this.conexion.prepareStatement(delete);
            ps.setInt(1, facturaProducto.getIdFactura());
            ps.execute();
        } catch(SQLException e) {
            throw new SQLException("Error al eliminar un registro de Factura_Producto", e);
        } finally {
            if (ps != null) try { ps.close(); } catch (Exception e) { /*Agregar manejo de excepcion*/}
        }
    }
}
