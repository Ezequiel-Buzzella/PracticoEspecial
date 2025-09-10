package dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductoDTO {

    private String nombre;
    private float valor;
    private int cantidadVendida;
    private float recaudacion;

    public ProductoDTO(String nombre, float valor, int cantidadVendida, float recaudacion) {
        this.nombre = nombre;
        this.valor = valor;
        this.cantidadVendida = cantidadVendida;
        this.recaudacion = recaudacion;
    }

}
