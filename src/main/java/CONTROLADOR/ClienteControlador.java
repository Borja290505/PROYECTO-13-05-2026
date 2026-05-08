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
import java.util.List;

public class ClienteControlador {

    private PaginaInicio inicioVista;
    private ClienteDAO clienteDAO;
    private Cliente clienteActivo;
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
    // MENÚs
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

        menu.getBtnFacturacion().addActionListener(e -> {
            menu.dispose();
            abrirMenuFacturacion();
        });

        menu.getBtnEstadisticas().addActionListener(e -> {
            menu.dispose();
            abrirMenuEstadisticas();
        });

        menu.getBtnSalir().addActionListener(e -> {

            String[] opciones = {
                    "Cerrar sesión",
                    "Salir del programa",
                    "Cancelar"
            };

            int eleccion = JOptionPane.showOptionDialog(
                    menu,
                    "¿Qué deseas hacer?",
                    "Cerrar sesión",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            // 0 → Cerrar sesión
            if (eleccion == 0) {
                clienteActivo = null;   // limpieza
                menu.dispose();         // cerramos el menú
                new ClienteControlador(); // volvemos al login
            }

            // 1 → Salir del programa
            else if (eleccion == 1) {
                System.exit(0);
            }

            // 2 → Cancelar (no hacemos nada)
        });

    }

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

        menu.getBtnModificarOrden().addActionListener(e -> {
            menu.dispose();
            abrirModificarOrden();
        });

