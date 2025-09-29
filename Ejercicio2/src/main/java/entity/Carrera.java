package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    public Carrera() {

    }

    public Carrera(int id,String nombre) {
        this.id=id;
        this.nombre=nombre;
    }

}
