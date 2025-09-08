package org.example.dao.MySQL;

import org.example.dao.interfaces.ClienteDAO;
import org.example.entity.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOMySQL implements ClienteDAO {

    private Connection conexion;

    public ClienteDAOMySQL(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void createTable() throws SQLException {
        String create = "CREATE TABLE IF NOT EXISTS Cliente(idCliente INT, " +
                "nombre VARCHAR(45), " +
                "email VARCHAR(100), " +
                "PRIMARY KEY (idCliente))";
        this.conexion.prepareStatement(create).executeUpdate();
        conexion.commit();
    }

    @Override
    public List<Cliente> listarTodo() throws SQLException{
        List<Cliente> lista = new ArrayList<>();
        String query = "SELECT * FROM Cliente";
        try {
            PreparedStatement ps = this.conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("nombre"),
                        rs.getString("email")
                );
                lista.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public Cliente obtenerPorId(Integer id) throws SQLException{
        String query = "SELECT * FROM Cliente WHERE idCliente=?";
        Cliente cliente = null;
        try(PreparedStatement ps = this.conexion.prepareStatement(query);){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    int idCliente = rs.getInt("idCliente");
                    String nombre =  rs.getString("nombre");
                    String email = rs.getString("email");
                    Cliente c = new Cliente(idCliente, nombre, email);
                }
            }catch(SQLException e){
                throw new RuntimeException(e);
            }

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void agregar(Cliente cliente) throws SQLException {
        String query =  "INSERT INTO Cliente (idCliente,nombre, email) VALUES (?,?, ?)";
        try(PreparedStatement ps = this.conexion.prepareStatement(query);){
            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getEmail());
            ps.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }


    }

    @Override
    public void eliminar(Cliente cliente) {
        String query = "DELETE FROM Cliente WHERE idCliente=?";
        try(PreparedStatement ps = this.conexion.prepareStatement(query);){
            ps.setInt(1, cliente.getIdCliente());
            int rowsaffected = ps.executeUpdate();

            if(rowsaffected == 0){
                throw new RuntimeException("No se pudo eliminar el cliente");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actualizar(int id, Cliente nuevo) throws SQLException {
        String query =  "UPDATE Cliente SET nombre=?, email=? WHERE idCliente=?";
        try(PreparedStatement ps = this.conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery();){
            if(rs.next()){
                ps.setString(1, nuevo.getNombre());
                ps.setString(2, nuevo.getEmail());
                ps.executeUpdate();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cliente> getClientesConMayorFacturacion() throws SQLException {


        String query = "SELECT nombre, SUM(fp.cantidad * p.valor) as totalFacturado FROM Cliente c" +
                "JOIN Factura f ON f.idCliente=c.idCliente" +
                "JOIN Factura_Producto ON fp.idFactura=f.idFactura" +
                "JOIN Producto p ON p.idProducto=f.idProducto" +
                "GROUP BY c.nombre" +
                "ORDER BY totalFacturado DESC";
        List<Cliente> clientes = new ArrayList<>();
        try(PreparedStatement ps = this.conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getString("nombre"),
                        rs.getFloat("totalFacturado")
                ));
            }
        }
        return clientes;

    }
}
