package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Alumno {
    @Id
    @Column(nullable = false)
    private int dni;
    @Column(nullable = false)
    private int lu;
    @Column(nullable = false,length = 255)
    private String nombre;
    @Column(nullable = false,length = 255)
    private String apellido;
    @Column(nullable = false)
    private int edad;
    @Column(nullable = false)
    private String genero;
    @Column(nullable = false,length = 255)
    private String ciudadResidencia;
    @OneToMany (mappedBy = "alumno")
    private List<AlumnoCarrera> carreras;

    public Alumno() {

    }

    public Alumno(int dni,int lu,String nombre,String apellido,int edad,String genero,String ciudadResidencia) {
        this.dni = dni;
        this.lu = lu;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.ciudadResidencia = ciudadResidencia;
        this.carreras = new ArrayList<>();
    }
}
