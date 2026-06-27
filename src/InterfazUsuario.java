//ESTA CLASE ES PARA SIMULAR TRAVELSTAY

import java.util.Scanner;

public class InterfazUsuario {
    public static Usuario mostrarInterfazUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-- TravelStay --");
        System.out.println("Cree su propio usuario usando este formato[nombre/email/telefono]: ");
        try {
            String user1 = scanner.nextLine();
            String[] usuarioDatos = user1.split("/");
            return new Usuario(usuarioDatos[0], usuarioDatos[1], usuarioDatos[2]);
        }
        catch (Exception e) {
            System.out.println("Datos erróneos, vuelve a intentar");
            mostrarInterfazUsuario();
        }
        return null;
    }
}
