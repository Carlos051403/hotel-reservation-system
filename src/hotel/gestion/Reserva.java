package hotel.gestion;

import hotel.economia.Cobrable;
import hotel.habitaciones.Habitacion;
import hotel.personas.Cliente;
import hotel.personas.Huesped;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class Reserva implements Cobrable {

    private LocalDate fechaInicio;
    private int numeroDias;
    private Cliente cliente;
    private Huesped[] huesped;
    private Habitacion habitacion;

    public Reserva(LocalDate fechaInicio, int numeroDias, Cliente cliente,
                   Huesped[] huesped, Habitacion habitacion) {
        this.fechaInicio = fechaInicio;
        this.numeroDias = numeroDias;
        this.cliente = cliente;
        this.huesped = huesped;
        this.habitacion = habitacion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(int numeroDias) {
        this.numeroDias = numeroDias;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Huesped[] getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped[] huesped) {
        this.huesped = huesped;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    //Este metodo nos devuelve él fecha de fin
    public LocalDate getFechaFin() {
        return fechaInicio.plusDays(numeroDias);
    }

    @Override
    public double getImporte() {
        return habitacion.getPrecio() * numeroDias;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return numeroDias == reserva.numeroDias && Objects.equals(fechaInicio, reserva.fechaInicio) && Objects.equals(cliente, reserva.cliente) && Objects.deepEquals(huesped, reserva.huesped) && Objects.equals(habitacion, reserva.habitacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaInicio, numeroDias, cliente, Arrays.hashCode(huesped), habitacion);
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "fechaInicio=" + fechaInicio +
                ", numeroDias=" + numeroDias +
                ", cliente=" + cliente +
                ", huesped=" + Arrays.toString(huesped) +
                ", habitacion=" + habitacion +
                '}';
    }
}
