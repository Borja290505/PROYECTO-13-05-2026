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
            new MenuPrincipalControlador(); // vuelve al menú principal
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
                JOptionPane.showMessageDialog(vista,
                        "Introduce un DNI para buscar");
                return;
            }

            Cliente c = clienteDAO.obtenerClientePorDni(dni);

            if (c == null) {
                JOptionPane.showMessageDialog(vista,
                        "No existe ningún cliente con ese DNI");
                vista.setClienteEncontrado(null);
                return;
            }

            vista.setClienteEncontrado(c);
        });

        vista.getBtnGuardar().addActionListener(e -> {

            String matricula = vista.getTxtMatricula().getText().trim().toUpperCase();
            String marca = vista.getTxtMarca().getText().trim();
            String modelo = vista.getTxtModelo().getText().trim();
            String anioTxt = vista.getTxtAnio().getText().trim();
            String kmsTxt = vista.getTxtKms().getText().trim();
            String color = vista.getTxtColor().getText().trim();

            Cliente propietario = vista.getClienteSeleccionado();

            if (!matriculaValida(matricula)) {
                JOptionPane.showMessageDialog(vista,
                        "La matrícula debe tener el formato 1234ABC");
                return;
            }

            if (!marcaValida(marca)) {
                JOptionPane.showMessageDialog(vista,
                        "La marca no es válida");
                return;
            }

            if (!anioValido(anioTxt)) {
                JOptionPane.showMessageDialog(vista,
                        "El año no es válido");
                return;
            }

            if (!kmsValido(kmsTxt)) {
                JOptionPane.showMessageDialog(vista,
                        "Los kilómetros deben ser un número positivo");
                return;
            }

            if (!colorValido(color)) {
                JOptionPane.showMessageDialog(vista,
                        "El color no es válido");
                return;
            }

            if (propietario == null) {
                JOptionPane.showMessageDialog(vista,
                        "Debes buscar y seleccionar un cliente");
                return;
            }

            int anio = Integer.parseInt(anioTxt);
            double kms = Double.parseDouble(kmsTxt);

            Vehiculo v = new Vehiculo(
                    matricula,
                    marca,
                    modelo,
                    anio,
                    kms,
                    "Gasolina",
                    color,
                    propietario.getIdCliente()
            );

            if (VehiculoDAO.InsertarVehiculo(v)) {
                JOptionPane.showMessageDialog(vista,
                        "Vehículo insertado correctamente");
                vista.dispose();
                abrirMenuVehiculo();
            } else {
                JOptionPane.showMessageDialog(vista,
                        "Error al insertar el vehículo");
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

    // =========================
    // LISTAR VEHÍCULOS
    // =========================
    private void abrirListarVehiculos() {

        ListarVehiculo vista = new ListarVehiculo();
        vista.cargarDatos(VehiculoDAO.ListarVehiculos());

        vista.getBtnBuscar().addActionListener(e -> {

            String matricula = vista.getTxtBuscarMatricula()
                    .getText()
                    .trim()
                    .toUpperCase();

            if (matricula.isEmpty()) {
                vista.cargarDatos(VehiculoDAO.ListarVehiculos());
                return;
            }

            Vehiculo v = VehiculoDAO.buscarPorMatricula(matricula);

            if (v == null) {
                JOptionPane.showMessageDialog(vista,
                        "No existe ningún vehículo con esa matrícula");
                vista.cargarDatos(List.of());
            } else {
                vista.cargarDatos(List.of(v));
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

        vista.getBtnBuscar().addActionListener(e -> {

            String matricula = vista.getTxtMatricula().getText().trim();

            Vehiculo v = VehiculoDAO.buscarPorMatricula(matricula);

            if (v != null) {
                vista.rellenarCampos(v);
                vista.setCamposEditables(true);
                vista.getTxtMatricula().setEditable(false);
            } else {
                JOptionPane.showMessageDialog(vista,
                        "No se encontró ningún vehículo con esa matrícula.");
            }
        });



        vista.getBtnModificar().addActionListener(e -> {
            try {
                String matricula = vista.getTxtMatricula().getText();
                String marca = vista.getTxtMarca().getText();
                String modelo = vista.getTxtModelo().getText();
                int anio = Integer.parseInt(vista.getTxtAnio().getText());
                double kms = Double.parseDouble(vista.getTxtKms().getText());

                Cliente c = (Cliente) vista.getComboClientes().getSelectedItem();

                if (c == null) {
                    JOptionPane.showMessageDialog(vista,
                            "Debes seleccionar un propietario.");
                    return;
                }

                Vehiculo v = new Vehiculo(
                        matricula,
                        marca,
                        modelo,
                        anio,
                        kms,
                        "Gasolina",
                        "Blanco",
                        c.getIdCliente()
                );

                if (VehiculoDAO.ModificarVehiculo(v)) {
                    JOptionPane.showMessageDialog(vista,
                            "Vehículo actualizado correctamente.");
                    vista.dispose();
                    abrirMenuVehiculo();
                } else {
                    JOptionPane.showMessageDialog(vista,
                            "Error al actualizar el vehículo.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista,
                        "Año y kilómetros deben ser numéricos.");
            }
        });

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuVehiculo();
        });
    }
}