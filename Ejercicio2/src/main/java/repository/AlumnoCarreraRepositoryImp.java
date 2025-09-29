package repository;

import entity.AlumnoCarrera;
import entity.IdAlumnoCarrera;

import java.util.List;

public class AlumnoCarreraRepositoryImp implements Repository<AlumnoCarrera, IdAlumnoCarrera>{

    @Override
    public AlumnoCarrera getById(IdAlumnoCarrera id) {
        return null;
    }

    @Override
    public List<AlumnoCarrera> getAll() {
        return List.of();
    }

    @Override
    public void save(AlumnoCarrera alumnoCarrera) {

    }

    @Override
    public void delete(AlumnoCarrera alumnoCarrera) {

    }

    @Override
    public void update(IdAlumnoCarrera id, AlumnoCarrera nuevo) {

    }
}
