package VISTA;

import javax.swing.*;

public abstract class VentanaBase extends JFrame {

    protected static final int ANCHO = 600;
    protected static final int ALTO = 600;

    public VentanaBase(String titulo) {
        setTitle(titulo);
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null); // Centrada
        setResizable(false);
        setVisible(true);
    }
}