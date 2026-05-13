package VISTA.MENU.VEHICULO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MODELO.Cliente;
import VISTA.VentanaBase;

public class AltaVehiculo extends VentanaBase {

    private JTextField txtMatricula, txtMarca, txtModelo, txtAnio, txtKms;
    private JTextField txtBuscarDni;
    private JLabel lblClienteEncontrado;

    private Cliente clienteEncontrado;

    private JButton btnGuardar, btnVolver, btnBuscarCliente;

    public AltaVehiculo() {
        super("Alta Vehículo");
        setLayout(new BorderLayout(10, 10));
        Font fuenteBotones = new Font("Arial", Font.BOLD, 14);


        // =========================
        // TÍTULO
        // =========================
        JLabel lblTitulo = new JLabel("Registrar Nuevo Vehículo", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // =========================
        // FORMULARIO
        // =========================
        JPanel formulario = new JPanel(new GridLayout(8, 2, 10, 10));
        formulario.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        formulario.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        formulario.add(txtMatricula);

        formulario.add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        formulario.add(txtMarca);

        formulario.add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        formulario.add(txtModelo);

        formulario.add(new JLabel("Año:"));
        txtAnio = new JTextField();
        formulario.add(txtAnio);

        formulario.add(new JLabel("Km actuales:"));
        txtKms = new JTextField();
        formulario.add(txtKms);

        formulario.add(new JLabel("Cliente (DNI):"));
        txtBuscarDni = new JTextField();
        formulario.add(txtBuscarDni);

        btnBuscarCliente = new JButton("Buscar cliente");
        formulario.add(btnBuscarCliente);
        btnBuscarCliente.setFont(fuenteBotones);

        lblClienteEncontrado = new JLabel("Cliente no seleccionado");
        formulario.add(lblClienteEncontrado);

        add(formulario, BorderLayout.CENTER);

        // =========================
        // BOTONES
        // =========================
        JPanel panelBotones = new JPanel();
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(fuenteBotones);
        btnVolver = new JButton("Volver");
        btnVolver.setFont(fuenteBotones);

        panelBotones.add(btnGuardar);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    // =========================
    // GETTERS PARA EL CONTROLADOR
    // =========================
    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public JButton getBtnBuscarCliente() {
        return btnBuscarCliente;
    }

    public JTextField getTxtMatricula() {
        return txtMatricula;
    }

    public JTextField getTxtMarca() {
        return txtMarca;
    }

    public JTextField getTxtModelo() {
        return txtModelo;
    }

    public JTextField getTxtAnio() {
        return txtAnio;
    }

    public JTextField getTxtKms() {
        return txtKms;
    }

    public JTextField getTxtBuscarDni() {
        return txtBuscarDni;
    }

    // =========================
    // CLIENTE SELECCIONADO
    // =========================
    public void setClienteEncontrado(Cliente c) {
        this.clienteEncontrado = c;

        if (c != null) {
            lblClienteEncontrado.setText(
                    c.getNombre() + " " + c.getApellidos()
            );
            lblClienteEncontrado.setForeground(Color.GREEN);
        } else {
            lblClienteEncontrado.setText("Cliente no encontrado");
            lblClienteEncontrado.setForeground(Color.RED);
        }
    }

    public Cliente getClienteSeleccionado() {
        return clienteEncontrado;
    }
}