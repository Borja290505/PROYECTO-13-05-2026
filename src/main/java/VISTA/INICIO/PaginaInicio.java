package VISTA.INICIO;

import CONTROLADOR.LoginControlador;
import VISTA.VentanaBase;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PaginaInicio extends VentanaBase {

    private JButton btnRegistro;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnInicioSesion;

    Font fuenteBotones = new Font("Arial", Font.BOLD, 20);
    Font fuenteTexto = new Font("Arial", Font.BOLD, 20);

    public PaginaInicio() {
        super("Inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel panelSuperior = new JPanel(new GridLayout(2, 1));
        panelSuperior.setBorder(new EmptyBorder(30, 0, 10, 0));

        JLabel titulo1 = new JLabel("BIENVENIDO A ", SwingConstants.CENTER);
        titulo1.setFont(new Font("Arial", Font.BOLD, 26));

        JLabel titulo2 = new JLabel("ROS POWER MOTOR", SwingConstants.CENTER);
        titulo2.setFont(new Font("Arial", Font.BOLD, 36));
        titulo2.setForeground(Color.RED);

        panelSuperior.add(titulo1);
        panelSuperior.add(titulo2);


        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(new EmptyBorder(20, 50, 20, 50));


        JPanel filaUsuario = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblUsuario = new JLabel("Usuario:      ");
        lblUsuario.setFont(fuenteTexto);
        txtUsuario = new JTextField();
        txtUsuario.setPreferredSize(new Dimension(250, 35)); // Aquí defines el tamaño
        filaUsuario.add(lblUsuario);
        filaUsuario.add(txtUsuario);


        JPanel filaPass = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblPass = new JLabel("Contraseña: ");
        lblPass.setFont(fuenteTexto);
        txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(250, 35)); // Aquí defines el tamaño
        filaPass.add(lblPass);
        filaPass.add(txtPassword);

        panelCentral.add(Box.createVerticalGlue());
        panelCentral.add(filaUsuario);
        panelCentral.add(filaPass);
        panelCentral.add(Box.createVerticalGlue());


        JPanel panelAbajo = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 30));

        btnInicioSesion = new JButton("Inicio Sesion");
        btnRegistro = new JButton("Registro");

        Dimension tamanoBoton = new Dimension(200, 60);
        btnInicioSesion.setPreferredSize(tamanoBoton);
        btnRegistro.setPreferredSize(tamanoBoton);
        btnInicioSesion.setFont(fuenteBotones);
        btnRegistro.setFont(fuenteBotones);

        panelAbajo.add(btnInicioSesion);
        panelAbajo.add(btnRegistro);


        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelAbajo, BorderLayout.SOUTH);

        setVisible(true);
    }
    public void setControlador(LoginControlador c){
        btnInicioSesion.addActionListener(e -> c.login(this));
        btnRegistro.addActionListener(e -> c.irRegistro(this));
    }

    public void setBtnInicioSesion(JButton btnInicioSesion) {
        this.btnInicioSesion = btnInicioSesion;
    }

    public void setBtnRegistro(JButton btnRegistro) {
        this.btnRegistro = btnRegistro;
    }

    public Font getFuenteBotones() {
        return fuenteBotones;
    }

    public void setFuenteBotones(Font fuenteBotones) {
        this.fuenteBotones = fuenteBotones;
    }

    public Font getFuenteTexto() {
        return fuenteTexto;
    }

    public void setFuenteTexto(Font fuenteTexto) {
        this.fuenteTexto = fuenteTexto;
    }

    public void setTxtPassword(JPasswordField txtPassword) {
        this.txtPassword = txtPassword;
    }

    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public JButton getBtnRegistro() { return btnRegistro; }
    public JButton getBtnInicioSesion() { return btnInicioSesion; }
    public JPasswordField getTxtPassword() { return txtPassword; }
    public JTextField getTxtUsuario() { return txtUsuario; }
}