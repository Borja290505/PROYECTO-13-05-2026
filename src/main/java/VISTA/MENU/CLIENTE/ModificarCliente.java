package VISTA.MENU.CLIENTE;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import MODELO.Cliente;
import VISTA.VentanaBase;

public class ModificarCliente extends VentanaBase {

    private JTextField txtDni, txtNombre, txtApellidos, txtTelefono, txtEmail, txtDireccion;
    private JButton btnBuscar, btnModificar, btnVolver;

    public ModificarCliente() {
        super("Modificar Cliente");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));


        JLabel lblTitulo = new JLabel("Gestión de Modificaciones de Clientes");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        add(lblTitulo, BorderLayout.NORTH);


        JPanel formulario = new JPanel(new GridLayout(7, 2, 10, 10));
        formulario.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        formulario.add(new JLabel("DNI:"));
        txtDni = new JTextField();
        formulario.add(txtDni);

        btnBuscar = new JButton("Buscar");
        formulario.add(btnBuscar);
        formulario.add(new JLabel(""));

        formulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        formulario.add(txtNombre);

        formulario.add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField();
        formulario.add(txtApellidos);

        formulario.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        formulario.add(txtTelefono);

        formulario.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formulario.add(txtEmail);

        formulario.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        formulario.add(txtDireccion);

        add(formulario, BorderLayout.CENTER);


        JPanel panelBotones = new JPanel();
        btnModificar = new JButton("Guardar Cambios");
        btnVolver = new JButton("Volver");

        panelBotones.add(btnModificar);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);

        // Bloqueamos los campos hasta buscar
        setCamposEditables(false);

        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void rellenarCampos(Cliente c) {
        txtNombre.setText(c.getNombre());
        txtApellidos.setText(c.getApellidos());
        txtTelefono.setText(c.getTelefono());
        txtEmail.setText(c.getEmail());
        txtDireccion.setText(c.getDireccion());
    }

    public void setCamposEditables(boolean estado) {
        txtNombre.setEditable(estado);
        txtApellidos.setEditable(estado);
        txtTelefono.setEditable(estado);
        txtEmail.setEditable(estado);
        txtDireccion.setEditable(estado);
        btnModificar.setEnabled(estado);
    }

    public JTextField getTxtDni() { return txtDni; }
    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtApellidos() { return txtApellidos; }
    public JTextField getTxtTelefono() { return txtTelefono; }
    public JTextField getTxtEmail() { return txtEmail; }
    public JTextField getTxtDireccion() { return txtDireccion; }

    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnModificar() { return btnModificar; }
    public JButton getBtnVolver() { return btnVolver; }
}