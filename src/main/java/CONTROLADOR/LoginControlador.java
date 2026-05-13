package CONTROLADOR;

import DAO.ClienteDAO;
import MODELO.Cliente;
import UTIL.LoginApp;
import VISTA.INICIO.PaginaInicio;
import VISTA.INICIO.RegistroCliente;

import javax.swing.*;

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