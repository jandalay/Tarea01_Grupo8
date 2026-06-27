public class AgenteSoporte extends Usuario {
    private String password;
    private String tipoOperador;
    private Reservable tipoProblema;
    private Incidente incidente;

    public void gestionarIncidente(Incidente incidente) {
        this.incidente = incidente;
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
