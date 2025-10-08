package repository.implementacion;

import dto.CarreraDTO;
import entity.Carrera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.interfaces.CarreraRepository;

import java.util.ArrayList;
import java.util.List;

public class CarreraRepositoryImp implements CarreraRepository {

    private EntityManager em;
    private static CarreraRepositoryImp instance;

    public CarreraRepositoryImp(EntityManager em) {
        this.em=em;
    }

    public static CarreraRepositoryImp getInstance(EntityManager em) {

        if (instance == null) {
            instance = new CarreraRepositoryImp(em);
        }
        return instance;

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
        Carrera registro = this.getById(carrera.getId());
        if(registro==null){
            throw new RuntimeException("Ya existe un registro con id " + carrera.getId());
        }

        em.getTransaction().begin();
        em.persist(carrera);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        Carrera registro = this.getById(id);
        if(registro==null){
            throw new RuntimeException("Carrera con la id  " + id + " no encontrada");
        }
        em.getTransaction().begin();
        em.remove(registro);
        em.getTransaction().commit();
    }

    @Override
    public void update(Integer id, Carrera nuevo) {
        Carrera registro = this.getById(nuevo.getId());
        if(registro==null){
            throw new RuntimeException("Carrera con la id  " + registro.getId() + " no encontrada");
        }
        em.getTransaction().begin();
        registro.setNombre(nuevo.getNombre());
        registro.setInscriptos(nuevo.getInscriptos());
        registro.setDuracion(nuevo.getDuracion());
        em.getTransaction().commit();
    }

    @Override
    public List<CarreraDTO> getCarreraOrderByCantAlumnos(){
        List<CarreraDTO> carreras = new ArrayList<>();
        String jpql = "SELECT c FROM Carrera c WHERE SIZE(c.inscriptos) > 0 ORDER BY SIZE(c.inscriptos) DESC";
        TypedQuery<Carrera> query = em.createQuery(jpql, Carrera.class);
        List<Carrera> list = query.getResultList();
        for (Carrera carrera : list) {
            carreras.add(new CarreraDTO(carrera));
        }
        return carreras;
    }

    @Override
    public List<Carrera> getCarrerasOrderByNombre() {
        String jpql = "SELECT c FROM Carrera c ORDER BY c.nombre ASC";
        TypedQuery<Carrera> query = em.createQuery(jpql, Carrera.class);
        return query.getResultList();
    }
}
