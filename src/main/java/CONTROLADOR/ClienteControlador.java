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
    // ABRIR MENÚ CLIENTES
    // =========================
    public void abrirMenuCliente() {
        MenuCliente menu = new MenuCliente();
        menu.setControlador(this);
    }

    // =========================
    // ABRIR VISTAS
    // =========================
    public void abrirAltaCliente() {
        AltaCliente vista = new AltaCliente();
        vista.setControlador(this);
    }

    public void abrirListarClientes() {
        ListaCliente vista = new ListaCliente();
        vista.setControlador(this);

        vista.cargarDatos(clienteDAO.listarClientes());
    }

    public void abrirModificarCliente() {
        ModificarCliente vista = new ModificarCliente();
        vista.setControlador(this);
    }

    // =========================
    // NAVEGACIÓN
    // =========================
    public void irAlta(MenuCliente vista) {
        vista.dispose();
        abrirAltaCliente();
    }

    public void irLista(MenuCliente vista) {
        vista.dispose();
        abrirListarClientes();
    }

    public void irModificar(MenuCliente vista) {
        vista.dispose();
        abrirModificarCliente();
    }


    public void volverMenu(JFrame vista) {
        vista.dispose();
        new MenuPrincipalControlador();
    }


    // =========================
    // GUARDAR CLIENTE
    // =========================
    public void guardarCliente(AltaCliente vista) {

        String dni = vista.getTxtDni().getText().trim().toUpperCase();
        String nombre = vista.getTxtNombre().getText().trim();
        String apellidos = vista.getTxtApellidos().getText().trim();
        String email = vista.getTxtEmail().getText().trim();
        String telefono = vista.getTxtTelefono().getText().trim();

        if (!dniValido(dni)) {
            JOptionPane.showMessageDialog(vista,
                    "El DNI tiene que tener formato 12345678A");
            return;
        }

        if (!nombreValido(nombre)) {
            JOptionPane.showMessageDialog(vista,
                    "El nombre no puede contener números");
            return;
        }

        if (!apellidoValido(apellidos)) {
            JOptionPane.showMessageDialog(vista,
                    "El apellido no puede contener números");
            return;
        }

        if (!telefonoValido(telefono)) {
            JOptionPane.showMessageDialog(vista,
                    "El teléfono debe tener 9 cifras");
            return;
        }

        if (!emailValido(email)) {
            JOptionPane.showMessageDialog(vista,
                    "El email no es válido");
            return;
        }

        if (clienteDAO.existeClientePorDni(dni)) {
            JOptionPane.showMessageDialog(vista,
                    "Cliente ya existente");
            return;
        }

        Cliente c = vista.getClienteFormulario();
        c.setFechaAlta(java.time.LocalDateTime.now());

        clienteDAO.insertarCliente(c);

        JOptionPane.showMessageDialog(vista,
                "Cliente registrado correctamente");

        vista.dispose();
        abrirMenuCliente();
    }

    // =========================
    // BUSCAR CLIENTES
    // =========================
    public void buscarClientes(ListaCliente vista) {

        String tipo = vista.getComboTipoBusqueda()
                .getSelectedItem()
                .toString();

        String texto = vista.getTxtBuscar()
                .getText()
                .trim()
                .toUpperCase();

        if (texto.isEmpty()) {
            vista.cargarDatos(clienteDAO.listarClientes());
            return;
        }

        if (tipo.equals("DNI")) {

            Cliente c = clienteDAO.obtenerClientePorDni(texto);

            if (c == null) {
                JOptionPane.showMessageDialog(vista,
                        "No existe ningún cliente con ese DNI");
                vista.cargarDatos(java.util.List.of());
            } else {
                vista.cargarDatos(java.util.List.of(c));
            }

        } else {
            vista.cargarDatos(
                    clienteDAO.buscarClientesPorNombre(texto)
            );
        }
    }

    // =========================
    // BUSCAR CLIENTE PARA MODIFICAR
    // =========================
    public void buscarClienteModificar(ModificarCliente vista) {

        clienteActual = clienteDAO.obtenerClientePorDni(
                vista.getTxtDni().getText().trim().toUpperCase()
        );

        if (clienteActual == null) {
            JOptionPane.showMessageDialog(vista,
                    "Cliente no encontrado");
            return;
        }

        vista.rellenarCampos(clienteActual);
        vista.setCamposEditables(true);
    }

    // =========================
    // MODIFICAR CLIENTE
    // =========================
    public void modificarCliente(ModificarCliente vista) {

        if (!emailValido(vista.getTxtEmail().getText())) {
            JOptionPane.showMessageDialog(vista,
                    "Email inválido");
            return;
        }

        clienteActual.setNombre(vista.getTxtNombre().getText());
        clienteActual.setApellidos(vista.getTxtApellidos().getText());
        clienteActual.setTelefono(vista.getTxtTelefono().getText());
        clienteActual.setEmail(vista.getTxtEmail().getText());
        clienteActual.setDireccion(vista.getTxtDireccion().getText());

        clienteDAO.modificarCliente(clienteActual);

        JOptionPane.showMessageDialog(vista,
                "Cliente actualizado correctamente");

        vista.dispose();
        abrirMenuCliente();
    }
}