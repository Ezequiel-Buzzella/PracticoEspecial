package repository;

import entity.Alumno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.sql.PreparedStatement;
import java.util.List;

public class AlumnoRepositoryImp implements Repository<Alumno,Integer>{

    private EntityManager em;

    public AlumnoRepositoryImp(EntityManager em) {
        this.em = em;
    }
    @Override
    public Alumno getById(Integer id) {
        return em.find(Alumno.class,id);
    }

    @Override
    public List<Alumno> getAll() {
        String jpql = "SELECT a FROM Alumno a";
        Query query = em.createQuery(jpql);
        List<Alumno> alumnos = query.getResultList();
        return alumnos;
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
