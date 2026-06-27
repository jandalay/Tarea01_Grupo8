import enums.EstadoPaseo;

public class PaseoTuristico implements Reservable{
    private String id;
    private String nombre;
    private int plazasDisponibles;
    private EstadoPaseo estado;
    private double precio;

    public PaseoTuristico(String id, String nombre, int plazasDisponibles, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.plazasDisponibles = plazasDisponibles;
        this.estado = EstadoPaseo.DISPONIBLE;
        this.precio = precio;
    }

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

    public String getId() {
        return id;
    }

    public String toString() {
        return "(ID: " + id + ", nombre: " + nombre + ", cantidad de plazas: " + plazasDisponibles + ", precio: " + precio + ")";
    }
}
