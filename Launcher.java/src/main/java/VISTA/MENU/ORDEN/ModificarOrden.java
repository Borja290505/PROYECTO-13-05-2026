package VISTA.MENU.ORDEN;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

import VISTA.VentanaBase;
import MODELO.OrdenReparacion;

public class ModificarOrden extends VentanaBase {

    private JTextField txtMatricula;
    private JComboBox<String> comboEstado;
    private JTextField txtObservaciones;
    private JTextField txtPrecio;
    private JTextField txtFechaEstimada;

    private JButton btnBuscar;
    private JButton btnModificar;
    private JButton btnVolver;

    public ModificarOrden() {

        super("Modificar Orden de Reparación");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // =========================
        // TÍTULO
        // =========================
        JLabel lblTitulo = new JLabel("Gestión de Modificaciones de Órdenes");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // =========================
        // FORMULARIO
        // =========================
        JPanel formulario = new JPanel(new GridLayout(6, 2, 10, 10));
        formulario.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        formulario.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        formulario.add(txtMatricula);

        btnBuscar = new JButton("Buscar");
        formulario.add(btnBuscar);
        formulario.add(new JLabel(""));

        formulario.add(new JLabel("Estado:"));
        comboEstado = new JComboBox<>(
                new String[]{"Abierta", "En proceso", "Finalizada"}
        );
        formulario.add(comboEstado);

        formulario.add(new JLabel("Observaciones:"));
        txtObservaciones = new JTextField();
        formulario.add(new JScrollPane(txtObservaciones));

        formulario.add(new JLabel("Precio (€):"));
        txtPrecio = new JTextField();
        formulario.add(txtPrecio);

        formulario.add(new JLabel("Fecha estimada (YYYY-MM-DD):"));
        txtFechaEstimada = new JTextField();
        formulario.add(txtFechaEstimada);

        add(formulario, BorderLayout.CENTER);

        // =========================
        // BOTONES
        // =========================
        JPanel botones = new JPanel();

        btnModificar = new JButton("Guardar Cambios");
        btnVolver = new JButton("Volver");

        botones.add(btnModificar);
        botones.add(btnVolver);

        add(botones, BorderLayout.SOUTH);

        // Bloqueamos campos hasta buscar
        setCamposEditables(false);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // =========================
    // MÉTODOS DE APOYO
    // =========================
    public void setCamposEditables(boolean editable) {
        comboEstado.setEnabled(editable);
        txtObservaciones.setEditable(editable);
        txtPrecio.setEditable(editable);
        txtFechaEstimada.setEditable(editable);
        btnModificar.setEnabled(editable);
    }

    public void rellenarCampos(OrdenReparacion o) {
        comboEstado.setSelectedItem(o.getEstado());
        txtObservaciones.setText(o.getObservaciones());
        txtPrecio.setText(String.valueOf(o.getPrecio()));
        txtFechaEstimada.setText(
                o.getFechaEstimadaCierre() != null
                        ? o.getFechaEstimadaCierre().toString()
                        : ""
        );
    }

    // =========================
    // GETTERS
    // =========================
    public JTextField getTxtMatricula() { return txtMatricula; }
    public JComboBox<String> getComboEstado() { return comboEstado; }
    public JTextField getTxtObservaciones() { return txtObservaciones; }
    public JTextField getTxtPrecio() { return txtPrecio; }
    public JTextField getTxtFechaEstimada() { return txtFechaEstimada; }

    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnModificar() { return btnModificar; }
    public JButton getBtnVolver() { return btnVolver; }
}