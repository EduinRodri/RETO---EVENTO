package models;

public abstract class Producto implements IDescuentoAplicable {
    // Atributos
    protected String nombre;
    protected int cantidad;
    protected double precio;

    // Constructor
    public Producto(String nombre, int cantidad, double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // getters
    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    // Metodos
    public double calcularSubtotal() {
        return cantidad * precio;
    }

}
