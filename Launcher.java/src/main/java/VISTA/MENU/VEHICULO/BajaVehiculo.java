package VISTA.MENU.VEHICULO;

import VISTA.VentanaBase;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BajaVehiculo extends VentanaBase {
    private JTextField txtMatricula;
    private JButton btnEliminar, btnVolver;

    public BajaVehiculo(){
        super("Baja Vehiculo");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel lblInfo = new JLabel("Introduce la matrícula del vehículo a eliminar", SwingConstants.CENTER);
        lblInfo.setFont(new Font("Arial", Font.BOLD, 14));
        lblInfo.setBorder(new EmptyBorder(20,0,10,0));
        add(lblInfo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 50));

        JLabel lblMatricula = new JLabel("Matrícula:");
        lblMatricula.setFont(new Font("Arial", Font.BOLD, 16));

        txtMatricula = new JTextField();
        txtMatricula.setPreferredSize(new Dimension(200, 35));

        formulario.add(lblMatricula);
        formulario.add(txtMatricula);
        add(formulario,BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 10));
        panelBotones.setBorder(new EmptyBorder(10, 20, 20, 20));

        btnEliminar = new JButton("Dar de Baja");
        btnEliminar.setBackground(new Color(255, 102, 102)); // Un color rojizo

        btnVolver = new JButton("Volver");

        panelBotones.add(btnEliminar);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnVolver() {
        return btnVolver    ;
    }

    public JTextField getTxtMatricula() {
        return txtMatricula;
    }
}
