package observer;

import java.util.*;
import composite.Usuario;

public abstract class abstractNotificador implements Notificador {
    // Lista de observadores protegida y reutilizable
    protected final List<Usuario> usuariosSuscritos = new ArrayList<>();

    @Override
    public void agregarUsuario(Usuario usuario) {
        if (usuario == null) return;
        if (usuariosSuscritos.contains(usuario)) {
            System.out.println("El usuario " + usuario.getNombre() + " ya está suscrito.");
            return;
        }
        usuariosSuscritos.add(usuario);
        System.out.println("Usuario " + usuario.getNombre() + " ha sido agregado.");
    }

    @Override
    public void removerUsuario(Usuario usuario) {
        if (!usuariosSuscritos.remove(usuario)) {
            System.out.println("El usuario no está suscrito.");
        } else {
            System.out.println("Usuario " + usuario.getNombre() + " ha sido removido.");
        }
    }

    @Override
    public List<Usuario> getListaUsuarios() {
        // Se devuelve una vista inmodificable para evitar encapsulación rota (Inappropriate Intimacy)
        return Collections.unmodifiableList(usuariosSuscritos);
    }
}
