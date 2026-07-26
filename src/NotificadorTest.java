import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class NotificadorTest {
    @Test
    public void whatsappValido() {
        Notificador notificador = new WhatsAppNotificador();
        Usuario usuario1 = new Usuario("Andrés", "andre04@mail.com", "+593123");
        Usuario usuario2 = new Usuario("Manuel", "manuel_cuenca@mail.com", "+593135");
        Usuario usuario3 = new Usuario("Lopez", "lopez_obrador@mail.com", "+593246");
        notificador.agregarUsuario(usuario1);
        notificador.agregarUsuario(usuario2);
        notificador.agregarUsuario(usuario3);

        //mensaje individual
        Usuario usuarioEnviado = new Usuario("Manuel", "manuel_cuenca@mail.com", "+593135");
        notificador.enviarNotificacion(usuarioEnviado, "Estimado usuario, se ha cambiado...");

        //mensaje a todos
        notificador.enviarNotificacionMasivo("se notifica a todos que debido a...");

    }

    public void whatsappError() {
        Notificador notificador = new WhatsAppNotificador();
        Usuario usuario1 = new Usuario("Andrés", "andre04@mail.com", "+593123");
        Usuario usuario2 = new Usuario("Manuel", "manuel_cuenca@mail.com", "+593135");
        Usuario usuario3 = new Usuario("Lopez", "lopez_obrador@mail.com", "+593246");
        notificador.agregarUsuario(usuario1);
        notificador.agregarUsuario(usuario2);
        notificador.agregarUsuario(usuario3);

        //mensaje individual
        Usuario usuarioEnviado = new Usuario("Manuel", "manuel_cuenca@mail.com", "+593135");
        notificador.enviarNotificacion(usuarioEnviado, "Estimado usuario, se ha cambiado...");

    }
}
