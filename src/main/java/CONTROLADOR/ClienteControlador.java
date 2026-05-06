package CONTROLADOR;

import DAO.ClienteDAO;
import DAO.OrdenReparacionDAO;
import DAO.VehiculoDAO;

import MODELO.Cliente;
import MODELO.OrdenReparacion;
import MODELO.Vehiculo;

import UTIL.LoginApp;

import VISTA.INICIO.RegistroCliente;
import VISTA.INICIO.PaginaInicio;

import VISTA.MENU.PRINCIPAL.MenuPrincipal;

import VISTA.MENU.CLIENTE.*;
import VISTA.MENU.VEHICULO.*;
import VISTA.MENU.ORDEN.*;

import javax.swing.*;
import java.util.List;

public class ClienteControlador {

    private PaginaInicio inicioVista;
    private ClienteDAO clienteDAO;
    private Cliente clienteActivo;

    public ClienteControlador() {

        clienteDAO = new ClienteDAO();
        inicioVista = new PaginaInicio();

        inicioVista.getBtnInicioSesion().addActionListener(e -> {
            String usuario = inicioVista.getTxtUsuario().getText().trim();
            String pass = new String(inicioVista.getTxtPassword()
                    .getPassword()).trim();

            boolean loginCorrecto = clienteDAO.loginCliente(usuario, pass);
            LoginApp.registrarLogin(usuario, loginCorrecto);

            if (loginCorrecto) {
                clienteActivo = clienteDAO.obtenerClientePorDni(usuario);
                inicioVista.dispose();
                abrirMenuPrincipal();
            } else {
                JOptionPane.showMessageDialog(inicioVista,
                        "Usuario o contraseña incorrectos");
            }
        });

        inicioVista.getBtnRegistro().addActionListener(e -> {
            inicioVista.dispose();
            abrirRegistro();
        });
    }

    // =========================
    // REGISTRO
    // =========================
    private void abrirRegistro() {

        RegistroCliente registro = new RegistroCliente();

        registro.getBtnRegistrar().addActionListener(e -> {
            try {
                Cliente c = registro.getClienteFormulario();
                c.setFechaAlta(java.time.LocalDateTime.now());
                clienteDAO.insertarCliente(c);

                registro.mostrarMensaje("Cliente registrado correctamente");
                registro.dispose();
                new ClienteControlador();

            } catch (Exception ex) {
                registro.mostrarMensaje("Error al registrar cliente");
            }
        });

        registro.getBtnVolver().addActionListener(e -> {
            registro.dispose();
            new ClienteControlador();
        });
    }

    // =========================
    // MENÚ PRINCIPAL
    // =========================
    private void abrirMenuPrincipal() {

        MenuPrincipal menu = new MenuPrincipal(clienteActivo.getDni());

        menu.getBtnVehiculos().addActionListener(e -> {
            menu.dispose();
            abrirMenuVehiculo();
        });

        menu.getBtnClientes().addActionListener(e -> {
            menu.dispose();
            abrirMenuCliente();
        });

        menu.getBtnOrdenes().addActionListener(e -> {
            menu.dispose();
            abrirMenuOrdenes();
        });
    }

    // =========================
    // MENÚ VEHÍCULO
    // =========================
    private void abrirMenuVehiculo() {

        MenuVehiculo menu = new MenuVehiculo();

        menu.getBtnAltaVehiculo().addActionListener(e -> {
            menu.dispose();
            abrirAltaVehiculo();
        });

        menu.getBtnBajaVehiculo().addActionListener(e -> {
            menu.dispose();
            abrirBajaVehiculo();
        });

        menu.getBtnListarVehiculo().addActionListener(e -> {
            menu.dispose();
            abrirListarVehiculos();
        });

        menu.getBtnModificarVehiculo().addActionListener(e -> {
            menu.dispose();
            abrirModificarVehiculo();
        });

        menu.getBtnVolver().addActionListener(e -> {
            menu.dispose();
            abrirMenuPrincipal();
        });
    }

