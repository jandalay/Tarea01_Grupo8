public class Administrador extends Usuario {
    private String password;
    private PoliticaReserva politica;

    public void configurarPoliticas(PoliticaReserva politica) {
        this.politica = politica;
    }
}
