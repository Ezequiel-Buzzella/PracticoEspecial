package entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Factura_Producto {
    private int idProducto;
    private int idFactura;
    private int cantidad;

    public Factura_Producto(int idFactura, int idProducto, int cantidad) {
        this.idProducto = idProducto;
        this.idFactura = idFactura;
        this.cantidad = cantidad;
    }
}
