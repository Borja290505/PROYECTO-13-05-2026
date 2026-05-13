package CONTROLADOR;

import DAO.FacturaDAO;
import DAO.OrdenReparacionDAO;
import DAO.VehiculoDAO;
import MODELO.Factura;
import MODELO.OrdenReparacion;
import MODELO.Vehiculo;
import VISTA.MENU.ORDEN.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

import static UTIL.Validaciones.kmsValido;
import static UTIL.Validaciones.matriculaValida;

public class OrdenRepControlador {

    private OrdenReparacion ordenActual;

    // =========================
    // MENÚ ÓRDENES
    // =========================
    public void abrirMenuOrdenes() {

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
            new MenuPrincipalControlador();
        });
    }

    // =========================
    // NUEVA ORDEN
    // =========================
    private void abrirNuevaOrden() {

        NuevaOrden vista = new NuevaOrden();


        vista.getBtnCrear().addActionListener(e -> {

            String matricula = vista.getTxtMatricula()
                    .getText().trim().toUpperCase();
            String kms = vista.getTxtKm().getText().trim();
            String precio = vista.getTxtPrecio().getText().trim();

            // 1️⃣ Campos obligatorios
            if (matricula.isEmpty() || kms.isEmpty() || precio.isEmpty()) {
                JOptionPane.showMessageDialog(vista,
                        "Matrícula, KM y precio son obligatorios",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 2️⃣ Validaciones con TU clase
            if (!matriculaValida(matricula)) {
                JOptionPane.showMessageDialog(vista,
                        "Formato de matrícula incorrecto (1234ABC)");
                return;
            }

            if (!kmsValido(kms)) {
                JOptionPane.showMessageDialog(vista,
                        "Los KM deben ser un número válido");
                return;
            }

            double precioNum;
            try {
                precioNum = Double.parseDouble(precio);
                if (precioNum < 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista,
                        "El precio debe ser un número positivo");
                return;
            }

            // 3️⃣ Vehículo
            Vehiculo vehiculo = VehiculoDAO.buscarPorMatricula(matricula);
            if (vehiculo == null) {
                JOptionPane.showMessageDialog(vista,
                        "No existe ningún vehículo con esa matrícula");
                return;
            }

            // 4️⃣ Orden abierta
            if (OrdenReparacionDAO.existeOrdenAbierta(matricula)) {
                JOptionPane.showMessageDialog(vista,
                        "Este vehículo ya tiene una orden ABIERTA");
                return;
            }

            // 5️⃣ Crear orden
            OrdenReparacion orden = vista.getOrdenFormulario(vehiculo);
            int id = OrdenReparacionDAO.insertarOrden(orden);

            if (id > 0) {
                JOptionPane.showMessageDialog(vista,
                        "Orden creada correctamente");
                vista.dispose();
                abrirMenuOrdenes();
            } else {
                JOptionPane.showMessageDialog(vista,
                        "Error al crear la orden");
            }
        });


        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuOrdenes();
        });
    }

    // =========================
    // FINALIZAR ORDEN
    // =========================
    private void abrirFinalizarOrden() {

        FinalizarOrden vista = new FinalizarOrden();

        vista.getBtnFinalizar().addActionListener(e -> {

            // ✅ Evitar doble ejecución
            vista.getBtnFinalizar().setEnabled(false);

            String matricula = vista.getTxtMatricula()
                    .getText()
                    .trim()
                    .toUpperCase();

            String observaciones = vista.getTxtObservaciones().getText().trim();

            if (matricula.isEmpty()) {
                JOptionPane.showMessageDialog(vista,
                        "Introduce una matrícula");
                vista.getBtnFinalizar().setEnabled(true);
                return;
            }

            // ✅ 1. Buscar SOLO la orden ABIERTA
            OrdenReparacion orden =
                    OrdenReparacionDAO.buscarOrdenAbiertaPorMatricula(matricula);

            if (orden == null) {
                JOptionPane.showMessageDialog(vista,
                        "No existe una orden ABIERTA para esa matrícula");
                vista.getBtnFinalizar().setEnabled(true);
                return;
            }

            int idOrden = orden.getIdOrden();

            // ✅ 2. Comprobar si YA existe factura para esa orden
            if (FacturaDAO.existeFacturaParaOrden(idOrden)) {
                JOptionPane.showMessageDialog(vista,
                        "Esta orden ya tiene una factura asociada");
                vista.dispose();
                abrirMenuOrdenes();
                return;
            }

            // ✅ 3. Finalizar orden en BD
            int resultado = OrdenReparacionDAO.finalizarOrdenPorMatricula(
                    matricula,
                    observaciones
            );

            if (resultado == -1) {
                JOptionPane.showMessageDialog(vista,
                        "Error al cerrar la orden");
                vista.getBtnFinalizar().setEnabled(true);
                return;
            }

            // ✅ 4. Crear factura (usando datos ya obtenidos)
            double subtotal = orden.getPrecio();
            double iva = Math.round(subtotal * 0.21 * 100.0) / 100.0;
            double total = Math.round((subtotal + iva) * 100.0) / 100.0;

            Factura factura = new Factura();
            factura.setFechaFactura(LocalDate.now());
            factura.setSubtotal(subtotal);
            factura.setIva(iva);
            factura.setTotal(total);
            factura.setIdOrden(idOrden);

            FacturaDAO.insertarFactura(factura);

            JOptionPane.showMessageDialog(vista,
                    "Orden finalizada y factura creada correctamente");

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
    private void abrirListarOrdenes() {

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
    private void abrirModificarOrden() {

        ModificarOrden vista = new ModificarOrden();

        vista.getBtnBuscar().addActionListener(e -> {

            String matricula = vista.getTxtMatricula()
                    .getText()
                    .trim()
                    .toUpperCase();

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

            if ("FINALIZADA".equalsIgnoreCase(ordenActual.getEstado())) {
                JOptionPane.showMessageDialog(vista,
                        "Esta orden está finalizada y no se puede modificar");
                vista.setCamposEditables(false);
                return;
            }

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
                ordenActual.setObservaciones(
                        vista.getTxtObservaciones().getText()
                );
                ordenActual.setPrecio(
                        Double.parseDouble(vista.getTxtPrecio().getText())
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