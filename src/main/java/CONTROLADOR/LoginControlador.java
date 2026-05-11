package CONTROLADOR;

import DAO.ClienteDAO;
import MODELO.Cliente;
import UTIL.LoginApp;
import VISTA.INICIO.PaginaInicio;
import VISTA.INICIO.RegistroCliente;
import VISTA.MENU.PRINCIPAL.MenuPrincipal;

import javax.swing.*;

public class LoginControlador {

    private PaginaInicio vista;
    private ClienteDAO clienteDAO;

    public LoginControlador() {
        clienteDAO = new ClienteDAO();
        vista = new PaginaInicio();
        configurarEventos();
    }

    public void configurarEventos() {

        // =========================
        // BOTÓN INICIO SESIÓN
        // =========================
        vista.getBtnInicioSesion().addActionListener(e -> {

            String usuario = vista.getTxtUsuario().getText().trim().toUpperCase();
            String pass = new String(vista.getTxtPassword().getPassword()).trim();

            if (usuario.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(vista,
                        "Debes introducir usuario y contraseña");
                return;
            }

            boolean loginCorrecto = clienteDAO.loginCliente(usuario, pass);
            LoginApp.registrarLogin(usuario, loginCorrecto);

            if (loginCorrecto) {
                Cliente clienteActivo = clienteDAO.obtenerClientePorDni(usuario);
                vista.dispose();

                new MenuPrincipal();

            } else {
                JOptionPane.showMessageDialog(vista,
                        "Usuario o contraseña incorrectos");
            }
        });

        // =========================
        // BOTÓN REGISTRO
        // =========================
        vista.getBtnRegistro().addActionListener(e -> {
            vista.dispose();
            abrirRegistro();
        });
    }

    static void abrirRegistro() {
        RegistroCliente registro = new RegistroCliente();

        registro.getBtnVolver().addActionListener(e -> {
            registro.dispose();
            new LoginControlador();
        });
    }
}