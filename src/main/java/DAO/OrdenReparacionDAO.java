package DAO;

import CONFIGURADOR.ConexionBD;
import MODELO.OrdenReparacion;
import MODELO.Vehiculo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdenReparacionDAO {

    public static int insertarOrden(OrdenReparacion orden) {

        String sql = "INSERT INTO ordenreparacion " +
                "(fechaApertura, kmEntrada, estado, observaciones, matricula, manoObra) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

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

    // 2️⃣ Comprobar si un vehículo ya tiene una orden ABIERTA
    public static boolean existeOrdenAbierta(String matricula) {

        String sql = "SELECT * FROM ordenreparacion " +
                "WHERE matricula = ? AND estado = 'ABIERTA'";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, matricula);
            ResultSet rs = ps.executeQuery();

            return rs.next(); // si hay resultado, existe orden abierta

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 3️⃣ Finalizar una orden por matrícula
    public static boolean finalizarOrdenPorMatricula(String matricula, String observaciones) {

        String sql = "UPDATE ordenreparacion SET " +
                "fechaCierre = ?, estado = ?, observaciones = ? " +
                "WHERE matricula = ? AND estado = 'ABIERTA'";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setString(2, "FINALIZADA");
            ps.setString(3, observaciones);
            ps.setString(4, matricula);

            // executeUpdate devuelve cuántas filas se han modificado
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // =========================
// LISTAR TODAS LAS ÓRDENES
// =========================
    public static List<OrdenReparacion> listarOrdenes() {

        List<OrdenReparacion> lista = new ArrayList<>();

        String sql = "SELECT * FROM ordenreparacion";

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

                if (rs.getDate("fechaEstimadaCierre") != null) {
                    o.setFechaEstimadaCierre(
                            rs.getDate("fechaEstimadaCierre").toLocalDate());
                }

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
}