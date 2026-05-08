package VISTA.MENU.VEHICULO;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import MODELO.Vehiculo;
import VISTA.VentanaBase;

public class ListarVehiculo extends VentanaBase {

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;

    public ListarVehiculo() {
        super("Listado de Vehículos");
        setLayout(new BorderLayout(10, 10));

        // Configuración de la tabla
        String[] columnas = {
            "Matrícula", "Marca", "Modelo", "Año", "Kms", "ID Cliente"
        };

        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Botón volver
        btnVolver = new JButton("Volver al Menú");
        JPanel panelSur = new JPanel();
        panelSur.add(btnVolver);
        add(panelSur, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void cargarDatos(List<Vehiculo> vehiculos) {
        modeloTabla.setRowCount(0); // Limpiar tabla
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

    public JButton getBtnVolver() {
        return btnVolver;
    }
}
