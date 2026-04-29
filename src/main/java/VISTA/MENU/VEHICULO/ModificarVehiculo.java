package VISTA.MENU.VEHICULO;

import MODELO.Cliente;
import MODELO.Vehiculo;
import VISTA.VentanaBase;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;
import DAO.ClienteDAO;

public class ModificarVehiculo extends VentanaBase {

    private JTextField txtMatricula, txtMarca, txtModelo, txtAnio, txtKms;
    private JComboBox<Cliente> comboClientes;
    private JButton btnBuscar, btnModificar, btnVolver;

    public ModificarVehiculo() {
        super("Modificar Vehículo");
        setSize(500, 600); // Un poco más alta para que quepa todo bien
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));

        // --- TÍTULO Y BUSCADOR (NORTE) ---
        JPanel panelNorte = new JPanel(new BorderLayout(10, 10));
        panelNorte.setBorder(new EmptyBorder(20, 20, 10, 20));

        JLabel lblTitulo = new JLabel("Gestión de Modificaciones", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        panelNorte.add(lblTitulo, BorderLayout.NORTH);

        JPanel buscador = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buscador.setBorder(BorderFactory.createTitledBorder(null, "Buscador de Vehículo", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.ITALIC, 12)));

        txtMatricula = new JTextField();
        txtMatricula.setPreferredSize(new Dimension(150, 30));
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(102, 178, 255)); // Azul claro
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 12));

        buscador.add(new JLabel("Matrícula:"));
        buscador.add(txtMatricula);
        buscador.add(btnBuscar);
        panelNorte.add(buscador, BorderLayout.CENTER);

        add(panelNorte, BorderLayout.NORTH);

        // --- FORMULARIO (CENTRO) ---
        JPanel formulario = new JPanel(new GridLayout(6, 2, 10, 15));
        formulario.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(10, 30, 10, 30),
                BorderFactory.createTitledBorder("Datos del Vehículo")
        ));

        // Estilo común para etiquetas
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        formulario.add(crearLabel("Marca:", labelFont));
        txtMarca = new JTextField();
        formulario.add(txtMarca);

        formulario.add(crearLabel("Modelo:", labelFont));
        txtModelo = new JTextField();
        formulario.add(txtModelo);

        formulario.add(crearLabel("Año:", labelFont));
        txtAnio = new JTextField();
        formulario.add(txtAnio);

        formulario.add(crearLabel("Kilómetros:", labelFont));
        txtKms = new JTextField();
        formulario.add(txtKms);

        formulario.add(crearLabel("Propietario:", labelFont));
        comboClientes = new JComboBox<>();
        cargarClientes();
        formulario.add(comboClientes);

        add(formulario, BorderLayout.CENTER);

        // --- BOTONES DE ACCIÓN (SUR) ---
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 15, 10));
        panelBotones.setBorder(new EmptyBorder(10, 30, 30, 30));

        btnModificar = new JButton("Guardar Cambios");
        btnModificar.setFont(new Font("Arial", Font.BOLD, 14));
        btnModificar.setBackground(new Color(144, 238, 144)); // Verde claro
        btnModificar.setEnabled(false); // Desactivado hasta buscar

        btnVolver = new JButton("Cancelar / Volver");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 14));

        panelBotones.add(btnModificar);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);

        // Bloquear campos inicialmente
        setCamposEditables(false);

        setLocationRelativeTo(null); // Centrar en pantalla
        setVisible(true);
    }

    private JLabel crearLabel(String texto, Font fuente) {
        JLabel label = new JLabel(texto);
        label.setFont(fuente);
        return label;
    }

    public void rellenarCampos(Vehiculo v) {
        txtMarca.setText(v.getMarca());
        txtModelo.setText(v.getModelo());
        txtAnio.setText(String.valueOf(v.getAnio()));
        txtKms.setText(String.valueOf(v.getKmsActuales()));

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
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> clientes = dao.listarClientes();
        for (Cliente c : clientes) comboClientes.addItem(c);
    }

    // Getters para el controlador
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
