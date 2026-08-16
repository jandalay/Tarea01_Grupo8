package observer;

import composite.Usuario;

public class WhatsAppNotificador extends abstractNotificador {

    @Override
    public void enviarNotificacion(Usuario usuario, String mensaje) {
        if (!usuariosSuscritos.contains(usuario)) {
            System.out.println("El usuario " + usuario.getNombre() + " no está suscrito a las notificaciones por WhatsApp.");
            return;
        }
        // Notificación directa invocando la respuesta del observador
        usuario.recibirNotificacion(mensaje);
    }

    @Override
    public void enviarNotificacionMasivo(String mensaje) {
        for (Usuario usuario : usuariosSuscritos) {
            usuario.recibirNotificacion("**WhatsApp** " + mensaje);
        }
    }
}