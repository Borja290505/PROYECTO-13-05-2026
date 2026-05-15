package VISTA.MENU.ORDEN;

import VISTA.VentanaBase;

import javax.swing.*;
import java.awt.*;

public class MenuOrden extends VentanaBase {
    private JButton btnNuevaOrden;
    private JButton btnListarOrdenes;
    private JButton btnVolver;
    private JButton btnModificarOrden;
    private JButton btnFinalizarOrden;

    public MenuOrden(){
        super("Menu Ordenes");

        setLayout(new BorderLayout(0, 20));

        JLabel titulo = new JLabel("Opciones de Ordenes", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 40, 40));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        btnListarOrdenes = new JButton("ListarOrdenes");
        btnNuevaOrden = new JButton("Nueva Orden");
        btnModificarOrden = new JButton("Modificar Orden");
        btnFinalizarOrden = new JButton("Finalizar Orden");

        Dimension tamaño = new Dimension(180, 120);
        btnListarOrdenes.setPreferredSize(tamaño);
        btnNuevaOrden.setPreferredSize(tamaño);
        btnModificarOrden.setPreferredSize(tamaño);
        btnFinalizarOrden.setPreferredSize(tamaño);

        Font fuenteBotones = new Font("Arial", Font.BOLD, 14);
        btnListarOrdenes.setFont(fuenteBotones);
        btnNuevaOrden.setFont(fuenteBotones);
        btnModificarOrden.setFont(fuenteBotones);
        btnFinalizarOrden.setFont(fuenteBotones);

        panelBotones.add(btnNuevaOrden);
        panelBotones.add(btnFinalizarOrden);
        panelBotones.add(btnListarOrdenes);
        panelBotones.add(btnModificarOrden);


        add(panelBotones, BorderLayout.CENTER);

        JPanel panelVolver = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelVolver.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        btnVolver = new JButton("Volver");
        btnVolver.setFont(fuenteBotones);
        panelVolver.add(btnVolver);

        add(panelVolver, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JButton getBtnListarOrdenes() {
        return btnListarOrdenes;
    }

    public void setBtnListarOrdenes(JButton btnListarOrdenes) {
        this.btnListarOrdenes = btnListarOrdenes;
    }

    public JButton getBtnModificarOrden() {
        return btnModificarOrden;
    }

    public void setBtnModificarOrden(JButton btnModificarOrden) {
        this.btnModificarOrden = btnModificarOrden;
    }

    public JButton getBtnNuevaOrden() {
        return btnNuevaOrden;
    }

    public void setBtnNuevaOrden(JButton btnNuevaOrden) {
        this.btnNuevaOrden = btnNuevaOrden;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public void setBtnVolver(JButton btnVolver) {
        this.btnVolver = btnVolver;
    }

    public JButton getBtnFinalizarOrden() {
        return btnFinalizarOrden;
    }

    public void setBtnFinalizarOrden(JButton btnFinalizarOrden) {
        this.btnFinalizarOrden = btnFinalizarOrden;
    }
}