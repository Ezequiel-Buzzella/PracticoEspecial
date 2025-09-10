package daos.mysql;

import daos.interfaces.ProductoDAO;
import dtos.ProductoDTO;
import entity.Producto;

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
                "PRIMARY KEY (idProducto))";
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
            rs.close();
            ps.close();
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
            Producto p  = new  Producto(rs.getInt(1), rs.getString(2), rs.getFloat(3));
            rs.close();
            ps.close();
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
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected == 0){
                System.out.println("No se encontró un cliente con el ID: " + p.getIdProducto());
            }
            ps.close();
            conn.commit();
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
                throw new RuntimeException("No se encontro el producto con la id especificada...");
            }
            ps.close();
            conn.commit();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void actualizar(int id, Producto nuevo){
        String query= "UPDATE Producto SET nombre = ?, valor = ? WHERE idProducto = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected == 0){
                System.out.println("No se encontró un cliente con el ID: " + id);
            }
            ps.setString(1, nuevo.getNombre());
            ps.setFloat(2, nuevo.getValor());
            ps.close();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductoDTO productoQueMasRecaudo(){
        String query = "SELECT p.nombre ,p.valor,sum(fp.cantidad= as cantidadVendida" +
                "SUM(fp.cantidad*p.valor) as recaudacion" +
                "FROM Producto p" +
                "JOIN Factura_Producto fp ON p.idProducto = fp.idProducto" +
                "GROUP BY p.nombre p.valor" +
                "ORDER BY recaudacion DESC" +
                "LIMIT 1";
        PreparedStatement ps = null;
        ProductoDTO p = null;
        try {
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                p = new ProductoDTO(
                        rs.getString("nombre"),
                        rs.getFloat("valor"),
                        rs.getInt("cantidadVendida"),
                        rs.getFloat("recaudacion")
                );
            }
            ps.close();
            conn.commit();
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}