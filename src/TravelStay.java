import java.util.ArrayList;
import java.util.List;

import Builder.PaqueteConcretoBuilder;
import Builder.PaqueteTuristicoConcretoBuilder;
import composite.Hospedaje;
import composite.PaqueteTuristico;
import composite.PaseoTuristico;
import composite.Reservable;
import composite.Reserva;
import composite.Usuario;
import enums.EstadoHabitacion;
import enums.EstadoPaseo;
import Observer.EmailNotificador;
import Observer.Notificador;
import Observer.WhatsAppNotificador;

public class TravelStay {

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("       DEMO SISTEMA TRAVELSTAY            ");
        

        // 1. Crear Usuario
        Usuario usuario1 = new Usuario("A1", "Maria Pacha", "maria@mail.com", "0999999999");

        // 2. Probar Patrón OBSERVER (Suscripciones)
        System.out.println("\n---> 1. PROBANDO PATRÓN OBSERVER");
        Notificador emailNotif = new EmailNotificador();
        Notificador wsNotif = new WhatsAppNotificador();

        emailNotif.agregarUsuario(usuario1);
        wsNotif.agregarUsuario(usuario1);

        //  Patrón BUILDER (Construir paquete compuesto)
        System.out.println("\n---> 2. PROBANDO PATRÓN BUILDER");
        PaqueteTuristicoConcretoBuilder builder = new PaqueteConcretoBuilder();
        PaqueteTuristico paqueteCombo = builder
                .setId("PKG-777")
                .setNombre("Paquete Galápagos Express")
                .setDescuento(15.0)
                .agregarItem(new Hospedaje("H01", "Hotel Playa", "Familiar", EstadoHabitacion.DISPONIBLE, 200.0))
                .agregarItem(new PaseoTuristico("P01", "Tour de Buceo", 10, EstadoPaseo.DISPONIBLE, 80.0))
                .build();

        // Patrón COMPOSITE (Calcular precios y reservas)
        System.out.println("\n---> 3. PROBANDO PATRÓN COMPOSITE");
        List<Reservable> itemsReserva = new ArrayList<>();
        itemsReserva.add(new Hospedaje("H02", "Hotel Resort", "Suite", EstadoHabitacion.DISPONIBLE, 300.0));
        itemsReserva.add(paqueteCombo); // Metemos el paquete (que adentro tiene más items)

        Reserva reserva = new Reserva(usuario1, itemsReserva);

        System.out.println("Reserva ID: " + reserva.getIdReserva());
        System.out.println("Cliente: " + reserva.getUsuario().getNombre());
        System.out.println("Total a pagar: $" + reserva.getTotal());

        // Enviar Notificación al confirmar
        System.out.println("\n---> 4. ENVIANDO NOTIFICACIONES");
        emailNotif.enviarNotificacion(usuario1, "Tu reserva " + reserva.getIdReserva() + " ha sido confirmada con éxito.");
        wsNotif.enviarNotificacion(usuario1, "¡Tu viaje está listo! Total abonado: $" + reserva.getTotal());
    }
}