package VISTA.MENU.CLIENTE;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import MODELO.Cliente;
import VISTA.VentanaBase;

public class ListaCliente extends VentanaBase {

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;

    public ListaCliente() {
        super("Listado de Clientes");

        setLayout(new BorderLayout(15, 15));

        // =========================
        // TÍTULO
        // =========================
        JLabel lblTitulo = new JLabel("Clientes Registrados");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // =========================
        // TABLA
        // =========================
        String[] columnas = {
                "DNI", "Nombre", "Apellidos", "Teléfono", "Email", "Dirección"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla solo lectura
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        add(scrollPane, BorderLayout.CENTER);


        JPanel panelSur = new JPanel();
        panelSur.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        btnVolver = new JButton("Volver al Menú");
        panelSur.add(btnVolver);

        add(panelSur, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // =========================
    // CARGAR DATOS
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

    public JButton getBtnVolver() {
        return btnVolver;
    }
}