import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TravelStay {

    private static Usuario usuario;
    
    //PRUEBA
    public static void main(String[] args) {
        List<Reservable> lista = new ArrayList<>();
        lista.add(new Hospedaje("A024", "Hospedaje_ABC", "TIPO A", 500));
        lista.add(new PaseoTuristico("B068", "Paseo_PAI", 9, 340));
        lista.add(new PaqueteTuristico("C909", "Paquete_PXP", 25, 
        Arrays.asList(new Hospedaje("A107", "Hospedaje_DFA", "TIPO B", 430), 
        new PaseoTuristico("B702", "Paseo_DGE", 5, 120))));


        usuario = InterfazUsuario.mostrarInterfazUsuario();

        Reserva reserva = new Reserva(usuario, lista);

        InterfazReserva.mostrarReserva(reserva);


    }
}
