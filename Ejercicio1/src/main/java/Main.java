import daos.interfaces.DAO;
import entity.Cliente;
import entity.Factura;
import entity.Factura_Producto;
import entity.Producto;
import factory.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Crear la base de datos:
        DAOFactory daoFactory = DAOFactory.getDAOFactory(1);

        DAO clienteDao = daoFactory.getClienteDAO();
        DAO productoDao = daoFactory.getProductoDAO();
        DAO facturaDao = daoFactory.getFacturaDAO();
        DAO facturaProductoDao = daoFactory.getFactura_ProductoDAO();

        // Agregar clientes.csv a la base.
        // CSV Reader retorna una lista de clientes.
        ArrayList<Cliente> clientes = new ArrayList<>();
        for(Cliente c : clientes) {
            clienteDao.agregar(c);
        }

        // Agregar productos.csv a la base.
        // CSV Reader retorna una lista de productos.
        ArrayList<Producto> productos = new ArrayList<>();
        for(Producto p : productos) {
            productoDao.agregar(p);
        }

        // Agregar facturas.csv a la base.
        // CSV Reader retorna una lista de facturas.
        ArrayList<Factura> facturas = new ArrayList<>();
        for(Factura f : facturas) {
            productoDao.agregar(f);
        }

        // Agregar Facturas_Productos.csv a la base.
        // CSV Reader retorna una lista de Facturas_Productos.
        ArrayList<Factura_Producto> facturas_productos = new ArrayList<>();
        for(Factura_Producto fp : facturas_productos) {
            productoDao.agregar(fp);
        }
    }
}