package VISTA.MENU.VEHICULO;

import MODELO.Vehiculo;
import VISTA.VentanaBase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarVehiculo extends VentanaBase {

    private JTable tabla;
    private DefaultTableModel modeloTabla;

    private JTextField txtBuscarMatricula;
    private JButton btnBuscar;
    private JButton btnVolver;

    public ListarVehiculo() {
        super("Listado de Vehículos");
        setLayout(new BorderLayout(10, 10));
        Font fuenteBotones = new Font("Arial", Font.BOLD, 14);

        // =========================
        // PANEL SUPERIOR (TÍTULO + BUSCADOR)
        // =========================
        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Vehículos Registrados", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBuscar = new JPanel(new BorderLayout(10, 10));
        panelBuscar.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        panelBuscar.add(new JLabel("Buscar por Matrícula:"), BorderLayout.WEST);

        txtBuscarMatricula = new JTextField();
        panelBuscar.add(txtBuscarMatricula, BorderLayout.CENTER);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(fuenteBotones);
        panelBuscar.add(btnBuscar, BorderLayout.EAST);

        panelSuperior.add(panelBuscar, BorderLayout.CENTER);
        add(panelSuperior, BorderLayout.NORTH);

        // =========================
        // TABLA
        // =========================
        String[] columnas = {
                "Matrícula", "Marca", "Modelo", "Año", "Km", "ID Cliente"
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

    // =========================
    // CARGAR DATOS
    // =========================
    public void cargarDatos(List<Vehiculo> vehiculos) {
        modeloTabla.setRowCount(0);

        for (Vehiculo v : vehiculos) {
            Object[] fila = {
                    v.getMatricula(),
                    v.getMarca(),
                    v.getModelo(),
                    v.getAnio(),
                    v.getKmsActuales(),
                    v.getIdCliente()
            };
            modeloTabla.addRow(fila);
        }
    }

    // =========================
    // GETTERS PARA CONTROLADOR
    // =========================
    public JTextField getTxtBuscarMatricula() {
        return txtBuscarMatricula;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}