package Builder;

import composite.PaqueteTuristico;
import composite.Reservable;

public interface PaqueteTuristicoConcretoBuilder {
    void reset();
    PaqueteTuristicoConcretoBuilder setId(String id);
    PaqueteTuristicoConcretoBuilder setNombre(String nombre);
    PaqueteTuristicoConcretoBuilder setDescuento(double descuento);
    PaqueteTuristicoConcretoBuilder agregarItem(Reservable item);
    PaqueteTuristico build();
}