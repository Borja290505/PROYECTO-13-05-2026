package UTIL;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginApp {

    private static final String CARPETA = "logs";
    private static final String ARCHIVO = "logs/login.log";

    public static void registrarLogin(String usuario, boolean correcto) {

        File dir = new File(CARPETA);
        if (!dir.exists()) {
            dir.mkdir();
        }

        LocalDateTime fechaHora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String usuarioLog = correcto ? usuario : "***";
        String linea = fechaHora.format(formato)
                + " | Usuario: " + usuarioLog
                + " | Resultado: " + (correcto ? "LOGIN CORRECTO" : "LOGIN INCORRECTO")
                + System.lineSeparator();

        try (FileWriter writer = new FileWriter(ARCHIVO, true)) {
            writer.write(linea);
        } catch (IOException e) {
            System.err.println("Error escribiendo el log de login");
            e.printStackTrace();
        }
    }
}