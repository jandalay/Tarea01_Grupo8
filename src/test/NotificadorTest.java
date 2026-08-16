package test;

import observer.Notificador;
import observer.EmailNotificador;
import observer.WhatsAppNotificador;
import composite.Usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotificadorTest {

    private Notificador notificadorEmail;
    private Notificador notificadorWhatsapp;
    
    private Usuario usuarioRegistrado;
    private Usuario usuarioNoRegistrado;

    @BeforeEach
    void setUp() {
        notificadorEmail = new EmailNotificador();
        notificadorWhatsapp = new WhatsAppNotificador();

        usuarioRegistrado = new Usuario("A1", "Andrés", "andre04@mail.com", "+593123");
        usuarioNoRegistrado = new Usuario("E9", "Moran", "moran_bonilla@mail.com", "+593987");

        // Registrar usuario base
        notificadorEmail.agregarUsuario(usuarioRegistrado);
        notificadorWhatsapp.agregarUsuario(usuarioRegistrado);
    }

    @Test
    @DisplayName("TC-04 / Email: Lanza IllegalArgumentException al notificar usuario no registrado")
    void testEmailErrorUsuarioNoRegistrado() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> notificadorEmail.enviarNotificacion(usuarioNoRegistrado, "Estimado usuario, se le notifica que...")
        );

        assertNotNull(ex.getMessage(), "La excepción debe contener un mensaje explicativo.");
    }

    @Test
    @DisplayName("WhatsApp: Lanza IllegalArgumentException al notificar usuario no registrado")
    void testWhatsappErrorUsuarioNoRegistrado() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> notificadorWhatsapp.enviarNotificacion(usuarioNoRegistrado, "Estimado usuario, se ha cambiado...")
        );

        assertNotNull(ex.getMessage(), "La excepción debe contener un mensaje explicativo.");
    }

    @Test
    @DisplayName("TC-05 / WhatsApp: Remueve usuario correctamente de la lista")
    void testWhatsappRemoverUsuario() {
        notificadorWhatsapp.removerUsuario(usuarioRegistrado);

        assertFalse(
            notificadorWhatsapp.getListaUsuarios().contains(usuarioRegistrado),
            "El usuario debe ser eliminado de la lista de notificaciones por WhatsApp."
        );
    }

    @Test
    @DisplayName("Email: Remueve usuario correctamente de la lista")
    void testEmailRemoverUsuario() {
        notificadorEmail.removerUsuario(usuarioRegistrado);

        assertFalse(
            notificadorEmail.getListaUsuarios().contains(usuarioRegistrado),
            "El usuario debe ser eliminado de la lista de notificaciones por Email."
        );
    }

    @Test
    @DisplayName("Notificación masiva enviada sin errores")
    void testNotificacionMasiva() {
        assertDoesNotThrow(() -> {
            notificadorEmail.enviarNotificacionMasivo("Comunicado general a todos los suscriptores");
            notificadorWhatsapp.enviarNotificacionMasivo("Comunicado general a todos los suscriptores");
        });
    }
}