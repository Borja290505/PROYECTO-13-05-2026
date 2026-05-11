package UTIL;



public class Validaciones {

    private static final String regexNombre = "[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+(\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)*";
    private static final String regexApellido = "[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+(\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)*";
    private static final String regexDni = "\\d{8}[A-Z]";
    private static final String regexEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String regexTelefono = "\\d{9}";


    private static final String regexMatricula = "\\d{4}[A-Z]{3}";
    private static final String regexMarca = "[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+(\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)*";
    private static final String regexAnio = "[0-9]{4}";
    private static final String regexKmActuales = "\\d+";
    private static final String regexColor = "[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+(\\s[a-záéíóúñ]+)*";

    // =========================
    // VALIDACIONES
    // =========================

    public static boolean nombreValido(String nombre) {
        return nombre.matches(regexNombre);
    }

    public static boolean apellidoValido(String nombre) {
        return nombre.matches(regexNombre);
    }

    public static boolean emailValido(String email) {
        return email.matches(regexEmail);
    }

    public static boolean dniValido(String dni) {
        return dni.matches(regexDni);
    }

    public static boolean telefonoValido(String telefono) {
        return telefono.matches(regexTelefono);
    }

    public static boolean matriculaValida(String matricula) {
        return matricula.matches(regexMatricula);
    }

    public static boolean marcaValida(String marca) {
        return marca.matches(regexMarca);
    }

    public static boolean anioValido(String anio) {
        try {
            int a = Integer.parseInt(anio);
            int actual = java.time.LocalDate.now().getYear();
            return a >= 1900 && a <= actual && anio.matches(regexAnio);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean kmsValido(String kms) {
        return kms.matches(regexKmActuales);
    }

    public static boolean colorValido(String color) {
        return color.matches(regexColor);
    }
}
