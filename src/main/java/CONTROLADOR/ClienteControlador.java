package CONTROLADOR;

import DAO.*;

import MODELO.Cliente;
import MODELO.Factura;
import MODELO.OrdenReparacion;
import MODELO.Vehiculo;

import UTIL.LoginApp;

import VISTA.INICIO.RegistroCliente;
import VISTA.INICIO.PaginaInicio;

import VISTA.MENU.ESTADISTICAS.Estadisticas;
import VISTA.MENU.FACTURACION.*;
import VISTA.MENU.PRINCIPAL.MenuPrincipal;


import VISTA.MENU.CLIENTE.*;
import VISTA.MENU.VEHICULO.*;
import VISTA.MENU.ORDEN.*;

import javax.swing.*;
import java.awt.image.VolatileImage;
import java.util.List;

import static UTIL.Validaciones.*;

public class ClienteControlador {

    private PaginaInicio inicioVista;
    private static ClienteDAO clienteDAO;
    private static Cliente clienteActivo;
    private OrdenReparacion ordenActual;

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
                 new MenuPrincipal();
            } else {
                JOptionPane.showMessageDialog(inicioVista,
                        "Usuario o contraseña incorrectos");
            }
        });

        inicioVista.getBtnRegistro().addActionListener(e -> {
            inicioVista.dispose();
            LoginControlador.abrirRegistro();
        });
    }

    // =========================
    // MENÚs
    // =========================


    public static void abrirMenuCliente() {

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
             new MenuPrincipal();
        });
    }


    // =========================
    // CLIENTES (YA LOS TENGO CON LAS COMPROBACIONES)
    // =========================
    private static void abrirAltaCliente() {

        AltaCliente vista = new AltaCliente();

        vista.getBtnGuardar().addActionListener(e -> {

            String dni = vista.getTxtDni().getText().trim().toUpperCase();
            String nombre = vista.getTxtNombre().getText().trim();
            String apellidos = vista.getTxtApellidos().getText().trim();
            String email = vista.getTxtEmail().getText().trim();
            String telefono = vista.getTxtTelefono().getText().trim();

            // Formato DNI
            if (!dniValido(dni)) {
                JOptionPane.showMessageDialog(vista,
                        "El DNI no tiene un formato válido");
                return;
            }

            // Nombre
            if (!nombreValido(nombre)) {
                JOptionPane.showMessageDialog(vista,
                        "El nombre no tiene un formato válido");
                return;
            }

            // Nombre
            if (!apellidoValido(apellidos)) {
                JOptionPane.showMessageDialog(vista,
                        "Los apellidos no tiene un formato válido");
                return;
            }

            // Teléfono
            if (!telefono.isEmpty() && !telefonoValido(telefono)) {
                JOptionPane.showMessageDialog(vista,
                        "El teléfono debe tener 9 dígitos");
                return;
            }
            // Email
            if (!emailValido(email)) {
                JOptionPane.showMessageDialog(vista,
                        "El email no es válido");
                return;
            }

            // Duplicado DNI
            if (clienteDAO.existeClientePorDni(dni)) {
                JOptionPane.showMessageDialog(vista,
                        "Ya existe un cliente con ese DNI");
                return;
            }


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

    private static void abrirListarClientes() {

        ListaCliente vista = new ListaCliente();
        vista.cargarDatos(clienteDAO.listarClientes());

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuCliente();
        });
    }

    private static void abrirModificarCliente() {

        ModificarCliente vistaModificarCliente = new ModificarCliente();

        // BOTÓN BUSCAR
        vistaModificarCliente.getBtnBuscar().addActionListener(e -> {

            String dni = vistaModificarCliente.getTxtDni().getText().trim();

            clienteActivo = ClienteDAO.obtenerClientePorDni(dni);

            if (clienteActivo != null) {
                vistaModificarCliente.rellenarCampos(clienteActivo);
                vistaModificarCliente.setCamposEditables(true);
                vistaModificarCliente.getTxtDni().setEditable(false);
            } else {
                JOptionPane.showMessageDialog(
                        vistaModificarCliente,
                        "No se encontró ningún cliente con ese DNI."
                );
            }
        });

        // BOTÓN MODIFICAR
        vistaModificarCliente.getBtnModificar().addActionListener(e -> {

            if (clienteActivo == null) {
                JOptionPane.showMessageDialog(
                        vistaModificarCliente,
                        "Primero debes buscar un cliente."
                );
                return;
            }

            String nombre = vistaModificarCliente.getTxtNombre().getText().trim();
            String apellidos = vistaModificarCliente.getTxtApellidos().getText().trim();
            String telefono = vistaModificarCliente.getTxtTelefono().getText().trim();
            String email = vistaModificarCliente.getTxtEmail().getText().trim();
            String direccion = vistaModificarCliente.getTxtDireccion().getText().trim();
            String dni = vistaModificarCliente.getTxtDni().getText().trim();


            if (!dniValido(dni)) {
                JOptionPane.showMessageDialog(vistaModificarCliente,
                        "El dni tiene que tener 8 numeros y un letra");
                return;
            }else if (!nombreValido(nombre)) {
                JOptionPane.showMessageDialog(vistaModificarCliente,
                        "El nombre solo puede tener letras");
                return;
            } else if (!apellidoValido(apellidos)) {
                JOptionPane.showMessageDialog(vistaModificarCliente,
                        "Los apellidos solo puede tener letras");
                return;
            } else if (!telefonoValido(telefono)) {
                JOptionPane.showMessageDialog(vistaModificarCliente,
                        "El teléfono debe tener 9 dígitos");
                return;
            } else if (!emailValido(email)) {
                JOptionPane.showMessageDialog(vistaModificarCliente,
                        "El email no tiene un formato válido");
                return;
            }
            clienteActivo.setNombre(nombre);
            clienteActivo.setApellidos(apellidos);
            clienteActivo.setTelefono(telefono);
            clienteActivo.setEmail(email);
            clienteActivo.setDni(dni);


            if (clienteDAO.modificarCliente(clienteActivo)) {
                JOptionPane.showMessageDialog(
                        vistaModificarCliente,
                        "Cliente actualizado correctamente."
                );
                vistaModificarCliente.dispose();
                abrirMenuCliente();
            } else {
                JOptionPane.showMessageDialog(
                        vistaModificarCliente,
                        "Error al actualizar el cliente."
                );
            }
        });

        // BOTÓN VOLVER
        vistaModificarCliente.getBtnVolver().addActionListener(e -> {
            vistaModificarCliente.dispose();
            abrirMenuCliente();
        });
    }
}