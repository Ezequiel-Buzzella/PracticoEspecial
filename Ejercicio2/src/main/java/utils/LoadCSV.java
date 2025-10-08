package utils;

import entity.Alumno;
import entity.AlumnoCarrera;
import entity.Carrera;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadCSV {

    String fileCarrera = "";
    String fileEstudianteCarrera = "";
    String fileEstudiantes = "";

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

    public List<AlumnoCarrera> LoadAlumnoCarrera() throws IOException {
        List<AlumnoCarrera> ac = new ArrayList<>();
        CSVParser csvParser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(fileEstudianteCarrera));
        for(CSVRecord row: csvParser) {
            int id = Integer.parseInt(row.get("id_carrera"));
            int id_alumno = Integer.parseInt(row.get("id_estudiante"));
            int id_carrera = Integer.parseInt(row.get("id_carrera"));
            int inscripcion = Integer.parseInt(row.get("inscripcion"));
            int graduacion = Integer.parseInt(row.get("graduacion"));
            int antiguedad = Integer.parseInt(row.get("antiguedad"));
            //corrregir constructor alumnoCarrera...
            AlumnoCarrera salida = new AlumnoCarrera(/*atributos del csv*/);

            ac.add(salida);
        }
        return ac;
    }

    public List<Alumno> LoadAlumnos() throws IOException {
        List<Alumno> alumnos = new ArrayList<>();
        CSVParser csvParser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(fileEstudiantes));
        for(CSVRecord row: csvParser) {
            int dni = Integer.parseInt(row.get("DNI"));
            String nombre = row.get("nombre");
            String apellido = row.get("apellido");
            int edad = Integer.parseInt(row.get("edad"));
            String genero = row.get("genero");
            String ciudad = row.get("ciudad");
            int lu = Integer.parseInt(row.get("LU"));
            //corrregir constructor alumno...
            Alumno a = new Alumno();

            alumnos.add(a);
        }
        return alumnos;
    }

}
