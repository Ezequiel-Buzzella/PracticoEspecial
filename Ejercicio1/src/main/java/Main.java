import daos.interfaces.ClienteDAO;
import daos.interfaces.DAO;
import daos.interfaces.ProductoDAO;
import daos.mysql.ProductoDAOMySQL;
import dtos.ClienteDTO;
import dtos.ProductoDTO;
import entity.Cliente;
import entity.Factura;
import entity.Factura_Producto;
import entity.Producto;
import factory.DAOFactory;
import utils.LoadCsv;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        // Crear la base de datos:
        DAOFactory daoFactory = DAOFactory.getDAOFactory(1);

        ClienteDAO clienteDao = daoFactory.getClienteDAO();
        ProductoDAO productoDao = daoFactory.getProductoDAO();
        DAO facturaDao = daoFactory.getFacturaDAO();
        DAO facturaProductoDao = daoFactory.getFactura_ProductoDAO();
        LoadCsv loadCsv = new LoadCsv();

        // Agregar clientes.csv a la base.
        // CSV Reader retorna una lista de clientes.
//        List<Cliente> clientes = loadCsv.LoadCliente();
//        for(Cliente c : clientes) {
//            clienteDao.agregar(c);
//        }

        // Agregar productos.csv a la base.
        // CSV Reader retorna una lista de productos.
        //Producto pro = new Producto(1, "nisl sem,", 92);
        //productoDao.eliminar(pro);
//        List<Producto> productos = loadCsv.LoadProducto();
//        for(Producto p : productos) {
//            productoDao.agregar(p);
//        }

        // Agregar facturas.csv a la base.
        // CSV Reader retorna una lista de facturas.
//        List<Factura> facturas = loadCsv.LoadFactura();
//        for(Factura f : facturas) {
//            facturaDao.agregar(f);
//        }

        // Agregar Facturas_Productos.csv a la base.
        // CSV Reader retorna una lista de Facturas_Productos.
//        List<Factura_Producto> facturas_productos = loadCsv.LoadFactura_Producto();
//        for(Factura_Producto fp : facturas_productos) {
//            facturaProductoDao.agregar(fp);
//        }

        ProductoDTO ejercicio3 = null;
        ejercicio3 = productoDao.productoQueMasRecaudo();
        System.out.println(ejercicio3.toString());

        List<ClienteDTO> ejercicio4 = null;
        ejercicio4 = clienteDao.getClientesConMayorFacturacion();
        for(ClienteDTO cliente : ejercicio4) {
            System.out.println(cliente.toString());
        }

    }
}