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
    @GeneratedValue
    private int id;
    @Column(nullable = false,length = 255)
    private String nombre;
    @OneToMany (mappedBy = "carrera")
    private List<AlumnoCarrera> inscriptos;

    public Carrera() {

    }

    public Carrera(int id,String nombre) {
        this.id  = id;
        this.nombre = nombre;
        this.inscriptos = new ArrayList<>();
    }

}
