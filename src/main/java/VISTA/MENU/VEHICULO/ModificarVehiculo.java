package VISTA.MENU.VEHICULO;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import MODELO.Cliente;
import MODELO.Vehiculo;
import VISTA.VentanaBase;
import DAO.ClienteDAO;

public class ModificarVehiculo extends VentanaBase {

    private JTextField txtMatricula, txtMarca, txtModelo, txtAnio, txtKms;
    private JComboBox<Cliente> comboClientes;
    private JButton btnBuscar, btnModificar, btnVolver;

    public ModificarVehiculo(){
        super("Modificar Vehículo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Gestión de Modificaciones");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel del formulario
        JPanel formulario = new JPanel(new GridLayout(7, 2, 10, 10));
        formulario.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        formulario.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        formulario.add(txtMatricula);

        btnBuscar = new JButton("Buscar");
        formulario.add(btnBuscar);
        formulario.add(new JLabel(""));

        formulario.add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        formulario.add(txtMarca);

        formulario.add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        formulario.add(txtModelo);

        formulario.add(new JLabel("Año:"));
        txtAnio = new JTextField();
        formulario.add(txtAnio);

        formulario.add(new JLabel("Kilómetros:"));
        txtKms = new JTextField();
        formulario.add(txtKms);

        formulario.add(new JLabel("Propietario:"));
        comboClientes = new JComboBox<>();
        cargarClientes();
        formulario.add(comboClientes);

        add(formulario, BorderLayout.CENTER);

        // Panel de botones
        JPanel botones = new JPanel();
        btnModificar = new JButton("Guardar Cambios");
        btnVolver = new JButton("Volver");

        // Bloqueo inicial hasta que se busque un vehículo
        setCamposEditables(false);

        botones.add(btnModificar);
        botones.add(btnVolver);
        add(botones, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // --- MÉTODOS REQUERIDOS POR EL CONTROLADOR ---

    public void rellenarCampos(Vehiculo v) {
        txtMarca.setText(v.getMarca());
        txtModelo.setText(v.getModelo());
        txtAnio.setText(String.valueOf(v.getAnio()));
        txtKms.setText(String.valueOf(v.getKmsActuales()));

        // Seleccionar el cliente correcto en el combo
        for (int i = 0; i < comboClientes.getItemCount(); i++) {
            if (comboClientes.getItemAt(i).getIdCliente() == v.getIdCliente()) {
                comboClientes.setSelectedIndex(i);
                break;
            }
        }
    }


    public void setCamposEditables(boolean estado) {
        txtMarca.setEditable(estado);
        txtModelo.setEditable(estado);
        txtAnio.setEditable(estado);
        txtKms.setEditable(estado);
        comboClientes.setEnabled(estado);
        btnModificar.setEnabled(estado);
    }


    private void cargarClientes() {
        new ClienteDAO().listarClientes().forEach(comboClientes::addItem);
    }

    // --- GETTERS ---
    public JTextField getTxtMatricula() { return txtMatricula; }
    public JTextField getTxtMarca() { return txtMarca; }
    public JTextField getTxtModelo() { return txtModelo; }
    public JTextField getTxtAnio() { return txtAnio; }
    public JTextField getTxtKms() { return txtKms; }
    public JComboBox<Cliente> getComboClientes() { return comboClientes; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnModificar() { return btnModificar; }
    public JButton getBtnVolver() { return btnVolver; }
}
