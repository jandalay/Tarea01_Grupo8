package composite;

import enums.EstadoPaseo;

public class PaseoTuristico implements Reservable {
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
        return this.estado == EstadoPaseo.DISPONIBLE && this.plazasDisponibles > 0;
    }

    @Override
    public void reservar() {
        if (verificarDisponibilidad()) {
            this.plazasDisponibles--;
            if (this.plazasDisponibles == 0) {
                this.estado = EstadoPaseo.AGOTADO;
            }
        }
    }

    public void ajustarInventario(int plazas) {
        this.plazasDisponibles = plazas;
        if (this.plazasDisponibles > 0 && this.estado == EstadoPaseo.AGOTADO) {
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
}