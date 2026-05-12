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

    public static void abrirRegistro() {
        RegistroCliente registro = new RegistroCliente();

        registro.getBtnVolver().addActionListener(e -> {
            registro.dispose();
            new LoginControlador();
        });
    }
}