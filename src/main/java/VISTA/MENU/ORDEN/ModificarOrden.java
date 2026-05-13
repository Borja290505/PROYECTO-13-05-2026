package VISTA.MENU.ORDEN;

import MODELO.OrdenReparacion;
import VISTA.VentanaBase;

import javax.swing.*;
import java.awt.*;

public class ModificarOrden extends VentanaBase {

    private JTextField txtMatricula;
    private JTextArea txtObservaciones;
    private JTextField txtPrecio;

    private JButton btnBuscar;
    private JButton btnModificar;
    private JButton btnVolver;

    public ModificarOrden() {
        super("Modificar Orden de Reparación");
        setLayout(new BorderLayout(10, 10));

        // =========================
        // PANEL SUPERIOR
        // =========================
        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Modificar Orden", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBuscar = new JPanel(new BorderLayout(10, 10));
        panelBuscar.add(new JLabel("Matrícula:"), BorderLayout.WEST);

        txtMatricula = new JTextField();
        panelBuscar.add(txtMatricula, BorderLayout.CENTER);

        btnBuscar = new JButton("Buscar");
        panelBuscar.add(btnBuscar, BorderLayout.EAST);

        panelSuperior.add(panelBuscar, BorderLayout.CENTER);
        add(panelSuperior, BorderLayout.NORTH);

        // =========================
        // FORMULARIO
        // =========================
        JPanel panelCentro = new JPanel(new GridLayout(4, 2, 10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        panelCentro.add(new JLabel("Observaciones:"));
        txtObservaciones = new JTextArea(3, 20);
        panelCentro.add(new JScrollPane(txtObservaciones));

        panelCentro.add(new JLabel("Precio (mano de obra):"));
        txtPrecio = new JTextField();
        panelCentro.add(txtPrecio);

        add(panelCentro, BorderLayout.CENTER);

        // =========================
        // PANEL INFERIOR
        // =========================
        JPanel panelSur = new JPanel();

        btnModificar = new JButton("Modificar");
        btnVolver = new JButton("Volver");

        panelSur.add(btnModificar);
        panelSur.add(btnVolver);

        add(panelSur, BorderLayout.SOUTH);

        // Al inicio NO se puede modificar
        setCamposEditables(false);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // =========================
    // MÉTODOS DE APOYO
    // =========================
    public void rellenarCampos(OrdenReparacion o) {
        txtObservaciones.setText(o.getObservaciones());
        txtPrecio.setText(String.valueOf(o.getPrecio()));
    }

    public void setCamposEditables(boolean editable) {
        txtObservaciones.setEditable(editable);
        txtPrecio.setEditable(editable);
        btnModificar.setEnabled(editable);
        txtMatricula.setEditable(!editable); // no se cambia matrícula
    }

    // =========================
    // GETTERS
    // =========================
    public JTextField getTxtMatricula() {
        return txtMatricula;
    }

    public JTextArea getTxtObservaciones() {
        return txtObservaciones;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}