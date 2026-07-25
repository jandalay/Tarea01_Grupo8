package Builder;

import composite.PaqueteTuristico;
import composite.Reservable;

public class PaqueteConcretoBuilder implements PaqueteTuristicoBuilder {
    private PaqueteTuristico paquete;

    public PaqueteConcretoBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        String idGenerado = "PKG-" + (int)(Math.random() * 9000 + 1000);
        this.paquete = new PaqueteTuristico(idGenerado, "Nuevo Paquete", 0.0);
    }

    @Override
    public PaqueteTuristicoBuilder setId(String id) {
        this.paquete.setId(id);
        return this;
    }

    @Override
    public PaqueteTuristicoBuilder setNombre(String nombre) {
        this.paquete.setNombre(nombre);
        return this;
    }

    @Override
    public PaqueteTuristicoBuilder setDescuento(double descuento) {
        this.paquete.setDescuento(descuento);
        return this;
    }

    @Override
    public PaqueteTuristicoBuilder agregarItem(Reservable item) {
        this.paquete.agregarItem(item);
        return this;
    }

    @Override
    public PaqueteTuristico build() {
        PaqueteTuristico resultado = this.paquete;
        this.reset();
        return resultado;
    }
}