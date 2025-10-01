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
        return em.find(Carrera.class,id);
    }

    @Override
    public List<Carrera> getAll() {
        String query = "select c from Carrera c";
        em.createQuery(query).getResultList();
        List<Carrera> carreras = em.createQuery(query).getResultList();
        return carreras;
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
