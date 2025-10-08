package repository.implementacion;

import dto.AlumnoDTO;
import entity.Alumno;
import entity.AlumnoCarrera;
import entity.Carrera;
import entity.IdAlumnoCarrera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

import jakarta.persistence.TypedQuery;
import repository.interfaces.AlumnoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class AlumnoRepositoryImp implements AlumnoRepository {

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
    public List<AlumnoDTO> getAlumnoByCarreraAndCiudad(int idCarrera, String ciudad) {
        String jpql = "SELECT new dto.AlumnoDTO(a.dni, a.nombre, a.apellido, a.edad, a.genero, a.ciudadResidencia, a.lu) " +
                "FROM AlumnoCarrera ac " +
                "JOIN ac.alumno a " +
                "JOIN ac.carrera c " +
                "WHERE c.id = :idCarrera AND a.ciudadResidencia = :ciudad";

        TypedQuery<AlumnoDTO> query = em.createQuery(jpql, AlumnoDTO.class);
        query.setParameter("idCarrera", idCarrera);
        query.setParameter("ciudad", ciudad);

        return query.getResultList();
    }

    @Override
    public AlumnoDTO getAlumnoByLibreta(int lu) {
        String jpql = "SELECT a FROM Alumno a WHERE a.lu = :lu";
        TypedQuery<Alumno> query = em.createQuery(jpql, Alumno.class);
        query.setParameter("lu",lu);
        Alumno a = query.getSingleResult();
        return new AlumnoDTO(a.getDni(),a.getNombre(),a.getApellido(),a.getEdad(),a.getGenero(),a.getCiudadResidencia(),lu);
    }

    @Override
    public List<AlumnoDTO> getAlumnoByGenero(String genero) {
        String jpql = "SELECT a FROM Alumno a WHERE a.genero = :genero";
        TypedQuery<Alumno> query = em.createQuery(jpql, Alumno.class);
        query.setParameter("genero",genero);
        List<Alumno> alumnosGenero = query.getResultList();
        List<AlumnoDTO> alumnosDTO = new ArrayList<>();
        for(Alumno a:alumnosGenero){
            alumnosDTO.add(new AlumnoDTO(a.getDni(),a.getNombre(),a.getApellido(),a.getEdad(),genero,a.getCiudadResidencia(),a.getLu()));

        }
        return alumnosDTO;
    }

    @Override
    public List<AlumnoDTO> getAlumnoByCarrera(int id, String ciudad) {
        String jpql = "SELECT a FROM Alumno a " +
                "JOIN AlumnoCarrera ac ON a.dni = ac.alumno.dni " +
                "JOIN Carrera c ON ac.carrera.id = c.id " +
                "WHERE c.id = :id AND a.ciudadResidencia = :ciudad";
        List<AlumnoDTO> alumnosDTO = new ArrayList<>();
        TypedQuery<Alumno> query = em.createQuery(jpql, Alumno.class);
        query.setParameter("id",id);
        query.setParameter("ciudad",ciudad);
        List<Alumno> alumnosCarrera = query.getResultList();
        for(Alumno a:alumnosCarrera){
            alumnosDTO.add(new AlumnoDTO(id,a.getNombre(),a.getApellido(),a.getEdad(),a.getGenero(),ciudad,a.getLu()));
        }
        return alumnosDTO;
    }

    @Override
    public void matricularAlumnoACarrera(int dni, int idCarrera, int inscripcion, int graduado, int antiguedad) {
        Alumno a = this.getById(dni);
        Carrera c = CarreraRepositoryImp.getInstance(this.em).getById(idCarrera);
        IdAlumnoCarrera IdAC = new IdAlumnoCarrera(a.getDni(), c.getId());
        AlumnoCarrera ac = new AlumnoCarrera(IdAC, a, c, graduado, inscripcion, antiguedad);
        AlumnoCarreraRepositoryImp.getInstance(this.em).save(ac);
    }
}
