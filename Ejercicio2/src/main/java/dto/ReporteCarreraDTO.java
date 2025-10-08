package dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReporteCarreraDTO {
    private String nombreCarrera;
    private List<AlumnoDTO> inscriptos;
    private List<AlumnoDTO> graduados;

    public ReporteCarreraDTO(String nombreCarrera, List<AlumnoDTO> inscriptos, List<AlumnoDTO> graduados) {
        this.nombreCarrera = nombreCarrera;
        this.inscriptos = inscriptos;
        this.graduados = graduados;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Carrera: ").append(this.getNombreCarrera()).append("\n");

        sb.append("Inscriptos:\n");
        for (AlumnoDTO a : inscriptos) {
            sb.append("  - ").append(a.toString()).append("\n");
        }

        sb.append("Graduados:\n");
        for (AlumnoDTO a : graduados) {
            sb.append("  - ").append(a.toString()).append("\n");
        }
        return sb.toString();
    }
}
