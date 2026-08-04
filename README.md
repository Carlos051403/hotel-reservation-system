# 🏨 Hotel Reservation System (Java POO)

Sistema de gestión de reservas hoteleras desarrollado en Java aplicando los pilares de la **Programación Orientada a Objetos (POO)**. Este proyecto forma parte de mi evolución técnica en el desarrollo backend (DAW), enfocado en salir de la zona de confort mediante una arquitectura modular avanzada.

---

## 🎯 Enfoque y Defensa del Proyecto

Aunque la lógica algorítmica de solapamiento de fechas y la validación cruzada supusieron un reto complejo, lo que domino al 100% de este sistema es su diseño arquitectónico y de empaquetado:
* **Separación de responsabilidades:** La lógica financiera y de cobros está aislada en el paquete de economía, mientras que la operativa central recae sobre la gestión.
* **Encapsulamiento estricto:** Uso de clases abstractas, herencia y polimorfismo aplicado en tipos de habitaciones (`HabitacionDoble`, `Suite`).
* **Utilidades limpias:** Centralización de validaciones de fechas mediante clases de utilidad estáticas (`UtilFechas`).

---

## 🛠️ Estructura del Proyecto

```text
src/
│
├── hotel/
│   ├── economia/         # Lógica de cobros y servicios
│   ├── gestion/          # Clases principales (Hotel, Reserva)
│   ├── habitaciones/     # Jerarquía de habitaciones (Habitacion, Doble, Suite)
│   ├── personas/         # Gestión de clientes, huéspedes y personas
│   ├── utils/            # Funciones auxiliares de control de fechas (UtilFechas)
│   └── App.java          # Clase principal de ejecución

💻 Tecnologías Utilizadas
Java (JDK 17+)

IntelliJ IDEA

Git & GitHub (Control de versiones)
