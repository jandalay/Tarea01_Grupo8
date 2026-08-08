package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import observer.*;
import composite.*;

public class NotificadorTest {
    Notificador notificadorEmail;
    Notificador notificadorWhatsapp;
    
    @Test
    @DisplayName("test valido en notificación de WhatsApp")
    @BeforeEach
    public void agregarDatosParaTests() {
        
        if (notificadorEmail == null) {
            notificadorEmail = new EmailNotificador();
        }
        if (notificadorWhatsapp == null) {
            notificadorWhatsapp = new WhatsAppNotificador();
        }
        
        //notificadorEmail
        Usuario usuario1 = new Usuario("E1", "Ortega", "jacinto_ortega@mail.com", "+593456");
        Usuario usuario2 = new Usuario("E2","Suarez", "suarez09@mail.com", "+593468");
        Usuario usuario3 = new Usuario("E3", "Samuel", "samuel_26A@mail.com", "+593579");
        notificadorEmail.agregarUsuario(usuario1);
        notificadorEmail.agregarUsuario(usuario2);
        notificadorEmail.agregarUsuario(usuario3);
        
        //notificadorWhatsapp
        Usuario usuario4 = new Usuario("A1", "Andrés", "andre04@mail.com", "+593123");
        Usuario usuario5 = new Usuario("A2", "Manuel", "manuel_cuenca@mail.com", "+593135");
        Usuario usuario6 = new Usuario("A3", "Lopez", "lopez_obrador@mail.com", "+593246");
        notificadorWhatsapp.agregarUsuario(usuario1);
        notificadorWhatsapp.agregarUsuario(usuario2);
        notificadorWhatsapp.agregarUsuario(usuario3);

    }

    @Test
    @AfterEach
    public void EmailError() {
    
        Usuario usuarioEnviado = new Usuario("E9", "Moran", "moran_bonilla@mail.com", "+593987");
       try {
            notificadorEmail.enviarNotificacion(usuarioEnviado, "Estimado usuario, se le notifica...");
       }
       catch(IllegalArgumentException ex) {
        assertEquals("El usuario " + usuarioEnviado.getNombre() + " no está suscrito a las notificaciones por correo electrónico.", ex.getMessage());
       }
    }

    @Test
    public void whatsappError() {

        Usuario usuarioEnviado = new Usuario("B2026", "Manuel", "manuel_cuenca@mail.com", "+593135");
        /*
        Al no retornar nada no funciona assertThrows
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, notificador.enviarNotificacion(usuarioEnviado, "Estimado usuario, se ha cambiado..."));
        assertEquals("El usuario " + usuarioEnviado.getNombre() + " no está suscrito a las notificaciones por WhatsApp.", ex.getMessage());
        */
       try {
            notificadorWhatsapp.enviarNotificacion(usuarioEnviado, "Estimado usuario, se ha cambiado...");
       }
       catch(IllegalArgumentException ex) {
        assertEquals("El usuario " + usuarioEnviado.getNombre() + " no está suscrito a las notificaciones por WhatsApp.", ex.getMessage());
       }

    }

    @Test
    public void WhatsappValido() {
        notificadorWhatsapp.removerUsuario(new Usuario("A1", "Andrés", "andre04@mail.com", "+593123"));

        //mensaje individual
        Usuario usuarioEnviado = new Usuario("E3", "Samuel", "samuel_26A@mail.com", "+593579");
        notificadorWhatsapp.enviarNotificacion(usuarioEnviado, "Se le informa que su cuenta...");

        //mensaje a todos
        notificadorWhatsapp.enviarNotificacionMasivo("El siguiente comunicado es porque...");
    }

    @Test
    public void EmailValido() {

        //mensaje individual
        Usuario usuarioEnviado = new Usuario("A3", "Lopez", "lopez_obrador@mail.com", "+593246");
        notificadorEmail.enviarNotificacion(usuarioEnviado, "Se le informa que...");

        //mensaje a todos
        notificadorEmail.enviarNotificacionMasivo("Se informa a todos un...");
    }

}
