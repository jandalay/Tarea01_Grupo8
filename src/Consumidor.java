import composite.Usuario;


//Revisar bien la clase padre usuario con los demas exceso de lazy class
public class Consumidor extends Usuario {

    public Consumidor(String id, String nombre, String email, String telefono) {
        super(id, nombre, email, telefono);
    }
    
}
