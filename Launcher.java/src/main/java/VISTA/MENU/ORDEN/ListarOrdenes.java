package VISTA.MENU.ORDEN;

import MODELO.OrdenReparacion;
import VISTA.VentanaBase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarOrdenes extends VentanaBase {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JButton btnVolver;

    public ListarOrdenes() {
        super("Listado de Órdenes de Reparación");

        setLayout(new BorderLayout());

        // Modelo de tabla
        modelo = new DefaultTableModel();
        tabla = new JTable(modelo);

        modelo.addColumn("Matrícula");
        modelo.addColumn("Fecha Apertura");
        modelo.addColumn("Fecha Cierre");
        modelo.addColumn("Fecha Estimada Cierre");
        modelo.addColumn("Estado");
        modelo.addColumn("KM Entrada");
        modelo.addColumn("Precio");
        modelo.addColumn("Observaciones");

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnVolver = new JButton("Volver");
        add(btnVolver, BorderLayout.SOUTH);
    }

    public void cargarDatos(List<OrdenReparacion> lista) {

        modelo.setRowCount(0); // limpiar tabla

        for (OrdenReparacion o : lista) {
            modelo.addRow(new Object[]{
                    o.getVehiculo().getMatricula(),
                    o.getFechaApertura(),
                    o.getFechaCierre(),
                    o.getFechaEstimadaCierre(),
                    o.getEstado(),
                    o.getKmEntrada(),
                    o.getPrecio(),
                    o.getObservaciones()

            });
        }
    }

    // =========================
    // GETTER BOTÓN VOLVER
    // =========================
    public JButton getBtnVolver() {
        return btnVolver;
    }

    public void setBtnVolver(JButton btnVolver) {
        this.btnVolver = btnVolver;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public JTable getTabla() {
        return tabla;
    }

    public void setTabla(JTable tabla) {
        this.tabla = tabla;
    }
}