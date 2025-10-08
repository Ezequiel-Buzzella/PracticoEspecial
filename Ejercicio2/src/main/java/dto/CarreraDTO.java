package dto;

import entity.Carrera;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarreraDTO {
    private String nombre;
    private int duracion;
    private int cantInscriptos;

    public CarreraDTO(String nombre, int duracion, int cantInscriptos) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.cantInscriptos = cantInscriptos;
    }

    public CarreraDTO() {

    }
}
