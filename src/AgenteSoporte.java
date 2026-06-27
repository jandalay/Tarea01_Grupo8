import java.util.PriorityQueue;

import enums.EstadoHabitacion;

public class AgenteSoporte extends Usuario {
    
    private String password;
    private String tipoOperador;
    private Reservable tipoProblema;
    private PriorityQueue<Incidente> incidentes;
    
    
    public AgenteSoporte(String nombre, String email, String telefono, String password) {
        super(nombre, email, telefono);
        this.password = password;
        incidentes = new PriorityQueue<>();
    }

    public void gestionarIncidente(Incidente incidente) {
        this.incidentes.add(incidente);
    }

    public void escalaHotel(Hospedaje hotel) {
        tipoProblema = hotel;
        tipoOperador = "Operador de hotel";
    }

    public void escalaPaseo(PaseoTuristico paseo) {
        tipoProblema = paseo;
        tipoOperador = "Operador turístico";
    }

}
