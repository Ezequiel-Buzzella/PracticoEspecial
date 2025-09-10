package dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ClienteDTO {
    private String nombre;
    private Float totalFacturado;

    public ClienteDTO(String nombreCliente, float totalFacturado) {
        this.nombre=nombreCliente;
        this.totalFacturado=totalFacturado;
    }
}
