package VISTA.MENU.CLIENTE;

import CONTROLADOR.ClienteControlador;
import VISTA.VentanaBase;

import javax.swing.*;
import java.awt.*;

public class MenuCliente extends VentanaBase {

    private JButton btnAltaCliente;
    private JButton btnListarCliente;
    private JButton btnModificarCliente;
    private JButton btnVolver;

    public MenuCliente() {
        super("Menú Cliente");

        setLayout(new BorderLayout(0, 20));
        Font fuenteBotones = new Font("Arial", Font.BOLD, 14);

        JLabel titulo = new JLabel("Opciones de Cliente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 40, 40));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        btnAltaCliente = new JButton("Alta Cliente");
        btnListarCliente = new JButton("Lista Clientes");
        btnModificarCliente = new JButton("Modificar Cliente");

        Dimension tamaño = new Dimension(180, 120);
        btnAltaCliente.setPreferredSize(tamaño);
        btnListarCliente.setPreferredSize(tamaño);
        btnModificarCliente.setPreferredSize(tamaño);

        Font fuente = new Font("Arial", Font.BOLD,14);
        btnAltaCliente.setFont(fuente);
        btnListarCliente.setFont(fuente);
        btnModificarCliente.setFont(fuente);

        panelBotones.add(btnAltaCliente);
        panelBotones.add(btnListarCliente);
        panelBotones.add(btnModificarCliente);

        add(panelBotones, BorderLayout.CENTER);

        JPanel panelVolver = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelVolver.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        btnVolver = new JButton("Volver");
        btnVolver.setFont(fuenteBotones);
        panelVolver.add(btnVolver);

        add(panelVolver, BorderLayout.SOUTH);

        setVisible(true);
    }

    // ✅ IMPORTANTE → MÉTODO PARA EL CONTROLADOR
    public void setControlador(ClienteControlador c) {

        btnAltaCliente.addActionListener(e -> c.irAlta(this));
        btnListarCliente.addActionListener(e -> c.irLista(this));
        btnModificarCliente.addActionListener(e -> c.irModificar(this));
        btnVolver.addActionListener(e -> c.volverMenu(this));
    }

    // ===== GETTERS (OBLIGATORIOS) =====
    public JButton getBtnAltaCliente() {
        return btnAltaCliente;
    }

    public JButton getBtnListarCliente() {
        return btnListarCliente;
    }

    public JButton getBtnModificarCliente() {
        return btnModificarCliente;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}
