package org.example.dao.MySQL;

import org.example.dao.interfaces.DAO;
import org.example.entity.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class FacturaDAOMySQL implements DAO<Factura, Integer> {

    private static FacturaDAOMySQL instancia;

    private Connection conexion;

    public static FacturaDAOMySQL obtenerInstancia(Connection conexion) throws SQLException {
        if(instancia == null) {
            instancia = new FacturaDAOMySQL(conexion);
        }
        return instancia;
    }

    private FacturaDAOMySQL(Connection conexion)  throws SQLException {
        this.conexion = conexion;
        this.createTable();
    }

    @Override
    public void createTable() throws SQLException {
        String create = "CREATE TABLE IF NOT EXISTS Factura(idFactura INT, " +
                "idCliente INT, " +
                "PRIMARY KEY (idFactura)";
        this.conexion.prepareStatement(create).executeUpdate();
        conexion.commit();
    }

    @Override
    public List<Factura> listarTodo() throws SQLException {
        List<Factura> lista = new ArrayList<>();
        String query = "SELECT * FROM Factura";
        try(PreparedStatement ps = this.conexion.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                Factura factura = new Factura(
                        rs.getInt("idFactura"),
                        rs.getInt("idCliente")
                );
                lista.add(factura);
            }
        } catch(SQLException e) {
            throw new SQLException("Error al listar todos los registros de Factura", e);
        }
        return lista;
    }

    @Override
    public Factura obtenerPorId(Integer id) throws SQLException {
        Factura resultado = null;
        String query = "SELECT * FROM Factura WHERE idFactura = ?";
        try(PreparedStatement ps = this.conexion.prepareStatement(query)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    resultado = new Factura(
                            rs.getInt("idFactura"),
                            rs.getInt("idCliente")
                    );
                }
            }
        } catch(SQLException e) {
            throw new SQLException("Error al obtener un registros de Factura por id : " + id  , e);
        }
        return resultado;

    }

    @Override
    public void agregar(Factura factura) throws SQLException {
        String insert = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ? )";
        try(PreparedStatement ps = this.conexion.prepareStatement(insert)) {
            ps.setInt(1, factura.getIdFactura());
            ps.setInt(2, factura.getIdCliente());
            ps.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException("Error al agregar un registro a Factura", e);
        }
    }

    @Override
    public void eliminar(Factura factura) throws SQLException {
        //No podriamos pasar directamente el id?
        String delete = "DELETE FROM Factura WHERE idFactura = ?";
        try(PreparedStatement ps = this.conexion.prepareStatement(delete)) {
            ps.setInt(1, factura.getIdFactura());
            ps.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException("Error al eliminar un registro de Factura", e);
        }
    }

    @Override
    public void actualizar(int id, Factura nuevo) throws SQLException {

    }
    //falta un update?
}
