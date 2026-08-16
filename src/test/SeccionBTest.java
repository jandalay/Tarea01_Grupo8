package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

import composite.*;
import enums.*;
import observer.*;
import Builder.*;

public class SeccionBTest {

    private Hospedaje hos1;
    private Hospedaje hos2;
    private PaseoTuristico pas1;
    private PaseoTuristico pas2;
    private PaseoTuristico pas3Agotado;

    @BeforeEach
    void setUp() {
        hos1 = new Hospedaje("HOS-1", "Hotel San José", "Doble", EstadoHabitacion.DISPONIBLE, 100.0);
        pas1 = new PaseoTuristico("PAS-1", "Tour Galápagos", 10, 50.0);
        pas2 = new PaseoTuristico("PAS-2", "Tour Cajas", 1, 40.0);
        hos2 = new Hospedaje("HOS-2", "Hotel Del Sol", "Simple", EstadoHabitacion.DISPONIBLE, 80.0);
        pas3Agotado = new PaseoTuristico("PAS-3", "Tour Playa", 0, 30.0);
    }

    // --- PRUEBAS ALINEADAS CON EL PLAN DE PRUEBAS (SECCIÓN A) ---

    @Test
    @DisplayName("TC-01: Cálculo de precio de paquete con descuento")
    void testTC01_calcularPrecioConDescuento() {
        PaqueteTuristico pkg1 = new PaqueteTuristico("PKG-1", "Pack Aventura", 10.0);
        pkg1.agregarItem(hos1);
        pkg1.agregarItem(pas1);

        double precioObtenido = pkg1.calcularPrecio();

        // 1. ASERCIÓN TYPE: assertEquals
        assertEquals(135.0, precioObtenido, 0.001, 
            "El cálculo del precio debe ser 135.0 tras aplicar el 10% de descuento");
    }

    @Test
    @DisplayName("TC-02: Reserva de última plaza en paseo turístico")
    void testTC02_agotarPlazasAlReservar() {
        assertEquals(1, pas2.getPlazasDisponibles());

        pas2.reservar();

        assertEquals(0, pas2.getPlazasDisponibles());
        // 2. ASERCIÓN TYPE: assertFalse
        assertFalse(pas2.verificarDisponibilidad(), "El estado del paseo debe cambiar a no disponible");
    }

    @Test
    @DisplayName("TC-03: Disponibilidad de paquete con un ítem agotado")
    void testTC03_retornarFalsoSiComponenteEstaAgotado() {
        PaqueteTuristico pkg2 = new PaqueteTuristico("PKG-2", "Pack Mixto", 0.0);
        pkg2.agregarItem(hos2);
        pkg2.agregarItem(pas3Agotado);

        boolean estaDisponible = pkg2.verificarDisponibilidad();

        assertFalse(estaDisponible, 
            "La disponibilidad debe ser false si al menos un ítem está agotado");
    }

    @Test
    @DisplayName("TC-04: Remover usuario de WhatsAppNotificador")
    void testTC04_removerUsuario() {
        WhatsAppNotificador wsNotif = new WhatsAppNotificador();
        Usuario usuario = new Usuario("A1", "Andrés", "andre04@mail.com", "+593123");

        wsNotif.agregarUsuario(usuario);
        wsNotif.removerUsuario(usuario);

        // 3. ASERCIÓN TYPE: assertTrue
        assertTrue(wsNotif.getListaUsuarios().isEmpty(), "La lista de usuarios debe quedar vacía");
    }

    @Test
    @DisplayName("TC-05: Error al enviar notificación a usuario no registrado")
    void testTC05_enviarNotificacionError() {
        EmailNotificador emailNotif = new EmailNotificador();
        Usuario usuarioNoAgregado = new Usuario("E9", "Moran", "moran_bonilla@mail.com", "+593987");

        // 4. ASERCIÓN TYPE: assertThrows
        assertThrows(IllegalArgumentException.class, () -> {
            emailNotif.enviarNotificacion(usuarioNoAgregado, "Estimado usuario, se notifica que...");
        }, "Debe lanzar IllegalArgumentException si el usuario no está suscrito");
    }

    @Test
    @DisplayName("TC-06: Caso límite en calcularPrecio (valores en 0.0)")
    void testTC06_CasoLimiteCalcularPrecio() {
        Hospedaje hos0 = new Hospedaje("A0", "Hosp_Nulo", "Simple", EstadoHabitacion.DISPONIBLE, 0.0);
        PaseoTuristico pas0 = new PaseoTuristico("B0", "Paseo_Nulo", 5, 0.0);
        PaqueteTuristico pkg0 = new PaqueteTuristico("PGK-0", "Conjunto 0", 0.0);
        pkg0.agregarItem(hos0);
        pkg0.agregarItem(pas0);

        assertEquals(0.0, pkg0.calcularPrecio(), "Debe devolver 0.0 cuando los componentes valen 0.0");
    }

    @Test
    @DisplayName("TC-07: Disponibilidad de paquete todos disponibles")
    void testTC07_retornarTrueSiComponenteEstaDisponible() {
        PaseoTuristico pas3Disponible = new PaseoTuristico("PAS-3.1", "Tour playa", 2, 30.0);
        PaqueteTuristico pkg2 = new PaqueteTuristico("PKG-2", "Pack Mixto", 0.0);
        pkg2.agregarItem(hos2);        
        pkg2.agregarItem(pas3Disponible); 

        assertTrue(pkg2.verificarDisponibilidad());
    }
@Test
    @DisplayName("TC-08: Procesar pago exitoso y confirmar reserva")
    void testTC08_procesarPagoExitoso() {
        Usuario usuario = new Usuario("U100", "Maria", "maria@mail.com", "0999999999");
        Reserva reserva = new Reserva(usuario, java.util.List.of(hos1));
        
        // Se implementa la interfaz PagoService simulando un pago exitoso (devuelve true)
        PagoService pagoExitoso = monto -> true;
        EmailNotificador emailNotif = new EmailNotificador();

        boolean resultado = reserva.procesarPagoYConfirmar(pagoExitoso, emailNotif);

        assertTrue(resultado, "El pago debe procesarse de forma exitosa");
        assertNotNull(reserva.getEstado(), "El estado de la reserva no debe ser nulo tras confirmar");
        assertEquals(EstadoReserva.RESERVADO, reserva.getEstado(), "El estado debe cambiar a RESERVADO");
    }

    @Test
    @DisplayName("TC-09: Abortar proceso de pago por fallo en servicio")
    void testTC09_procesarPagoMontoInsuficiente() {
        Usuario usuario = new Usuario("U101", "Pedro", "pedro@mail.com", "0988888888");
        Reserva reserva = new Reserva(usuario, java.util.List.of(hos1));
        
        // Se implementa la interfaz PagoService simulando un pago fallido (devuelve false)
        PagoService pagoFallido = monto -> false;
        EmailNotificador emailNotif = new EmailNotificador();

        boolean resultado = reserva.procesarPagoYConfirmar(pagoFallido, emailNotif);

        assertFalse(resultado, "El procesamiento de pago debe retornar false");
        // ASERCIÓN TYPE: assertNotEquals
        assertNotEquals(EstadoReserva.RESERVADO, reserva.getEstado(), "El estado no debe ser RESERVADO si el pago falla");
    }
 }