package DAO;

import CONFIGURADOR.ConexionBD;
import MODELO.OrdenReparacion;
import MODELO.Vehiculo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdenReparacionDAO {

    // =========================
    // INSERTAR ORDEN
    // =========================
    public static int insertarOrden(OrdenReparacion orden) {

        String sql = "INSERT INTO ordenreparacion " +
                "(fechaApertura, kmEntrada, estado, observaciones, matricula, manoObra) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDate(1, Date.valueOf(orden.getFechaApertura()));
            ps.setInt(2, orden.getKmEntrada());
            ps.setString(3, orden.getEstado());
            ps.setString(4, orden.getObservaciones());
            ps.setString(5, orden.getVehiculo().getMatricula());
            ps.setDouble(6, orden.getPrecio());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    // =========================
    // EXISTE ORDEN ABIERTA
    // =========================
    public static boolean existeOrdenAbierta(String matricula) {

        String sql = "SELECT 1 FROM ordenreparacion WHERE matricula = ? AND estado = 'ABIERTA'";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, matricula);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // =========================
    // BUSCAR ORDEN ABIERTA
    // =========================
    public static OrdenReparacion buscarOrdenAbiertaPorMatricula(String matricula) {

        String sql = """
            SELECT *
            FROM ordenreparacion
            WHERE matricula = ? AND estado = 'ABIERTA'
            ORDER BY fechaApertura DESC
            LIMIT 1
        """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, matricula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                OrdenReparacion o = new OrdenReparacion();
                o.setIdOrden(rs.getInt("idOrden"));
                o.setFechaApertura(rs.getDate("fechaApertura").toLocalDate());
                o.setKmEntrada(rs.getInt("kmEntrada"));
                o.setEstado(rs.getString("estado"));
                o.setObservaciones(rs.getString("observaciones"));
                o.setPrecio(rs.getDouble("manoObra"));

                // ✅ VEHÍCULO → SOLO MATRÍCULA
                Vehiculo v = new Vehiculo();
                v.setMatricula(rs.getString("matricula"));
                o.setVehiculo(v);

                return o;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // =========================
    // FINALIZAR ORDEN
    // =========================
    public static int finalizarOrdenPorMatricula(String matricula, String observaciones) {

        // 1. Obtener el id de la orden abierta ANTES de cerrarla
        int idOrden = obtenerIdOrdenAbiertaPorMatricula(matricula);
        if (idOrden == -1) return -1;

        String sql = """
            UPDATE ordenreparacion
            SET fechaCierre = ?, estado = ?, observaciones = ?
            WHERE idOrden = ? AND estado = 'ABIERTA'
        """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setString(2, "FINALIZADA");
            ps.setString(3, observaciones);
            ps.setInt(4, idOrden);

            int filas = ps.executeUpdate();
            if (filas > 0) return idOrden;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    // =========================
    // OBTENER ID DE ORDEN ABIERTA (uso interno)
    // =========================
    private static int obtenerIdOrdenAbiertaPorMatricula(String matricula) {

        String sql = """
            SELECT idOrden
            FROM ordenreparacion
            WHERE matricula = ? AND estado = 'ABIERTA'
            LIMIT 1
        """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, matricula);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("idOrden");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    // =========================
    // LISTAR ORDENES
    // =========================
    public static List<OrdenReparacion> listarOrdenes() {

        List<OrdenReparacion> lista = new ArrayList<>();

        String sql = "SELECT * FROM ordenreparacion ORDER BY fechaApertura DESC";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                OrdenReparacion o = new OrdenReparacion();
                o.setIdOrden(rs.getInt("idOrden"));
                o.setFechaApertura(rs.getDate("fechaApertura").toLocalDate());
                o.setKmEntrada(rs.getInt("kmEntrada"));
                o.setEstado(rs.getString("estado"));
                o.setObservaciones(rs.getString("observaciones"));
                o.setPrecio(rs.getDouble("manoObra"));

                if (rs.getDate("fechaCierre") != null) {
                    o.setFechaCierre(rs.getDate("fechaCierre").toLocalDate());
                }

                // ✅ VEHÍCULO
                Vehiculo v = new Vehiculo();
                v.setMatricula(rs.getString("matricula"));
                o.setVehiculo(v);

                lista.add(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // =========================
    // BUSCAR ÚLTIMA ORDEN
    // =========================
    public static OrdenReparacion buscarPorMatricula(String matricula) {

        String sql = """
            SELECT *
            FROM ordenreparacion
            WHERE matricula = ?
            ORDER BY fechaApertura DESC
            LIMIT 1
        """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, matricula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                OrdenReparacion o = new OrdenReparacion();

                o.setIdOrden(rs.getInt("idOrden"));
                o.setEstado(rs.getString("estado"));
                o.setObservaciones(rs.getString("observaciones"));
                o.setKmEntrada(rs.getInt("kmEntrada"));
                o.setPrecio(rs.getDouble("manoObra"));
                o.setFechaApertura(rs.getDate("fechaApertura").toLocalDate());

                if (rs.getDate("fechaCierre") != null) {
                    o.setFechaCierre(rs.getDate("fechaCierre").toLocalDate());
                }

                // ✅ VEHÍCULO
                Vehiculo v = new Vehiculo();
                v.setMatricula(rs.getString("matricula"));
                o.setVehiculo(v);

                return o;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // =========================
    // MODIFICAR ORDEN
    // =========================
    public static boolean modificarOrden(OrdenReparacion o) {

        String sql = """
            UPDATE ordenreparacion
            SET estado = ?, observaciones = ?, manoObra = ?
            WHERE idOrden = ?
        """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, o.getEstado());
            ps.setString(2, o.getObservaciones());
            ps.setDouble(3, o.getPrecio());
            ps.setInt(4, o.getIdOrden());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}