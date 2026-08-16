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

    // CASOS DE PRUEBA DEL PLAN DE PRUEBAS (SECCIÓN A)

@Test
    @DisplayName("TC-01: Cálculo de precio de paquete con descuento")
    void  testTC01_calcularPrecioConDescuento() {
        // Un paquete con 10% de descuento ($100 + $50)
        PaqueteTuristico pkg1 = new PaqueteTuristico("PKG-1", "Pack Aventura", 10.0);
        pkg1.agregarItem(hos1);
        pkg1.agregarItem(pas1);

        //  Se calcula el precio total
        double precioObtenido = pkg1.calcularPrecio();

        // Entonces: Debe aplicar el descuento correctamente ($135.0)
        assertEquals(135.0, precioObtenido, 0.001, 
            "El cálculo del precio debe ser 135.0 tras aplicar el 10% de descuento al subtotal de 150.0");
    }

    @Test
    @DisplayName("TC-02: Reserva de última plaza en paseo turístico")
    void testTC02_agotarPlazasAlReservar() {
        // Dado: Un paseo con 1 sola plaza
        assertEquals(1, pas2.getPlazasDisponibles(), "El paseo debe iniciar con 1 plaza disponible");

        // Cuando: Se reserva el último cupo
        pas2.reservar();

        // Entonces: Debe quedar sin plazas y no disponible
        assertEquals(0, pas2.getPlazasDisponibles(), "El número de plazas disponibles debe reducirse a 0");
        assertFalse(pas2.verificarDisponibilidad(), "El estado del paseo debe cambiar a AGOTADO (no disponible)");
    }

    @Test
    @DisplayName("TC-03: Disponibilidad de paquete con un ítem agotado")
    void testTC03_retornarFalsoSiComponenteEstaAgotado() {
        // Un paquete con un ítem disponible y uno agotado
        PaqueteTuristico pkg2 = new PaqueteTuristico("PKG-2", "Pack Mixto", 0.0);
        pkg2.agregarItem(hos2);        // DISPONIBLE
        pkg2.agregarItem(pas3Agotado); // AGOTADO (0 plazas)

        // Se consulta la disponibilidad del paquete
        boolean estaDisponible = pkg2.verificarDisponibilidad();

        //  La disponibilidad global debe ser falsa
        assertFalse(estaDisponible, 
            "La disponibilidad global del paquete debe ser false si al menos uno de sus componentes está agotado");
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

     @Test
    @DisplayName("TC-06: Caso limite en calcularPrecio")
    void testTC06_CasoLimiteCalcularPrecio() {
        Hospedaje hos0 = new Hospedaje("A0", "Hosp_Nulo", "Simple", EstadoHabitacion.DISPONIBLE, 0.0);
        PaseoTuristico pas0 = new PaseoTuristico("B0", "Paseo_Nulo", 5, 0.0);
        PaqueteTuristico pkg0 = new PaqueteTuristico("PGK-0", "Conjunto 0", 0.0);
        pkg0.agregarItem(hos0);
        pkg0.agregarItem(pas0);

        double valor = pkg0.calcularPrecio();
        assertEquals(0.0, valor, "Al tener reservables con precios 0.0 (limite) devuelve un total de 0.0");
    }

    @Test
    @DisplayName("TC-07: Disponibilidad de paquete")
    void testTC07_retornarTrueSiComponenteEstaDisponible() {
        PaseoTuristico pas3Disponible = new PaseoTuristico("PAS-3.1", "Tour playa", 2, 30.0);
        PaqueteTuristico pkg2 = new PaqueteTuristico("PKG-2", "Pack Mixto", 0.0);
        pkg2.agregarItem(hos2);        
        pkg2.agregarItem(pas3Disponible); 

        boolean estaDisponible = pkg2.verificarDisponibilidad();

        assertTrue(estaDisponible, 
            "La disponibilidad global del paquete debe ser true si todos sus componentes están disponibles");
    }

    
}