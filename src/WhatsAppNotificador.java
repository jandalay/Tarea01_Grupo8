public class WhatsAppNotificador implements Notificador {

    @Override
    public void enviarNotificacion(Usuario usuario, String mensaje) {
        System.out.println("Mensaje para: " + usuario.getNombre() + "\nMensaje: " + mensaje);
        
    }

}
