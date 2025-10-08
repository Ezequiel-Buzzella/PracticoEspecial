package repository;

import dto.AlumnoDTO;
import entity.AlumnoCarrera;
import entity.IdAlumnoCarrera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AlumnoCarreraRepositoryImp implements Repository<AlumnoCarrera, IdAlumnoCarrera>, AlumnoCarreraRepository{

    private static AlumnoCarreraRepositoryImp instance;
    private EntityManager em;

    public static AlumnoCarreraRepositoryImp getInstance(EntityManager em) {
        if (instance == null) {
            instance = new AlumnoCarreraRepositoryImp(em);
        }
        return instance;
    }

    private AlumnoCarreraRepositoryImp(EntityManager em) {
        this.em = em;
    }

    @Override
    public AlumnoCarrera getById(IdAlumnoCarrera id) {
        return em.find(AlumnoCarrera.class, id);
    }

    /* GetById con DTO
    @Override
    public AlumnoCarreraDTO getById(IdAlumnoCarrera id) {
        return em.createQuery(
                        "SELECT new AlumnoCarreraDTO(a.nombre, c.nombre, ac.fechaInscripcion) " +
                                "FROM AlumnoCarrera ac " +
                                "JOIN ac.alumno a " +
                                "JOIN ac.carrera c " +
                                "WHERE ac.id = :id", AlumnoCarreraDTO.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    */

    @Override
    public List<AlumnoCarrera> getAll() {
        return em.createQuery("SELECT ac FROM AlumnoCarrera ac", AlumnoCarrera.class).getResultList();
    }

    /* GetAll con DTO
    @Override
    public List<AlumnoCarreraDTO> getAll() {
        return em.createQuery(
                        "SELECT new AlumnoCarreraDTO(a.nombre, c.nombre, ac.fechaInscripcion) " +
                                "FROM AlumnoCarrera ac " +
                                "JOIN ac.alumno a " +
                                "JOIN ac.carrera c", AlumnoCarreraDTO.class)
                .getResultList();
    }
    */

    @Override
    public void save(AlumnoCarrera alumnoCarrera) {

        AlumnoCarrera registro = this.getById(alumnoCarrera.getId());
        if(registro != null) {
            throw new RuntimeException("Ya existe un registro con id " + alumnoCarrera.getId() + " cargado en la base de datos");
        }

        em.getTransaction().begin();
        em.persist(alumnoCarrera);
        em.getTransaction().commit();
    }

    @Override
    public void delete(IdAlumnoCarrera id) {

        AlumnoCarrera registro = this.getById(id);
        if (registro == null) {
            throw new EntityNotFoundException("No se encontró AlumnoCarrera con ID: " + id);
        }

        em.getTransaction().begin();
        em.remove(registro);
        em.getTransaction().commit();
    }

    @Override
    public void update(IdAlumnoCarrera id, AlumnoCarrera nuevo) {

        AlumnoCarrera registro = this.getById(id);
        if (registro == null) {
            throw new EntityNotFoundException("No se encontró AlumnoCarrera con ID: " + id);
        }

        em.getTransaction().begin();
        registro.setFechaInscripcion(nuevo.getFechaInscripcion());
        registro.setGraduado(nuevo.isGraduado());
        em.getTransaction().commit();
    }

    @Override
    public List<AlumnoDTO> getInscriptos(int idCarrera) {
        String jpql = "SELECT new dto.AlumnoDTO(a.dni, a.nombre, a.apellido, a.fechaNacimiento, a.genero, a.ciudadResidencia, a.lu) " +
                "FROM AlumnoCarrera ac JOIN ac.alumno a " +
                "WHERE ac.carrera.id = :idCarrera AND ac.graduado = false " +
                "ORDER BY ac.fechaInscripcion ASC";
        TypedQuery<AlumnoDTO> query = em.createQuery(jpql, AlumnoDTO.class);
        query.setParameter("idCarrera", idCarrera);
        return query.getResultList();
    }

    @Override
    public List<AlumnoDTO> getGraduados(int idCarrera) {
        String jpql = "SELECT new dto.AlumnoDTO(a.dni, a.nombre, a.apellido, a.fechaNacimiento, a.genero, a.ciudadResidencia, a.lu) " +
                "FROM AlumnoCarrera ac JOIN ac.alumno a " +
                "WHERE ac.carrera.id = :idCarrera AND ac.graduado = true " +
                "ORDER BY ac.fechaInscripcion ASC";
        TypedQuery<AlumnoDTO> query = em.createQuery(jpql, AlumnoDTO.class);
        query.setParameter("idCarrera", idCarrera);
        return query.getResultList();
    }
}