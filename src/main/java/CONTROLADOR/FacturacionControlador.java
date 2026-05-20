package CONTROLADOR;

import DAO.FacturaDAO;
import MODELO.Factura;
import VISTA.MENU.FACTURACION.MenuFacturacion;

import javax.swing.*;
import java.util.List;

public class FacturacionControlador {

    // =========================
    // ABRIR MENÚ FACTURACIÓN
    // =========================
    public void abrirMenuFacturacion() {

        MenuFacturacion vista = new MenuFacturacion();
        vista.setControlador(this);

        vista.cargarDatos(FacturaDAO.listarFacturas());
    }

    // =========================
    // BUSCAR FACTURAS
    // =========================
    public void buscar(MenuFacturacion vista) {

        List<Factura> lista;

        String tipo = vista.getComboTipoBusqueda()
                .getSelectedItem().toString();

        String valor = vista.getTxtValor()
                .getText()
                .trim()
                .toUpperCase();

        if (tipo.equals("Matrícula")) {
            lista = FacturaDAO.listarFacturasPorMatricula(valor);
        } else {
            lista = FacturaDAO.listarFacturasPorDni(valor);
        }

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No hay facturas");
            lista = FacturaDAO.listarFacturas();
        }

        vista.cargarDatos(lista);
    }

    // =========================
    // VOLVER AL MENÚ PRINCIPAL
    // =========================
    public void volver(MenuFacturacion vista) {
        vista.dispose();
        new MenuPrincipalControlador();
    }
}