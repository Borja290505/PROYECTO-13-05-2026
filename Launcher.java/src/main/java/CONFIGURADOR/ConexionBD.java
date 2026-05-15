package CONFIGURADOR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String url = "jdbc:mysql://localhost:3306/rospowermotor";
    private static final String usuario = "root";
    private static final String contraseña = "1234";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(url, usuario, contraseña);
    }

}
