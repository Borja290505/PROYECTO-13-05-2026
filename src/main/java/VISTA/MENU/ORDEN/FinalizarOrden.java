package VISTA.MENU.ORDEN;

import DAO.OrdenReparacionDAO;
import VISTA.VentanaBase;

import javax.swing.*;
import java.awt.*;

public class FinalizarOrden extends VentanaBase {

    private JTextField txtMatricula, txtObservaciones;
    private JButton btnFinalizar, btnVolver;

    public FinalizarOrden() {
        super("Finalizar Orden de Reparación");

        setLayout(new GridLayout(4, 2, 10, 10));

        // Matrícula
        add(new JLabel("Matrícula"));
        txtMatricula = new JTextField();
        add(txtMatricula);

        // Observaciones finales
        add(new JLabel("Observaciones finales"));
        txtObservaciones = new JTextField();
        add(new JScrollPane(txtObservaciones));

        btnFinalizar = new JButton("Finalizar Orden");
        btnVolver = new JButton("Volver");

        add(btnFinalizar);
        add(btnVolver);

        configurarEventos();
    }

    private void configurarEventos() {

        btnFinalizar.addActionListener(e -> {

            if (txtMatricula.getText().isBlank()) {
                JOptionPane.showMessageDialog(this,
                        "Debes introducir una matrícula",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // ✅ AHORA ES UN INT
            int idOrden = OrdenReparacionDAO.finalizarOrdenPorMatricula(
                    txtMatricula.getText(),
                    txtObservaciones.getText()
            );

            // ✅ COMPROBAMOS EL VALOR
            if (idOrden == -1) {
                JOptionPane.showMessageDialog(this,
                        "No existe una orden ABIERTA para esa matrícula",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this,
                    "Orden finalizada correctamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            dispose();
        });
    }

    // GETTERS
    public JButton getBtnFinalizar() {
        return btnFinalizar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public JTextField getTxtMatricula() {
        return txtMatricula;
    }

    public JTextField getTxtObservaciones() {
        return txtObservaciones;
    }
}