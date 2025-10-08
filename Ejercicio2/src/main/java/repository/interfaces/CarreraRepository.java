package repository.interfaces;

import dto.CarreraDTO;
import entity.Carrera;

import java.util.List;

public interface CarreraRepository extends Repository<Carrera,Integer> {
    List<CarreraDTO> getCarreraOrderByCantAlumnos();
    List<Carrera> getCarrerasOrderByNombre();
}
