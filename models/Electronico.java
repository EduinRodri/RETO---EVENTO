package models;

public class Electronico extends Producto {
    // Constructor
    public Electronico(String nombre, int cantidad, double precio) {
        super(nombre, cantidad, precio);
    }

    // Método para calcular el descuento
    @Override
    public double calcularDescuento() {
        return (cantidad >= 3) ? calcularSubtotal() * 0.15 : 0;
    }

}
