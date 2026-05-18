package CONTROLADOR;

import DAO.FacturaDAO;
import MODELO.Factura;
import VISTA.MENU.FACTURACION.*;

import javax.swing.*;
import java.util.List;

public class FacturacionControlador {

    public void abrirMenuFacturacion(){
        MenuFacturacion vista = new MenuFacturacion();

        vista.cargarDatos(FacturaDAO.listarFacturas());

        // BOTÓN BUSCAR

        vista.getBtnBuscar().addActionListener(e -> {

            String tipo = vista.getComboTipoBusqueda()
                    .getSelectedItem().toString();

            String valor = vista.getTxtValor()
                    .getText()
                    .trim()
                    .toUpperCase();

            if (valor.isEmpty()) {
                vista.cargarDatos(FacturaDAO.listarFacturas());
                return;
            }

            List<Factura> lista = List.of();

            if (tipo.equals("Matrícula")) {
                lista = FacturaDAO.listarFacturasPorMatricula(valor);
            } else if (tipo.equals("DNI")){
                lista = FacturaDAO.listarFacturasPorDni(valor);
            }

            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "No hay facturas para ese criterio");
                lista = FacturaDAO.listarFacturas();
            }

            vista.cargarDatos(lista);
        });

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            new MenuPrincipalControlador();
        });
    }
}