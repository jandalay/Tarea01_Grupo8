package Builder;

import composite.PaqueteTuristico;
import composite.Reservable;

public interface PaqueteTuristicoBuilder {
    void reset();
    PaqueteTuristicoBuilder setId(String id);
    PaqueteTuristicoBuilder setNombre(String nombre);
    PaqueteTuristicoBuilder setDescuento(double descuento);
    PaqueteTuristicoBuilder agregarItem(Reservable item);
    PaqueteTuristico build();
}