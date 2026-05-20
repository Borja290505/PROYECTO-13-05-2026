package CONTROLADOR;

import DAO.ClienteDAO;
import MODELO.Cliente;
import UTIL.LoginApp;
import VISTA.INICIO.PaginaInicio;
import VISTA.INICIO.RegistroCliente;

import javax.swing.*;

import static UTIL.Validaciones.*;

public class LoginControlador {

    private PaginaInicio vista;
    private ClienteDAO clienteDAO;

    // =========================
    // CONSTRUCTOR
    // =========================
    public LoginControlador() {
        clienteDAO = new ClienteDAO();
        vista = new PaginaInicio();
        vista.setControlador(this);
    }

    // =========================
    // LOGIN
    // =========================
    public void login(PaginaInicio vista) {

        String usuario = vista.getTxtUsuario().getText().trim().toUpperCase();
        String pass = new String(vista.getTxtPassword().getPassword()).trim();

        boolean ok = clienteDAO.loginCliente(usuario, pass);
        LoginApp.registrarLogin(usuario, ok);

        if (ok) {
            vista.dispose();
            new MenuPrincipalControlador();
        } else {
            JOptionPane.showMessageDialog(vista,
                    "Usuario o contraseña incorrectos");
        }
    }

    // =========================
    // IR A REGISTRO
    // =========================
    public void irRegistro(PaginaInicio vista) {
        vista.dispose();
        abrirRegistro();
    }

    // =========================
    // REGISTRO CLIENTE
    // =========================
    private void abrirRegistro() {

        RegistroCliente registroVista = new RegistroCliente();
        registroVista.setControlador(this);
    }

    public void registrarCliente(RegistroCliente vista) {

        try {
            Cliente c = vista.getClienteFormulario();
            c.setFechaAlta(java.time.LocalDateTime.now());

            String dni = vista.getTxtDni().getText().trim().toUpperCase();
            String nombre = vista.getTxtNombre().getText().trim();
            String apellidos = vista.getTxtApellidos().getText().trim();
            String email = vista.getTxtEmail().getText().trim();
            String telefono = vista.getTxtTelefono().getText().trim();

            if (!dniValido(dni)) {
                JOptionPane.showMessageDialog(vista, "El DNI debe tener formato 12345678A");
                return;
            }

            if (!nombreValido(nombre)) {
                JOptionPane.showMessageDialog(vista, "El nombre no puede tener números");
                return;
            }

            if (!apellidoValido(apellidos)) {
                JOptionPane.showMessageDialog(vista, "El apellido no puede tener números");
                return;
            }

            if (!emailValido(email)) {
                JOptionPane.showMessageDialog(vista, "Email inválido");
                return;
            }

            if (!telefonoValido(telefono)) {
                JOptionPane.showMessageDialog(vista, "El teléfono debe tener 9 cifras");
                return;
            }

            if (clienteDAO.existeClientePorDni(dni)) {
                JOptionPane.showMessageDialog(vista, "Cliente ya existente");
                return;
            }

            clienteDAO.insertarCliente(c);

            vista.mostrarMensaje("Cliente registrado correctamente");

            vista.dispose();
            new LoginControlador();

        } catch (Exception ex) {
            vista.mostrarMensaje("Error al registrar cliente");
        }
    }

    // =========================
    // VOLVER DESDE REGISTRO
    // =========================
    public void volverLogin(RegistroCliente vista) {
        vista.dispose();
        new LoginControlador();
    }
}