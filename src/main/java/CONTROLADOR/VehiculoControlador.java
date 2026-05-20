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
    private Vehiculo vehiculoActual;

    // =========================
    // ABRIR MENÚ VEHÍCULOS
    // =========================
    public void abrirMenuVehiculo() {

        MenuVehiculo menu = new MenuVehiculo();
        menu.setControlador(this);
    }

    // =========================
    // NAVEGACIÓN
    // =========================
    public void irAlta(MenuVehiculo vista) {
        vista.dispose();
        abrirAltaVehiculo();
    }

    public void irBaja(MenuVehiculo vista) {
        vista.dispose();
        abrirBajaVehiculo();
    }

    public void irLista(MenuVehiculo vista) {
        vista.dispose();
        abrirListarVehiculos();
    }

    public void irModificar(MenuVehiculo vista) {
        vista.dispose();
        abrirModificarVehiculo();
    }

    public void volverMenu(JFrame vista) {
        vista.dispose();
        new MenuPrincipalControlador();
    }


    // =========================
    // ALTA VEHÍCULO
    // =========================
    private void abrirAltaVehiculo() {

        AltaVehiculo vista = new AltaVehiculo();
        vista.setControlador(this);
    }

    public void buscarCliente(AltaVehiculo vista) {

        String dni = vista.getTxtBuscarDni().getText().trim().toUpperCase();

        if (dni.isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                    "Introduce un DNI");
            return;
        }

        Cliente c = clienteDAO.obtenerClientePorDni(dni);

        if (c == null) {
            JOptionPane.showMessageDialog(vista,
                    "Cliente no encontrado");
            vista.setClienteEncontrado(null);
            return;
        }

        vista.setClienteEncontrado(c);
    }

    public void guardarVehiculo(AltaVehiculo vista) {

        String matricula = vista.getTxtMatricula().getText().trim().toUpperCase();
        String marca = vista.getTxtMarca().getText().trim();
        String modelo = vista.getTxtModelo().getText().trim();
        String anioTxt = vista.getTxtAnio().getText().trim();
        String kmsTxt = vista.getTxtKms().getText().trim();

        Cliente propietario = vista.getClienteSeleccionado();

        if (!matriculaValida(matricula)) {
            JOptionPane.showMessageDialog(vista,
                    "Formato matrícula incorrecto");
            return;
        }

        if (!marcaValida(marca)) {
            JOptionPane.showMessageDialog(vista,
                    "Marca inválida");
            return;
        }

        if (!anioValido(anioTxt)) {
            JOptionPane.showMessageDialog(vista,
                    "Año inválido");
            return;
        }

        if (!kmsValido(kmsTxt)) {
            JOptionPane.showMessageDialog(vista,
                    "KM inválidos");
            return;
        }

        if (propietario == null) {
            JOptionPane.showMessageDialog(vista,
                    "Selecciona un cliente");
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
                propietario.getIdCliente()
        );

        if (VehiculoDAO.InsertarVehiculo(v)) {
            JOptionPane.showMessageDialog(vista,
                    "Vehículo guardado");
            vista.dispose();
            abrirMenuVehiculo();
        } else {
            JOptionPane.showMessageDialog(vista,
                    "Error al guardar");
        }
    }

    // =========================
    // BAJA VEHÍCULO
    // =========================
    private void abrirBajaVehiculo() {

        BajaVehiculo vista = new BajaVehiculo();
        vista.setControlador(this);
    }

    public void eliminarVehiculo(BajaVehiculo vista) {

        String matricula = vista.getTxtMatricula().getText().trim();

        if (VehiculoDAO.EliminarVehiculo(matricula)) {
            JOptionPane.showMessageDialog(vista,
                    "Vehículo eliminado");
        } else {
            JOptionPane.showMessageDialog(vista,
                    "No existe ese vehículo");
        }

        vista.dispose();
        abrirMenuVehiculo();
    }

    // =========================
    // LISTAR VEHÍCULOS
    // =========================
    private void abrirListarVehiculos() {

        ListarVehiculo vista = new ListarVehiculo();
        vista.setControlador(this);

        vista.cargarDatos(VehiculoDAO.ListarVehiculos());
    }

    public void buscarVehiculo(ListarVehiculo vista) {

        String tipo = vista.getComboTipoBusqueda().getSelectedItem().toString();
        String valor = vista.getTxtValor().getText().trim().toUpperCase();

        if (valor.isEmpty()) {
            vista.cargarDatos(VehiculoDAO.ListarVehiculos());
            return;
        }

        if (tipo.equals("Matrícula")) {

            Vehiculo v = VehiculoDAO.buscarPorMatricula(valor);

            if (v == null) {
                JOptionPane.showMessageDialog(vista,
                        "No encontrado");
                vista.cargarDatos(List.of());
            } else {
                vista.cargarDatos(List.of(v));
            }

        } else {

            List<Vehiculo> lista = VehiculoDAO.buscarPorDni(valor);

            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(vista,
                        "Sin resultados");
            }

            vista.cargarDatos(lista);
        }
    }

    // =========================
    // MODIFICAR VEHÍCULO
    // =========================
    private void abrirModificarVehiculo() {

        ModificarVehiculo vista = new ModificarVehiculo();
        vista.setControlador(this);
    }

    public void buscarModificar(ModificarVehiculo vista) {

        String matricula = vista.getTxtMatricula().getText().trim();

        vehiculoActual = VehiculoDAO.buscarPorMatricula(matricula);

        if (vehiculoActual != null) {
            vista.rellenarCampos(vehiculoActual);
            vista.setCamposEditables(true);
            vista.getTxtMatricula().setEditable(false);
        } else {
            JOptionPane.showMessageDialog(vista,
                    "Vehículo no encontrado");
        }
    }

    public void modificarVehiculo(ModificarVehiculo vista) {

        try {
            String matricula = vista.getTxtMatricula().getText();
            String marca = vista.getTxtMarca().getText();
            String modelo = vista.getTxtModelo().getText();
            int anio = Integer.parseInt(vista.getTxtAnio().getText());
            double kms = Double.parseDouble(vista.getTxtKms().getText());

            Cliente c = (Cliente) vista.getComboClientes().getSelectedItem();

            if (c == null) {
                JOptionPane.showMessageDialog(vista,
                        "Selecciona cliente");
                return;
            }

            Vehiculo v = new Vehiculo(
                    matricula,
                    marca,
                    modelo,
                    anio,
                    kms,
                    c.getIdCliente()
            );

            if (VehiculoDAO.ModificarVehiculo(v)) {
                JOptionPane.showMessageDialog(vista,
                        "Vehículo actualizado");
                vista.dispose();
                abrirMenuVehiculo();
            } else {
                JOptionPane.showMessageDialog(vista,
                        "Error al actualizar");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista,
                    "Datos incorrectos");
        }
    }
}