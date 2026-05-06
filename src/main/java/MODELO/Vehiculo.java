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

    public Vehiculo() {

    }

    public String getMatricula() { return matricula; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAnio() { return anio; }
    public int getKmsActuales() { return kmsActuales; }
    public String getCombustible() { return combustible; }
    public String getColor() { return color; }
    public int getIdCliente() { return idCliente; }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setKmsActuales(int kmsActuales) {
        this.kmsActuales = kmsActuales;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}