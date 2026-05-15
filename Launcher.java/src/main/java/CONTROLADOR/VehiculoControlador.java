package CONTROLADOR;

import DAO.ClienteDAO;
import DAO.VehiculoDAO;
import MODELO.Cliente;
import MODELO.Vehiculo;
import VISTA.MENU.VEHICULO.*;

import javax.swing.*;
import java.util.List;

import static UTIL.Validaciones.*;

public class VehiculoControlador {

    private ClienteDAO clienteDAO = new ClienteDAO();

    // =========================
    // MENÚ PRINCIPAL
    // =========================
    public void abrirMenuVehiculo() {

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
            new MenuPrincipalControlador();
        });
    }

    // =========================
    // ALTA VEHÍCULO
    // =========================
    private void abrirAltaVehiculo() {

        AltaVehiculo vista = new AltaVehiculo();

        vista.getBtnBuscarCliente().addActionListener(e -> {

            String dni = vista.getTxtBuscarDni().getText().trim().toUpperCase();

            if (dni.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Introduce un DNI");
                return;
            }

            Cliente c = clienteDAO.obtenerClientePorDni(dni);

            if (c == null) {
                JOptionPane.showMessageDialog(vista, "Cliente no encontrado");
                vista.setClienteEncontrado(null);
                return;
            }

            vista.setClienteEncontrado(c);
        });

        vista.getBtnGuardar().addActionListener(e -> {

            String matricula = vista.getTxtMatricula().getText().trim().toUpperCase();
            String marca     = vista.getTxtMarca().getText().trim();
            String modelo    = vista.getTxtModelo().getText().trim();
            String anioTxt   = vista.getTxtAnio().getText().trim();
            String kmsTxt    = vista.getTxtKms().getText().trim();
            Cliente propietario = vista.getClienteSeleccionado();

            if (!matriculaValida(matricula)) {
                JOptionPane.showMessageDialog(vista, "Matrícula no válida");
                return;
            }

            if (!marcaValida(marca)) {
                JOptionPane.showMessageDialog(vista, "Marca no válida");
                return;
            }

            // ✅ NUEVO FIX
            if (!modeloValido(modelo)) {
                JOptionPane.showMessageDialog(vista, "Modelo vacío");
                return;
            }

            if (!anioValido(anioTxt)) {
                JOptionPane.showMessageDialog(vista, "Año no válido");
                return;
            }

            if (!kmsValido(kmsTxt)) {
                JOptionPane.showMessageDialog(vista, "Km no válidos");
                return;
            }

            if (propietario == null) {
                JOptionPane.showMessageDialog(vista, "Selecciona un cliente");
                return;
            }

            int anio = Integer.parseInt(anioTxt);
            double kms = Double.parseDouble(kmsTxt);

            Vehiculo v = new Vehiculo(
                    matricula, marca, modelo, anio, kms, propietario.getIdCliente()
            );

            if (VehiculoDAO.insertarVehiculo(v)) {
                JOptionPane.showMessageDialog(vista, "Vehículo insertado");
                vista.dispose();
                abrirMenuVehiculo();
            } else {
                JOptionPane.showMessageDialog(vista, "Error (¿matrícula duplicada?)");
            }
        });

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuVehiculo();
        });
    }

    // =========================
    // BAJA VEHÍCULO
    // =========================
    private void abrirBajaVehiculo() {

        BajaVehiculo vista = new BajaVehiculo();

        vista.getBtnEliminar().addActionListener(e -> {

            String matricula = vista.getTxtMatricula().getText().trim().toUpperCase();

            if (matricula.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Introduce matrícula");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    vista,
                    "¿Eliminar vehículo " + matricula + "?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) return;

            if (VehiculoDAO.eliminarVehiculo(matricula)) {
                JOptionPane.showMessageDialog(vista, "Vehículo eliminado");
            } else {
                JOptionPane.showMessageDialog(vista, "No existe la matrícula");
            }

            vista.dispose();
            abrirMenuVehiculo();
        });

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuVehiculo();
        });
    }

    // =========================
    // LISTAR VEHÍCULOS
    // =========================
    private void abrirListarVehiculos() {

        ListarVehiculo vista = new ListarVehiculo();
        vista.cargarDatos(VehiculoDAO.listarVehiculos());

        vista.getBtnBuscar().addActionListener(e -> {

            String tipo  = vista.getComboTipoBusqueda().getSelectedItem().toString();
            String valor = vista.getTxtValor().getText().trim().toUpperCase();

            if (valor.isEmpty()) {
                vista.cargarDatos(VehiculoDAO.listarVehiculos());
                return;
            }

            if (tipo.equals("Matrícula")) {

                Vehiculo v = VehiculoDAO.buscarPorMatricula(valor);

                if (v == null) {
                    JOptionPane.showMessageDialog(vista, "No encontrado");
                    vista.cargarDatos(List.of());
                } else {
                    vista.cargarDatos(List.of(v));
                }

            } else if (tipo.equals("DNI Cliente")) {

                List<Vehiculo> lista = VehiculoDAO.buscarPorDni(valor);

                if (lista.isEmpty()) {
                    JOptionPane.showMessageDialog(vista, "Sin resultados");
                }

                vista.cargarDatos(lista);
            }
        });

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuVehiculo();
        });
    }

    // =========================
    // MODIFICAR VEHÍCULO
    // =========================
    private void abrirModificarVehiculo() {

        ModificarVehiculo vista = new ModificarVehiculo();

        // FIX: la vista ya no llama a la BD en su constructor.
        // El controlador obtiene los clientes aquí y se los pasa a la vista.
        vista.cargarClientes(clienteDAO.listarClientes());

        vista.getBtnBuscar().addActionListener(e -> {

            String matricula = vista.getTxtMatricula().getText().trim().toUpperCase();

            Vehiculo v = VehiculoDAO.buscarPorMatricula(matricula);

            if (v != null) {
                vista.rellenarCampos(v);
                vista.setCamposEditables(true);
                vista.getTxtMatricula().setEditable(false);
            } else {
                JOptionPane.showMessageDialog(vista, "No encontrado");
            }
        });

        vista.getBtnModificar().addActionListener(e -> {

            try {
                String matricula = vista.getTxtMatricula().getText();
                String marca     = vista.getTxtMarca().getText().trim();
                String modelo    = vista.getTxtModelo().getText().trim();
                int anio         = Integer.parseInt(vista.getTxtAnio().getText().trim());
                double kms       = Double.parseDouble(vista.getTxtKms().getText().trim());

                Cliente c = (Cliente) vista.getComboClientes().getSelectedItem();

                if (c == null) {
                    JOptionPane.showMessageDialog(vista, "Selecciona cliente");
                    return;
                }

                Vehiculo v = new Vehiculo(matricula, marca, modelo, anio, kms, c.getIdCliente());

                if (VehiculoDAO.modificarVehiculo(v)) {
                    JOptionPane.showMessageDialog(vista, "Vehículo actualizado");
                    vista.dispose();
                    abrirMenuVehiculo();
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al actualizar");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Datos numéricos inválidos");
            }
        });

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuVehiculo();
        });
    }
}