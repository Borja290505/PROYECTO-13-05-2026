package MODELO;

import java.time.LocalDate;
import java.util.List;

public class OrdenReparacion {

    private int idOrden;
    private LocalDate fechaApertura;
    private LocalDate fechaCierre;
    private LocalDate fechaEstimadaCierre;
    private int kmEntrada;
    private String estado;
    private String observaciones;
    private Double precio_;

    private Vehiculo vehiculo;

    public OrdenReparacion() {}

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public LocalDate getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDate fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getKmEntrada() {
        return kmEntrada;
    }

    public void setKmEntrada(int kmEntrada) {
        this.kmEntrada = kmEntrada;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Double getPrecio() {
        return precio_;
    }

    public void setPrecio(Double precio_) {
        this.precio_ = precio_;
    }

    public LocalDate getFechaEstimadaCierre() {
        return fechaEstimadaCierre;
    }

    public void setFechaEstimadaCierre(LocalDate fechaEstimadaCierre) {
        this.fechaEstimadaCierre = fechaEstimadaCierre;
    }

    public Double getPrecio_() {
        return precio_;
    }

    public void setPrecio_(Double precio_) {
        this.precio_ = precio_;
    }
}