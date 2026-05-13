package CONTROLADOR;

import DAO.ClienteDAO;
import MODELO.Cliente;
import UTIL.LoginApp;
import VISTA.INICIO.PaginaInicio;
import VISTA.INICIO.RegistroCliente;

import javax.swing.*;

import static UTIL.Validaciones.*;
import static UTIL.Validaciones.emailValido;
import static UTIL.Validaciones.telefonoValido;

public class LoginControlador {

    private PaginaInicio vista;
    private ClienteDAO clienteDAO;

    public LoginControlador() {
        clienteDAO = new ClienteDAO();
        vista = new PaginaInicio();
        configurarEventos();
    }

    private void configurarEventos() {

        vista.getBtnInicioSesion().addActionListener(e -> {

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
        });

        vista.getBtnRegistro().addActionListener(e -> {
            vista.dispose();
            abrirRegistro();
        });
    }

    private void abrirRegistro() {

        RegistroCliente registroVista = new RegistroCliente();

        registroVista.getBtnRegistrar().addActionListener(e -> {
            try {
                Cliente c = registroVista.getClienteFormulario();
                c.setFechaAlta(java.time.LocalDateTime.now());

                String dni = registroVista.getTxtDni().getText().trim().toUpperCase();
                String nombre = registroVista.getTxtNombre().getText().trim();
                String apellidos = registroVista.getTxtApellidos().getText().trim();
                String email = registroVista.getTxtEmail().getText().trim();
                String telefono = registroVista.getTxtTelefono().getText().trim();

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

                clienteDAO.insertarCliente(c);
                registroVista.mostrarMensaje("Cliente registrado correctamente");
                registroVista.dispose();
                new LoginControlador();

            } catch (Exception ex) {
                registroVista.mostrarMensaje("Error al registrar cliente");
            }
        });

        registroVista.getBtnVolver().addActionListener(e -> {
            registroVista.dispose();
            new ClienteControlador();
        });
    }
}