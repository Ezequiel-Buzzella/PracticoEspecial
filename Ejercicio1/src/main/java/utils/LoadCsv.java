package utils;

import factory.MysqlDAOFactory;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.example.dao.MySQL.ProductoDAOMySQL;
import org.example.dao.interfaces.ProductoDAO;
import org.example.entity.Producto;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class LoadCsv {

    private String fileCliente = "src/utils/cliente.csv";
    private String fileProducto = "src/utils/producto.csv";
    private String fileFactura = "src/utils/factura.csv";
    private String fileFactura_Producto = "src/utils/factura_producto.csv";



}
