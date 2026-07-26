package composite;

import java.util.ArrayList;
import java.util.List;

public class PaqueteTuristico implements Reservable {
    private String id;
    private String nombre;
    private double descuento;
    private List<Reservable> items;

    public PaqueteTuristico(String id, String nombre, double descuento, List<Reservable> items) {
        this.id = id;
        this.nombre = nombre;
        this.descuento = descuento;
        this.items = (items != null) ? items : new ArrayList<>();
    }

    public PaqueteTuristico(String id, String nombre, double descuento) {
        this(id, nombre, descuento, new ArrayList<>());
    }

    @Override
    public double calcularPrecio() {
        double sumaPrecios = 0.0;
        for (Reservable item : items) {
            sumaPrecios += item.calcularPrecio();
        }
        return sumaPrecios - (sumaPrecios * (descuento / 100.0));
    }

    @Override
    public boolean verificarDisponibilidad() {
        if (items.isEmpty()) {
            return false;
        }
        for (Reservable item : items) {
            if (!item.verificarDisponibilidad()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void reservar() {
        for (Reservable item : items) {
            item.reservar();
        }
    }

    public void agregarItem(Reservable item) {
        if (item != null) {
            items.add(item);
        }
    }

    public void eliminarItem(Reservable item) {
        if (items.contains(item)) {
            items.remove(item);
        } else {
            System.out.println("Reserva no encontrada");
        }
    }

    @Override
    public String toString() {
        return "(ID: " + id + ", nombre: " + nombre + ", descuento: " + descuento + "% - Contiene: " + items + ")";
    }
}