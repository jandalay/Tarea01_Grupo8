import enums.EstadoPaseo;

public class PaseoTuristico implements Reservable{
    private String id;
    private String nombre;
    private int plazasDisponibles;
    private EstadoPaseo estado;
    private double precio;

    @Override
    public double calcularPrecio() {
        return precio;
    }

    @Override
    public boolean verificarDisponibilidad() {
        if (estado == EstadoPaseo.DISPONIBLE) {
            return true;
        }
        return false;
    }

    public void ajustarInventario(int plazas) {
        this.plazasDisponibles = plazas;
    }

}
