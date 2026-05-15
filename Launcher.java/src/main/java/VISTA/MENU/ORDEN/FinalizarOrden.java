package VISTA.MENU.ORDEN;

import VISTA.VentanaBase;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FinalizarOrden extends VentanaBase {

    private JTextField txtMatricula;
    private JTextArea txtObservaciones;
    private JButton btnFinalizar, btnVolver;

    public FinalizarOrden() {
        super("Finalizar Orden de Reparación");
        // Asegura que aparezca el nombre en la barra o pestaña superior
        setTitle("Finalizar Orden de Reparación");

        // Layout principal con márgenes exteriores limpios
        setLayout(new BorderLayout(15, 15));
        ((JPanel)getContentPane()).setBorder(new EmptyBorder(15, 20, 15, 20));

        // Fuentes del proyecto
        Font fuenteComponentes = new Font("Arial", Font.PLAIN, 14);
        Font fuenteBotones = new Font("Arial", Font.BOLD, 14);

        // =========================
        // TÍTULO
        // =========================
        JLabel lblTitulo = new JLabel("Finalizar Orden de Reparación", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo, BorderLayout.NORTH);

        // =========================
        // FORMULARIO (Sin estirarse feo)
        // =========================
        // Usamos un GridLayout de 4 filas. Las filas 3 y 4 empujan los campos hacia arriba
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));

        // Fila 1: Matrícula
        JLabel lblMatricula = new JLabel("Matrícula:");
        lblMatricula.setFont(fuenteComponentes);
        panelForm.add(lblMatricula);
        txtMatricula = new JTextField();
        txtMatricula.setFont(fuenteComponentes);
        panelForm.add(txtMatricula);

        // Fila 2: Observaciones finales
        JLabel lblObs = new JLabel("Observaciones finales:");
        lblObs.setFont(fuenteComponentes);
        panelForm.add(lblObs);

        txtObservaciones = new JTextArea(3, 20);
        txtObservaciones.setFont(fuenteComponentes);

        panelForm.add(new JScrollPane(txtObservaciones));

        // Filas 3 y 4: Huecos vacíos para mantener los cuadros pequeños
        panelForm.add(new JLabel(""));
        panelForm.add(new JLabel(""));
        panelForm.add(new JLabel(""));
        panelForm.add(new JLabel(""));

        add(panelForm, BorderLayout.CENTER);

        // =========================
        // BOTONES
        // =========================
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

        btnFinalizar = new JButton("Finalizar Orden");
        btnFinalizar.setFont(fuenteBotones);

        btnVolver = new JButton("Volver");
        btnVolver.setFont(fuenteBotones);

        panelBotones.add(btnFinalizar);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    // =========================
    // GETTERS Y SETTERS
    // =========================
    public JButton getBtnFinalizar() {
        return btnFinalizar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public JTextField getTxtMatricula() {
        return txtMatricula;
    }

    public JTextArea getTxtObservaciones() {
        return txtObservaciones;
    }

    public void setBtnFinalizar(JButton btnFinalizar) {
        this.btnFinalizar = btnFinalizar;
    }

    public void setBtnVolver(JButton btnVolver) {
        this.btnVolver = btnVolver;
    }

    public void setTxtMatricula(JTextField txtMatricula) {
        this.txtMatricula = txtMatricula;
    }

    public void setTxtObservaciones(JTextArea txtObservaciones) {
        this.txtObservaciones = txtObservaciones;
    }


}