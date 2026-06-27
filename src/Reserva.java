import java.util.Iterator;
import java.util.List;

import enums.EstadoReserva;

public class Reserva {
    private String idReserva;
    private Usuario usuario;
    private List<Reservable> itemsReservados;
    private EstadoReserva estado;
    private double total;

    public Reserva(String idReserva, Usuario usuario, List<Reservable> itemsReservados, EstadoReserva estado,
            double total) {
        this.idReserva = idReserva;
        this.usuario = usuario;
        this.itemsReservados = itemsReservados;
        this.estado = estado;
        this.total = total;
    }

    public Reserva() {
        double sumaPrecios = 0.0;
        Iterator<Reservable> iterator = itemsReservados.iterator();
        while (iterator.hasNext()) {
            sumaPrecios += iterator.next().calcularPrecio();
        }

        total = sumaPrecios;
    }

    public void bloquearTemporalmente() {
        this.estado = EstadoReserva.BLOQUEADO;
    }

    public void confirmarReserva() {
        if (estado == EstadoReserva.RESERVADO) {
            System.out.println("Ya ha sido reservado");
            return;
        }
        this.estado = EstadoReserva.RESERVADO;
    }

    public double getTotal() {
        return total;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
