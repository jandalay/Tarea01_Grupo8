<<<<<<< HEAD:src/Notificador.java
=======
package Observer;
import composite.Usuario;

>>>>>>> main:src/Observer/Notificador.java
public interface Notificador {
    //un mensaje para un usuario
    void enviarNotificacion(Usuario usuario, String mensaje);

    //un mensaje para un conjunto de usuario
    void enviarNotificacionMasivo(String mensaje);

    void agregarUsuario(Usuario usuario);
    void removerUsuario(Usuario usuario);
}
