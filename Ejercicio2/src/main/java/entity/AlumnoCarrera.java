package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.Date;

@Entity
public class AlumnoCarrera {
    @Id
    private IdAlumnoCarrera id;
    @Column(nullable = false)
    private boolean graduado;
    @Column(nullable = false)
    private Date fechaInscripcion;
}
