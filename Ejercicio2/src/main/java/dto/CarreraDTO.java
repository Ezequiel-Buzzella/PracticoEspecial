package dto;

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
}
