package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import composite.PagoService;
import composite.PaseoTuristico;
import composite.Reserva;
import composite.Reservable;
import composite.Usuario;
import enums.EstadoPaseo;
import enums.EstadoReserva;
import observer.EmailNotificador;
import observer.Notificador;
import observer.WhatsAppNotificador;

public class SeccionDRefactTest {

    @Test
    @DisplayName("Refactor 1 - Lazy Class: Verificar creación directa de Usuario")
    void testCreacionUsuarioDirecto() {
        Usuario usuario = new Usuario("U1", "Brandon Andrade", "carlos@mail.com", "0991234567");

        assertNotNull(usuario);
        assertEquals("U1", usuario.getId());
        assertEquals("Brandon Andrade", usuario.getNombre());
        }

    @Test
    @DisplayName("Verificar procesamiento exitoso de reserva desde el objeto de dominio")
    void testProcesarPagoYConfirmarReserva() {
        Usuario usuario = new Usuario("U1", "Ana", "ana@mail.com", "0990000000");
        List<Reservable> items = new ArrayList<>();

        Reserva reserva = new Reserva(usuario, items);

        PagoService pagoServiceMock = monto -> true;

        Notificador notificadorMock = new WhatsAppNotificador();

        boolean resultado = reserva.procesarPagoYConfirmar(pagoServiceMock, notificadorMock);

        assertTrue(resultado);
        assertEquals(EstadoReserva.RESERVADO, reserva.getEstado());
        }

    @Test
    @DisplayName("Verificar instanciación de PaseoTuristico con constructor simplificado")
    void testCreacionPaseoTuristicoRefactorizado() {
        PaseoTuristico paseo = new PaseoTuristico("P1", "Tour Baños", 12, 45.0);

        assertNotNull(paseo);
        assertEquals("P1", paseo.getId());
        assertEquals("Tour Baños", paseo.getNombre());
        assertEquals(12, paseo.getPlazasDisponibles());
        assertEquals(45.0, paseo.getPrecio());
        assertEquals(EstadoPaseo.DISPONIBLE, paseo.getEstado());
    }

    @Test
    @DisplayName("Verificar que reservar la última plaza actualiza el estado a AGOTADO")
    void testReservarCambiaEstadoAAgotado() {
        PaseoTuristico paseo = new PaseoTuristico("PAS-TEST", "Tour Galápagos", 1, 50.0);
        
        paseo.reservar();

        assertEquals(0, paseo.getPlazasDisponibles());
        assertEquals(EstadoPaseo.AGOTADO, paseo.getEstado());
        }

    @Test
    @DisplayName("Verificar que intentar reservar sin plazas lanza una excepción")
    void testReservarSinPlazasLanzaExcepcion() {
        PaseoTuristico paseo = new PaseoTuristico("PAS-TEST2", "Tour Cajas", 0, 40.0);

        assertThrows(IllegalStateException.class, () -> {
            paseo.reservar();
        });
    }

    @Test
    @DisplayName("Verificar que ajustarInventario con valor válido actualiza cupos y estado")
    void testAjustarInventarioValido() {
        PaseoTuristico paseo = new PaseoTuristico("PAS-01", "Tour Galápagos", 0, 50.0);
    
        paseo.ajustarInventario(5);

        assertEquals(5, paseo.getPlazasDisponibles());
        assertEquals(EstadoPaseo.DISPONIBLE, paseo.getEstado());
    }

    @Test
    @DisplayName("Verificar que ajustarInventario con valor negativo lanza IllegalArgumentException")
    void testAjustarInventarioNegativoLanzaExcepcion() {
        PaseoTuristico paseo = new PaseoTuristico("PAS-01", "Tour Galápagos", 2, 50.0);

        assertThrows(IllegalArgumentException.class, () -> {
            paseo.ajustarInventario(-3);
        });
    }

    @Test
    @DisplayName("Verificar que la gestión de usuarios heredada de abstractNotificador funciona correctamente")
    void testGestionUsuariosHeredada() {
        EmailNotificador notificador = new EmailNotificador();
        Usuario usuario = new Usuario("U1", "Carlos", "carlos@mail.com", "0999999999");

        // Prueba agregar usuario
        notificador.agregarUsuario(usuario);
        assertEquals(1, notificador.getListaUsuarios().size());
        assertTrue(notificador.getListaUsuarios().contains(usuario));

        // Prueba remover usuario
        notificador.removerUsuario(usuario);
        assertEquals(0, notificador.getListaUsuarios().size());
    }
}