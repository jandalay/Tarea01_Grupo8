package observer;
import java.util.ArrayList;
import java.util.List;

import composite.Usuario;
//Revisar el importe y la verificacion del usuario
public class EmailNotificador implements Notificador {
    private final List<Usuario> usuariosSuscritos = new ArrayList<>();
    
    @Override
    public void enviarNotificacion(Usuario usuario, String mensaje) {
        if (!usuariosSuscritos.contains(usuario)) {
            System.out.println("El usuario " + usuario.getNombre() + " no está suscrito a las notificaciones por correo electrónico.");
            return;
        }
        System.out.println("Mensaje para: " + usuario.getNombre() + "\nMensaje: " + mensaje);
    }
//Completada verificacion y envio de mensaje
    @Override
    public void enviarNotificacionMasivo(String mensaje) {
        for (Usuario usuario : usuariosSuscritos) {
            enviarNotificacion(usuario, mensaje);
        }
    }
//Revisar la verificacion de usuario y envio de mensaje 
    @Override
    public void agregarUsuario(Usuario usuario) {
        if(usuariosSuscritos.contains(usuario)) {
            System.out.println("El usuario " + usuario.getNombre() + " ya está suscrito a las notificaciones por correo electrónico.");
            return;
        }
        usuariosSuscritos.add(usuario);
        System.out.println("Usuario " + usuario.getNombre() + " ha sido agregado.");
    }

    @Override
    public void removerUsuario(Usuario usuario) {
        boolean elim = usuariosSuscritos.remove(usuario);
        if (!elim) {
            System.out.println("El usuario " + usuario.getNombre() + " no está suscrito a las notificaciones por correo electrónico.");
            return;
        }
        System.out.println("Usuario " + usuario.getNombre() + " ha sido eliminado.");

    }
    public List<Usuario> getListaUsuarios() {
        return usuariosSuscritos;
    }

    
}