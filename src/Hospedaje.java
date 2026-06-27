import enums.EstadoHabitacion;

public class Hospedaje implements Reservable {
    
    private String id;
    private String nombre;
    private String tipoHabitacion;
    private EstadoHabitacion estado;
    private double precio;
    
    public Hospedaje(String id, String nombre, String tipoHabitacion, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.tipoHabitacion = tipoHabitacion;
        this.estado = EstadoHabitacion.DISPONIBLE;
        this.precio = precio;
    }

    @Override
    public double calcularPrecio() {
        return precio;
    }
    
    @Override
    public boolean verificarDisponibilidad() {
        if (estado == EstadoHabitacion.DISPONIBLE) {
            return true;
        }
        return false;
    }
    
    public void cambiarEstado(EstadoHabitacion estado) {
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return "(ID: " + id + ", nombre: " + nombre + "tipo: " + tipoHabitacion + ", precio: " + precio + ")";
    }

}
