package MODELO;

public class Vehiculo {

    private String matricula;
    private String marca;
    private String modelo;
    private int anio;
    private int kmsActuales;
    private String combustible;
    private String color;
    private int idCliente;

    public Vehiculo(String matricula, String marca, String modelo,
                    int anio, int kmsActuales,
                    String combustible, String color, int idCliente) {

        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.kmsActuales = kmsActuales;
        this.combustible = combustible;
        this.color = color;
        this.idCliente = idCliente;
    }

    public String getMatricula() { return matricula; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAnio() { return anio; }
    public int getKmsActuales() { return kmsActuales; }
    public String getCombustible() { return combustible; }
    public String getColor() { return color; }
    public int getIdCliente() { return idCliente; }
}