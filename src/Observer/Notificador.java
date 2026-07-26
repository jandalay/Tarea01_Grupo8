package Observer;
import composite.Usuario;

public interface Notificador {
    //un mensaje para un usuario
    void enviarNotificacion(Usuario usuario, String mensaje);

    //un mensaje para un conjunto de usuario
    void enviarNotificacionMasivo(String mensaje);
    
    void agregarUsuario(Usuario usuario);
    void removerUsuario(Usuario usuario);
}
