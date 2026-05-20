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
    // ABRIR MENÚ ÓRDENES
    // =========================
    public void abrirMenuOrdenes() {

        MenuOrden menu = new MenuOrden();
        menu.setControlador(this);
    }

    // =========================
    // NAVEGACIÓN
    // =========================
    public void irNueva(MenuOrden vista) {
        vista.dispose();
        abrirNuevaOrden();
    }

    public void irFinalizar(MenuOrden vista) {
        vista.dispose();
        abrirFinalizarOrden();
    }

    public void irLista(MenuOrden vista) {
        vista.dispose();
        abrirListarOrdenes();
    }

    public void irModificar(MenuOrden vista) {
        vista.dispose();
        abrirModificarOrden();
    }

    public void volverMenu(JFrame vista) {
        vista.dispose();
        new MenuPrincipalControlador();
    }


    // =========================
    // NUEVA ORDEN
    // =========================
    private void abrirNuevaOrden() {

        NuevaOrden vista = new NuevaOrden();
        vista.setControlador(this);
    }

    public void crearOrden(NuevaOrden vista) {

        String matricula = vista.getTxtMatricula().getText().trim().toUpperCase();
        String kms = vista.getTxtKm().getText().trim();
        String precio = vista.getTxtPrecio().getText().trim();

        if (matricula.isEmpty() || kms.isEmpty() || precio.isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                    "Matrícula, KM y precio son obligatorios");
            return;
        }

        if (!matriculaValida(matricula)) {
            JOptionPane.showMessageDialog(vista,
                    "Formato matrícula incorrecto (1234ABC)");
            return;
        }

        if (!kmsValido(kms)) {
            JOptionPane.showMessageDialog(vista,
                    "KM inválidos");
            return;
        }

        double precioNum;
        try {
            precioNum = Double.parseDouble(precio);
            if (precioNum < 0) throw new NumberFormatException();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista,
                    "Precio inválido");
            return;
        }

        Vehiculo vehiculo = VehiculoDAO.buscarPorMatricula(matricula);

        if (vehiculo == null) {
            JOptionPane.showMessageDialog(vista,
                    "Vehículo no encontrado");
            return;
        }

        if (OrdenReparacionDAO.existeOrdenAbierta(matricula)) {
            JOptionPane.showMessageDialog(vista,
                    "Ya existe una orden abierta");
            return;
        }

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
    }

    // =========================
    // FINALIZAR ORDEN
    // =========================
    private void abrirFinalizarOrden() {

        FinalizarOrden vista = new FinalizarOrden();
        vista.setControlador(this);
    }

    public void finalizarOrden(FinalizarOrden vista) {

        String matricula = vista.getTxtMatricula().getText().trim().toUpperCase();
        String observaciones = vista.getTxtObservaciones().getText().trim();

        if (matricula.isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                    "Introduce una matrícula");
            return;
        }

        OrdenReparacion orden =
                OrdenReparacionDAO.buscarOrdenAbiertaPorMatricula(matricula);

        if (orden == null) {
            JOptionPane.showMessageDialog(vista,
                    "No hay orden abierta");
            return;
        }

        int idOrden = orden.getIdOrden();

        if (FacturaDAO.existeFacturaParaOrden(idOrden)) {
            JOptionPane.showMessageDialog(vista,
                    "Ya tiene factura");
            vista.dispose();
            abrirMenuOrdenes();
            return;
        }

        int resultado = OrdenReparacionDAO.finalizarOrdenPorMatricula(
                matricula,
                observaciones
        );

        if (resultado == -1) {
            JOptionPane.showMessageDialog(vista,
                    "Error al cerrar orden");
            return;
        }

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
                "Orden finalizada y factura creada");

        vista.dispose();
        abrirMenuOrdenes();
    }

    // =========================
    // LISTAR ORDENES
    // =========================
    private void abrirListarOrdenes() {

        ListarOrdenes vista = new ListarOrdenes();
        vista.setControlador(this);

        List<OrdenReparacion> lista = OrdenReparacionDAO.listarOrdenes();
        vista.cargarDatos(lista);
    }

    // =========================
    // MODIFICAR ORDEN
    // =========================
    private void abrirModificarOrden() {

        ModificarOrden vista = new ModificarOrden();
        vista.setControlador(this);
    }

    public void buscarModificar(ModificarOrden vista) {

        String matricula = vista.getTxtMatricula().getText().trim().toUpperCase();

        if (matricula.isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                    "Introduce matrícula");
            return;
        }

        ordenActual = OrdenReparacionDAO.buscarPorMatricula(matricula);

        if (ordenActual == null) {
            JOptionPane.showMessageDialog(vista,
                    "Orden no encontrada");
            return;
        }

        vista.rellenarCampos(ordenActual);

        if ("FINALIZADA".equalsIgnoreCase(ordenActual.getEstado())) {
            JOptionPane.showMessageDialog(vista,
                    "No se puede modificar");
            vista.setCamposEditables(false);
        } else {
            vista.setCamposEditables(true);
        }
    }

    public void modificarOrden(ModificarOrden vista) {

        if (ordenActual == null) {
            JOptionPane.showMessageDialog(vista,
                    "Busca una orden primero");
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
                        "Modificado correctamente");
                vista.dispose();
                abrirMenuOrdenes();
            } else {
                JOptionPane.showMessageDialog(vista,
                        "Error al modificar");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista,
                    "Datos inválidos");
        }
    }
}
