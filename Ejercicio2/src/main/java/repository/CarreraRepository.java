package repository;

import entity.Carrera;

import java.util.List;

public interface CarreraRepository extends Repository<Carrera,Integer> {
    List<Carrera> getCarrerasOrderByNombre();
}
