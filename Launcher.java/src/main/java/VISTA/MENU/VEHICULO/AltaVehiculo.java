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

    private JTextField txtMatricula, txtMarca, txtModelo, txtAnio, txtKms;
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
        JPanel formulario = new JPanel(new GridLayout(7, 2, 10, 10));
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

        btnBuscarCliente.addActionListener(e -> {
            String dni = txtBuscarDni.getText().trim();

            if (dni.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Introduce un DNI para buscar el cliente",
                        "Dato requerido",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            ClienteDAO dao = new ClienteDAO();
            clienteEncontrado = dao.obtenerClientePorDni(dni);

            if (clienteEncontrado != null) {
                lblClienteEncontrado.setText(
                        clienteEncontrado.getNombre() + " " + clienteEncontrado.getApellidos() + " - DNI: " + clienteEncontrado.getDni()
                );
                lblClienteEncontrado.setForeground(Color.GREEN);
            } else {
                lblClienteEncontrado.setText("Cliente no encontrado");
                lblClienteEncontrado.setForeground(Color.RED);
                clienteEncontrado = null;

                JOptionPane.showMessageDialog(
                        this,
                        "No existe ningún cliente en la base de datos con el DNI:\n" + dni,
                        "Cliente no encontrado",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        btnGuardar.addActionListener(e -> {
            try {
                if (clienteEncontrado == null) {
                    JOptionPane.showMessageDialog(this,
                            "Debes buscar y seleccionar un cliente antes");
                    return;
                }

                Vehiculo v = new Vehiculo(
                        txtMatricula.getText().trim(),
                        txtMarca.getText().trim(),
                        txtModelo.getText().trim(),
                        Integer.parseInt(txtAnio.getText()),
                        Integer.parseInt(txtKms.getText()),
                        "Gasolina",
                        "Blanco",
                        clienteEncontrado.getIdCliente()
                );

                if (InsertarVehiculo(v)) {
                    JOptionPane.showMessageDialog(this,
                            "Vehículo insertado correctamente");
                    dispose();
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Año y KM deben ser numéricos");
            }
        });

        add(botones, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}
