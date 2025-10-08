package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Carrera {
    @Id
    private int id;
    @Column(nullable = false,length = 255)
    private String nombre;
    @Column(nullable = false)
    private int duracion;
    @OneToMany (mappedBy = "carrera")
    private List<AlumnoCarrera> inscriptos;



    public Carrera(int id,String nombre, int duracion) {
        this.id  = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.inscriptos = new ArrayList<>();
    }

    public Carrera() {

    }
}
