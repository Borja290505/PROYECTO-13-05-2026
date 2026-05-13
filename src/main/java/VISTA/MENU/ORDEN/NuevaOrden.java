package VISTA.MENU.ORDEN;

import MODELO.OrdenReparacion;
import MODELO.Vehiculo;
import VISTA.VentanaBase;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class NuevaOrden extends VentanaBase {

    private JTextField txtKm, txtMatricula, txtObservaciones, txtPrecio;
    private JButton btnCrear, btnVolver;

    public NuevaOrden() {
        super("Nueva Orden de Reparación");
        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Matrícula"));
        txtMatricula = new JTextField();
        add(txtMatricula);

        add(new JLabel("KM Entrada"));
        txtKm = new JTextField();
        add(txtKm);

        add(new JLabel("Precio"));
        txtPrecio = new JTextField();
        add(txtPrecio);

        add(new JLabel("Observaciones"));
        txtObservaciones = new JTextField();
        add(txtObservaciones);

        btnCrear = new JButton("Crear Orden");
        btnVolver = new JButton("Volver");
        add(btnCrear);
        add(btnVolver);

        btnVolver.addActionListener(e -> dispose());
    }

    // Construye la orden (NO inserta)
    public OrdenReparacion getOrdenFormulario(Vehiculo vehiculo) {
        OrdenReparacion orden = new OrdenReparacion();
        orden.setFechaApertura(LocalDate.now());
        orden.setKmEntrada(Integer.parseInt(txtKm.getText()));
        orden.setEstado("ABIERTA");
        orden.setObservaciones(txtObservaciones.getText());
        orden.setPrecio(Double.parseDouble(txtPrecio.getText()));
        orden.setVehiculo(vehiculo);
        return orden;
    }

    // Getters
    public JButton getBtnCrear() { return btnCrear; }
    public JTextField getTxtMatricula() { return txtMatricula; }
    public JTextField getTxtKm() { return txtKm; }
    public JTextField getTxtPrecio() { return txtPrecio; }

    public void setTxtKm(JTextField txtKm) {
        this.txtKm = txtKm;
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

    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
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
}