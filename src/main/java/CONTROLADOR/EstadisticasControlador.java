package CONTROLADOR;

import DAO.EstadisticasDAO;
import VISTA.MENU.ESTADISTICAS.Estadisticas;

public class EstadisticasControlador {

    // =========================
    // ABRIR MENÚ ESTADÍSTICAS
    // =========================
    public void abrirMenuEstadisticas() {

        Estadisticas vista = new Estadisticas();
        vista.setControlador(this);

        vista.cargarDatos(
                EstadisticasDAO.totalClientes(),
                EstadisticasDAO.totalVehiculos(),
                EstadisticasDAO.totalSinIva(),
                EstadisticasDAO.totalIva(),
                EstadisticasDAO.totalConIva()
        );
    }

    // =========================
    // VOLVER AL MENÚ PRINCIPAL
    // =========================
    public void volver(Estadisticas vista) {
        vista.dispose();
        new MenuPrincipalControlador();
    }
}