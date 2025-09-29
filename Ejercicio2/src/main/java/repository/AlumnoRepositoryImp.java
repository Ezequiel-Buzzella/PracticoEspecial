package repository;

import entity.Alumno;

import java.util.List;

public class AlumnoRepositoryImp implements Repository<Alumno,Integer>{
    @Override
    public Alumno getById(Integer id) {
        return null;
    }

    @Override
    public List<Alumno> getAll() {
        return List.of();
    }

    @Override
    public void save(Alumno alumno) {

    }

    @Override
    public void delete(Alumno alumno) {

    }

    @Override
    public void update(Integer id, Alumno nuevo) {

    }
}
