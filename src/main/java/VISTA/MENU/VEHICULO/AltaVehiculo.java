package VISTA.MENU.VEHICULO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.ClienteDAO;
import static DAO.VehiculoDAO.InsertarVehiculo;
import MODELO.Cliente;
import MODELO.Vehiculo;
import VISTA.VentanaBase;

public class AltaVehiculo extends VentanaBase {

    private JTextField txtMatricula, txtMarca, txtModelo, txtAnio, txtKms, txtColor;
    private JTextField txtBuscarDni;
    private JLabel lblClienteEncontrado;
    private Cliente clienteEncontrado;

    private JButton btnGuardar, btnVolver, btnBuscarCliente;

    public AltaVehiculo() {
        super("Alta Vehículo");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setLayout(new BorderLayout(10, 10));

        // Título simple en la parte superior
        JLabel lblTitulo = new JLabel("Registrar Nuevo Vehículo");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel del formulario usando GridLayout simple
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

        formulario.add(new JLabel("Color:"));
        txtColor = new JTextField();
        formulario.add(txtColor);

        formulario.add(new JLabel("Cliente (DNI):"));
        txtBuscarDni = new JTextField();
        formulario.add(txtBuscarDni);

        btnBuscarCliente = new JButton("Buscar cliente");
        formulario.add(btnBuscarCliente);

        lblClienteEncontrado = new JLabel("Cliente no seleccionado");
        formulario.add(lblClienteEncontrado);

        add(formulario, BorderLayout.CENTER);

        // Panel de botones en la parte inferior usando el FlowLayout por defecto
        JPanel botones = new JPanel();
        botones.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0)); // Margen inferior
        
        btnGuardar = new JButton("Guardar");
        btnVolver = new JButton("Volver");
        
        botones.add(btnGuardar);
        botones.add(btnVolver);



        add(botones, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public JButton getBtnBuscarCliente() {
        return btnBuscarCliente;
    }

    public void setBtnBuscarCliente(JButton btnBuscarCliente) {
        this.btnBuscarCliente = btnBuscarCliente;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public void setBtnVolver(JButton btnVolver) {
        this.btnVolver = btnVolver;
    }

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

    public JLabel getLblClienteEncontrado() {
        return lblClienteEncontrado;
    }

    public void setLblClienteEncontrado(JLabel lblClienteEncontrado) {
        this.lblClienteEncontrado = lblClienteEncontrado;
    }

    public JTextField getTxtAnio() {
        return txtAnio;
    }

    public void setTxtAnio(JTextField txtAnio) {
        this.txtAnio = txtAnio;
    }

    public JTextField getTxtBuscarDni() {
        return txtBuscarDni;
    }

    public void setTxtBuscarDni(JTextField txtBuscarDni) {
        this.txtBuscarDni = txtBuscarDni;
    }

    public JTextField getTxtKms() {
        return txtKms;
    }

    public void setTxtKms(JTextField txtKms) {
        this.txtKms = txtKms;
    }

    public JTextField getTxtMarca() {
        return txtMarca;
    }

    public void setTxtMarca(JTextField txtMarca) {
        this.txtMarca = txtMarca;
    }

    public JTextField getTxtMatricula() {
        return txtMatricula;
    }

    public void setTxtMatricula(JTextField txtMatricula) {
        this.txtMatricula = txtMatricula;
    }

    public JTextField getTxtModelo() {
        return txtModelo;
    }

    public void setTxtModelo(JTextField txtModelo) {
        this.txtModelo = txtModelo;
    }

    public JTextField getTxtColor() {
        return txtColor;
    }

    public void setTxtColor(JTextField txtColor) {
        this.txtColor = txtColor;
    }
}
