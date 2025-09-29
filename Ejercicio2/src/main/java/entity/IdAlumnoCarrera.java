package entity;

@Getter
@Setter
public class IdAlumnoCarrera {
    private int idAlumno;
    private int idCarrera;

    public IdAlumnoCarrera(int idAlumno, int idCarrera) {
        this.idAlumno = idAlumno;
        this.idCarrera = idCarrera;
    }
}
