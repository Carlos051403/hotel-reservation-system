package hotel;

import hotel.gestion.Hotel;
import hotel.gestion.Reserva;
import hotel.personas.Cliente;
import hotel.personas.Huesped;

import java.time.LocalDate;

public class App {
    public static void main (String[] args) {

        Hotel hotel = new Hotel();
        hotel.mostrarHabitaciones();

        Cliente c1 = new Cliente("Carlos Reyes", "12345678", 35, "12345678");
        Huesped h1 = new Huesped("Marta López", "23564789", 33);

        Reserva r1 = hotel.agregarReserva(c1, new Huesped[] {h1}, LocalDate.now(), 2, "DOBLE");

        if (r1 != null) {
            System.out.println("La Reserva se ha realizado correctamente");
        }

        Cliente c2 = new Cliente("Alejandro Reyes", "4578965", 36, "58966632");
        Huesped h2 = new Huesped("Lucía sol Madrid", "7894532", 40);
        Huesped h3 = new Huesped("Martina Muñoz", "12345875", 12 );

        Reserva r2 = hotel.agregarReserva(c2, new Huesped[] {h2, h3}, LocalDate.now(), 3, "SUITE");

        hotel.mostrarreserva();
        hotel.mostrarHabitaciones();
    }

}
