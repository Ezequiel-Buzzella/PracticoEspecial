package utils;

import entity.Cliente;
import entity.Factura;
import entity.Factura_Producto;
import entity.Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadCsv {

    private String fileCliente = "Ejercicio1/src/main/java/utils/CSV/clientes.csv";
    private String fileProducto = "Ejercicio1/src/main/java/utils/CSV/productos.csv";
    private String fileFactura = "Ejercicio1/src/main/java/utils/CSV/facturas.csv";
    private String fileFactura_Producto = "Ejercicio1/src/main/java/utils/CSV/facturas-productos.csv";

    public LoadCsv() {}

    public List<Cliente> LoadCliente() throws IOException {
        List<Cliente> c = new ArrayList<>();
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(fileCliente));
        for(CSVRecord row: parser) {
            int id =  Integer.parseInt(row.get("idCliente"));
            String nombre = row.get("nombre");
            String email = row.get("email");

            Cliente cliente = new Cliente(id, nombre, email);
            c.add(cliente);
        }
        return c;
    }

    public List<Producto> LoadProducto() throws IOException {
        List<Producto> p = new ArrayList<>();
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(fileProducto));
        for(CSVRecord row: parser) {
            int id =  Integer.parseInt(row.get("idProducto"));
            String nombre = row.get("nombre");
            Float valor = Float.parseFloat(row.get("valor"));

            Producto producto = new Producto(id, nombre, valor);
            p.add(producto);
        }
        return p;
    }

    public List<Factura> LoadFactura() throws IOException {
        List<Factura> f = new ArrayList<>();
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(fileFactura));
        for(CSVRecord row: parser) {
            int idFactura =  Integer.parseInt(row.get("idFactura"));
            int idCliente =  Integer.parseInt(row.get("idCliente"));

            Factura factura = new Factura(idFactura, idCliente);
            f.add(factura);
        }
        return f;
    }

    public List<Factura_Producto> LoadFactura_Producto() throws IOException {
        List<Factura_Producto> f = new ArrayList<>();
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(fileFactura_Producto));
        for(CSVRecord row: parser) {
            int idProducto =  Integer.parseInt(row.get("idProducto"));
            int idFactura =  Integer.parseInt(row.get("idFactura"));
            int cantidad =  Integer.parseInt(row.get("cantidad"));

            Factura_Producto producto = new Factura_Producto(idProducto, idFactura, cantidad);
            f.add(producto);
        }
        return f;
    }
}
