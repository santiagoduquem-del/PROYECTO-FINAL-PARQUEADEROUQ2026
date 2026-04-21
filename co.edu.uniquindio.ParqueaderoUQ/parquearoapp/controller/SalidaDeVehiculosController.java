package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import co.edu.uniquindio.parqueaderouq.parquearoapp.ClienteApplication;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.*;
import co.edu.uniquindio.parqueaderouq.parquearoapp.utils.DataHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SalidaDeVehiculosController {

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane mainStackPane;

    @FXML
    private Group scalingGroup;

    @FXML
    private Button btnSalidaVehiculo;

    @FXML
    private Button btnBuscarVehiculo;

    @FXML
    private Button btnRegresarMenuOperador;

    @FXML
    private ComboBox<tipoVehiculo> cmbTipoVehiculo;

    @FXML
    private TextField txtEspacioAsignado;

    @FXML
    private TextField txtHoraSalida;

    @FXML
    private TextField txtIdConductor;

    @FXML
    private TextField txtNombreConductor;

    @FXML
    private TextField txtPlaca;

    @FXML
    private TextField txtDescuento;

    @FXML
    void onBuscarVehiculo(ActionEvent event) {
        String placa = txtPlaca.getText().trim();
        if (placa.isEmpty()) {
            mostrarMensaje("Error", "Placa faltante", "Ingrese una placa para buscar.", Alert.AlertType.ERROR);
            return;
        }

        Vehiculo v = DataHolder.getInstance().getVehicleList().stream()
                .filter(vehiculo -> vehiculo.getPlaca().equalsIgnoreCase(placa) && vehiculo.getEstado() == estadoVehiculo.DENTRO)
                .findFirst().orElse(null);

        if (v != null) {
            txtNombreConductor.setText(v.getNombreConductor());
            txtIdConductor.setText(v.getIdentificacionConductor());
            txtEspacioAsignado.setText(v.getEspacioAsignado().getCodigo());
            if (cmbTipoVehiculo != null) cmbTipoVehiculo.setValue(v.getTipoVehiculo());
            
            // --- CORRECCIÓN DEFINITIVA: LIMPIAR HORA PARA INGRESO MANUAL ---
            txtHoraSalida.setEditable(true);
            txtHoraSalida.setDisable(false);
            txtHoraSalida.setText(""); // IMPORTANTE: No ponemos la hora actual
            txtHoraSalida.setPromptText("yyyy-MM-dd HH:mm:ss");
            txtHoraSalida.requestFocus(); // Ponemos el cursor ahí
            // -------------------------------------------------------------

            txtDescuento.setText("0");
            Usuario usuario = buscarUsuarioPorIdentificacion(v.getIdentificacionConductor());
            if (usuario != null && usuario.getTipo() == tipoUsuario.VISITANTE) {
                txtDescuento.setEditable(false);
                txtDescuento.setText("0");
            } else {
                txtDescuento.setEditable(true);
            }
        } else {
            mostrarMensaje("Error", "No encontrado", "No hay un vehículo activo con la placa '" + placa + "'.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onSalidaVehiculo(ActionEvent event) {
        String placa = txtPlaca.getText().trim();
        String descuentoStr = txtDescuento.getText();
        String horaSalidaStr = txtHoraSalida.getText().trim();

        if (placa.isEmpty() || horaSalidaStr.isEmpty()) {
            mostrarMensaje("Error", "Datos incompletos", "Por favor ingrese la placa y escriba la hora de salida manualmente.", Alert.AlertType.ERROR);
            return;
        }

        Vehiculo vehiculo = DataHolder.getInstance().getVehicleList().stream()
                .filter(v -> v.getPlaca().equalsIgnoreCase(placa) && v.getEstado() == estadoVehiculo.DENTRO)
                .findFirst().orElse(null);

        if (vehiculo == null) {
            mostrarMensaje("Error", "Operación no válida", "Busque primero el vehículo antes de registrar salida.", Alert.AlertType.ERROR);
            return;
        }

        LocalDateTime horaSalida;
        try {
            horaSalida = LocalDateTime.parse(horaSalidaStr, dtf);
        } catch (DateTimeParseException e) {
            mostrarMensaje("Error", "Formato de hora inválido", "Use: yyyy-MM-dd HH:mm:ss\nEjemplo: 2023-10-25 18:00:00", Alert.AlertType.ERROR);
            return;
        }

        if (horaSalida.isBefore(vehiculo.getHoraIngreso())) {
            mostrarMensaje("Error", "Hora inválida", "La salida no puede ser antes del ingreso (" + vehiculo.getHoraIngreso().format(dtf) + ").", Alert.AlertType.ERROR);
            return;
        }

        double porcentajeDescuento = 0;
        try {
            porcentajeDescuento = Double.parseDouble(descuentoStr.isEmpty() ? "0" : descuentoStr);
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "Descuento inválido", "Ingrese un número.", Alert.AlertType.ERROR);
            return;
        }

        // --- CÁLCULO DE FACTURA ---
        Duration duracion = Duration.between(vehiculo.getHoraIngreso(), horaSalida);
        long totalMinutos = Math.max(1, duracion.toMinutes());
        double valorHora = DataHolder.getInstance().getTariffList().stream()
                .filter(t -> t.getTipo() == vehiculo.getTipoVehiculo()).mapToDouble(Tarifa::getValorPorHora).findFirst().orElse(0);
        double total = (totalMinutos / 60.0) * valorHora * (1 - (porcentajeDescuento / 100));

        mostrarMensaje("Factura", "Salida exitosa", "Total a pagar: $" + String.format("%.2f", total), Alert.AlertType.INFORMATION);

        // --- LIBERACIÓN DEL ESPACIO ---
        if (vehiculo.getEspacioAsignado() != null) {
            String codigoEspacio = vehiculo.getEspacioAsignado().getCodigo();
            for (int i = 0; i < DataHolder.getInstance().getSpaceList().size(); i++) {
                Espacio e = DataHolder.getInstance().getSpaceList().get(i);
                if (e.getCodigo().equalsIgnoreCase(codigoEspacio)) {
                    e.setVehiculoAsignado(null);
                    DataHolder.getInstance().getSpaceList().set(i, e);
                    break;
                }
            }
        }

        // --- ACTUALIZACIÓN DEL VEHÍCULO ---
        vehiculo.setEstado(estadoVehiculo.SALIO);
        int indexV = DataHolder.getInstance().getVehicleList().indexOf(vehiculo);
        if (indexV != -1) DataHolder.getInstance().getVehicleList().set(indexV, vehiculo);
        
        limpiarCampos();
    }

    private Usuario buscarUsuarioPorIdentificacion(String id) {
        return DataHolder.getInstance().getUserList().stream()
                .filter(u -> u.getIdentificacion().equals(id))
                .findFirst().orElse(null);
    }

    @FXML
    void onRegresarMenuOperador(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("MenuFuncionesOperador.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnRegresarMenuOperador.getScene().getWindow();
            stage.setTitle("Menú Operador - Parqueadero UQ");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void limpiarCampos() {
        txtPlaca.clear();
        txtNombreConductor.clear();
        txtIdConductor.clear();
        txtEspacioAsignado.clear();
        txtHoraSalida.clear();
        txtDescuento.clear();
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        txtHoraSalida.setEditable(true);
        txtHoraSalida.setDisable(false);
        if (mainStackPane != null && scalingGroup != null) {
            double initialWidth = 988.0;
            double initialHeight = 634.0;
            mainStackPane.widthProperty().addListener((obs, oldVal, newVal) -> {
                double scale = Math.min(newVal.doubleValue() / initialWidth, mainStackPane.getHeight() / initialHeight);
                scalingGroup.setScaleX(scale); scalingGroup.setScaleY(scale);
            });
            mainStackPane.heightProperty().addListener((obs, oldVal, newVal) -> {
                double scale = Math.min(mainStackPane.getWidth() / initialWidth, newVal.doubleValue() / initialHeight);
                scalingGroup.setScaleX(scale); scalingGroup.setScaleY(scale);
            });
        }
    }
}
