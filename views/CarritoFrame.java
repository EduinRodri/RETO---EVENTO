package views;

import controllers.CarritoController;
import models.Producto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private CarritoController carritoController = new CarritoController();

    public MainFrame() {
        setTitle("TechMarket - Tienda Tecnológica");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 🖼️ Panel principal con productos
        JPanel panelProductos = new JPanel();
        panelProductos.setLayout(new GridLayout(3, 2, 10, 10));

        // 🛒 Lista de productos (ejemplo)
        Producto[] productos = {
                new Producto("Laptop", 650.00, 1),
                new Producto("Smartphone", 300.00, 1),
                new Producto("Auriculares", 50.00, 1),
                new Producto("Smartwatch", 120.00, 1),
                new Producto("Teclado mecánico", 80.00, 1),
                new Producto("Mouse inalámbrico", 40.00, 1)
        };

        for (Producto producto : productos) {
            JPanel panelItem = new JPanel();
            panelItem.setLayout(new BorderLayout());
            panelItem.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            JLabel lblNombre = new JLabel(producto.getNombre());
            JLabel lblPrecio = new JLabel("$" + producto.getPrecio());

            JButton btnAgregar = new JButton("Añadir al carrito 🛒");
            btnAgregar.addActionListener(e -> {
                carritoController.agregarAlCarrito(producto);
                JOptionPane.showMessageDialog(this, producto.getNombre() + " añadido al carrito.");
            });

            panelItem.add(lblNombre, BorderLayout.NORTH);
            panelItem.add(lblPrecio, BorderLayout.CENTER);
            panelItem.add(btnAgregar, BorderLayout.SOUTH);

            panelProductos.add(panelItem);
        }

        // 🔽 Scroll para la lista de productos
        JScrollPane scrollPane = new JScrollPane(panelProductos);

        // 📦 Botón para ver el carrito
        JButton btnVerCarrito = new JButton("🛍️ Ver Carrito");
        btnVerCarrito.addActionListener(e -> new CarritoFrame(carritoController));

        // 📌 Agregar elementos a la ventana
        add(scrollPane, BorderLayout.CENTER);
        add(btnVerCarrito, BorderLayout.SOUTH);

        setVisible(true);
    }
}