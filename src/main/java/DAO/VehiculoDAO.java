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
    public static boolean InsertarVehiculo(Vehiculo v) {

        String sql = """
            INSERT INTO Vehiculo
            (matricula, marca, modelo, anio, kmsActuales, combustible, color, idCliente)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, v.getMatricula());
            ps.setString(2, v.getMarca());
            ps.setString(3, v.getModelo());
            ps.setInt(4, v.getAnio());
            ps.setInt(5, v.getKmsActuales());
            ps.setString(6, v.getCombustible());
            ps.setString(7, v.getColor());
            ps.setInt(8, v.getIdCliente());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // =========================
    // ELIMINAR VEHÍCULO
    // =========================
    public static boolean EliminarVehiculo(String matricula) {

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
    // LISTAR VEHÍCULOS
    // =========================
    public static List<Vehiculo> ListarVehiculos() {

        List<Vehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Vehiculo";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vehiculo v = new Vehiculo(
                        rs.getString("matricula"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("anio"),
                        rs.getInt("kmsActuales"),
                        rs.getString("combustible"),
                        rs.getString("color"),
                        rs.getInt("idCliente")
                );
                lista.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // =========================
    // MODIFICAR VEHÍCULO
    // =========================
    public static boolean ModificarVehiculo(Vehiculo v) {

        String sql = """
            UPDATE Vehiculo
            SET marca = ?, modelo = ?, anio = ?, kmsActuales = ?,
                combustible = ?, color = ?, idCliente = ?
            WHERE matricula = ?
        """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, v.getMarca());
            ps.setString(2, v.getModelo());
            ps.setInt(3, v.getAnio());
            ps.setInt(4, v.getKmsActuales());
            ps.setString(5, v.getCombustible());
            ps.setString(6, v.getColor());
            ps.setInt(7, v.getIdCliente());
            ps.setString(8, v.getMatricula());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}