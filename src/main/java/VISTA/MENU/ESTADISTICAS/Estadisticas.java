package VISTA.MENU.ESTADISTICAS;

import VISTA.VentanaBase;

import javax.swing.*;
import java.awt.*;

public class Estadisticas extends VentanaBase {

    private JLabel lblClientes;
    private JLabel lblVehiculos;
    private JLabel lblSinIva;
    private JLabel lblIva;
    private JLabel lblConIva;

    private JButton btnVolver;

    public Estadisticas() {

        super("Estadísticas Generales");
        setLayout(new BorderLayout(10, 10));
        Font fuenteBotones = new Font("Arial", Font.BOLD, 14);


        // TÍTULO
        JLabel lblTitulo = new JLabel("Estadísticas del Taller");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // PANEL CENTRAL
        JPanel panelCentro = new JPanel(new GridLayout(5, 1, 10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        lblClientes = new JLabel();
        lblVehiculos = new JLabel();
        lblSinIva = new JLabel();
        lblIva = new JLabel();
        lblConIva = new JLabel();

        panelCentro.add(lblClientes);
        panelCentro.add(lblVehiculos);
        panelCentro.add(lblSinIva);
        panelCentro.add(lblIva);
        panelCentro.add(lblConIva);

        add(panelCentro, BorderLayout.CENTER);

        // PANEL SUR
        btnVolver = new JButton("Volver");
        btnVolver.setFont(fuenteBotones);

        JPanel panelSur = new JPanel();
        panelSur.add(btnVolver);
        add(panelSur, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MÉTODO PARA CARGAR LOS DATOS
    public void cargarDatos(int clientes, int vehiculos,
                            double sinIva, double iva, double conIva) {

        lblClientes.setText("Total clientes: " + clientes);
        lblVehiculos.setText("Total vehículos: " + vehiculos);
        lblSinIva.setText("Total facturado sin IVA: " + sinIva + " €");
        lblIva.setText("IVA total: " + iva + " €");
        lblConIva.setText("Total facturado con IVA: " + conIva + " €");
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}