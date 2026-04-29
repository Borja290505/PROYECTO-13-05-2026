package VISTA.INICIO;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import MODELO.Cliente;
import VISTA.VentanaBase;

public class RegistroCliente extends VentanaBase {

    private final JTextField txtDni, txtNombre, txtApellidos, txtTelefono, txtEmail, txtDireccion;
    private final JPasswordField txtContraseña;
    private final JButton btnRegistrar;
    private final JButton btnVolver;

    public RegistroCliente() {
        super("Registro de Cliente");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ===== FORMULARIO =====
        JPanel formulario = new JPanel(new GridLayout(7, 2, 15, 15));

        formulario.add(new JLabel("DNI:"));
        txtDni = new JTextField();
        formulario.add(txtDni);

        formulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        formulario.add(txtNombre);

        formulario.add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField();
        formulario.add(txtApellidos);

        formulario.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        formulario.add(txtTelefono);

        formulario.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        formulario.add(txtDireccion);

        formulario.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formulario.add(txtEmail);

        formulario.add(new JLabel("Contraseña:"));
        txtContraseña = new JPasswordField();
        formulario.add(txtContraseña);

        // ✅ SCROLL AQUÍ
        JScrollPane scroll = new JScrollPane(formulario);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scroll, BorderLayout.CENTER);


        // ===== BOTONES =====
        JPanel botones = new JPanel(new GridLayout(1, 2, 10, 10));

        btnRegistrar = new JButton("Registrar");
        btnVolver = new JButton("Volver");

        botones.add(btnRegistrar);
        botones.add(btnVolver);

        add(botones, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public Cliente getClienteFormulario() {
        Cliente c = new Cliente();
        c.setDni(txtDni.getText());
        c.setNombre(txtNombre.getText());
        c.setApellidos(txtApellidos.getText());
        c.setTelefono(txtTelefono.getText());
        c.setEmail(txtEmail.getText());
        c.setDireccion(txtDireccion.getText());
        c.setContraseña(new String(txtContraseña.getPassword()));
        return c;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }


}