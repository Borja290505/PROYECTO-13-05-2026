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

    private int intentosFallidos = 0;
    private static final int MAX_INTENTOS = 5;

    public LoginControlador() {
        clienteDAO = new ClienteDAO();
        vista = new PaginaInicio();
        configurarEventos();
    }

    private void configurarEventos() {

        vista.getBtnInicioSesion().addActionListener(e -> {

            if (intentosFallidos >= MAX_INTENTOS) {
                JOptionPane.showMessageDialog(vista,
                        "Demasiados intentos fallidos. Reinicia la aplicación.");
                vista.getBtnInicioSesion().setEnabled(false);
                return;
            }

            String usuario = vista.getTxtUsuario().getText().trim().toUpperCase();
            String pass = new String(vista.getTxtPassword().getPassword()).trim();

            boolean ok = clienteDAO.loginCliente(usuario, pass);
            LoginApp.registrarLogin(usuario, ok);

            if (ok) {
                intentosFallidos = 0;
                vista.dispose();
                new MenuPrincipalControlador();
            } else {
                intentosFallidos++;
                int restantes = MAX_INTENTOS - intentosFallidos;

                JOptionPane.showMessageDialog(vista,
                        "Usuario o contraseña incorrectos. Intentos restantes: " + restantes);
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

                String dni = registroVista.getTxtDni().getText().trim().toUpperCase();
                String nombre = registroVista.getTxtNombre().getText().trim();
                String apellidos = registroVista.getTxtApellidos().getText().trim();
                String email = registroVista.getTxtEmail().getText().trim();
                String telefono = registroVista.getTxtTelefono().getText().trim();
                String contrasena = new String(registroVista.getTxtContraseña().getPassword());

                if (!dniValido(dni)) {
                    JOptionPane.showMessageDialog(registroVista, "DNI no válido");
                    return;
                }

                if (!nombreValido(nombre)) {
                    JOptionPane.showMessageDialog(registroVista, "Nombre no válido");
                    return;
                }

                if (!apellidoValido(apellidos)) {
                    JOptionPane.showMessageDialog(registroVista, "Apellidos no válidos");
                    return;
                }

                if (!emailValido(email)) {
                    JOptionPane.showMessageDialog(registroVista, "Email no válido");
                    return;
                }

                if (!telefonoValido(telefono)) {
                    JOptionPane.showMessageDialog(registroVista, "Teléfono no válido");
                    return;
                }

                if (contrasena.isBlank()) {
                    JOptionPane.showMessageDialog(registroVista, "Contraseña vacía");
                    return;
                }

                if (clienteDAO.existeClientePorDni(dni)) {
                    JOptionPane.showMessageDialog(registroVista, "Cliente ya existe");
                    return;
                }

                Cliente c = new Cliente();
                c.setDni(dni);
                c.setNombre(nombre);
                c.setApellidos(apellidos);
                c.setEmail(email);
                c.setTelefono(telefono);
                c.setDireccion(registroVista.getTxtDireccion().getText().trim());
                c.setContraseña(contrasena);
                c.setFechaAlta(java.time.LocalDateTime.now());

                clienteDAO.insertarCliente(c);

                JOptionPane.showMessageDialog(registroVista,
                        "Cliente registrado correctamente");

                registroVista.dispose();
                new LoginControlador();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(registroVista,
                        "Error al registrar cliente");
            }
        });

        registroVista.getBtnVolver().addActionListener(e -> {
            registroVista.dispose();
            new LoginControlador();
        });
    }
}