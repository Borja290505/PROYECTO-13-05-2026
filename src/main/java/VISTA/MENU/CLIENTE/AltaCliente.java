package VISTA.MENU.CLIENTE;

import CONTROLADOR.ClienteControlador;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

import MODELO.Cliente;
import VISTA.VentanaBase;

public class AltaCliente extends VentanaBase {

    private JTextField txtDni, txtNombre, txtApellidos, txtTelefono, txtEmail, txtDireccion;
    private JButton btnGuardar, btnVolver;

    public AltaCliente() {
        super("Alta Cliente");

        setLayout(new BorderLayout(10, 10));
        Font fuenteBotones = new Font("Arial", Font.BOLD, 14);

        JLabel lblTitulo = new JLabel("Registro de Nuevo Cliente");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridLayout(6, 2, 10, 10));
        formulario.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

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

        formulario.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formulario.add(txtEmail);

        formulario.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        formulario.add(txtDireccion);

        add(formulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(fuenteBotones);
        btnVolver = new JButton("Volver");
        btnVolver.setFont(fuenteBotones);

        panelBotones.add(btnGuardar);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);
    }

    // ✅ MÉTODO CLAVE (LO QUE TE PEDÍA EL PROFESOR)
    public void setControlador(ClienteControlador c){

        btnGuardar.addActionListener(e -> c.guardarCliente(this));
        btnVolver.addActionListener(e -> c.volverMenu(this));
    }

    // =========================
    // DATOS DEL FORMULARIO
    // =========================
    public Cliente getClienteFormulario() {

        Cliente c = new Cliente();
        c.setDni(txtDni.getText().trim());
        c.setNombre(txtNombre.getText().trim());
        c.setApellidos(txtApellidos.getText().trim());
        c.setTelefono(txtTelefono.getText().trim());
        c.setEmail(txtEmail.getText().trim());
        c.setDireccion(txtDireccion.getText().trim());
        c.setContraseña("");
        return c;
    }

    // ===== GETTERS =====
    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnVolver() { return btnVolver; }

    public JTextField getTxtApellidos() { return txtApellidos; }
    public JTextField getTxtDireccion() { return txtDireccion; }
    public JTextField getTxtDni() { return txtDni; }
    public JTextField getTxtEmail() { return txtEmail; }
    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtTelefono() { return txtTelefono; }

    // (estos setters realmente no los necesitas, pero los dejo porque los tenías)
    public void setBtnGuardar(JButton btnGuardar) { this.btnGuardar = btnGuardar; }
    public void setBtnVolver(JButton btnVolver) { this.btnVolver = btnVolver; }
    public void setTxtApellidos(JTextField txtApellidos) { this.txtApellidos = txtApellidos; }
    public void setTxtDireccion(JTextField txtDireccion) { this.txtDireccion = txtDireccion; }
    public void setTxtDni(JTextField txtDni) { this.txtDni = txtDni; }
    public void setTxtEmail(JTextField txtEmail) { this.txtEmail = txtEmail; }
    public void setTxtNombre(JTextField txtNombre) { this.txtNombre = txtNombre; }
    public void setTxtTelefono(JTextField txtTelefono) { this.txtTelefono = txtTelefono; }
}