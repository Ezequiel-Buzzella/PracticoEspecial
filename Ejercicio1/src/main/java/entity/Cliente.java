package entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import utils.LoadCsv;


@Getter
@Setter
@ToString
public class Cliente {
    private int idCliente;
    private String nombre;
    private String email;

    public Cliente(int idCliente, String nombre, String email) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
    }

}
