package composite;

import enums.EstadoPaseo;

public class PaseoTuristico implements Reservable {
    private String id;
    private String nombre;
    private int plazasDisponibles;
    private EstadoPaseo estado;
    private double precio;

 // Se elimina el parámetro 'estado' para simplificar la lista de parámetros
public PaseoTuristico(String id, String nombre, int plazasDisponibles, double precio) {
    if (plazasDisponibles < 0) {
            throw new IllegalArgumentException("Las plazas iniciales no pueden ser negativas.");
        }
    this.id = id;
    this.nombre = nombre;
    this.plazasDisponibles = plazasDisponibles;
    this.precio = precio;
    this.estado = EstadoPaseo.DISPONIBLE; // El valor inicial se resuelve internamente
}

    @Override
    public double calcularPrecio() {
        return precio;
    }

    @Override
    public boolean verificarDisponibilidad() {
        return this.estado == EstadoPaseo.DISPONIBLE && this.plazasDisponibles > 0;
    }

    @Override
    public void reservar() {
        if (!verificarDisponibilidad()) {
            throw new IllegalStateException("No hay plazas disponibles para realizar la reserva.");
        }
        this.plazasDisponibles--;
        actualizarEstado(); // Extract Method
    }

    public void ajustarInventario(int plazas) {
        // Validación de dominio
        if (plazas < 0) {
            throw new IllegalArgumentException("La cantidad de plazas no puede ser negativa.");
        }
        this.plazasDisponibles = plazas;
        actualizarEstado(); // Extract Method
    }

    private void actualizarEstado() {
        if (this.plazasDisponibles == 0) {
            this.estado = EstadoPaseo.AGOTADO;
        } else if (this.plazasDisponibles > 0) {
            this.estado = EstadoPaseo.DISPONIBLE;
        }
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "(ID: " + id + ", nombre: " + nombre + ", cantidad de plazas: " + plazasDisponibles + ", precio: " + precio + ")";
    }

    public String getNombre() {
        return nombre;
    }

    public int getPlazasDisponibles() {
        return plazasDisponibles;
    }

    public EstadoPaseo getEstado() {
        return estado;
    }

    public double getPrecio() {
        return precio;
    }
}