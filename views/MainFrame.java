import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Interfaz para el carrito
interface ICarrito {
    void agregarProducto(Producto producto);
    void eliminarProducto(String nombreProducto);
    double calcularTotal();
    List<Producto> obtenerProductos();
}

// Clase Producto
class Producto {
    private String nombre;
    private int cantidad;
    private double precio;

    public Producto(String nombre, int cantidad, double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public double getPrecio() { return precio; }
    
    public double calcularSubtotal() { return cantidad * precio; }
}

// Implementación del carrito
class Carrito implements ICarrito {
    private List<Producto> productos;

    public Carrito() {
        this.productos = new ArrayList<>();
    }

    @Override
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    @Override
    public void eliminarProducto(String nombreProducto) {
        productos.removeIf(p -> p.getNombre().equalsIgnoreCase(nombreProducto));
    }

    @Override
    public double calcularTotal() {
        return productos.stream().mapToDouble(Producto::calcularSubtotal).sum();
    }

    @Override
    public List<Producto> obtenerProductos() {
        return productos;
    }
}

// Interfaz Gráfica
public class CarritoGUI extends JFrame {
    private Carrito carrito;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtNombre, txtCantidad, txtPrecio;
    private JLabel lblTotal;

    public CarritoGUI() {
        carrito = new Carrito();
        setTitle("Carrito de Compras");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior para agregar productos
        JPanel panelInput = new JPanel();
        panelInput.setLayout(new GridLayout(2, 3));
        panelInput.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelInput.add(txtNombre);
        panelInput.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField();
        panelInput.add(txtCantidad);
        panelInput.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panelInput.add(txtPrecio);
        
        JButton btnAgregar = new JButton("Agregar Producto");
        panelInput.add(btnAgregar);
        add(panelInput, BorderLayout.NORTH);

        // Tabla para mostrar productos
        tableModel = new DefaultTableModel(new String[]{"Nombre", "Cantidad", "Precio", "Subtotal"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        // Panel inferior con total y eliminar
        JPanel panelBottom = new JPanel();
        panelBottom.setLayout(new GridLayout(2, 1));
        lblTotal = new JLabel("Total: $0.00");
        panelBottom.add(lblTotal);
        JButton btnEliminar = new JButton("Eliminar Producto Seleccionado");
        panelBottom.add(btnEliminar);
        add(panelBottom, BorderLayout.SOUTH);

        // Acción botón Agregar
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                int cantidad = Integer.parseInt(txtCantidad.getText());
                double precio = Double.parseDouble(txtPrecio.getText());
                Producto p = new Producto(nombre, cantidad, precio);
                carrito.agregarProducto(p);
                actualizarTabla();
            }
        });

        // Acción botón Eliminar
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String nombre = tableModel.getValueAt(selectedRow, 0).toString();
                    carrito.eliminarProducto(nombre);
                    actualizarTabla();
                }
            }
        });
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        for (Producto p : carrito.obtenerProductos()) {
            tableModel.addRow(new Object[]{p.getNombre(), p.getCantidad(), p.getPrecio(), p.calcularSubtotal()});
        }
        lblTotal.setText("Total: $" + carrito.calcularTotal());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CarritoGUI().setVisible(true);
            }
        });
    }
}
