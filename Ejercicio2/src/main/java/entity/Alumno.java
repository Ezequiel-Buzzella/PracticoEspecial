package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Alumno {
    @Id
    @GeneratedValue
    private int dni;
    @Column(nullable = false)
    private int lu;
    @Column(nullable = false,length = 255)
    private String nombre;
    @Column(nullable = false,length = 255)
    private String apellido;
    @Column(nullable = false)
    private Date fechaNacimiento;
    @Column(nullable = false)
    private char genero;
    @Column(nullable = false,length = 255)
    private String ciudadResidencia;

    public Alumno() {

    }

    public Alumno(int dni,int lu,String nombre,String apellido,Date fechaNacimiento,String ciudadResidencia) {
        this.dni = dni;
        this.lu = lu;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.ciudadResidencia = ciudadResidencia;
    }
}
