import dto.ReporteCarreraDTO;
import entity.Alumno;
import entity.AlumnoCarrera;
import entity.Carrera;
import factory.RepositoryFactory;
import utils.LoadCSV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // Creación de los Repository
        RepositoryFactory rf = RepositoryFactory.getInstance();

        LoadCSV loadCSV = new LoadCSV();

        List<Alumno> alumnos = loadCSV.LoadAlumnos();
        List<Carrera> carreras = loadCSV.LoadCarrera();
        List<AlumnoCarrera> AlumnosCarrera = loadCSV.LoadAlumnoCarrera(rf);

        for(Alumno a:alumnos){
            rf.getAlumnoRepository().save(a);
        }

        for(Carrera c:carreras){
            rf.getCarreraRepository().save(c);
        }

        for(AlumnoCarrera ac:AlumnosCarrera){
            rf.getAlumnoCarreraRepository().save(ac);
        }





        // Servicio del punto 3: Reporte ---------------------------------------------------
//        List<Carrera> carreras = rf.getCarreraRepository().getCarrerasOrderByNombre();
//        List<ReporteCarreraDTO> reporteCarreras = new ArrayList<>();
//        for(Carrera c : carreras) {
//            ReporteCarreraDTO reporteCarrera = new ReporteCarreraDTO(
//                    c.getNombre(),
//                    rf.getAlumnoCarreraRepository().getInscriptos(c.getId()),
//                    rf.getAlumnoCarreraRepository().getGraduados(c.getId())
//            );
//            reporteCarreras.add(reporteCarrera);
//        }
//        System.out.println("--- REPORTE DE CARRERAS ---");
//        for (ReporteCarreraDTO rc : reporteCarreras) {
//            System.out.println(rc);
//        }
    }
}