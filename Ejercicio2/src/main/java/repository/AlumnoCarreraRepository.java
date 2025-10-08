package repository;

import dto.AlumnoDTO;
import entity.AlumnoCarrera;
import entity.IdAlumnoCarrera;

import java.util.List;

public interface AlumnoCarreraRepository extends Repository<AlumnoCarrera, IdAlumnoCarrera>{
    List<AlumnoDTO> getInscriptos(int idCarrera);
    List<AlumnoDTO> getGraduados(int idCarrera);
}
