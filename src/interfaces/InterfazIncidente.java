//ESTA CLASE ES PARA SIMULAR TRAVELSTAY

import java.util.Scanner;

public class InterfazIncidente {
    
    public static Incidente reportarIncidente(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Qué desea reportar?");
        String reporte = scanner.nextLine();
        return new Incidente(reporte, usuario);
    }

    public static void reportarSoporte(Usuario usuario, Reserva reserva, AgenteSoporte agente) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escriba el ID del servicio");
        String reporte = scanner.nextLine();
        for (Reservable r: reserva.getItemsReservados()) {
            if (r instanceof Hospedaje h) {
                if(h.getId().equals(reporte)) {
                    agente.escalaHotel(h);
                    System.out.println("Nuestro agente " + agente.getNombre() + " está resolviendo:\n" + h);
                    return;
                }
            }
            if (r instanceof PaseoTuristico p) {
                if(p.getId().equals(reporte)) {
                    agente.escalaPaseo(p);
                    System.out.println("Nuestro agente " + agente.getNombre() + " está resolviendo:\n" + p);
                    return;
                }
            }
        }
        System.out.println("No es posible atender o no está en la reserva");
    }
}
