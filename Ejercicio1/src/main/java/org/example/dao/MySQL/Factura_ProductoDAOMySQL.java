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

    public Factura_ProductoDAOMySQL(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Factura_Producto> listarTodo() throws SQLException {
        List<Factura_Producto> lista = new ArrayList<>();
        String query = "SELECT * FROM Factura_Producto";
        try(PreparedStatement ps = this.conexion.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
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
        }
        return lista;
    }

    @Override
    public Factura_Producto listarPorId(Integer id) throws SQLException {
        Factura_Producto resultado = null;
        String query = "SELECT * FROM Factura_Producto WHERE idFactura = ?";
        try(PreparedStatement ps = this.conexion.prepareStatement(query)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    resultado = new Factura_Producto(
                        rs.getInt("idFactura"),
                        rs.getInt("idProducto"),
                        rs.getInt("cantidad")
                    );
                }
            }
        } catch(SQLException e) {
            throw new SQLException("Error al obtener un registro de Factura_Producto por id", e);
        }
        return resultado;
    }

    @Override
    public void agregar(Factura_Producto facturaProducto) throws SQLException {
        String insert = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        try(PreparedStatement ps = this.conexion.prepareStatement(insert)) {
            ps.setInt(1, facturaProducto.getIdFactura());
            ps.setInt(2, facturaProducto.getIdProducto());
            ps.setInt(3, facturaProducto.getCantidad());
            ps.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException("Error al agregar un registro a Factura_Producto", e);
        }
    }

    @Override
    public void eliminar(Factura_Producto facturaProducto) throws SQLException {
        String delete = "DELETE FROM Factura_Producto WHERE idFactura = ?";
        try(PreparedStatement ps = this.conexion.prepareStatement(delete)) {
            ps.setInt(1, facturaProducto.getIdFactura());
            ps.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException("Error al eliminar un registro de Factura_Producto", e);
        }
    }
}
