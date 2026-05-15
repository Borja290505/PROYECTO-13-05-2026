package DAO;

import CONFIGURADOR.ConexionBD;
import MODELO.Vehiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {

    // =========================
    // INSERTAR VEHÍCULO
    // =========================
    public static boolean insertarVehiculo(Vehiculo v) {

        String sql = """
            INSERT INTO Vehiculo
            (matricula, marca, modelo, anio, kmsActuales, idCliente)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, v.getMatricula());
            ps.setString(2, v.getMarca());
            ps.setString(3, v.getModelo());
            ps.setInt(4, v.getAnio());
            ps.setDouble(5, v.getKmsActuales());
            ps.setInt(6, v.getIdCliente());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // =========================
    // ELIMINAR VEHÍCULO
    // =========================
    public static boolean eliminarVehiculo(String matricula) {

        String sql = "DELETE FROM Vehiculo WHERE matricula = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, matricula);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // =========================
    // LISTAR VEHÍCULOS (CON DNI)
    // =========================
    public static List<Vehiculo> listarVehiculos() {

        List<Vehiculo> lista = new ArrayList<>();

        String sql = """
            SELECT v.*, c.dni
            FROM vehiculo v
            JOIN cliente c ON v.idCliente = c.idCliente
            ORDER BY v.matricula
        """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Vehiculo v = new Vehiculo(
                        rs.getString("matricula"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("anio"),
                        rs.getDouble("kmsActuales"),
                        rs.getInt("idCliente")
                );

                v.setDni(rs.getString("dni")); // ✅ IMPORTANTE

                lista.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // =========================
    // BUSCAR POR MATRÍCULA
    // =========================
    public static Vehiculo buscarPorMatricula(String matricula) {

        String sql = """
            SELECT v.*, c.dni
            FROM vehiculo v
            JOIN cliente c ON v.idCliente = c.idCliente
            WHERE v.matricula = ?
        """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, matricula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Vehiculo v = new Vehiculo(
                        rs.getString("matricula"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("anio"),
                        rs.getDouble("kmsActuales"),
                        rs.getInt("idCliente")
                );

                v.setDni(rs.getString("dni"));

                return v;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // =========================
    // BUSCAR POR DNI
    // =========================
    public static List<Vehiculo> buscarPorDni(String dni) {

        List<Vehiculo> lista = new ArrayList<>();

        String sql = """
            SELECT v.*, c.dni
            FROM vehiculo v
            JOIN cliente c ON v.idCliente = c.idCliente
            WHERE c.dni = ?
        """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Vehiculo v = new Vehiculo(
                        rs.getString("matricula"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("anio"),
                        rs.getDouble("kmsActuales"),
                        rs.getInt("idCliente")
                );

                v.setDni(rs.getString("dni"));

                lista.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // =========================
    // MODIFICAR VEHÍCULO (CORREGIDO)
    // =========================
    public static boolean modificarVehiculo(Vehiculo v) {

        String sql = """
            UPDATE Vehiculo
            SET marca = ?, modelo = ?, anio = ?, kmsActuales = ?, idCliente = ?
            WHERE matricula = ?
        """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, v.getMarca());
            ps.setString(2, v.getModelo());
            ps.setInt(3, v.getAnio());
            ps.setDouble(4, v.getKmsActuales());
            ps.setInt(5, v.getIdCliente());
            ps.setString(6, v.getMatricula());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
