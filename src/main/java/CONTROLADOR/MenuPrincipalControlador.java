package CONTROLADOR;

import VISTA.MENU.PRINCIPAL.MenuPrincipal;

import javax.swing.*;

public class MenuPrincipalControlador {

    // =========================
    // CONSTRUCTOR
    // =========================
    public MenuPrincipalControlador() {
        abrirMenu();
    }

    // =========================
    // ABRIR MENÚ
    // =========================
    private void abrirMenu() {

        MenuPrincipal menu = new MenuPrincipal();
        menu.setControlador(this);
    }

    // =========================
    // NAVEGACIÓN
    // =========================
    public void irClientes(MenuPrincipal vista) {
        vista.dispose();
        new ClienteControlador().abrirMenuCliente();
    }

    public void irVehiculos(MenuPrincipal vista) {
        vista.dispose();
        new VehiculoControlador().abrirMenuVehiculo();
    }

    public void irOrdenes(MenuPrincipal vista) {
        vista.dispose();
        new OrdenRepControlador().abrirMenuOrdenes();
    }

    public void irFacturacion(MenuPrincipal vista) {
        vista.dispose();
        new FacturacionControlador().abrirMenuFacturacion();
    }

    public void irEstadisticas(MenuPrincipal vista) {
        vista.dispose();
        new EstadisticasControlador().abrirMenuEstadisticas();
    }

    // =========================
    // SALIR
    // =========================
    public void salir(MenuPrincipal vista) {

        String[] opciones = {"Cerrar sesión", "Salir del programa", "Cancelar"};

        int op = JOptionPane.showOptionDialog(
                vista,
                "¿Qué deseas hacer?",
                "Salir",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (op == 0) {
            vista.dispose();
            new LoginControlador();
        } else if (op == 1) {
            System.exit(0);
        }
        // Cancelar → no hace nada
    }
}