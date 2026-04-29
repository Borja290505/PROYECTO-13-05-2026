package CONTROLADOR;

import DAO.ClienteDAO;
import DAO.VehiculoDAO;
import MODELO.Cliente;
import MODELO.Vehiculo;
import UTIL.LoginApp;

import VISTA.INICIO.RegistroCliente;
import VISTA.INICIO.PaginaInicio;
import VISTA.MENU.CLIENTE.AltaCliente;
import VISTA.MENU.CLIENTE.ListaCliente;
import VISTA.MENU.CLIENTE.MenuCliente;
import VISTA.MENU.CLIENTE.ModificarCliente;
import VISTA.MENU.ORDEN.MenuOrden;
import VISTA.MENU.PRINCIPAL.MenuPrincipal;
import VISTA.MENU.VEHICULO.*;

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
            String pass = new String(inicioVista.getTxtPassword().getPassword()).trim();

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
    // REGISTRO CLIENTE
    // =========================
    private void abrirRegistro() {

        RegistroCliente registroVista = new RegistroCliente();

        registroVista.getBtnRegistrar().addActionListener(e -> {
            try {
                Cliente c = registroVista.getClienteFormulario();
                c.setFechaAlta(java.time.LocalDateTime.now());

                clienteDAO.insertarCliente(c);
                registroVista.mostrarMensaje("Cliente registrado correctamente");
                registroVista.dispose();
                new ClienteControlador();

            } catch (Exception ex) {
                registroVista.mostrarMensaje("Error al registrar cliente");
            }
        });

        registroVista.getBtnVolver().addActionListener(e -> {
            registroVista.dispose();
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

        MenuVehiculo menuVehiculo = new MenuVehiculo();

        menuVehiculo.getBtnAltaVehiculo().addActionListener(e -> {
            menuVehiculo.dispose();
            abrirAltaVehiculo();
        });

        menuVehiculo.getBtnBajaVehiculo().addActionListener(e -> {
            menuVehiculo.dispose();
            abrirBajaVehiculo();
        });

        menuVehiculo.getBtnListarVehiculo().addActionListener(e -> {
            menuVehiculo.dispose();
            abrirListarVehiculos();
        });

        menuVehiculo.getBtnModificarVehiculo().addActionListener(e -> {
            menuVehiculo.dispose();
            abrirModificarVehiculo();
        });

        menuVehiculo.getBtnVolver().addActionListener(e -> {
            menuVehiculo.dispose();
            abrirMenuPrincipal();
        });
    }

    // =========================
    // MENÚ CLIENTE
    // =========================
    private void abrirMenuCliente() {

        MenuCliente menuCliente = new MenuCliente();

        menuCliente.getBtnAltaCliente().addActionListener(e -> {
            menuCliente.dispose();
            abrirAltaCliente();
        });

        menuCliente.getBtnListarCliente().addActionListener(e -> {
            menuCliente.dispose();
            abrirListarClientes();
        });

        menuCliente.getBtnModificarCliente().addActionListener(e -> {
            menuCliente.dispose();
            abrirModificarCliente();
        });

        menuCliente.getBtnVolver().addActionListener(e -> {
            menuCliente.dispose();
            abrirMenuPrincipal();
        });
    }

    // =========================
    // MENÚ ÓRDENES
    // =========================
    private void abrirMenuOrdenes() {

        MenuOrden menuOrden = new MenuOrden();

        menuOrden.getBtnVolver().addActionListener(e -> {
            menuOrden.dispose();
            abrirMenuPrincipal();
        });
    }

    // =========================
    // ALTA VEHÍCULO
    // =========================
    private void abrirAltaVehiculo() {


        AltaVehiculo altaVehiculo = new AltaVehiculo();

        altaVehiculo.getBtnVolver().addActionListener(e -> {
            altaVehiculo.dispose();
            abrirMenuVehiculo();
        });

        altaVehiculo.getBtnGuardar().addActionListener(e -> {
            altaVehiculo.dispose();
            abrirMenuPrincipal();
        });
    }

    // =========================
    // BAJA VEHÍCULO
    // =========================
    private void abrirBajaVehiculo() {

        BajaVehiculo bajaVehiculo = new BajaVehiculo();

        bajaVehiculo.getBtnEliminar().addActionListener(e -> {
            String matricula = bajaVehiculo.getTxtMatricula().getText().trim();
            if (VehiculoDAO.EliminarVehiculo(matricula)) {

                JOptionPane.showMessageDialog(bajaVehiculo,
                        "Vehículo eliminado con éxito");
                bajaVehiculo.getTxtMatricula().setText("");

            } else {
                JOptionPane.showMessageDialog(bajaVehiculo,
                        "No se encontró la matrícula: " + matricula);
            }
            bajaVehiculo.dispose();
            abrirMenuPrincipal();
        });

        bajaVehiculo.getBtnVolver().addActionListener(e -> {
            bajaVehiculo.dispose();
            abrirMenuVehiculo();
        });
    }

    // =========================
    // LISTAR VEHÍCULOS
    // =========================
    private void abrirListarVehiculos() {

        ListarVehiculo vistaLista = new ListarVehiculo();
        List<Vehiculo> lista = VehiculoDAO.ListarVehiculos();

        vistaLista.cargarDatos(lista);

        vistaLista.getBtnVolver().addActionListener(e -> {
            vistaLista.dispose();
            abrirMenuVehiculo();
        });
    }

    // =========================
    // MODIFICAR VEHÍCULO
    // =========================
    private void abrirModificarVehiculo() {

        ModificarVehiculo vistaModificar = new ModificarVehiculo();

        vistaModificar.getBtnBuscar().addActionListener(e -> {

            String matricula = vistaModificar.getTxtMatricula().getText().trim();

            Vehiculo v = VehiculoDAO.ListarVehiculos().stream()
                    .filter(veh -> veh.getMatricula().equalsIgnoreCase(matricula))
                    .findFirst()
                    .orElse(null);

            if (v != null) {
                vistaModificar.rellenarCampos(v);
                vistaModificar.setCamposEditables(true);
                vistaModificar.getTxtMatricula().setEditable(false);
            } else {
                JOptionPane.showMessageDialog(vistaModificar,
                        "No se encontró ningún vehículo con esa matrícula.");
            }
        });

        vistaModificar.getBtnModificar().addActionListener(e -> {
            try {
                String mat = vistaModificar.getTxtMatricula().getText();
                String mar = vistaModificar.getTxtMarca().getText();
                String mod = vistaModificar.getTxtModelo().getText();
                int anio = Integer.parseInt(vistaModificar.getTxtAnio().getText());
                int kms = Integer.parseInt(vistaModificar.getTxtKms().getText());

                Cliente c = (Cliente) vistaModificar.getComboClientes().getSelectedItem();

                Vehiculo v = new Vehiculo(
                        mat,
                        mar,
                        mod,
                        anio,
                        kms,
                        "Gasolina",
                        "Sin definir",
                        c.getIdCliente()
                );

                if (VehiculoDAO.ModificarVehiculo(v)) {
                    JOptionPane.showMessageDialog(vistaModificar,
                            "Vehículo actualizado correctamente.");
                    vistaModificar.dispose();
                    abrirMenuVehiculo();
                } else {
                    JOptionPane.showMessageDialog(vistaModificar,
                            "Error al actualizar el vehículo.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vistaModificar,
                        "Año y kilómetros deben ser numéricos.");
            }
        });

        vistaModificar.getBtnVolver().addActionListener(e -> {
            vistaModificar.dispose();
            abrirMenuVehiculo();
        });
    }

    private void abrirAltaCliente() {

        AltaCliente vista = new AltaCliente();

        vista.getBtnGuardar().addActionListener(e -> {
            try {
                Cliente c = vista.getClienteFormulario();
                c.setFechaAlta(java.time.LocalDateTime.now());

                clienteDAO.insertarCliente(c);

                JOptionPane.showMessageDialog(
                        vista,
                        "Cliente registrado correctamente"
                );
                vista.dispose();
                abrirMenuCliente();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        vista,
                        "Error al registrar el cliente"
                );
            }
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

        // BOTÓN BUSCAR
        vista.getBtnBuscar().addActionListener(e -> {
            String dni = vista.getTxtDni().getText().trim();

            Cliente c = clienteDAO.obtenerClientePorDni(dni);

            if (c != null) {
                vista.rellenarCampos(c);
                vista.setCamposEditables(true);
                vista.getTxtDni().setEditable(false);
            } else {
                JOptionPane.showMessageDialog(
                        vista,
                        "No se encontró ningún cliente con ese DNI"
                );
            }
        });

        // BOTÓN MODIFICAR
        vista.getBtnModificar().addActionListener(e -> {
            try {
                Cliente c = new Cliente();
                c.setDni(vista.getTxtDni().getText());
                c.setNombre(vista.getTxtNombre().getText());
                c.setApellidos(vista.getTxtApellidos().getText());
                c.setTelefono(vista.getTxtTelefono().getText());
                c.setEmail(vista.getTxtEmail().getText());
                c.setDireccion(vista.getTxtDireccion().getText());

                if (clienteDAO.modificarCliente(c)) {
                    JOptionPane.showMessageDialog(vista,
                            "Cliente modificado correctamente");
                    vista.dispose();
                    abrirMenuCliente();
                } else {
                    JOptionPane.showMessageDialog(vista,
                            "Error al modificar el cliente");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        vista,
                        "Error en los datos introducidos"
                );
            }
        });

        // BOTÓN VOLVER
        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuCliente();
        });
    }
}