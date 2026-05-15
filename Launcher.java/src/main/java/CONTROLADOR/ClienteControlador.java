package CONTROLADOR;

import DAO.ClienteDAO;
import MODELO.Cliente;
import VISTA.MENU.CLIENTE.*;

import javax.swing.*;

import static UTIL.Validaciones.*;

public class ClienteControlador {

    private ClienteDAO clienteDAO = new ClienteDAO();
    private Cliente clienteActual;

    // =========================
    // MENÚ PRINCIPAL
    // =========================
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

    // =========================
    // ALTA CLIENTE
    // =========================
    private void abrirAltaCliente() {

        AltaCliente vista = new AltaCliente();

        vista.getBtnGuardar().addActionListener(e -> {

            String dni       = vista.getTxtDni().getText().trim().toUpperCase();
            String nombre    = vista.getTxtNombre().getText().trim();
            String apellidos = vista.getTxtApellidos().getText().trim();
            String email     = vista.getTxtEmail().getText().trim();
            String telefono  = vista.getTxtTelefono().getText().trim();

            if (!dniValido(dni)) {
                JOptionPane.showMessageDialog(vista, "DNI no válido");
                return;
            }

            if (!nombreValido(nombre)) {
                JOptionPane.showMessageDialog(vista, "Nombre no válido");
                return;
            }

            if (!apellidoValido(apellidos)) {
                JOptionPane.showMessageDialog(vista, "Apellidos no válidos");
                return;
            }

            if (!emailValido(email)) {
                JOptionPane.showMessageDialog(vista, "Email no válido");
                return;
            }

            // ✅ FIX IMPORTANTE
            if (!telefonoValido(telefono)) {
                JOptionPane.showMessageDialog(vista, "Teléfono no válido");
                return;
            }

            if (clienteDAO.existeClientePorDni(dni)) {
                JOptionPane.showMessageDialog(vista, "Cliente ya existente");
                return;
            }

            Cliente c = vista.getClienteFormulario();
            c.setFechaAlta(java.time.LocalDateTime.now());

            clienteDAO.insertarCliente(c);

            JOptionPane.showMessageDialog(vista, "Cliente registrado correctamente");

            vista.dispose();
            abrirMenuCliente();
        });

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuCliente();
        });
    }

    // =========================
    // LISTAR CLIENTES
    // =========================
    private void abrirListarClientes() {

        ListaCliente vista = new ListaCliente();

        // ✅ cargar todo al inicio
        vista.cargarDatos(clienteDAO.listarClientes());

        vista.getBtnBuscar().addActionListener(e -> {

            String tipo  = vista.getComboTipoBusqueda().getSelectedItem().toString();
            String texto = vista.getTxtBuscar().getText().trim(); // ✅ sin toUpperCase

            if (texto.isEmpty()) {
                vista.cargarDatos(clienteDAO.listarClientes());
                return;
            }

            // =========================
            // BUSCAR POR DNI
            // =========================
            if (tipo.equals("DNI")) {

                Cliente c = clienteDAO.obtenerClientePorDni(texto.toUpperCase());

                if (c == null) {
                    JOptionPane.showMessageDialog(vista, "No existe ningún cliente");
                    vista.cargarDatos(java.util.List.of());
                } else {
                    vista.cargarDatos(java.util.List.of(c));
                }

            }
            // =========================
            // BUSCAR POR NOMBRE / APELLIDOS
            // =========================
            else {

                vista.cargarDatos(clienteDAO.buscarClientesPorNombre(texto));
            }
        });

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuCliente();
        });
    }

    // =========================
    // MODIFICAR CLIENTE
    // =========================
    private void abrirModificarCliente() {

        ModificarCliente vista = new ModificarCliente();

        vista.getBtnBuscar().addActionListener(e -> {

            clienteActual = clienteDAO.obtenerClientePorDni(
                    vista.getTxtDni().getText().trim().toUpperCase());

            if (clienteActual == null) {
                JOptionPane.showMessageDialog(vista, "Cliente no encontrado");
                return;
            }

            vista.rellenarCampos(clienteActual);
            vista.setCamposEditables(true);
        });

        vista.getBtnModificar().addActionListener(e -> {

            if (clienteActual == null) {
                JOptionPane.showMessageDialog(vista, "Primero busca un cliente");
                return;
            }

            String nombre    = vista.getTxtNombre().getText().trim();
            String apellidos = vista.getTxtApellidos().getText().trim();
            String telefono  = vista.getTxtTelefono().getText().trim();
            String email     = vista.getTxtEmail().getText().trim();
            String direccion = vista.getTxtDireccion().getText().trim();

            // ✅ Validación completa
            if (!nombreValido(nombre)) {
                JOptionPane.showMessageDialog(vista, "Nombre no válido");
                return;
            }

            if (!apellidoValido(apellidos)) {
                JOptionPane.showMessageDialog(vista, "Apellidos no válidos");
                return;
            }

            if (!telefonoValido(telefono)) {
                JOptionPane.showMessageDialog(vista, "Teléfono no válido");
                return;
            }

            if (!emailValido(email)) {
                JOptionPane.showMessageDialog(vista, "Email no válido");
                return;
            }

            // ✅ FIX CRÍTICO → aplicar cambios
            clienteActual.setNombre(nombre);
            clienteActual.setApellidos(apellidos);
            clienteActual.setTelefono(telefono);
            clienteActual.setEmail(email);
            clienteActual.setDireccion(direccion);

            clienteDAO.modificarCliente(clienteActual);

            JOptionPane.showMessageDialog(vista, "Cliente actualizado correctamente");

            vista.dispose();
            abrirMenuCliente();
        });

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuCliente();
        });
    }
}