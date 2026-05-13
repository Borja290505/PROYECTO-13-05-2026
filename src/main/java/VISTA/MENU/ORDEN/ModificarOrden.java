package VISTA.MENU.ORDEN;

import MODELO.OrdenReparacion;
import VISTA.VentanaBase;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        setTitle("Modificar Orden de Reparación");

        // Layout principal con márgenes limpios
        setLayout(new BorderLayout(15, 15));
        ((JPanel)getContentPane()).setBorder(new EmptyBorder(15, 20, 15, 20));

        Font fuenteComponentes = new Font("Arial", Font.PLAIN, 14);
        Font fuenteBotones = new Font("Arial", Font.BOLD, 14);

        // =========================
        // TÍTULO (Corregido: Ahora se añade al panel norte)
        // =========================
        JPanel panelNorte = new JPanel(new GridLayout(2, 1, 0, 10));

        JLabel lblTituloVentana = new JLabel("Modificar Orden de Reparación", JLabel.CENTER);
        lblTituloVentana.setFont(new Font("Arial", Font.BOLD, 18));
        panelNorte.add(lblTituloVentana);

        // Panel Búsqueda integrado en la zona norte
        JPanel panelBusqueda = new JPanel(new BorderLayout(10, 10));
        JLabel lblMatricula = new JLabel("Matrícula:  ");
        lblMatricula.setFont(fuenteComponentes);
        panelBusqueda.add(lblMatricula, BorderLayout.WEST);

        txtMatricula = new JTextField();
        txtMatricula.setFont(fuenteComponentes);
        panelBusqueda.add(txtMatricula, BorderLayout.CENTER);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(fuenteBotones);

        panelBusqueda.add(btnBuscar, BorderLayout.EAST);

        panelNorte.add(panelBusqueda);
        add(panelNorte, BorderLayout.NORTH);

        // =========================
        // FORMULARIO (Corregido: Cuadros más pequeños)
        // =========================
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));

        // Fila 1: Observaciones
        JLabel lblObservaciones = new JLabel("Observaciones:");
        lblObservaciones.setFont(fuenteComponentes);
        panelForm.add(lblObservaciones);

        txtObservaciones = new JTextArea(3, 20); // 3 filas de alto máximas
        txtObservaciones.setFont(fuenteComponentes);

        panelForm.add(new JScrollPane(txtObservaciones));

        // Fila 2: Precio
        JLabel lblPrecio = new JLabel("Precio (€):");
        lblPrecio.setFont(fuenteComponentes);
        panelForm.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setFont(fuenteComponentes);
        panelForm.add(txtPrecio);

        // Fila 3 y 4: Huecos vacíos para evitar que el layout estire los componentes de arriba
        panelForm.add(new JLabel(""));
        panelForm.add(new JLabel(""));
        panelForm.add(new JLabel(""));
        panelForm.add(new JLabel(""));

        add(panelForm, BorderLayout.CENTER);

        // =========================
        // BOTONES DE ACCIÓN
        // =========================
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

        btnModificar = new JButton("Modificar");
        btnModificar.setFont(fuenteBotones);

        btnVolver = new JButton("Volver");
        btnVolver.setFont(fuenteBotones);

        panelBotones.add(btnModificar);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        setCamposEditables(false);
        btnVolver.addActionListener(e -> dispose());
    }

    // Métodos de apoyo y getters/setters se mantienen exactamente igual
    public void rellenarCampos(OrdenReparacion o) {
        txtObservaciones.setText(o.getObservaciones());
        txtPrecio.setText(String.valueOf(o.getPrecio()));
    }

    public void setCamposEditables(boolean editable) {
        txtObservaciones.setEditable(editable);
        txtPrecio.setEditable(editable);
        btnModificar.setEnabled(editable);
        txtMatricula.setEditable(!editable);
    }

    public JTextField getTxtMatricula() { return txtMatricula; }
    public JTextArea getTxtObservaciones() { return txtObservaciones; }
    public JTextField getTxtPrecio() { return txtPrecio; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnModificar() { return btnModificar; }
    public JButton getBtnVolver() { return btnVolver; }
    public void setBtnBuscar(JButton btnBuscar) { this.btnBuscar = btnBuscar; }
    public void setBtnModificar(JButton btnModificar) { this.btnModificar = btnModificar; }
    public void setBtnVolver(JButton btnVolver) { this.btnVolver = btnVolver; }
    public void setTxtMatricula(JTextField txtMatricula) { this.txtMatricula = txtMatricula; }
    public void setTxtObservaciones(JTextArea txtObservaciones) { this.txtObservaciones = txtObservaciones; }
    public void setTxtPrecio(JTextField txtPrecio) { this.txtPrecio = txtPrecio; }
}