import dto.ReporteCarreraDTO;
import entity.Alumno;
import entity.AlumnoCarrera;
import entity.Carrera;
import factory.RepositoryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repository.implementacion.AlumnoCarreraRepositoryImp;
import repository.implementacion.AlumnoRepositoryImp;
import repository.implementacion.CarreraRepositoryImp;
import repository.interfaces.AlumnoRepository;
import utils.LoadCSV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // Creación de los Repository
        RepositoryFactory rf = RepositoryFactory.getInstance();
        /*LoadCSV loadCSV = new LoadCSV();

        List<Alumno> alumnos = loadCSV.LoadAlumnos();
        List<Carrera> carreras = loadCSV.LoadCarrera();


        for(Alumno a:alumnos){
           rf.getAlumnoRepository().save(a);
        }

        for(Carrera c:carreras){
            rf.getCarreraRepository().save(c);
        }

        List<AlumnoCarrera> AlumnosCarrera = loadCSV.LoadAlumnoCarrera(rf);
        for(AlumnoCarrera ac:AlumnosCarrera){
            rf.getAlumnoCarreraRepository().save(ac);
        }*/

        //Servicio 1 : Dar de alta un estudaimte

        //Alumno a = new Alumno(7777777,55555,"Jose","Jose",23,"Masculino","tandil");
        //rf.getAlumnoRepository().save(a);

        //Servicio 2 : Matricular un estudiante

        //rf.getAlumnoRepository().matricularAlumnoACarrera(7777777,2,2024,0,1);

        //Servicio 3:Recuperar todos los alumnos
        //System.out.println(rf.getAlumnoRepository().getAll());

        //Servicio 4 :
        //System.out.println(rf.getAlumnoRepository().getAlumnoByLibreta(55555));

        //Servicio 5:

            //System.out.println(rf.getAlumnoRepository().getAlumnoByGenero("Male"));

        //Servicio 6: Obtener carreras ordenada por cantidad de alumnos

        //System.out.println(rf.getCarreraRepository().getCarreraOrderByCantAlumnos());

        //Servicio 7: Obtener alumno por carrera y ciudad

        //System.out.println(rf.getAlumnoRepository().getAlumnoByCarreraAndCiudad(2,"tandil"));



        /* //Servicio del punto 3: Reporte ---------------------------------------------------
       List<Carrera> carreras = rf.getCarreraRepository().getCarrerasOrderByNombre();
        List<ReporteCarreraDTO> reporteCarreras = new ArrayList<>();
        for(Carrera c : carreras) {
            ReporteCarreraDTO reporteCarrera = new ReporteCarreraDTO(
                    c.getNombre(),
                    rf.getAlumnoCarreraRepository().getInscriptos(c.getId()),
                    rf.getAlumnoCarreraRepository().getGraduados(c.getId())
            );
            reporteCarreras.add(reporteCarrera);}
        System.out.println("--- REPORTE DE CARRERAS ---");
        for (ReporteCarreraDTO rc : reporteCarreras) {
            System.out.println(rc);
        }*/
    }
}