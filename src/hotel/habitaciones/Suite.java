package hotel.habitaciones;

import hotel.economia.Precios;

public class Suite extends Habitacion {

    private String nombre;
    private int numeroPlazas;
    private String serviciosExtra;

    public Suite () {

    }

    public Suite(int numero, String descripcion,
                 String nombre, int numeroPlazas, String serviciosExtra) {
        super(numero, Precios.PRECIO_SUITE, descripcion);
        this.nombre = nombre;
        this.numeroPlazas = numeroPlazas;
        this.serviciosExtra = serviciosExtra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroPlazas() {
        return numeroPlazas;
    }

    public void setNumeroPlazas(int numeroPlazas) {
        this.numeroPlazas = numeroPlazas;
    }

    public String getServiciosExtra() {
        return serviciosExtra;
    }

    public void setServiciosExtra(String serviciosExtra) {
        this.serviciosExtra = serviciosExtra;
    }

    @Override
    public String toString() {
        return "Suite{" +
                "nombre='" + nombre + '\'' +
                ", numeroPlazas=" + numeroPlazas +
                ", serviciosExtra=" + serviciosExtra +
                "} " + super.toString();
    }
}
