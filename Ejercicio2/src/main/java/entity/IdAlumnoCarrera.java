package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IdAlumnoCarrera {
    private int idAlumno;
    private int idCarrera;

    public IdAlumnoCarrera(int idAlumno, int idCarrera) {
        this.idAlumno = idAlumno;
        this.idCarrera = idCarrera;
    }
}
