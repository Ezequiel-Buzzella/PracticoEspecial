package repository;

import entity.Carrera;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CarreraRepositoryImp implements Repository<Carrera,Integer>{

    private EntityManager em;

    public CarreraRepositoryImp(EntityManager em) {
        this.em=em;
    }
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
