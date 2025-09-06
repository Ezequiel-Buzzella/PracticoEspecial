package org.example.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
public class Cliente {
    private int idCliente;
    private String nombre;
    private String email;
    private Float totalFacturado;

    public Cliente(int idCliente, String nombre, String email) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
    }


    public Cliente(String nombreCliente, float totalFacturado) {
        this.nombre=nombreCliente;
        this.totalFacturado=totalFacturado;
    }
}
