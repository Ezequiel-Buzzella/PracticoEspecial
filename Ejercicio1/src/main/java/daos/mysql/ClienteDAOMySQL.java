package daos.mysql;

import dtos.ClienteDTO;
import daos.interfaces.ClienteDAO;
import entity.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOMySQL implements ClienteDAO {

    private static ClienteDAOMySQL instancia;

    private Connection conexion;

    public static ClienteDAOMySQL obtenerInstancia(Connection conexion) throws SQLException {
        if(instancia == null) {
            instancia = new ClienteDAOMySQL(conexion);
        }
        return instancia;
    }

    private ClienteDAOMySQL(Connection conexion) throws SQLException {
        this.conexion = conexion;
        this.createTable();
    }

    @Override
    public void createTable(){
        String create = "CREATE TABLE IF NOT EXISTS Cliente(idCliente INT, " +
                "nombre VARCHAR(45), " +
                "email VARCHAR(100), " +
                "PRIMARY KEY (idCliente))";
        try {
            this.conexion.prepareStatement(create).executeUpdate();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
            rs.close();
            ps.close();
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Cliente obtenerPorId(Integer id){
        String query = "SELECT * FROM Cliente WHERE idCliente=?";
        Cliente cliente = null;
        PreparedStatement ps = null;
        try {
            ps = this.conexion.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                int idCliente = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                cliente = new Cliente(idCliente, nombre, email);
            }
            rs.close();
            ps.close();
            return cliente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void agregar(Cliente cliente){
        String query =  "INSERT INTO Cliente (idCliente,nombre, email) VALUES (?,?, ?)";
        PreparedStatement ps = null;
        try {
            ps = this.conexion.prepareStatement(query);
            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getEmail());
            ps.executeUpdate();
            ps.close();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(Cliente cliente) {
        String query = "DELETE FROM Cliente WHERE idCliente=?";
        PreparedStatement ps = null;
        try {
            ps = this.conexion.prepareStatement(query);
            ps.setInt(1, cliente.getIdCliente());
            int rowsaffected = ps.executeUpdate();
            if(rowsaffected == 0){
                throw new RuntimeException("No se encontro el producto con la id especificada...");
            }
            ps.close();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actualizar(int id, Cliente nuevo){
        String query =  "UPDATE Cliente SET nombre=?, email=? WHERE idCliente=?";
        PreparedStatement ps = null;
        try {
            ps = this.conexion.prepareStatement(query);
            ps.setString(1, nuevo.getNombre());
            ps.setString(2, nuevo.getEmail());
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected == 0){
                System.out.println("No se encontró un cliente con el ID: " + id);
            }
            ps.close();
            conexion.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ClienteDTO> getClientesConMayorFacturacion(){
        String query = "SELECT nombre, SUM(fp.cantidad * p.valor) as totalFacturado FROM Cliente c" +
                "JOIN Factura f ON f.idCliente=c.idCliente" +
                "JOIN Factura_Producto ON fp.idFactura=f.idFactura" +
                "JOIN Producto p ON p.idProducto=f.idProducto" +
                "GROUP BY c.nombre" +
                "ORDER BY totalFacturado DESC";
        List<ClienteDTO> clientes = new ArrayList<>();
        PreparedStatement ps = null;
        try {
            ps = this.conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                clientes.add(new ClienteDTO(
                        rs.getString("nombre"),
                        rs.getFloat("totalFacturado")
                ));
            }
            rs.close();
            ps.close();
            return clientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