        menu.getBtnVolver().addActionListener(e -> {
            menu.dispose();
            abrirMenuPrincipal();
        });
    }

    private void abrirMenuFacturacion(){
        MenuFacturacion menu = new MenuFacturacion();

        menu.getBtnListarFacturas().addActionListener(e -> {
            menu.dispose();
            abrirListarFacturas();
        });

        menu.getBtnBuscarFacturas().addActionListener(e -> {
            menu.dispose();
            abrirBuscarFacturas();
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

        vista.getBtnFinalizar().addActionListener(e -> {

            String matricula = vista.getTxtMatricula().getText().trim();
            String observaciones = vista.getTxtObservaciones().getText().trim();

            if (matricula.isEmpty()) {
                JOptionPane.showMessageDialog(vista,
                        "Introduce una matrícula");
                return;
            }

            // Finalizamos la orden y obtenemos su id
            int idOrden = OrdenReparacionDAO.finalizarOrdenPorMatricula(
                    matricula,
                    observaciones
            );

            if (idOrden != -1) {

                // =========================
                // CREAR FACTURA
                // =========================
                OrdenReparacion orden = OrdenReparacionDAO.buscarPorMatricula(matricula);

                Factura f = new Factura();
                f.setFechaFactura(java.time.LocalDate.now());

                double subtotal = orden.getPrecio();   // manoObra
                double total = subtotal * 1.21;
                double iva = total-subtotal;

                f.setSubtotal(subtotal);
                f.setIva(iva);
                f.setTotal(total);
                f.setIdOrden(idOrden);

                FacturaDAO.insertarFactura(f);

                JOptionPane.showMessageDialog(vista,
                        "Orden finalizada y factura creada correctamente");

                vista.dispose();
                abrirMenuOrdenes();

            } else {
                JOptionPane.showMessageDialog(vista,
                        "No hay ninguna orden abierta para esa matrícula");
            }
        });

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

    private void abrirModificarOrden() {

        ModificarOrden vista = new ModificarOrden();

        // =========================
        // BOTÓN BUSCAR
        // =========================
        vista.getBtnBuscar().addActionListener(e -> {

            String matricula = vista.getTxtMatricula().getText().trim();

            if (matricula.isEmpty()) {
                JOptionPane.showMessageDialog(vista,
                        "Introduce una matrícula");
                return;
            }

            // Buscamos la orden por matrícula
            ordenActual = OrdenReparacionDAO.buscarPorMatricula(matricula);

            if (ordenActual != null) {

                // Rellenamos los campos de la vista
                vista.getComboEstado().setSelectedItem(ordenActual.getEstado());
                vista.getTxtObservaciones().setText(ordenActual.getObservaciones());

                vista.getTxtPrecio().setText(
                        String.valueOf(ordenActual.getPrecio())
                );

                vista.getTxtFechaEstimada().setText(
                        String.valueOf(ordenActual.getFechaEstimadaCierre())
                );

                vista.setCamposEditables(true);
                vista.getTxtMatricula().setEditable(false); // no se cambia

            } else {
                JOptionPane.showMessageDialog(vista,
                        "No hay ninguna orden con esa matrícula");
            }
        });

        // =========================
        // BOTÓN MODIFICAR
        // =========================
        vista.getBtnModificar().addActionListener(e -> {

            if (ordenActual == null) {
                JOptionPane.showMessageDialog(vista,
                        "Primero debes buscar una orden");
                return;
            }

            try {
                // Actualizamos el objeto
                ordenActual.setEstado(
                        vista.getComboEstado().getSelectedItem().toString()
                );
                ordenActual.setObservaciones(
                        vista.getTxtObservaciones().getText()
                );
                ordenActual.setPrecio(
                        Double.parseDouble(vista.getTxtPrecio().getText())
                );
                ordenActual.setFechaEstimadaCierre(
                        java.time.LocalDate.parse(
                                vista.getTxtFechaEstimada().getText()
                        )
                );

                // Guardamos en la base de datos
                if (OrdenReparacionDAO.modificarOrden(ordenActual)) {
                    JOptionPane.showMessageDialog(vista,
                            "Orden modificada correctamente");
                    vista.dispose();
                    abrirMenuOrdenes();
                } else {
                    JOptionPane.showMessageDialog(vista,
                            "Error al modificar la orden");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista,
                        "Datos incorrectos");
            }
        });

        // =========================
        // BOTÓN VOLVER
        // =========================
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

        ModificarVehiculo vistaModificar = new ModificarVehiculo();

        // Acción del botón BUSCAR
        vistaModificar.getBtnBuscar().addActionListener(e -> {
            String matricula = vistaModificar.getTxtMatricula().getText().trim();

            // Usamos el método estático del DAO
            Vehiculo v = VehiculoDAO.buscarPorMatricula(matricula);

            if (v != null) {
                vistaModificar.rellenarCampos(v);
                vistaModificar.setCamposEditables(true);
                vistaModificar.getTxtMatricula().setEditable(false); // Bloqueamos ID
            } else {
                JOptionPane.showMessageDialog(vistaModificar,
                        "No se encontró ningún vehículo con esa matrícula.");
            }
        });

        // Acción del botón GUARDAR (Modificar)
        vistaModificar.getBtnModificar().addActionListener(e -> {
            try {
                // Capturamos los datos de la vista
                String matricula = vistaModificar.getTxtMatricula().getText();
                String marca = vistaModificar.getTxtMarca().getText();
                String modelo = vistaModificar.getTxtModelo().getText();
                int anio = Integer.parseInt(vistaModificar.getTxtAnio().getText());
                int kms = Integer.parseInt(vistaModificar.getTxtKms().getText());

                // Obtenemos el cliente seleccionado del ComboBox
                Cliente c = (Cliente) vistaModificar.getComboClientes().getSelectedItem();

                if (c == null) {
                    JOptionPane.showMessageDialog(vistaModificar, "Debes seleccionar un propietario.");
                    return;
                }

                // Creamos el objeto vehículo con los nuevos datos
                Vehiculo v = new Vehiculo(matricula, marca, modelo, anio, kms, "Gasolina", "Blanco", c.getIdCliente());

                // Llamamos al DAO para actualizar en la BD
                if (VehiculoDAO.ModificarVehiculo(v)) {
                    JOptionPane.showMessageDialog(vistaModificar, "Vehículo actualizado con éxito.");
                    vistaModificar.dispose();
                    abrirMenuVehiculo();
                } else {
                    JOptionPane.showMessageDialog(vistaModificar, "Error al actualizar en la base de datos.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vistaModificar, "Año y Kilómetros deben ser números válidos.");
            }
        });

        // Acción del botón VOLVER
        vistaModificar.getBtnVolver().addActionListener(e -> {
            vistaModificar.dispose();
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

            clienteActivo.setNombre(vistaModificarCliente.getTxtNombre().getText().trim());
            clienteActivo.setApellidos(vistaModificarCliente.getTxtApellidos().getText().trim());
            clienteActivo.setTelefono(vistaModificarCliente.getTxtTelefono().getText().trim());
            clienteActivo.setEmail(vistaModificarCliente.getTxtEmail().getText().trim());
            clienteActivo.setDireccion(vistaModificarCliente.getTxtDireccion().getText().trim());

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

    // =========================
    // FACTURAS
    // =========================
    private void abrirListarFacturas() {

        // Creamos la vista
        ListarFacturas vista = new ListarFacturas();

        // Obtenemos las facturas de la base de datos
        vista.cargarDatos(FacturaDAO.listarFacturas());

        // Botón volver
        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuFacturacion();
        });
    }

    private void abrirBuscarFacturas() {

        BuscarFacturas vista = new BuscarFacturas();

        // BOTÓN BUSCAR
        vista.getBtnBuscar().addActionListener(e -> {

            String tipo = vista.getComboTipoBusqueda()
                    .getSelectedItem().toString();

            String valor = vista.getTxtValor()
                    .getText()
                    .trim()
                    .toUpperCase();

            List<Factura> lista;

            if (tipo.equals("Matrícula")) {
                lista = FacturaDAO.listarFacturasPorMatricula(valor);
            } else {
                lista = FacturaDAO.listarFacturasPorDni(valor);
            }

            System.out.println("Facturas encontradas: " + lista.size());

            vista.cargarDatos(lista);
        });

        // BOTÓN VOLVER
        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuFacturacion();
        });
    }

    // =========================
    // ESTADISTICAS
    // =========================
    private void abrirMenuEstadisticas() {

        Estadisticas vista = new Estadisticas();

        int totalClientes = EstadisticasDAO.totalClientes();
        int totalVehiculos = EstadisticasDAO.totalVehiculos();
        double sinIva = EstadisticasDAO.totalSinIva();
        double iva = EstadisticasDAO.totalIva();
        double conIva = EstadisticasDAO.totalConIva();

        vista.cargarDatos(
                totalClientes,
                totalVehiculos,
                sinIva,
                iva,
                conIva
        );

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuPrincipal();
        });
    }
}