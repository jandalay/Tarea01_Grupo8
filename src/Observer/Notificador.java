package observer;
import java.util.List;

import composite.Usuario;

public interface Notificador {
    //un mensaje para un usuario
    void enviarNotificacion(Usuario usuario, String mensaje) throws IllegalArgumentException;

    //un mensaje para un conjunto de usuario
    void enviarNotificacionMasivo(String mensaje);
    
    void agregarUsuario(Usuario usuario);
    void removerUsuario(Usuario usuario);
    List<Usuario> getListaUsuarios();
}
