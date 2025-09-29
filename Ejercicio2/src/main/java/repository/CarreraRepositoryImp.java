package repository;

import entity.Carrera;

import java.util.List;

public class CarreraRepositoryImp implements Repository<Carrera,Integer>{
    @Override
    public Carrera getById(Integer id) {
        return null;
    }

    @Override
    public List<Carrera> getAll() {
        return List.of();
    }

    @Override
    public void save(Carrera carrera) {

    }

    @Override
    public void delete(Carrera carrera) {

    }

    @Override
    public void update(Integer id, Carrera nuevo) {

    }
}
