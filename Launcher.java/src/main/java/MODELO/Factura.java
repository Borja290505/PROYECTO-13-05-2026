package MODELO;

import java.time.LocalDate;

public class Factura {

    private int idFactura;
    private LocalDate fechaFactura;
    private double subtotal;
    private double iva;
    private double total;
    private String matricula;
    private int idOrden;

    public Factura() {
    }

    public Factura(LocalDate fechaFactura, int idFactura, int idOrden, double iva, String matricula, double subtotal, double total) {
        this.fechaFactura = fechaFactura;
        this.idFactura = idFactura;
        this.idOrden = idOrden;
        this.iva = iva;
        this.matricula = matricula;
        this.subtotal = subtotal;
        this.total = total;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public LocalDate getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(LocalDate fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
