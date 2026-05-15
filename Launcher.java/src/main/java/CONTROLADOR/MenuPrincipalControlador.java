package CONTROLADOR;

import MODELO.OrdenReparacion;
import VISTA.MENU.PRINCIPAL.MenuPrincipal;

import javax.swing.*;

public class MenuPrincipalControlador {

    public MenuPrincipalControlador() {
        abrirMenu();
    }

    private void abrirMenu() {

        MenuPrincipal menu = new MenuPrincipal();

        menu.getBtnClientes().addActionListener(e -> {
            menu.dispose();
            new ClienteControlador().abrirMenuCliente();
        });

        menu.getBtnVehiculos().addActionListener(e -> {
            menu.dispose();
            new VehiculoControlador().abrirMenuVehiculo();
        });

        menu.getBtnOrdenes().addActionListener(e -> {
            menu.dispose();
            new OrdenRepControlador().abrirMenuOrdenes();
        });

        menu.getBtnFacturacion().addActionListener(e -> {
            menu.dispose();
            new FacturacionControlador().abrirMenuFacturacion();
        });

        menu.getBtnEstadisticas().addActionListener(e -> {
            menu.dispose();
            new EstadisticasControlador().abrirMenuEstadisticas();
        });

        menu.getBtnSalir().addActionListener(e -> {

            String[] opciones = {"Cerrar sesión", "Salir del programa", "Cancelar"};
            int op = JOptionPane.showOptionDialog(menu,
                    "¿Qué deseas hacer?",
                    "Salir",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, opciones, opciones[0]);

            if (op == 0) {
                menu.dispose();
                new LoginControlador();
            } else if (op == 1) {
                System.exit(0);
            }
        });
    }
}