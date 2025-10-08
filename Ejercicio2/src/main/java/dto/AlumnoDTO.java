package dto;

import entity.Alumno;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class AlumnoDTO {
    private int dni;
    private String nombre;
    private String apellido;
    private Date fechanacimiento;
    private char genero;
    private String ciudad;
    private int lu;

    public AlumnoDTO(int dni, String nombre, String apellido, Date fechanacimiento, char genero, String ciudad, int lu) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechanacimiento = fechanacimiento;
        this.genero = genero;
        this.ciudad = ciudad;
        this.lu = lu;
    }

    public AlumnoDTO(Alumno alumno) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.ciudad = ciudad;
        this.lu = lu;
    }
}
