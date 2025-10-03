package repository;

import dto.AlumnoDTO;
import entity.Alumno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class AlumnoRepositoryImp implements Repository<Alumno,Integer>,AlumnoRepository{
    @Override
    public List<AlumnoDTO> obtenerAlumnoPorCarreraYCiudad(int idCarrera, String ciudad) {
        return List.of();
    }

    @Override
    public AlumnoDTO obtenerAlumnoPorLibreta(int lu) {
        String jpql = "SELECT a FROM Alumno a WHERE a.lu = :lu";
        TypedQuery<Alumno> query = em.createQuery(jpql, Alumno.class);
        query.setParameter("lu",lu);
        Alumno a = query.getSingleResult();
        return new AlumnoDTO(a);
    }

    @Override
    public List<AlumnoDTO> obtenerAlumnoPorGenero(char genero) {
        String jpql = "SELECT a FROM Alumno a WHERE a.genero = :genero";
        TypedQuery<Alumno> query = em.createQuery(jpql, Alumno.class);
        query.setParameter("genero",genero);
        List<Alumno> alumnosGenero = query.getResultList();
        List<AlumnoDTO> alumnosDTO = new ArrayList<>();
        for(Alumno a:alumnosGenero){
            alumnosDTO.add(new AlumnoDTO(a));

        }
        return alumnosDTO;
    }

    @Override
    public List<AlumnoDTO> obtenerAlumnoPorCarrera(int id) {
        return List.of();
    }

    @Override
    public void matricularAlumnoACarrera(int id, int dni, int carreraId, int inscripcion, int graduacion, int antiguedad) {

    }

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
        TypedQuery<Alumno> query = em.createQuery(jpql,Alumno.class);
        return query.getResultList();
    }

    @Override
    public void save(Alumno alumno) {
        em.getTransaction().begin();
        em.persist(alumno);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {

        Alumno existe = this.getById(id);

        if (existe != null) {
            throw new EntityNotFoundException("Alumno con el id " +  id + " no encontrado");
        }

        em.getTransaction().begin();

        em.remove(existe);
        em.getTransaction().commit();
    }

    @Override
    public void update(Integer id, Alumno nuevo) {

    }
}
