package CONTROLADOR;

import DAO.EstadisticasDAO;
import VISTA.MENU.ESTADISTICAS.Estadisticas;

public class EstadisticasControlador {

    public void abrirMenuEstadisticas() {

        Estadisticas vista = new Estadisticas();

        vista.cargarDatos(
                EstadisticasDAO.totalClientes(),
                EstadisticasDAO.totalVehiculos(),
                EstadisticasDAO.totalSinIva(),
                EstadisticasDAO.totalIva(),
                EstadisticasDAO.totalConIva()
        );

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            new MenuPrincipalControlador();
        });
    }
}