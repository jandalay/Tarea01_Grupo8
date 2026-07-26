package test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import composite.*;
import enums.*;
import observer.*;
import Builder.*;

public class TravelStayPatternsTest {

    private Usuario usuarioPrueba;
    private Hospedaje hotelPrueba;
    private PaseoTuristico paseoPrueba;

    @BeforeEach
    void setUp() {
        usuarioPrueba = new Usuario("U1", "Maria Pacha", "maria@mail.com", "0991234567");
        hotelPrueba = new Hospedaje("H1", "Hotel San José", "Doble", EstadoHabitacion.DISPONIBLE, 100.0);
        paseoPrueba = new PaseoTuristico("P1", "Tour Galápagos", 10, EstadoPaseo.DISPONIBLE, 50.0);
    }

    // 1. PRUEBAS PARA EL PATRÓN COMPOSITE
  

    @Test
    @DisplayName("Composite: Calcular precio total de un paquete con descuento")
    void testCalcularPrecioPaqueteTuristico() {
        PaqueteTuristico paquete = new PaqueteTuristico("PKG1", "Ecuador Full", 10.0); // 10% de descuento
        paquete.agregarItem(hotelPrueba); // $100.0
        paquete.agregarItem(paseoPrueba); // $50.0

        // Subtotal = 150.0, Descuento = 10% -> Total esperado = 135.0
        double precioEsperado = 135.0;
        assertEquals(precioEsperado, paquete.calcularPrecio(), 0.01, 
            "El cálculo del precio del paquete con descuento debe ser preciso.");
    }

    @Test
    @DisplayName("Composite: Verificar disponibilidad de componentes")
    void testVerificarDisponibilidadComposite() {
        PaqueteTuristico paquete = new PaqueteTuristico("PKG2", "Pack Aventura", 0.0);
        paquete.agregarItem(hotelPrueba);
        paquete.agregarItem(paseoPrueba);

        assertTrue(paquete.verificarDisponibilidad(), "El paquete debe estar disponible si todos sus items lo están.");
    }

    // 2. PRUEBAS PARA EL PATRÓN BUILDER

    @Test
    @DisplayName("Builder: Construcción correcta de un Paquete Turístico")
    void testBuilderConstruccionPaquete() {
        PaqueteTuristicoConcretoBuilder builder = new PaqueteTuristicoConcretoBuilder();

        PaqueteTuristico paqueteConstruido = builder
                .setId("B1")
                .setNombre("Paquete Builder")
                .setDescuento(15.0)
                .agregarItem(hotelPrueba)
                .agregarItem(paseoPrueba)
                .build();

        assertNotNull(paqueteConstruido, "El paquete construido no debe ser nulo.");
        assertEquals("B1", paqueteConstruido.getId());
        assertEquals("Paquete Builder", paqueteConstruido.getNombre());
        
        // (100 + 50) - 15% = 127.5
        assertEquals(127.5, paqueteConstruido.calcularPrecio(), 0.01);
    }

    // 3. PRUEBAS PARA EL PATRÓN OBSERVER

    @Test
    @DisplayName("Observer: Agregar y remover usuarios de Notificador")
    void testGestionUsuariosObserver() {
        EmailNotificador notificador = new EmailNotificador();

        notificador.agregarUsuario(usuarioPrueba);
        assertTrue(notificador.getListaUsuarios().contains(usuarioPrueba), 
            "El usuario debe ser agregado a la lista de observadores.");

        notificador.removerUsuario(usuarioPrueba);
        assertFalse(notificador.getListaUsuarios().contains(usuarioPrueba), 
            "El usuario debe ser removido de la lista de observadores.");
    }

    @Test
    @DisplayName("Observer: Envío masivo de notificaciones sin lanzar excepciones")
    void testEnviarNotificacionMasiva() {
        Notificador notificador = new WhatsAppNotificador();
        notificador.agregarUsuario(usuarioPrueba);

        assertDoesNotThrow(() -> {
            notificador.enviarNotificacionMasiva("¡Tu reserva ha sido confirmada!");
        }, "El envío de notificaciones masivas no debe fallar ni lanzar excepciones.");
    }
}