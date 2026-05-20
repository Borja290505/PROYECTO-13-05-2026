package VISTA.MENU.CLIENTE;

import CONTROLADOR.ClienteControlador;
import MODELO.Cliente;
import VISTA.VentanaBase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListaCliente extends VentanaBase {

    private JTable tabla;
    private DefaultTableModel modeloTabla;

    private JComboBox<String> comboTipoBusqueda;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnVolver;

    public ListaCliente() {
        super("Listado de Clientes");
        setLayout(new BorderLayout(15, 15));
        Font fuenteBotones = new Font("Arial", Font.BOLD, 14);

        // =========================
        // PANEL SUPERIOR (TÍTULO + BUSCADOR)
        // =========================
        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Clientes Registrados", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBuscar = new JPanel(new GridLayout(2, 2, 10, 10));
        panelBuscar.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        comboTipoBusqueda = new JComboBox<>(new String[]{
                "DNI",
                "Nombre / Apellidos"
        });

        txtBuscar = new JTextField();
        btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(fuenteBotones);

        panelBuscar.add(new JLabel("Buscar por:"));
        panelBuscar.add(comboTipoBusqueda);
        panelBuscar.add(new JLabel("Valor:"));
        panelBuscar.add(txtBuscar);

        panelSuperior.add(panelBuscar, BorderLayout.CENTER);
        add(panelSuperior, BorderLayout.NORTH);

        // =========================
        // TABLA
        // =========================
        String[] columnas = {
                "DNI", "Nombre", "Apellidos", "Teléfono", "Email", "Dirección"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.getTableHeader().setReorderingAllowed(false);

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // =========================
        // PANEL INFERIOR
        // =========================
        JPanel panelSur = new JPanel();
        btnVolver = new JButton("Volver");
        btnVolver.setFont(fuenteBotones);

        panelSur.add(btnBuscar);
        panelSur.add(btnVolver);

        add(panelSur, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ✅ MÉTODO CLAVE (LO QUE TE FALTABA)
    public void setControlador(ClienteControlador c){

        btnBuscar.addActionListener(e -> c.buscarClientes(this));
        btnVolver.addActionListener(e -> c.volverMenu(this));
    }

    // =========================
    // CARGAR DATOS EN TABLA
    // =========================
    public void cargarDatos(List<Cliente> clientes) {
        modeloTabla.setRowCount(0);

        for (Cliente c : clientes) {
            Object[] fila = {
                    c.getDni(),
                    c.getNombre(),
                    c.getApellidos(),
                    c.getTelefono(),
                    c.getEmail(),
                    c.getDireccion()
            };
            modeloTabla.addRow(fila);
        }
    }

    // =========================
    // GETTERS PARA EL CONTROLADOR
    // =========================
    public JComboBox<String> getComboTipoBusqueda() {
        return comboTipoBusqueda;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}