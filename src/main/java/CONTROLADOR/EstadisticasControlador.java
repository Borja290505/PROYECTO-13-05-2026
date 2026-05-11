package CONTROLADOR;

import DAO.EstadisticasDAO;
import VISTA.MENU.ESTADISTICAS.Estadisticas;
import VISTA.MENU.PRINCIPAL.MenuPrincipal;

public class EstadisticasControlador {
    // =========================
    // ESTADISTICAS
    // =========================
    public static void abrirMenuEstadisticas() {

        Estadisticas vista = new Estadisticas();

        int totalClientes = EstadisticasDAO.totalClientes();
        int totalVehiculos = EstadisticasDAO.totalVehiculos();
        double sinIva = EstadisticasDAO.totalSinIva();
        double iva = EstadisticasDAO.totalIva();
        double conIva = EstadisticasDAO.totalConIva();

        vista.cargarDatos(
                totalClientes,
                totalVehiculos,
                sinIva,
                iva,
                conIva
        );

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            new MenuPrincipal();
        });
    }
}
