public class RealizarReserva {
    private Notificador notificador;
    private PagoService pagoService;

     

    public RealizarReserva(Notificador notificador, PagoService pagoService) {
        this.notificador = notificador;
        this.pagoService = pagoService;
    }

    public void procesar(Reserva reserva) {
        if (pagoService.procesarPago(reserva.getTotal())) {
            notificador.enviarNotificacion(reserva.getUsuario(), "El pago ha sido procesado");
            reserva.confirmarReserva();
        }
        else {
            System.out.println("No es posible");
        }
    }
}
