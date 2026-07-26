import java.util.Objects;

public class Usuario {
    private String id;
    private String nombre;
    private String email;
    private String telefono;

    public Usuario(String nombre, String email, String telefono) {
        //El id es un número aleatorio
        this.id = String.valueOf(Math.random()*1000.0);
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public void recibirNotificacion(String mensaje) {
        System.out.println("[Notificación] " + mensaje);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Usuario otro = (Usuario) o;
        if (Objects.equals(this.nombre, otro.nombre) && Objects.equals(this.id, otro.id) && Objects.equals(email, otro.email) && Objects.equals(telefono, otro.telefono)) {
            return true;
        }
        else {
            return false;
        }
    }

    //getters y setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombre + "(" + id + ")";
    }

}