    // =========================
    // MENÚ CLIENTE
    // =========================
    private void abrirMenuCliente() {

        MenuCliente menu = new MenuCliente();

        menu.getBtnAltaCliente().addActionListener(e -> {
            menu.dispose();
            abrirAltaCliente();
        });

        menu.getBtnListarCliente().addActionListener(e -> {
            menu.dispose();
            abrirListarClientes();
        });

        menu.getBtnModificarCliente().addActionListener(e -> {
            menu.dispose();
            abrirModificarCliente();
        });

        menu.getBtnVolver().addActionListener(e -> {
            menu.dispose();
            abrirMenuPrincipal();
        });
    }

    // =========================
    // MENÚ ÓRDENES
    // =========================
    private void abrirMenuOrdenes() {

        MenuOrden menu = new MenuOrden();

        menu.getBtnNuevaOrden().addActionListener(e -> {
            menu.dispose();
            abrirNuevaOrden();
        });

        menu.getBtnFinalizarOrden().addActionListener(e -> {
            menu.dispose();
            abrirFinalizarOrden();
        });

        menu.getBtnListarOrdenes().addActionListener(e -> {
            menu.dispose();
            abrirListarOrdenes();
        });

        menu.getBtnVolver().addActionListener(e -> {
            menu.dispose();
            abrirMenuPrincipal();
        });
    }

    // =========================
    // ÓRDENES
    // =========================
    private void abrirNuevaOrden() {

        NuevaOrden vista = new NuevaOrden();

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuOrdenes();
        });
    }

    private void abrirFinalizarOrden() {

        FinalizarOrden vista = new FinalizarOrden();

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuOrdenes();
        });
    }

    private void abrirListarOrdenes() {

        ListarOrdenes vista = new ListarOrdenes();
        List<OrdenReparacion> lista =
                OrdenReparacionDAO.listarOrdenes();

        vista.cargarDatos(lista);

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuOrdenes();
        });
    }

    // =========================
    // VEHÍCULOS
    // =========================
    private void abrirAltaVehiculo() {

        AltaVehiculo vista = new AltaVehiculo();

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuVehiculo();
        });

        vista.getBtnGuardar().addActionListener(e -> {
            vista.dispose();
            abrirMenuVehiculo();
        });
    }

    private void abrirBajaVehiculo() {

        BajaVehiculo vista = new BajaVehiculo();

        vista.getBtnEliminar().addActionListener(e -> {

            String matricula = vista.getTxtMatricula().getText().trim();

            if (VehiculoDAO.EliminarVehiculo(matricula)) {
                JOptionPane.showMessageDialog(vista,
                        "Vehículo eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(vista,
                        "No existe esa matrícula");
            }

            vista.dispose();
            abrirMenuVehiculo();
        });

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuVehiculo();
        });
    }

    private void abrirListarVehiculos() {

        ListarVehiculo vista = new ListarVehiculo();
        vista.cargarDatos(VehiculoDAO.ListarVehiculos());

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuVehiculo();
        });
    }

    private void abrirModificarVehiculo() {

        ModificarVehiculo vista = new ModificarVehiculo();

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuVehiculo();
        });
    }

    // =========================
    // CLIENTES
    // =========================
    private void abrirAltaCliente() {

        AltaCliente vista = new AltaCliente();

        vista.getBtnGuardar().addActionListener(e -> {

            Cliente c = vista.getClienteFormulario();
            c.setFechaAlta(java.time.LocalDateTime.now());

            clienteDAO.insertarCliente(c);

            JOptionPane.showMessageDialog(vista,
                    "Cliente registrado correctamente");

            vista.dispose();
            abrirMenuCliente();
        });

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuCliente();
        });
    }

    private void abrirListarClientes() {

        ListaCliente vista = new ListaCliente();
        vista.cargarDatos(clienteDAO.listarClientes());

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuCliente();
        });
    }

    private void abrirModificarCliente() {

        ModificarCliente vista = new ModificarCliente();

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuCliente();
        });
    }
}