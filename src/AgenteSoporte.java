import java.util.PriorityQueue;
import composite.Hospedaje;
import composite.PaseoTuristico;
import composite.Reservable;
import composite.Usuario;

public class AgenteSoporte extends Usuario {
    private String password;
    private String tipoOperador;
    private Reservable tipoProblema;
    private PriorityQueue<Incidente> incidentes;

    public AgenteSoporte(String id, String nombre, String email, String telefono, String password) {
        super(id, nombre, email, telefono);
        this.password = password;
        this.incidentes = new PriorityQueue<>();
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