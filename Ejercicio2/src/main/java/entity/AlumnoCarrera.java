package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class AlumnoCarrera {
    @EmbeddedId
    private IdAlumnoCarrera id;
    @Column(nullable = false)
    private int graduado;
    @Column(nullable = false)
    private int inscripcion;
    @Column(nullable = false)
    private int antiguedad;
    @ManyToOne
    @MapsId("id")
    private Carrera carrera;
    @ManyToOne
    @MapsId("dni")
    private Alumno alumno;

    public AlumnoCarrera(IdAlumnoCarrera id, Alumno a, Carrera c, int graduado, int inscripcion, int antiguedad) {
        this.id = id;
        this.alumno = a;
        this.carrera = c;
        this.graduado = graduado;
        this.inscripcion = inscripcion;
        this.antiguedad = antiguedad;
        int idCarrera = carrera.getId();
        int idAlumno = alumno.getDni();
        this.id = new IdAlumnoCarrera(idAlumno, idCarrera);
    }

    public AlumnoCarrera() {

    }
}
