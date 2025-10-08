import dto.ReporteCarreraDTO;
import entity.Carrera;
import factory.RepositoryFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Creación de los Repository
        RepositoryFactory rf = RepositoryFactory.getInstance();
        
        // Servicio del punto 3: Reporte ---------------------------------------------------
        List<Carrera> carreras = rf.getCarreraRepository().getCarrerasOrderByNombre();
        List<ReporteCarreraDTO> reporteCarreras = new ArrayList<>();

        for(Carrera c : carreras) {
            ReporteCarreraDTO reporteCarrera = new ReporteCarreraDTO(
                    c.getNombre(),
                    rf.getAlumnoCarreraRepository().getInscriptos(c.getId()),
                    rf.getAlumnoCarreraRepository().getGraduados(c.getId())
            );
            reporteCarreras.add(reporteCarrera);
        }
        System.out.println("--- REPORTE DE CARRERAS ---");
        for (ReporteCarreraDTO rc : reporteCarreras) {
            System.out.println(rc);
        }
    }
}