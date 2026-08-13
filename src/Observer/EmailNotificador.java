package observer;
import java.util.ArrayList;
import java.util.List;

import composite.Usuario;
public class EmailNotificador implements Notificador {
    private final List<Usuario> usuariosSuscritos;

    public EmailNotificador() {
        usuariosSuscritos = new ArrayList<>();
    }
    
    @Override
    public void enviarNotificacion(Usuario usuario, String mensaje) throws IllegalArgumentException {
        if (!usuariosSuscritos.contains(usuario)) {
            System.out.println("El usuario " + usuario.getNombre() + " no está suscrito a las notificaciones por correo electrónico.");
        }
        else {
            System.out.println("Mensaje para: " + usuario.getNombre() + "\nMensaje: " + mensaje);
        }
    }

    @Override
    public void enviarNotificacionMasivo(String mensaje) {
        for (Usuario usuario : usuariosSuscritos) {
            enviarNotificacion(usuario, mensaje);
        }
    }

    @Override
    public void agregarUsuario(Usuario usuario) {
        if(usuariosSuscritos.contains(usuario)) {
            System.out.println("El usuario " + usuario.getNombre() + " ya está suscrito a las notificaciones por correo electrónico.");
        }
        else {
            usuariosSuscritos.add(usuario);
            System.out.println("Usuario " + usuario.getNombre() + " ha sido agregado.");
        }
    }

    @Override
    public void removerUsuario(Usuario usuario) {
        if (!usuariosSuscritos.remove(usuario)) {
            System.out.println("El usuario " + usuario.getNombre() + " no está suscrito a las notificaciones por correo electrónico.");
        }
        else {
            System.out.println("Usuario " + usuario.getNombre() + " ha sido eliminado.");
        }

    }

    public List<Usuario> getListaUsuarios() {
        return usuariosSuscritos;
    }

    
}