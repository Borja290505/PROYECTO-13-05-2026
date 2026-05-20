package VISTA.MENU.ORDEN;

import CONTROLADOR.OrdenRepControlador;
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

        setLayout(new BorderLayout(10, 10));
        Font fuenteBotones = new Font("Arial", Font.BOLD, 14);


        // =========================
        // TÍTULO
        // =========================
        JLabel lblTitulo = new JLabel("Órdenes de Reparación", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // =========================
        // TABLA
        // =========================
        modelo = new DefaultTableModel();
        tabla = new JTable(modelo);

        // Columnas
        modelo.addColumn("Matrícula");
        modelo.addColumn("Apertura");
        modelo.addColumn("Cierre");
        modelo.addColumn("Estado");
        modelo.addColumn("KM");
        modelo.addColumn("Precio");
        modelo.addColumn("Observaciones");

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        add(scroll, BorderLayout.CENTER);

        // =========================
        // BOTONES
        // =========================
        JPanel panelBotones = new JPanel();

        btnVolver = new JButton("Volver");
        btnVolver.setFont(fuenteBotones);

        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        // Acción volver
        btnVolver.addActionListener(e -> dispose());
    }

    // =========================
    // CARGAR DATOS
    // =========================
    public void cargarDatos(List<OrdenReparacion> lista) {

        modelo.setRowCount(0); // limpiar tabla

        for (OrdenReparacion o : lista) {
            modelo.addRow(new Object[]{
                    o.getVehiculo().getMatricula(),
                    o.getFechaApertura(),
                    o.getFechaCierre(),
                    o.getEstado(),
                    o.getKmEntrada(),
                    o.getPrecio(),
                    o.getObservaciones()
            });
        }
    }
    public void setControlador(OrdenRepControlador c){

        btnVolver.addActionListener(e -> c.volverMenu(this));
    }
    // =========================
    // GETTERS
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