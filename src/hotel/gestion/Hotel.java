package hotel.gestion;

import hotel.utils.UtilFechas;
import hotel.habitaciones.Habitacion;
import hotel.habitaciones.HabitacionDoble;
import hotel.habitaciones.Suite;
import hotel.personas.Cliente;
import hotel.personas.Huesped;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Random;

public class Hotel {

    private Habitacion[] habitaciones;
    private Reserva[] reservas;
    private int cantidadReservas;

    public Hotel() {
        habitaciones = new Habitacion[10];
        reservas = new Reserva[100];
        cantidadReservas = 0;
        init(); // llamamos la inicializacion desde dentro del constructor
    }

    //creamos un metodo privado que sirva para inicializar
    private void init() {
        for(int i=0; i < 8; i++) {
            int numero = i + 1;
            String descripcion = "Habitación doble estándar";
            habitaciones[i] = new HabitacionDoble(numero, descripcion);
        }

        String[] nombresSuites = {"Mil y una noches", "Palacio Dorado"};

        //Esta clase Random nos permite generar numeros aleatorios
        Random random = new Random();
        for(int i = 0; i<2; i++) {
            int numero = 9 + i;
            String descripcion = "Suite de lujo";
            String nombre = "Suite " + nombresSuites[i];
            int plazas = random.nextInt(3) + 3; //Esto hace que devuelva un número aleatorio entre 0 y 2, nunca al tope
            String serviciosExtra = "Servicio de minibar, Acceso gratuíto al spa";
            habitaciones[i+8] = new Suite(numero, descripcion, nombre, plazas, serviciosExtra);
        }
    }

    /* Para llamar a este método debe haber comprobado antes que se puede hacer la
    *                        reserva de esta habitación */
    public Reserva agregarReserva(Cliente cliente, Huesped[] huespedes,
                                  LocalDate fechaInicio, int numeroDias, Habitacion habitacion){
        if (cantidadReservas >= reservas.length){
            reservas = Arrays.copyOf(reservas, cantidadReservas + 10);
        }
        Reserva r = new Reserva(fechaInicio, numeroDias, cliente, huespedes, habitacion);
        reservas[cantidadReservas++] = r;
        return r;
    }

    public void mostrarreserva() {

        System.out.println("RESERVAS DEL HOTEL");
        System.out.println("==================");
        System.out.println("");

        if (cantidadReservas == 0) {
            System.out.println("El hotel no dispone aun de reservas realizadas");
        }
        for (int i = 0; i < cantidadReservas; i++) {
            Reserva r = reservas[i];
            System.out.println("RESERVA");
            System.out.println("-------");
            System.out.println("Cliente: " + r.getCliente().getNombre());
            System.out.println("DNI: " + r.getCliente().getDni());
            System.out.println("N Huéspedes: " + (r.getHuesped().length + 1));
            System.out.println("Llegada: " + r.getFechaInicio()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Salida: " + r.getFechaFin()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("N# días: " + r.getNumeroDias());
            System.out.println("Importe: " + r.getImporte());
            System.out.println();
        }
        System.out.println();
    }

    //METODO PARA DISPONIBILIDAD DE HABITACIONES
    public boolean isHabitacionDisponible (int numero, LocalDate fecha, int numeroDias) {
        boolean disponible = true;
        int i = 0;
        while (i < cantidadReservas && disponible) {
            Reserva r = reservas[i];
            if (r.getHabitacion().getNumero() == numero) {
                disponible = !UtilFechas.overlaps(r.getFechaInicio(), r.getFechaFin(),
                        fecha, fecha.plusDays(numeroDias));
            }
                i++;
        }
        return disponible;
    }
    //METODO PARA OBTENER HABITACIONES DISPONIBLES
    // TIPO DEBE SER DOBLE O SUITE
    public Habitacion[] getHabitacionesDisponibles(String tipo, LocalDate fecha, int numeroDias) {
        Habitacion[] result = new Habitacion[10];
        int cantidad = 0;
        for (Habitacion h : habitaciones) {
            if (this.isHabitacionDisponible(h.getNumero(), fecha, numeroDias)) {
                if (tipo.toUpperCase().equals("DOBLE")) {
                    if (h instanceof HabitacionDoble) {
                        result[cantidad++] = h;
                    }
                }
                else {
                    if (h instanceof Suite) {
                        result[cantidad++] = h;
                    }
                }
            }
        }
        System.out.println("Debug - Habitaciones encontradas: " + cantidad);
        return Arrays.copyOf(result, cantidad);
    }
    /*
    * Una copia de las habitaciones
     */

    public Habitacion[] getHabitaciones() {
        return habitaciones.clone();
    }

    //Metodo que nos mostrara las habitaciones del hotel
    public void mostrarHabitaciones() {
        System.out.println("HABITACIONES DEL HOTEL");
        System.out.println("======================");
        System.out.println();

        for (Habitacion h : habitaciones) {

            String tipo = (h instanceof HabitacionDoble) ? "Habitación Doble" : "Suite";

            System.out.println("Habitacion N# " + h.getNumero());
            System.out.println("Tipo: " + tipo);
            System.out.println("Precio por noche: %.2f".formatted(h.getPrecio()));
            System.out.println("Descripción: " + h.getDescripcion());

            if (h instanceof Suite s) {
                System.out.println("Nombre: " + s.getNombre());
                System.out.println("Números de plazas: " + s.getNumeroPlazas());
                System.out.println("Servicios extra: " + s.getServiciosExtra());
            }

            String disponibilidad = (this.isHabitacionDisponible(h.getNumero(), LocalDate.now(), 1) ? "Sí" : "No");

            System.out.println("Disponible hoy: " + disponibilidad);

            System.out.println();
        }
    }

        //AGREGAMOS RESERVA CON COMPROBACIÓN
        public Reserva agregarReserva (Cliente cliente, Huesped[] huespedes, LocalDate fechaInicio,
                int numeroDias, String tipoHabitacion) {

        //Buscamos si hay una habitación disponible para nosotros hoy
        Habitacion[] disponibles = getHabitacionesDisponibles(tipoHabitacion, fechaInicio,numeroDias);
        Reserva r = null;

        if ( disponibles.length > 0) {
            // Revisamos si el número de húespedes se puede alojar
            if (tipoHabitacion.toUpperCase() == "DOBLE") {
                if (huespedes.length > 1) {
                    System.out.println("No se pueden alojar más de 2 personas en una habitación doble");
                    return r;
                } else {
                    r = agregarReserva(cliente, huespedes, fechaInicio, numeroDias, disponibles[0]);
                }
            }
            else {
                // Si es SUITE, comprobamos si caben los huéspedes habitación a habitación
                for (int i = 0; i < disponibles.length && r == null; i++) {
                    Suite s = (Suite) disponibles[i];
                    if (s.getNumeroPlazas() >= huespedes.length + 1) { //Cliente + Huespedes
                        r = agregarReserva(cliente, huespedes, fechaInicio, numeroDias, s);
                    }
                }
                if (r == null){
                    System.out.println("Lo sentimos, pero no hay ninguna SUITE disponible con esa capacidad");
                }
            }
        }else {
            System.out.println("Lo sentimos, pero no hay habitaciones disponibles");
        }
        return r;
    }
}