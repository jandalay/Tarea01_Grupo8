//ESTA CLASE ES PARA SIMULAR TRAVELSTAY

import java.util.Iterator;

public class InterfazReserva {

    public static void mostrarReserva(Reserva reserva) {
        System.out.println("\nBienvenido " + reserva.getUsuario().getNombre());
        System.out.println("Usted puede reservar en:");
        Iterator<Reservable> iterator = reserva.getItemsReservados().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
