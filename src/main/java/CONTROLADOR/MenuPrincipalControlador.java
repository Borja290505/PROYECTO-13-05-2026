package CONTROLADOR;

import MODELO.Cliente;
import VISTA.MENU.PRINCIPAL.MenuPrincipal;

import javax.swing.*;

public class MenuPrincipalControlador {
    private Cliente clienteActivo;

    public MenuPrincipalControlador(Cliente clienteActivo) {
        this.clienteActivo = clienteActivo;
    }

    private void abrirMenuPrincipal() {

        MenuPrincipal menu = new MenuPrincipal();

        menu.getBtnVehiculos().addActionListener(e -> {
            menu.dispose();
            VehiculoControlador.abrirMenuVehiculo();
        });

        menu.getBtnClientes().addActionListener(e -> {
            menu.dispose();
            ClienteControlador.abrirMenuCliente();
        });

        menu.getBtnOrdenes().addActionListener(e -> {
            menu.dispose();
            OrdenRepControlador.abrirMenuOrdenes();
        });

        menu.getBtnFacturacion().addActionListener(e -> {
            menu.dispose();
            FacturacionControlador.abrirMenuFacturacion();
        });

        menu.getBtnEstadisticas().addActionListener(e -> {
            menu.dispose();
            EstadisticasControlador.abrirMenuEstadisticas();
        });

        menu.getBtnSalir().addActionListener(e -> {

            String[] opciones = {
                    "Cerrar sesión",
                    "Salir del programa",
                    "Cancelar"
            };

            int eleccion = JOptionPane.showOptionDialog(
                    menu,
                    "¿Qué deseas hacer?",
                    "Cerrar sesión",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            // 0 → Cerrar sesión
            if (eleccion == 0) {
                clienteActivo = null;   // limpieza
                menu.dispose();         // cerramos el menú
                new ClienteControlador(); // volvemos al login
            }

            // 1 → Salir del programa
            else if (eleccion == 1) {
                System.exit(0);
            }

            // 2 → Cancelar (no hacemos nada)
        });

    }
}
