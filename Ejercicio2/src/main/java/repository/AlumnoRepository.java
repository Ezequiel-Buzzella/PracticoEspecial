package repository;
import dto.AlumnoDTO;
import entity.Alumno;

import java.util.List;

public interface AlumnoRepository extends Repository<Alumno,Integer>{
    List<AlumnoDTO> obtenerAlumnoPorCarreraYCiudad(int idCarrera, String ciudad);
    AlumnoDTO obtenerAlumnoPorLibreta(int lu);
    List<AlumnoDTO> obtenerAlumnoPorGenero(char genero);
    List<AlumnoDTO> obtenerAlumnoPorCarrera(int id);
    void matricularAlumnoACarrera(int id,int dni,int carreraId,int inscripcion,int graduacion,int antiguedad);



}
