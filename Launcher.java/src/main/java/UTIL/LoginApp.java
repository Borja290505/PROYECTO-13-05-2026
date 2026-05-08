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

        try {

            File dir = new File(CARPETA);
            if (!dir.exists()) {
                dir.mkdir();
            }

            FileWriter writer = new FileWriter(ARCHIVO, true);

            LocalDateTime fechaHora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String linea = fechaHora.format(formato)
                    + " | Usuario: " + usuario
                    + " | Resultado: " + (correcto ? "LOGIN CORRECTO" : "LOGIN INCORRECTO")
                    + System.lineSeparator();

            writer.write(linea);
            writer.close();

        } catch (IOException e) {
            System.err.println("Error escribiendo el log de login");
            e.printStackTrace();
        }
    }
}

