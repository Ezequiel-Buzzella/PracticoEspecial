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
    @Id
    private IdAlumnoCarrera id;
    @Column(nullable = false)
    private boolean graduado;
    @Column(nullable = false)
    private Date fechaInscripcion;
    @ManyToOne
    private Carrera carrera;
    @ManyToOne
    private Alumno alumno;

    public AlumnoCarrera(IdAlumnoCarrera id, boolean graduado, Date fechaInscripcion, Carrera carrera, Alumno alumno) {
        this.id = id;
        this.graduado = graduado;
        this.fechaInscripcion = fechaInscripcion;
        this.carrera = carrera;
        this.alumno = alumno;
    }
}
