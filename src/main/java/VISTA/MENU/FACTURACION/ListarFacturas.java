package VISTA.MENU.FACTURACION;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import MODELO.Factura;
import VISTA.VentanaBase;

public class ListarFacturas extends VentanaBase {

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;

    public ListarFacturas() {

        super("Listado de Facturas");
        setLayout(new BorderLayout(15, 15));

        // =========================
        // TÍTULO
        // =========================
        JLabel lblTitulo = new JLabel("Facturas Registradas");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // =========================
        // TABLA
        // =========================
        String[] columnas = {
                "Fecha", "Subtotal", "IVA", "Total", "Matricula"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // solo lectura
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        add(scrollPane, BorderLayout.CENTER);

        // =========================
        // PANEL INFERIOR
        // =========================
        JPanel panelSur = new JPanel();
        panelSur.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        btnVolver = new JButton("Volver");
        panelSur.add(btnVolver);

        add(panelSur, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // =========================
    // CARGAR DATOS EN LA TABLA
    // =========================
    public void cargarDatos(List<Factura> facturas) {

        modeloTabla.setRowCount(0); // limpiar tabla

        for (Factura f : facturas) {
            Object[] fila = {
                    f.getFechaFactura(),
                    f.getSubtotal(),
                    f.getIva(),
                    f.getTotal(),
                    f.getMatricula()
            };
            modeloTabla.addRow(fila);
        }
    }

    // =========================
    // GETTER
    // =========================
    public JButton getBtnVolver() {
        return btnVolver;
    }
}