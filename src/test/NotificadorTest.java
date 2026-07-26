package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import observer.*;
import composite.*;

public class NotificadorTest {
    @Test
    @DisplayName("test valido en notificación de WhatsApp")
    public void whatsappValido() {
        Notificador notificador = new WhatsAppNotificador();
        Usuario usuario1 = new Usuario("A1", "Andrés", "andre04@mail.com", "+593123");
        Usuario usuario2 = new Usuario("A2", "Manuel", "manuel_cuenca@mail.com", "+593135");
        Usuario usuario3 = new Usuario("A3", "Lopez", "lopez_obrador@mail.com", "+593246");
        notificador.agregarUsuario(usuario1);
        notificador.agregarUsuario(usuario2);
        notificador.agregarUsuario(usuario3);

        //mensaje individual
        Usuario usuarioEnviado = new Usuario("A2", "Manuel", "manuel_cuenca@mail.com", "+593135");
        notificador.enviarNotificacion(usuarioEnviado, "Estimado usuario, se ha cambiado...");

        //mensaje a todos
        notificador.enviarNotificacionMasivo("se notifica a todos que debido a...");

    }

    @Test
    @AfterEach
    public void whatsappError() {
        Notificador notificador = new WhatsAppNotificador();
        Usuario usuario1 = new Usuario("A1", "Andrés", "andre04@mail.com", "+593123");
        Usuario usuario2 = new Usuario("A2","Manuel", "manuel_cuenca@mail.com", "+593135");
        Usuario usuario3 = new Usuario("A3", "Lopez", "lopez_obrador@mail.com", "+593246");
        notificador.agregarUsuario(usuario1);
        notificador.agregarUsuario(usuario2);
        notificador.agregarUsuario(usuario3);

        Usuario usuarioEnviado = new Usuario("B2026", "Manuel", "manuel_cuenca@mail.com", "+593135");
        /*
        Al no retornar nada no funciona assertThrows
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, notificador.enviarNotificacion(usuarioEnviado, "Estimado usuario, se ha cambiado..."));
        assertEquals("El usuario " + usuarioEnviado.getNombre() + " no está suscrito a las notificaciones por WhatsApp.", ex.getMessage());
        */
       try {
            notificador.enviarNotificacion(usuarioEnviado, "Estimado usuario, se ha cambiado...");
       }
       catch(IllegalArgumentException ex) {
        assertEquals("El usuario " + usuarioEnviado.getNombre() + " no está suscrito a las notificaciones por WhatsApp.", ex.getMessage());
       }

    }

    @Test
    @AfterEach
    public void EmailValido() {
        Notificador notificador = new EmailNotificador();
        Usuario usuario1 = new Usuario("E1", "Ortega", "jacinto_ortega@mail.com", "+593456");
        Usuario usuario2 = new Usuario("E2","Suarez", "suarez09@mail.com", "+593468");
        Usuario usuario3 = new Usuario("E3", "Samuel", "samuel_26A@mail.com", "+593579");
        notificador.agregarUsuario(usuario1);
        notificador.agregarUsuario(usuario2);
        notificador.agregarUsuario(usuario3);

        //mensaje individual
        Usuario usuarioEnviado = new Usuario("E3", "Samuel", "samuel_26A@mail.com", "+593579");
        notificador.enviarNotificacion(usuarioEnviado, "Se le informa que...");

        //mensaje a todos
        notificador.enviarNotificacionMasivo("Se informa a todos un...");
    }

    @Test
    @AfterEach
    public void EmailError() {
        Notificador notificador = new EmailNotificador();
        Usuario usuario1 = new Usuario("E1", "Ortega", "jacinto_ortega@mail.com", "+593456");
        Usuario usuario2 = new Usuario("E2","Suarez", "suarez09@mail.com", "+593468");
        Usuario usuario3 = new Usuario("E3", "Samuel", "samuel_26A@mail.com", "+593579");
        notificador.agregarUsuario(usuario1);
        notificador.agregarUsuario(usuario2);
        notificador.agregarUsuario(usuario3);

        Usuario usuarioEnviado = new Usuario("E9", "Moran", "moran_bonilla@mail.com", "+593987");
       try {
            notificador.enviarNotificacion(usuarioEnviado, "Estimado usuario, se le notifica...");
       }
       catch(IllegalArgumentException ex) {
        assertEquals("El usuario " + usuarioEnviado.getNombre() + " no está suscrito a las notificaciones por correo electrónico.", ex.getMessage());
       }
    }
}
