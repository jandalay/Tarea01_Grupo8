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

 
    
}