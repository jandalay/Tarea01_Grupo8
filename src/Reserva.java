import java.util.Iterator;
import java.util.List;

import enums.EstadoReserva;

public class Reserva {
    private String idReserva;
    private Usuario usuario;
    private List<Reservable> itemsReservados;
    private EstadoReserva estado;
    private double total;

    public Reserva(Usuario usuario, List<Reservable> itemsReservados) {
        //el id es un número aleatorio
        this.idReserva = String.valueOf(Math.random()*1000.0);
        this.usuario = usuario;
        this.itemsReservados = itemsReservados;
        this.estado = EstadoReserva.DISPONIBLE;
        this();
    }

    private Reserva() {
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

    public List<Reservable> getItemsReservados(){
        return itemsReservados;
    }
}
