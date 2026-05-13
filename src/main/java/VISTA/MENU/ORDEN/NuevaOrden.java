package VISTA.MENU.ORDEN;

import MODELO.OrdenReparacion;
import MODELO.Vehiculo;
import VISTA.VentanaBase;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;

public class NuevaOrden extends VentanaBase {

    private JTextField txtMatricula;
    private JTextField txtKm;
    private JTextField txtPrecio;
    private JTextArea txtObservaciones;

    private JButton btnCrear;
    private JButton btnVolver;

    public NuevaOrden() {
        super("Nueva Orden de Reparación");
        // Asegura que aparezca el nombre en la pestaña o barra superior de Windows
        setTitle("Nueva Orden de Reparación");

        // Layout principal con márgenes exteriores limpios
        setLayout(new BorderLayout(15, 15));
        ((JPanel)getContentPane()).setBorder(new EmptyBorder(15, 20, 15, 20));

        // Fuentes estándar del proyecto
        Font fuenteComponentes = new Font("Arial", Font.PLAIN, 14);
        Font fuenteBotones = new Font("Arial", Font.BOLD, 14);

        // =========================
        // TÍTULO
        // =========================
        JLabel lblTitulo = new JLabel("Nueva Orden de Reparación", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo, BorderLayout.NORTH);

        // =========================
        // FORMULARIO (Ordenado y sin estirarse)
        // =========================
        // Usamos un GridLayout de 6 filas. Las filas 5 y 6 se quedan vacías para empujar los cuadros hacia arriba
        JPanel panelForm = new JPanel(new GridLayout(6, 2, 10, 10));

        // Fila 1
        JLabel lblMatricula = new JLabel("Matrícula:");
        lblMatricula.setFont(fuenteComponentes);
        panelForm.add(lblMatricula);
        txtMatricula = new JTextField();
        txtMatricula.setFont(fuenteComponentes);
        panelForm.add(txtMatricula);

        // Fila 2
        JLabel lblKm = new JLabel("KM Entrada:");
        lblKm.setFont(fuenteComponentes);
        panelForm.add(lblKm);
        txtKm = new JTextField();
        txtKm.setFont(fuenteComponentes);
        panelForm.add(txtKm);

        // Fila 3
        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setFont(fuenteComponentes);
        panelForm.add(lblPrecio);
        txtPrecio = new JTextField();
        txtPrecio.setFont(fuenteComponentes);
        panelForm.add(txtPrecio);

        // Fila 4
        JLabel lblObs = new JLabel("Observaciones:");
        lblObs.setFont(fuenteComponentes);
        panelForm.add(lblObs);

        txtObservaciones = new JTextArea(3, 20);
        txtObservaciones.setFont(fuenteComponentes);
        txtObservaciones.setLineWrap(true); // Salto de línea automático
        txtObservaciones.setWrapStyleWord(true);
        panelForm.add(new JScrollPane(txtObservaciones));

        // Filas 5 y 6: Espacios vacíos de relleno para hacer los campos más pequeños
        panelForm.add(new JLabel(""));
        panelForm.add(new JLabel(""));
        panelForm.add(new JLabel(""));
        panelForm.add(new JLabel(""));

        add(panelForm, BorderLayout.CENTER);

        // =========================
        // BOTONES
        // =========================
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

        btnCrear = new JButton("Crear Orden");
        btnCrear.setFont(fuenteBotones);

        btnVolver = new JButton("Volver");
        btnVolver.setFont(fuenteBotones);

        panelBotones.add(btnCrear);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        // Acción volver
        btnVolver.addActionListener(e -> dispose());
    }

    // =========================
    // CONSTRUIR ORDEN
    // =========================
    public OrdenReparacion getOrdenFormulario(Vehiculo vehiculo) {
        OrdenReparacion orden = new OrdenReparacion();

        orden.setFechaApertura(LocalDate.now());
        orden.setKmEntrada(Integer.parseInt(txtKm.getText()));
        orden.setEstado("ABIERTA");
        orden.setObservaciones(txtObservaciones.getText());
        orden.setPrecio(Double.parseDouble(txtPrecio.getText()));
        orden.setVehiculo(vehiculo);

        return orden;
    }

    // =========================
    // GETTERS Y SETTERS
    // =========================
    public JButton getBtnCrear() {
        return btnCrear;
    }

    public JTextField getTxtMatricula() {
        return txtMatricula;
    }

    public JTextField getTxtKm() {
        return txtKm;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public JTextArea getTxtObservaciones() {
        return txtObservaciones;
    }

    public void setBtnCrear(JButton btnCrear) {
        this.btnCrear = btnCrear;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public void setBtnVolver(JButton btnVolver) {
        this.btnVolver = btnVolver;
    }

    public void setTxtKm(JTextField txtKm) {
        this.txtKm = txtKm;
    }

    public void setTxtMatricula(JTextField txtMatricula) {
        this.txtMatricula = txtMatricula;
    }

    public void setTxtObservaciones(JTextArea txtObservaciones) {
        this.txtObservaciones = txtObservaciones;
    }

    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }
}