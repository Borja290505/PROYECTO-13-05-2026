package CONTROLADOR;

import DAO.OrdenReparacionDAO;
import DAO.FacturaDAO;
import MODELO.OrdenReparacion;
import MODELO.Factura;

import VISTA.MENU.ORDEN.*;
import VISTA.MENU.PRINCIPAL.MenuPrincipal;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class OrdenRepControlador {

    private static OrdenReparacion ordenActual;

    // =========================
    // MENÚ ÓRDENES
    // =========================
    public static void abrirMenuOrdenes() {

        MenuOrden menu = new MenuOrden();

        menu.getBtnNuevaOrden().addActionListener(e -> {
            menu.dispose();
            abrirNuevaOrden();
        });

        menu.getBtnFinalizarOrden().addActionListener(e -> {
            menu.dispose();
            abrirFinalizarOrden();
        });

        menu.getBtnListarOrdenes().addActionListener(e -> {
            menu.dispose();
            abrirListarOrdenes();
        });

        menu.getBtnModificarOrden().addActionListener(e -> {
            menu.dispose();
            abrirModificarOrden();
        });

        menu.getBtnVolver().addActionListener(e -> {
            menu.dispose();
            new MenuPrincipal(); // vuelve al menú principal
        });
    }

    // =========================
    // NUEVA ORDEN
    // =========================
    private static void abrirNuevaOrden() {

        NuevaOrden vista = new NuevaOrden();

        vista.getBtnCrear().addActionListener(e -> {
            // aquí iría la lógica de crear orden
            JOptionPane.showMessageDialog(vista,
                    "Orden creada correctamente");
            vista.dispose();
            abrirMenuOrdenes();
        });

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuOrdenes();
        });
    }

    // =========================
    // FINALIZAR ORDEN
    // =========================
    private static void abrirFinalizarOrden() {

        FinalizarOrden vista = new FinalizarOrden();

        vista.getBtnFinalizar().addActionListener(e -> {

            String matricula = vista.getTxtMatricula().getText().trim();
            String observaciones = vista.getTxtObservaciones().getText().trim();

            if (matricula.isEmpty()) {
                JOptionPane.showMessageDialog(vista,
                        "Introduce una matrícula");
                return;
            }

            int idOrden = OrdenReparacionDAO.finalizarOrdenPorMatricula(
                    matricula,
                    observaciones
            );

            if (idOrden == -1) {
                JOptionPane.showMessageDialog(vista,
                        "No hay ninguna orden abierta para esa matrícula");
                return;
            }

            OrdenReparacion orden =
                    OrdenReparacionDAO.buscarPorMatricula(matricula);

            // ===== CREAR FACTURA =====
            double subtotal = orden.getPrecio();
            double total = subtotal * 1.21;
            double iva = total - subtotal;

            Factura f = new Factura();
            f.setFechaFactura(LocalDate.now());
            f.setSubtotal(subtotal);
            f.setIva(iva);
            f.setTotal(total);
            f.setIdOrden(idOrden);

            FacturaDAO.insertarFactura(f);

            JOptionPane.showMessageDialog(vista,
                    "Orden finalizada y factura creada");

            vista.dispose();
            abrirMenuOrdenes();
        });

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuOrdenes();
        });
    }

    // =========================
    // LISTAR ÓRDENES
    // =========================
    private static void abrirListarOrdenes() {

        ListarOrdenes vista = new ListarOrdenes();
        List<OrdenReparacion> lista =
                OrdenReparacionDAO.listarOrdenes();

        vista.cargarDatos(lista);

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuOrdenes();
        });
    }

    // =========================
    // MODIFICAR ORDEN
    // =========================
    private static void abrirModificarOrden() {

        ModificarOrden vista = new ModificarOrden();

        vista.getBtnBuscar().addActionListener(e -> {

            String matricula = vista.getTxtMatricula().getText().trim();

            if (matricula.isEmpty()) {
                JOptionPane.showMessageDialog(vista,
                        "Introduce una matrícula");
                return;
            }

            ordenActual = OrdenReparacionDAO.buscarPorMatricula(matricula);

            if (ordenActual == null) {
                JOptionPane.showMessageDialog(vista,
                        "No existe ninguna orden para esa matrícula");
                return;
            }

            vista.rellenarCampos(ordenActual);
            vista.setCamposEditables(true);
            vista.getTxtMatricula().setEditable(false);
        });

        vista.getBtnModificar().addActionListener(e -> {

            if (ordenActual == null) {
                JOptionPane.showMessageDialog(vista,
                        "Primero debes buscar una orden");
                return;
            }

            try {
                ordenActual.setEstado(
                        vista.getComboEstado().getSelectedItem().toString()
                );
                ordenActual.setObservaciones(
                        vista.getTxtObservaciones().getText()
                );
                ordenActual.setPrecio(
                        Double.parseDouble(vista.getTxtPrecio().getText())
                );
                ordenActual.setFechaEstimadaCierre(
                        LocalDate.parse(
                                vista.getTxtFechaEstimada().getText()
                        )
                );

                if (OrdenReparacionDAO.modificarOrden(ordenActual)) {
                    JOptionPane.showMessageDialog(vista,
                            "Orden modificada correctamente");
                    vista.dispose();
                    abrirMenuOrdenes();
                } else {
                    JOptionPane.showMessageDialog(vista,
                            "Error al modificar la orden");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista,
                        "Datos incorrectos");
            }
        });

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuOrdenes();
        });
    }
}