package VISTA.MENU.FACTURACION;

import javax.swing.*;
import java.awt.*;

import VISTA.VentanaBase;

public class MenuFacturacion extends VentanaBase {

    private JButton btnListarFacturas;
    private JButton btnBuscarFacturas;
    private JButton btnVolver;

    public MenuFacturacion(){
        super("Menú de Facturación");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // =========================
        // TÍTULO
        // =========================
        JLabel lblTitulo = new JLabel("Gestión de Facturas");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // =========================
        // PANEL CENTRAL (BOTONES)
        // =========================
        JPanel panelCentro = new JPanel(new GridLayout(1, 2, 15, 15));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        btnListarFacturas = new JButton("Listar Facturas");
        btnBuscarFacturas = new JButton("Buscar Facturas");

        panelCentro.add(btnListarFacturas);
        panelCentro.add(btnBuscarFacturas);

        add(panelCentro, BorderLayout.CENTER);

        // =========================
        // PANEL SUR
        // =========================
        JPanel panelSur = new JPanel();
        panelSur.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        btnVolver = new JButton("Volver");
        panelSur.add(btnVolver);

        add(panelSur, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // =========================
    // GETTERS
    // =========================
    public JButton getBtnListarFacturas() {
        return btnListarFacturas;
    }

    public JButton getBtnBuscarFacturas() {
        return btnBuscarFacturas;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}