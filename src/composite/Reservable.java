package composite;

public interface Reservable {
    double calcularPrecio();
    boolean verificarDisponibilidad();
    void reservar();
}
