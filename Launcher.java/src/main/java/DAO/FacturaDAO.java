package DAO;

import CONFIGURADOR.ConexionBD;
import MODELO.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {

    // =========================
    // INSERTAR FACTURA
    // =========================
    public static boolean insertarFactura(Factura f) {

        String sql = """
            INSERT INTO factura (fechaFactura, subtotal, iva, total, idOrden)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, java.sql.Date.valueOf(f.getFechaFactura()));
            ps.setDouble(2, f.getSubtotal());
            ps.setDouble(3, f.getIva());
            ps.setDouble(4, f.getTotal());
            ps.setInt(5, f.getIdOrden());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // =========================
    // LISTAR TODAS (ORDENADAS)
    // =========================
    public static List<Factura> listarFacturas() {

        List<Factura> lista = new ArrayList<>();

        String sql = """
            SELECT f.*, o.matricula
            FROM factura f
            JOIN ordenreparacion o ON f.idOrden = o.idOrden
            ORDER BY f.fechaFactura DESC
        """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(rellenarFactura(rs)); // ✅ método reutilizable
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // =========================
    // FILTRAR POR MATRÍCULA
    // =========================
    public static List<Factura> listarFacturasPorMatricula(String matricula) {

        List<Factura> lista = new ArrayList<>();

        String sql = """
            SELECT f.*, o.matricula
            FROM factura f
            JOIN ordenreparacion o ON f.idOrden = o.idOrden
            WHERE o.matricula = ?
            ORDER BY f.fechaFactura DESC
        """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, matricula);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(rellenarFactura(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // =========================
    // FILTRAR POR DNI
    // =========================
    public static List<Factura> listarFacturasPorDni(String dni) {

        List<Factura> lista = new ArrayList<>();

        String sql = """
            SELECT f.*, o.matricula
            FROM factura f
            JOIN ordenreparacion o ON f.idOrden = o.idOrden
            JOIN vehiculo v ON o.matricula = v.matricula
            JOIN cliente c ON v.idCliente = c.idCliente
            WHERE c.dni = ?
            ORDER BY f.fechaFactura DESC
        """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(rellenarFactura(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // =========================
    // EXISTE FACTURA PARA ORDEN
    // =========================
    public static boolean existeFacturaParaOrden(int idOrden) {

        String sql = "SELECT 1 FROM factura WHERE idOrden = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idOrden);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private static Factura rellenarFactura(ResultSet rs) throws Exception {

        Factura f = new Factura();

        f.setIdFactura(rs.getInt("idFactura"));
        f.setFechaFactura(rs.getDate("fechaFactura").toLocalDate());
        f.setSubtotal(rs.getDouble("subtotal"));
        f.setIva(rs.getDouble("iva"));
        f.setTotal(rs.getDouble("total"));
        f.setMatricula(rs.getString("matricula"));

        return f;
    }
}