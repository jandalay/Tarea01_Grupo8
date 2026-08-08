package observer;
import java.util.ArrayList;
import java.util.List;

import composite.Usuario;

public class WhatsAppNotificador implements Notificador {

    private final List<Usuario> listaUsuarios;

    public WhatsAppNotificador() {
        listaUsuarios = new ArrayList<>();
    }

    @Override
    public void enviarNotificacion(Usuario usuario, String mensaje) {
        if (!listaUsuarios.contains(usuario)) {
            System.out.println("El usuario " + usuario.getNombre() + " no está suscrito a las notificaciones por WhatsApp.");
            return;
        }
        listaUsuarios.get(listaUsuarios.indexOf(usuario)).recibirNotificacion(mensaje);
    }

    @Override
    public void enviarNotificacionMasivo(String mensaje) {
        for (Usuario u : listaUsuarios) {
            u.recibirNotificacion("**WhatsApp** " + mensaje);
        }
    }

    @Override
    public void agregarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
        System.out.println("El usuario: [" + usuario + "] ha sido agregado");
        
    }

    @Override
    public void removerUsuario(Usuario usuario) {
        for (Usuario u : listaUsuarios) {
            if (u.equals(usuario)) {
                listaUsuarios.remove(listaUsuarios.indexOf(u));
                System.out.println("Usuario " + usuario + " ha sido removido.");
                return;
            }
        }
        System.out.println("No existe ese usuario");
        
    }

    @Override
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

}
