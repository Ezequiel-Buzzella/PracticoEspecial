package daos.mysql;

import daos.interfaces.DAO;
import entity.Factura_Producto;
import entity.Factura_ProductoId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class Factura_ProductoDAOMySQL implements DAO<Factura_Producto, Factura_ProductoId> {

    private static Factura_ProductoDAOMySQL instancia;

    private Connection conexion;

    public static Factura_ProductoDAOMySQL obtenerInstancia(Connection conexion) throws SQLException {
        if(instancia == null) {
            instancia = new Factura_ProductoDAOMySQL(conexion);
        }
        return instancia;
    }

    private Factura_ProductoDAOMySQL(Connection conexion) throws SQLException {
        this.conexion = conexion;
        this.createTable();
    }

    @Override
    public void createTable() throws SQLException {
        String create = "CREATE TABLE IF NOT EXISTS Factura_Producto(idFactura INT, " +
                "idProducto INT, " +
                "cantidad INT, " +
                "PRIMARY KEY (idFactura, idProducto)," +
                "    FOREIGN KEY (idFactura) REFERENCES Factura(idFactura)" +
                "        ON UPDATE CASCADE" +
                "        ON DELETE CASCADE," +
                "    FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)" +
                "        ON UPDATE CASCADE " +
                "        ON DELETE RESTRICT)";
        this.conexion.prepareStatement(create).executeUpdate();
        conexion.commit();
    }

    @Override
    public List<Factura_Producto> listarTodo(){
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
            ps.close();
            rs.close();
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Factura_Producto obtenerPorId(Factura_ProductoId id){
        Factura_Producto resultado = null;
        String query = "SELECT * FROM Factura_Producto WHERE idFactura = ?, idProducto = ?";
        PreparedStatement ps = null;
        Factura_Producto fp = null;
        try {
            ps = this.conexion.prepareStatement(query);
            ps.setInt(1, id.getIdFactura());
            ps.setInt(2, id.getIdProducto());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                fp = new Factura_Producto(
                        rs.getInt("idFactura"),
                        rs.getInt("idProducto"),
                        rs.getInt("cantidad")
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
    public void agregar(Factura_Producto facturaProducto){
        String insert = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = this.conexion.prepareStatement(insert);
            ps.setInt(1, facturaProducto.getIdFactura());
            ps.setInt(2, facturaProducto.getIdProducto());
            ps.setInt(3, facturaProducto.getCantidad());
            ps.executeUpdate();
            ps.close();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(Factura_Producto facturaProducto){
        String delete = "DELETE FROM Factura_Producto WHERE idFactura = ?";
        PreparedStatement ps = null;
        try {
            ps = this.conexion.prepareStatement(delete);
            ps.setInt(1, facturaProducto.getIdFactura());
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected==0){
                throw new RuntimeException("No se encontro el producto con la id especificada...");
            }
            ps.close();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizar(int id, Factura_Producto nuevo){
        String update = "UPDATE Factura_Producto SET cantidad = ? WHERE idFactura = ?";
        PreparedStatement ps = null;
        try {
            ps = this.conexion.prepareStatement(update);
            ps.setInt(1, nuevo.getCantidad());
            ps.setInt(2, id);
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected==0){
                throw new RuntimeException("No se encontro el producto con la id especificada...");
            }
            ps.close();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
