package dto;

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

    public  AlumnoDTO() {
    }

    public AlumnoDTO(int dni, String nombre, String apellido, char genero, String ciudad, int lu) {

    }
}
