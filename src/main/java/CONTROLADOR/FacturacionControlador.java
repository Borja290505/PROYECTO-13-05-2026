package CONTROLADOR;

import DAO.FacturaDAO;
import MODELO.Factura;
import VISTA.MENU.FACTURACION.*;

import java.util.List;

public class FacturacionControlador {

    public static void abrirMenuFacturacion(){
        MenuFacturacion menu = new MenuFacturacion();

        menu.getBtnListarFacturas().addActionListener(e -> {
            menu.dispose();
            abrirListarFacturas();
        });

        menu.getBtnBuscarFacturas().addActionListener(e -> {
            menu.dispose();
            abrirBuscarFacturas();
        });

        menu.getBtnVolver().addActionListener(e -> {
            menu.dispose();
            new MenuPrincipalControlador();
        });
    }
    // =========================
    // FACTURAS
    // =========================
    private static void abrirListarFacturas() {

        // Creamos la vista
        ListarFacturas vista = new ListarFacturas();

        // Obtenemos las facturas de la base de datos
        vista.cargarDatos(FacturaDAO.listarFacturas());

        // Botón volver
        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuFacturacion();
        });
    }

    private static void abrirBuscarFacturas() {

        BuscarFacturas vista = new BuscarFacturas();

        // BOTÓN BUSCAR
        vista.getBtnBuscar().addActionListener(e -> {

            String tipo = vista.getComboTipoBusqueda()
                    .getSelectedItem().toString();

            String valor = vista.getTxtValor()
                    .getText()
                    .trim()
                    .toUpperCase();

            List<Factura> lista;

            if (tipo.equals("Matrícula")) {
                lista = FacturaDAO.listarFacturasPorMatricula(valor);
            } else {
                lista = FacturaDAO.listarFacturasPorDni(valor);
            }

            System.out.println("Facturas encontradas: " + lista.size());

            vista.cargarDatos(lista);
        });

        // BOTÓN VOLVER
        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuFacturacion();
        });
    }
}