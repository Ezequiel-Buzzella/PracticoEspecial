package factory;

import entity.Alumno;
import entity.AlumnoCarrera;
import entity.Carrera;
import entity.IdAlumnoCarrera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repository.AlumnoRepositoryImp;
import repository.CarreraRepositoryImp;
import repository.AlumnoCarreraRepositoryImp;
import repository.Repository;

public class RepositoryFactory {

    private static RepositoryFactory instance;

    private EntityManager em;
    private EntityManagerFactory emf;

    public static RepositoryFactory getInstance() {
        if(instance == null) {
            instance = new RepositoryFactory();
        }
        return instance;
    }

    private RepositoryFactory() {
        this.emf = Persistence.createEntityManagerFactory("MySQLPersistence");
        this.em = emf.createEntityManager();
    }

    public Repository<Carrera, Integer> getCarreraRepository() {
        return CarreraRepositoryImp.getInstance(this.em);
    }

    public Repository<Alumno, Integer> getAlumnoRepository() {
        return AlumnoRepositoryImp.getInstance(this.em);
    }

    public Repository<AlumnoCarrera, IdAlumnoCarrera> getAlumnoCarreraRepository() {
        return AlumnoCarreraRepositoryImp.getInstance(this.em);
    }
}
