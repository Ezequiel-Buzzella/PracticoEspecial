package repository;

import entity.Alumno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class AlumnoRepositoryImp implements Repository<Alumno,Integer>{

    private EntityManager em;

    public AlumnoRepositoryImp(EntityManager em) {
        this.em = em;
    }
    @Override
    public Alumno getById(Integer id) {
        String jpql = "SELECT a FROM Alumno a WHERE a.id = ?";
        Query query = em.createQuery(jpql);
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
