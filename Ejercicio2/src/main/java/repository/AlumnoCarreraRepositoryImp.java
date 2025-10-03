package repository;

import entity.AlumnoCarrera;
import entity.IdAlumnoCarrera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class AlumnoCarreraRepositoryImp implements Repository<AlumnoCarrera, IdAlumnoCarrera>{

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
}
