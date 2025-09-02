package org.example.entity;


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
}
