import composite.PagoService;
import composite.Reserva;
import observer.Notificador;


public class RealizarReserva {
    private Notificador notificador;
    private PagoService pagoService;

    public RealizarReserva(Notificador notificador, PagoService pagoService) {
        this.notificador = notificador;
        this.pagoService = pagoService;
    }

 public void procesar(Reserva reserva) {
        reserva.procesarPagoYConfirmar(this.pagoService, this.notificador);
    }
}