package repository.interfaces;
import dto.AlumnoDTO;
import entity.Alumno;

import java.util.Date;
import java.util.List;

public interface AlumnoRepository extends Repository<Alumno,Integer> {
    List<AlumnoDTO> getAlumnoByCarreraAndCiudad(int idCarrera, String ciudad);
    AlumnoDTO getAlumnoByLibreta(int lu);
    List<AlumnoDTO> getAlumnoByGenero(String genero);
    List<AlumnoDTO> getAlumnoByCarrera(int id, String ciudad);
    void matricularAlumnoACarrera(int dni, int carreraId, int inscripcion, int graduacion, int antiguedad);
}
