package observer;

import composite.Usuario;

public class EmailNotificador extends abstractNotificador {

    @Override
    public void enviarNotificacion(Usuario usuario, String mensaje) {
        if (!usuariosSuscritos.contains(usuario)) {
            System.out.println("El usuario " + usuario.getNombre() + " no está suscrito a las notificaciones por correo.");
            return;
        }
        System.out.println("Mensaje enviado por Email a: " + usuario.getNombre() + "\nContenido: " + mensaje);
    }

    @Override
    public void enviarNotificacionMasivo(String mensaje) {
        for (Usuario usuario : usuariosSuscritos) {
            enviarNotificacion(usuario, mensaje);
        }
    }
}