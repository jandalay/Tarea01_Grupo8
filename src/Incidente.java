public class Incidente implements Comparable<Incidente> {
    
    private int prioridad;
    private String descripcion;
    private Usuario usuario;
    
    public Incidente(String descripcion, Usuario usuario) {
        this.descripcion = descripcion;
        this.usuario = usuario;
    }
    
    public void establecerPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
    
    @Override
    public int compareTo(Incidente otro) {
        return Integer.compare(otro.prioridad, prioridad);
    }

}
