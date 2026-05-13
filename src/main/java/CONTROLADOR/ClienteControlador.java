package CONTROLADOR;

import DAO.ClienteDAO;
import MODELO.Cliente;
import VISTA.MENU.CLIENTE.*;

import javax.swing.*;

import static UTIL.Validaciones.*;

public class ClienteControlador {

    private ClienteDAO clienteDAO = new ClienteDAO();
    private Cliente clienteActual;

    public void abrirMenuCliente() {

        MenuCliente menu = new MenuCliente();

        menu.getBtnAltaCliente().addActionListener(e -> {
            menu.dispose();
            abrirAltaCliente();
        });

        menu.getBtnListarCliente().addActionListener(e -> {
            menu.dispose();
            abrirListarClientes();
        });

        menu.getBtnModificarCliente().addActionListener(e -> {
            menu.dispose();
            abrirModificarCliente();
        });

        menu.getBtnVolver().addActionListener(e -> {
            menu.dispose();
            new MenuPrincipalControlador();
        });
    }

    private void abrirAltaCliente() {

        AltaCliente vista = new AltaCliente();

        vista.getBtnGuardar().addActionListener(e -> {

            String dni = vista.getTxtDni().getText().trim().toUpperCase();
            String nombre = vista.getTxtNombre().getText().trim();
            String apellidos = vista.getTxtApellidos().getText().trim();
            String email = vista.getTxtEmail().getText().trim();
            String telefono = vista.getTxtTelefono().getText().trim();

            if (!dniValido(dni)) {
                JOptionPane.showMessageDialog(vista, "El DNI tiene que tener formato 12345678A");
                return;
            }

            if (!nombreValido(nombre)) {
                JOptionPane.showMessageDialog(vista, "El nombre no puede contener numeros");
                return;
            }
            if (!apellidoValido(apellidos) ) {
                JOptionPane.showMessageDialog(vista, "El apellido no puede contener numeros");
                return;
            }
            if (!emailValido(email)) {
                JOptionPane.showMessageDialog(vista, "El email tiene que tener formato ****@****.****");
                return;
            }

            if(!telefonoValido(telefono)){
                JOptionPane.showMessageDialog(vista,"El telefo tiene que estar formado por 9 numeros");
            }

            if (clienteDAO.existeClientePorDni(dni)) {
                JOptionPane.showMessageDialog(vista, "Cliente ya existente");
                return;
            }

            Cliente c = vista.getClienteFormulario();
            c.setFechaAlta(java.time.LocalDateTime.now());
            clienteDAO.insertarCliente(c);

            JOptionPane.showMessageDialog(vista, "Cliente registrado");
            vista.dispose();
            abrirMenuCliente();
        });

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuCliente();
        });
    }

    private void abrirListarClientes() {

        ListaCliente vista = new ListaCliente();

        // Cargar todos al inicio
        vista.cargarDatos(clienteDAO.listarClientes());

        // BOTÓN BUSCAR
        vista.getBtnBuscar().addActionListener(e -> {

            String tipo = vista.getComboTipoBusqueda()
                    .getSelectedItem()
                    .toString();

            String texto = vista.getTxtBuscar()
                    .getText()
                    .trim()
                    .toUpperCase();

            // Si no escriben nada → listar todos
            if (texto.isEmpty()) {
                vista.cargarDatos(clienteDAO.listarClientes());
                return;
            }

            // ===== BUSCAR POR DNI =====
            if (tipo.equals("DNI")) {

                Cliente c = clienteDAO.obtenerClientePorDni(texto);

                if (c == null) {
                    JOptionPane.showMessageDialog(vista,
                            "No existe ningún cliente con ese DNI");
                    vista.cargarDatos(java.util.List.of());
                } else {
                    vista.cargarDatos(java.util.List.of(c));
                }

            }
            // ===== BUSCAR POR NOMBRE / APELLIDOS =====
            else {
                vista.cargarDatos(
                        clienteDAO.buscarClientesPorNombre(texto)
                );
            }
        });

        // BOTÓN VOLVER
        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuCliente();
        });
    }

    private void abrirModificarCliente() {

        ModificarCliente vista = new ModificarCliente();

        vista.getBtnBuscar().addActionListener(e -> {
            clienteActual = clienteDAO.obtenerClientePorDni(
                    vista.getTxtDni().getText().trim().toUpperCase());

            if (clienteActual == null) {
                JOptionPane.showMessageDialog(vista, "No encontrado");
                return;
            }

            vista.rellenarCampos(clienteActual);
            vista.setCamposEditables(true);
        });

        vista.getBtnModificar().addActionListener(e -> {

            if (!emailValido(vista.getTxtEmail().getText())) {
                JOptionPane.showMessageDialog(vista, "Email inválido");
                return;
            }

            clienteDAO.modificarCliente(clienteActual);
            JOptionPane.showMessageDialog(vista, "Cliente actualizado");
            vista.dispose();
            abrirMenuCliente();
        });

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuCliente();
        });
    }
}