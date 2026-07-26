package Builder;

import java.util.ArrayList;
import java.util.List;
import composite.PaqueteTuristico;
import composite.Reservable;

public class PaqueteConcretoBuilder implements PaqueteTuristicoConcretoBuilder {
    private String id;
    private String nombre;
    private double descuento;
    private List<Reservable> items;

    public PaqueteConcretoBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.id = "PKG-" + (int)(Math.random() * 9000 + 1000);
        this.nombre = "Nuevo Paquete";
        this.descuento = 0.0;
        this.items = new ArrayList<>();
    }

    @Override
    public PaqueteTuristicoConcretoBuilder setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public PaqueteTuristicoConcretoBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @Override
    public PaqueteTuristicoConcretoBuilder setDescuento(double descuento) {
        this.descuento = descuento;
        return this;
    }

    @Override
    public PaqueteTuristicoConcretoBuilder agregarItem(Reservable item) {
        this.items.add(item);
        return this;
    }

    @Override
    public PaqueteTuristico build() {
        PaqueteTuristico resultado = new PaqueteTuristico(this.id, this.nombre, this.descuento, this.items);
        this.reset();
        return resultado;
    }
}