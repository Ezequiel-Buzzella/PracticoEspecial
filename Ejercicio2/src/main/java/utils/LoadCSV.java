package utils;

import entity.Alumno;
import entity.AlumnoCarrera;
import entity.Carrera;
import entity.IdAlumnoCarrera;
import factory.RepositoryFactory;
import jakarta.persistence.EntityManager;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import repository.implementacion.AlumnoRepositoryImp;
import repository.implementacion.CarreraRepositoryImp;
import repository.interfaces.AlumnoRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadCSV {

    String fileCarrera = "Ejercicio2/src/main/java/utils/CSV/carreras.csv";
    String fileEstudianteCarrera = "Ejercicio2/src/main/java/utils/CSV/estudianteCarrera.csv";
    String fileEstudiantes = "Ejercicio2/src/main/java/utils/CSV/estudiantes.csv";

    public LoadCSV(){}

    public List<Carrera> LoadCarrera() throws IOException {
        List<Carrera> carreras = new ArrayList<>();
        CSVParser csvParser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(fileCarrera));
        for(CSVRecord row: csvParser) {
            int id = Integer.parseInt(row.get("id_carrera"));
            String nombre = row.get("carrera");
            int duracion = Integer.parseInt(row.get("duracion"));
            //corrregir constructor carrera...
            Carrera carrera = new Carrera(id, nombre, duracion);

            carreras.add(carrera);
        }
        return carreras;
    }

    public List<Alumno> LoadAlumnos() throws IOException {
        List<Alumno> alumnos = new ArrayList<>();
        CSVParser csvParser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(fileEstudiantes));
        for(CSVRecord row: csvParser) {
            int dni = Integer.parseInt(row.get("DNI"));
            int lu = Integer.parseInt(row.get("LU"));
            String nombre = row.get("nombre");
            String apellido = row.get("apellido");
            int edad = Integer.parseInt(row.get("edad"));
            String genero = row.get("genero");
            String ciudad = row.get("ciudad");
            //corrregir constructor alumno...
            Alumno a = new Alumno(dni, lu, nombre, apellido, edad, genero, ciudad);
            alumnos.add(a);
        }
        return alumnos;
    }

    public List<AlumnoCarrera> LoadAlumnoCarrera(RepositoryFactory rf) throws IOException {
        List<AlumnoCarrera> ac = new ArrayList<>();
        CSVParser csvParser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(fileEstudianteCarrera));
        for(CSVRecord row: csvParser) {
            //int id = Integer.parseInt(row.get("id_carrera"));
            int id_alumno = Integer.parseInt(row.get("id_estudiante"));
            int id_carrera = Integer.parseInt(row.get("id_carrera"));
            int graduacion = Integer.parseInt(row.get("graduacion"));
            int inscripcion = Integer.parseInt(row.get("inscripcion"));
            int antiguedad = Integer.parseInt(row.get("antiguedad"));
            //Alumno a = em.getReference(Alumno.class, id_alumno);
            Alumno a = rf.getAlumnoRepository().getById(id_alumno);
            Carrera c =rf.getCarreraRepository().getById(id_carrera);
            IdAlumnoCarrera IdAC = new IdAlumnoCarrera(a.getDni(), c.getId());
            AlumnoCarrera salida = new AlumnoCarrera(IdAC, a, c, graduacion, inscripcion, antiguedad);
            ac.add(salida);
        }
        return ac;
    }

}
