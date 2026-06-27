import java.util.List;
import java.util.Iterator;


public class PaqueteTuristico implements Reservable {
    private String id;
    private String nombre;
    private double descuento;
    private List<Reservable> items;

    public PaqueteTuristico(String id, String nombre, double descuento, List<Reservable> items) {
        this.id = id;
        this.nombre = nombre;
        this.descuento = descuento;
        this.items = items;
    }

    public double calcularPrecio() {
        double sumaPrecios = 0.0;
        Iterator<Reservable> iterator = items.iterator();
        while (iterator.hasNext()) {
            sumaPrecios += iterator.next().calcularPrecio();
        }

        return sumaPrecios - sumaPrecios*(descuento/100.0);
    }

    public boolean verificarDisponibilidad() {
        if (items.isEmpty()) {
            return false;
        }

        Iterator<Reservable> iterator = items.iterator();
        while (iterator.hasNext()) {
            /* Si al menos un item no está disponible entonces no
            tiene disponibilidad */
            if (!iterator.next().verificarDisponibilidad()) {
                return false;
            }
        }

        return true;
    }

    public void agregarItem(Reservable item) {
        items.add(item);
    }

    public void eliminarItem(Reservable item) {
        if (items.contains(item)) {
            items.remove(item);
        }
        else {
            System.out.println("Reserva no encontrada");
        }
    }

    public String toString() {
        return "(ID: "+ id + ", nombre: " + nombre + ", descuento: " + descuento + " - Contiene: " + items + ")";
    }


}
