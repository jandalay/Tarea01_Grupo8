import java.util.ArrayList;
import java.util.List;
import composite.Reservable;
import enums.EstadoReserva;

public class Reserva {

    private String idReserva;
    private Usuario usuario;
    private List<Reservable> itemsReservados;
    private EstadoReserva estado;
    private double total;

    public Reserva(Usuario usuario, List<Reservable> itemsReservados) {
        this.idReserva = String.valueOf((int) (Math.random() * 10000));
        this.usuario = usuario;
        this.itemsReservados = (itemsReservados != null) ? itemsReservados : new ArrayList<>();
        this.estado = EstadoReserva.DISPONIBLE;
        this.total = calcularTotal();
    }

    private double calcularTotal() {
        double sumaPrecios = 0.0;
        for (Reservable item : itemsReservados) {
            sumaPrecios += item.calcularPrecio();
        }
        return sumaPrecios;
    }

    public void bloquearTemporalmente() {
        this.estado = EstadoReserva.BLOQUEADO;
    }

    public void confirmarReserva() {
        if (this.estado == EstadoReserva.RESERVADO) {
            System.out.println("Ya ha sido reservado");
            return;
        }

        this.estado = EstadoReserva.RESERVADO;

        for (Reservable item : itemsReservados) {
            item.reservar();
        }
    }

    public String getIdReserva() {
        return idReserva;
    }

    public double getTotal() {
        return total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Reservable> getItemsReservados() {
        return itemsReservados;
    }

    public EstadoReserva getEstado() {
        return estado;
    }
}