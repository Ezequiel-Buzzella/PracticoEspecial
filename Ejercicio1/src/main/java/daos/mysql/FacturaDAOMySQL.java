package daos.mysql;

import daos.interfaces.DAO;
import entity.Factura;

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
    public List<Factura> listarTodo(){
        List<Factura> lista = new ArrayList<>();
        String query = "SELECT * FROM Factura";
        PreparedStatement ps = null;
        try {
            ps = this.conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Factura factura = new Factura(
                        rs.getInt("idFactura"),
                        rs.getInt("idCliente")
                );
                lista.add(factura);
            }
            rs.close();
            ps.close();
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Factura obtenerPorId(Integer id){
        Factura resultado = null;
        String query = "SELECT * FROM Factura WHERE idFactura = ?";
        PreparedStatement ps = null;
        try {
            ps = this.conexion.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                resultado = new Factura(
                        rs.getInt("idFactura"),
                        rs.getInt("idCliente")
                );
            }
            rs.close();
            ps.close();
            return resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void agregar(Factura factura) {
        String insert = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ? )";
        PreparedStatement ps = null;
        try {
            ps = this.conexion.prepareStatement(insert);
            ps.setInt(1, factura.getIdFactura());
            ps.setInt(2, factura.getIdCliente());
            ps.executeUpdate();
            ps.close();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(Factura factura){
        String delete = "DELETE FROM Factura WHERE idFactura = ?";
        PreparedStatement ps = null;
        try {
            ps = this.conexion.prepareStatement(delete);
            ps.setInt(1, factura.getIdFactura());
            int rowsAffecteds = ps.executeUpdate();
            if(rowsAffecteds == 0) {
                throw new RuntimeException("No se encontro el producto con la id especificada...");
            }
            ps.close();
            conexion.commit();
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actualizar(int id, Factura nuevo){
        String update = "UPDATE Factura SET idCliente = ? WHERE idFactura = ?";
        PreparedStatement ps = null;
        try {
            ps = this.conexion.prepareStatement(update);
            ps.setInt(1, nuevo.getIdCliente());
            ps.setInt(2, id);
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected == 0) {
                throw new RuntimeException("No se encontro el producto con la id especificada...");
            }
            ps.close();
            conexion.commit();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //falta un update?
}
