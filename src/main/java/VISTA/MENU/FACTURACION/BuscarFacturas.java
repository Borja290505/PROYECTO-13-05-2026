package VISTA.MENU.FACTURACION;

import MODELO.Factura;
import VISTA.VentanaBase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BuscarFacturas extends VentanaBase {

    private JComboBox<String> comboTipoBusqueda;
    private JTextField txtValor;
    private JButton btnBuscar, btnVolver;

    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public BuscarFacturas() {

        super("Buscar Facturas");
        setLayout(new BorderLayout(10, 10));

        // =========================
        // PANEL SUPERIOR (TÍTULO + BUSCADOR)
        // =========================
        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Buscar Facturas");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBuscar = new JPanel(new GridLayout(2, 2, 10, 10));
        panelBuscar.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        comboTipoBusqueda = new JComboBox<>(
                new String[]{"Matrícula", "DNI Cliente"}
        );
        txtValor = new JTextField();

        panelBuscar.add(new JLabel("Buscar por:"));
        panelBuscar.add(comboTipoBusqueda);
        panelBuscar.add(new JLabel("Valor:"));
        panelBuscar.add(txtValor);

        panelSuperior.add(panelBuscar, BorderLayout.CENTER);

        add(panelSuperior, BorderLayout.NORTH);

        // =========================
        // TABLA
        // =========================
        String[] columnas = {
                "ID Factura", "Fecha", "Subtotal", "IVA", "Total", "Matricula"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // =========================
        // PANEL SUR
        // =========================
        JPanel panelSur = new JPanel();
        btnBuscar = new JButton("Buscar");
        btnVolver = new JButton("Volver");

        panelSur.add(btnBuscar);
        panelSur.add(btnVolver);

        add(panelSur, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // =========================
    // CARGAR RESULTADOS
    // =========================
        public void cargarDatos(List<Factura> facturas) {

        modeloTabla.setRowCount(0);

        for (Factura f : facturas) {
            Object[] fila = {
                    f.getIdFactura(),
                    f.getFechaFactura(),
                    f.getSubtotal(),
                    f.getIva(),
                    f.getTotal(),
                    f.getMatricula()
            };
            modeloTabla.addRow(fila);
        }
    }

    // GETTERS
    public JComboBox<String> getComboTipoBusqueda() {
        return comboTipoBusqueda;
    }

    public JTextField getTxtValor() {
        return txtValor;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}