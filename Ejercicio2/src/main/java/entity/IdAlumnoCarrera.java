package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class IdAlumnoCarrera  {
    private int dni;
    private int id;

    public IdAlumnoCarrera(int dni, int id) {
        this.dni = dni;
        this.id = id;
    }
}
