public class Administrador extends Usuario {
    private String password;
    private PoliticaReserva politica;

    public Administrador(String id, String nombre, String email, String telefono, String password,
            PoliticaReserva politica) {
        super(id, nombre, email, telefono);
        this.password = password;
        this.politica = politica;
    }

    public void configurarPoliticas(PoliticaReserva politica) {
        this.politica = politica;
    }
}
