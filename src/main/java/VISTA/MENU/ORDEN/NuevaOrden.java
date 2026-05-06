package VISTA.MENU.ORDEN;

import DAO.OrdenReparacionDAO;
import DAO.VehiculoDAO;
import MODELO.OrdenReparacion;
import MODELO.Vehiculo;
import VISTA.VentanaBase;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class NuevaOrden extends VentanaBase {

    private JTextField txtKm, txtMatricula, txtObservaciones, txtPrecio, txtFechaEstimadaCierre;
    private JButton btnCrear, btnVolver;

    private OrdenReparacion orden;

    public NuevaOrden() {
        super("Nueva Orden de Reparación");

        setLayout(new GridLayout(7, 2, 10, 10));

        // Matrícula
        add(new JLabel("Matrícula"));
        txtMatricula = new JTextField();
        add(txtMatricula);

        // KM Entrada
        add(new JLabel("KM Entrada"));
        txtKm = new JTextField();
        add(txtKm);

        // Precio
        add(new JLabel("Precio"));
        txtPrecio = new JTextField();
        add(txtPrecio);

        //Fecha estimada cierre
        add(new JLabel("Fecha Estimada Cierra"));
        txtFechaEstimadaCierre = new JTextField();
        add(txtFechaEstimadaCierre);

        // Observaciones
        add(new JLabel("Observaciones"));
        txtObservaciones = new JTextField();
        add(txtObservaciones);

        btnCrear = new JButton("Crear Orden");
        btnVolver = new JButton("Volver");

        add(btnCrear);
        add(btnVolver);

        configurarEventos();
    }

    private void configurarEventos() {

        btnCrear.addActionListener(e -> {

            if (!validarDatos()) return;

            // 1️⃣ Comprobar que el vehículo existe
            Vehiculo vehiculo = VehiculoDAO.buscarPorMatricula(txtMatricula.getText());

            if (vehiculo == null) {
                JOptionPane.showMessageDialog(this,
                        "No existe ningún vehículo con esa matrícula",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 2️⃣ Comprobar si ya tiene una orden ABIERTA
            if (OrdenReparacionDAO.existeOrdenAbierta(vehiculo.getMatricula())) {
                JOptionPane.showMessageDialog(this,
                        "Este vehículo ya tiene una orden ABIERTA.\n" +
                                "Debe modificar o finalizar esa orden.",
                        "Orden existente",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Deseas crear la orden de reparación?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacion != JOptionPane.YES_OPTION) return;

            // 3️⃣ Crear la orden
            orden = new OrdenReparacion();
            orden.setFechaApertura(LocalDate.now());
            orden.setKmEntrada(Integer.parseInt(txtKm.getText()));
            orden.setEstado("ABIERTA");
            orden.setObservaciones(txtObservaciones.getText());
            orden.setPrecio(Double.parseDouble(txtPrecio.getText()));
            orden.setVehiculo(vehiculo);

            int id = OrdenReparacionDAO.insertarOrden(orden);
            orden.setIdOrden(id);

            JOptionPane.showMessageDialog(this,
                    "Orden creada correctamente.\nID: " + id,
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            bloquearFormulario();
            btnVolver.setEnabled(true);
        });

        btnVolver.addActionListener(e -> dispose());
    }

    private boolean validarDatos() {

        if (txtKm.getText().isBlank()
                || txtMatricula.getText().isBlank()
                || txtPrecio.getText().isBlank()) {

            JOptionPane.showMessageDialog(this,
                    "KM, matrícula y precio son obligatorios",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            int km = Integer.parseInt(txtKm.getText());
            double precio = Double.parseDouble(txtPrecio.getText());

            if (km < 0 || precio < 0) {
                JOptionPane.showMessageDialog(this,
                        "KM y precio no pueden ser negativos",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "KM debe ser entero y el precio numérico",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void bloquearFormulario() {
        txtKm.setEnabled(false);
        txtMatricula.setEnabled(false);
        txtObservaciones.setEnabled(false);
        txtPrecio.setEnabled(false);
        btnCrear.setEnabled(false);
    }

    public JButton getBtnCrear() {
        return btnCrear;
    }

    public void setBtnCrear(JButton btnCrear) {
        this.btnCrear = btnCrear;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public void setBtnVolver(JButton btnVolver) {
        this.btnVolver = btnVolver;
    }

    public OrdenReparacion getOrden() {
        return orden;
    }

    public void setOrden(OrdenReparacion orden) {
        this.orden = orden;
    }

    public JTextField getTxtKm() {
        return txtKm;
    }

    public void setTxtKm(JTextField txtKm) {
        this.txtKm = txtKm;
    }

    public JTextField getTxtMatricula() {
        return txtMatricula;
    }

    public void setTxtMatricula(JTextField txtMatricula) {
        this.txtMatricula = txtMatricula;
    }

    public JTextField getTxtObservaciones() {
        return txtObservaciones;
    }

    public void setTxtObservaciones(JTextField txtObservaciones) {
        this.txtObservaciones = txtObservaciones;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }
}