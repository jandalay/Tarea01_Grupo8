import enums.EstadoHabitacion;

public class Hospedaje implements Reservable {
    
    private String id;
    private String nombre;
    private String tipoHabitacion;
    private EstadoHabitacion estado;
    private double precio;
    
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

}
