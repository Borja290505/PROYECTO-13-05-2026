package VISTA.MENU.PRINCIPAL;

import VISTA.VentanaBase;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends VentanaBase {

    private JButton btnVehiculos;
    private JButton btnClientes;
    private JButton btnOrdenes;
    private JButton btnFacturacion;
    private JButton btnEstadisticas;
    private JButton btnSalir;

    private String usuario;

    public MenuPrincipal(String usuario) {
        super("Menú Principal");
        this.usuario = usuario;


        // =========================
        // PANEL SUPERIOR (TÍTULO + USUARIO ACTIVO)
        // =========================
        JPanel panelSuperior = new JPanel(new GridLayout(2, 1));

        JLabel titulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 20, 5, 20));

        JLabel lblUsuario = new JLabel(
                "Usuario activo: " + usuario,
                SwingConstants.CENTER
        );
        lblUsuario.setFont(new Font("Arial", Font.ITALIC, 14));
        lblUsuario.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));

        panelSuperior.add(titulo);
        panelSuperior.add(lblUsuario);

        add(panelSuperior, BorderLayout.NORTH);


        JPanel panelCentral = new JPanel(new GridLayout(2, 2, 40, 40));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 40, 30, 40));


        //Texto de cada boton
        btnClientes = new JButton("Clientes");
        btnVehiculos = new JButton("Vehiculos");
        btnOrdenes = new JButton("Ordenes");
        btnFacturacion = new JButton("Facturacion");
        btnEstadisticas = new JButton("Estadisticas");
        btnSalir = new JButton("Salir");

        //Tamaño que tiene cada boton
        Dimension tamaño = new Dimension(180, 100);
        btnClientes.setPreferredSize(tamaño);
        btnVehiculos.setPreferredSize(tamaño);
        btnOrdenes.setPreferredSize(tamaño);
        btnFacturacion.setPreferredSize(tamaño);
        btnEstadisticas.setPreferredSize(tamaño);
        btnSalir.setPreferredSize(tamaño);

        //Le damos a cada boton el mismo tipo de letra
        Font fuente = new Font("Arial", Font.BOLD, 14);
        btnClientes.setFont(fuente);
        btnVehiculos.setFont(fuente);
        btnOrdenes.setFont(fuente);
        btnFacturacion.setFont(fuente);
        btnEstadisticas.setFont(fuente);
        btnSalir.setFont(fuente);

        //Mostramos los botones
        panelCentral.add(btnClientes);
        panelCentral.add(btnVehiculos);
        panelCentral.add(btnOrdenes);
        panelCentral.add(btnFacturacion);
        panelCentral.add(btnEstadisticas);
        panelCentral.add(btnSalir);

        add(panelCentral, BorderLayout.CENTER);

        setVisible(true);
    }

    public JButton getBtnClientes() {
        return btnClientes;
    }

    public void setBtnClientes(JButton btnClientes) {
        this.btnClientes = btnClientes;
    }

    public JButton getBtnOrdenes() {
        return btnOrdenes;
    }

    public void setBtnOrdenes(JButton btnOrdenes) {
        this.btnOrdenes = btnOrdenes;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public void setBtnSalir(JButton btnSalir) {
        this.btnSalir = btnSalir;
    }

    public JButton getBtnVehiculos() {
        return btnVehiculos;
    }

    public void setBtnVehiculos(JButton btnVehiculos) {
        this.btnVehiculos = btnVehiculos;
    }

    public JButton getBtnFacturacion() {
        return btnFacturacion;
    }

    public void setBtnFacturacion(JButton btnFacturacion) {
        this.btnFacturacion = btnFacturacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public JButton getBtnEstadisticas() {
        return btnEstadisticas;
    }

    public void setBtnEstadisticas(JButton btnEstadisticas) {
        this.btnEstadisticas = btnEstadisticas;
    }
}