package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Factura_ProductoId {
    int idFactura;
    int idProducto;

    public Factura_ProductoId(int idFactura, int idProducto) {
        this.idFactura = idFactura;
        this.idProducto = idProducto;
    }
}