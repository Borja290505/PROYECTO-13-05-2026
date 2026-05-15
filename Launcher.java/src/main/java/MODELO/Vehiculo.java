package MODELO;

public class Vehiculo {

    private String matricula;
    private String marca;
    private String modelo;
    private int anio;
    private double kmsActuales;
    private int idCliente;
    private String dni; 

    public Vehiculo(String matricula, String marca, String modelo,
                    int anio, double kmsActuales, int idCliente) {

        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.kmsActuales = kmsActuales;
        this.idCliente = idCliente;
    }

    public Vehiculo() {}

    // GETTERS
    public String getMatricula() { return matricula; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAnio() { return anio; }
    public double getKmsActuales() { return kmsActuales; }
    public int getIdCliente() { return idCliente; }
    public String getDni() { return dni; }

    // SETTERS
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setAnio(int anio) { this.anio = anio; }
    public void setKmsActuales(double kmsActuales) { this.kmsActuales = kmsActuales; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public void setDni(String dni) { this.dni = dni; }
}