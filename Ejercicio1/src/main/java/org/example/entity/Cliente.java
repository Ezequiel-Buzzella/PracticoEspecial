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
}
