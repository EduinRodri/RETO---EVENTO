package Controller;

import java.util.ArrayList;
import java.util.List;

// Clase que representa un carrito de compras
public class Carrito {
    
    // Lista de productos en el carrito
    private List<Producto> productos;
    
    // Precio total del carrito
    private double precioTotal;
    
    // Cantidad total de productos en el carrito
    private int cantidadTotal;

    // Constructor: Inicializa el carrito vacío
    public Carrito() {
        this.productos = new ArrayList<>();
        this.precioTotal = 0.0;
        this.cantidadTotal = 0;
    }

    // Método para agregar un producto al carrito
    public void agregarProducto(Producto producto, int cantidad) {
        if (producto != null && cantidad > 0) {
            productos.add(producto);
            precioTotal += producto.getPrecio() * cantidad;
            cantidadTotal += cantidad;
        }
    }

    // Método para eliminar un producto del carrito
    public void eliminarProducto(Producto producto) {
        if (productos.remove(producto)) {
            precioTotal -= producto.getPrecio();
            cantidadTotal--;
        }
    }

    // Método para calcular el precio total del carrito
    public double calcularTotal() {
        return precioTotal;
    }

    // Método para mostrar los productos en el carrito
    public void mostrarProductos() {
        if (productos.isEmpty()) {
            System.out.println("El carrito está vacío.");
        } else {
            System.out.println("Productos en el carrito:");
            for (Producto producto : productos) {
                System.out.println("- " + producto.getNombre() + " | Precio: " + producto.getPrecio());
            }
            System.out.println("Total: " + precioTotal);
        }
    }

    // Método para vaciar el carrito
    public void vaciarCarrito() {
        productos.clear();
        precioTotal = 0.0;
        cantidadTotal = 0;
    }

    // Getters para obtener información del carrito
    public double getPrecioTotal() {
        return precioTotal;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public List<Producto> getProductos() {
        return productos;
    }
}
