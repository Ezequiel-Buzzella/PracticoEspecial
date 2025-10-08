package repository;

import dto.AlumnoDTO;
import entity.Alumno;
import entity.AlumnoCarrera;
import entity.Carrera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class AlumnoRepositoryImp implements Repository<Alumno,Integer>,AlumnoRepository{

    private static AlumnoRepositoryImp instance;
    private EntityManager em;

    public static AlumnoRepositoryImp getInstance(EntityManager em) {
        if (instance == null) {
            instance = new AlumnoRepositoryImp(em);
        }
        return instance;
    }

    private AlumnoRepositoryImp(EntityManager em) {
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
        Alumno registro = this.getById(alumno.getDni());

        if(registro != null){
            throw new RuntimeException("Ya existe un registro con id " + alumno.getDni());
        }

        em.getTransaction().begin();
        em.persist(alumno);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {

        Alumno registro = this.getById(id);

        if (registro == null) {
            throw new EntityNotFoundException("Alumno con el id " +  id + " no encontrado");
        }

        em.getTransaction().begin();
        em.remove(registro);
        em.getTransaction().commit();
    }

    @Override
    public void update(Integer id, Alumno nuevo) {

    }

    @Override
    public List<AlumnoDTO> obtenerAlumnoPorCarreraYCiudad(int idCarrera, String ciudad) {
        return null;
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
    public List<AlumnoDTO> obtenerAlumnoPorCarrera(int id, String ciudad) {
        String jpql = "SELECT a FROM Alumno a " +
                "JOIN AlumnoCarrera ac ON a.dni = ac.idAlumno " +
                "JOIN Carrera c ON ac.idCarrera = c.id " +
                "WHERE c.id = :id AND a.ciudadResidencia = :ciudad";
        List<AlumnoDTO> alumnosDTO = new ArrayList<>();
        TypedQuery<Alumno> query = em.createQuery(jpql, Alumno.class);
        query.setParameter("id",id);
        query.setParameter("ciudad",ciudad);
        List<Alumno> alumnosCarrera = query.getResultList();
        for(Alumno a:alumnosCarrera){
            alumnosDTO.add(new AlumnoDTO(a));
        }
        return alumnosDTO;
    }

    @Override
    public void matricularAlumnoACarrera(int dni, int idCarrera, Date inscripcion, boolean graduado) {
        Alumno a = this.getById(dni);
        Carrera c = CarreraRepositoryImp.getInstance(this.em).getById(idCarrera);
        AlumnoCarrera ac = new AlumnoCarrera(a, c, graduado, inscripcion);
        AlumnoCarreraRepositoryImp.getInstance(this.em).save(ac);
    }
}
