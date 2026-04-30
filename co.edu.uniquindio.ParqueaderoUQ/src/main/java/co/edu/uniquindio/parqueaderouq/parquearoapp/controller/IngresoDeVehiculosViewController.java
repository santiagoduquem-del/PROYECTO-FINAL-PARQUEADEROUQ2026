package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import co.edu.uniquindio.parqueaderouq.parquearoapp.ClienteApplication;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.tipoVehiculo;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.estadoVehiculo;
import javafx.collections.FXCollections;
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

public class IngresoDeVehiculosViewController {

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private IngresoDeVehiculosController controller = new IngresoDeVehiculosController();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane mainStackPane;

    @FXML
    private Group scalingGroup;

    @FXML
    private Button btnIngresarVehiculo;

    @FXML
    private Button btnRegresarMenuOperador;

    @FXML
    private ComboBox<tipoVehiculo> cmbTipoVehiculo;

    @FXML
    private ComboBox<estadoVehiculo> cmbEstadoVehiculo;

    @FXML
    private TextField txtEspacioAsignado;

    @FXML
    private TextField txtHoraIngreso;

    @FXML
    private TextField txtIdConductor;

    @FXML
    private TextField txtNombreConductor;

    @FXML
    private TextField txtPlaca;

    @FXML
    void onIngresarVehiculo(ActionEvent event) {
        String placa = txtPlaca.getText().trim();
        String nombre = txtNombreConductor.getText().trim();
        String id = txtIdConductor.getText().trim();
        String codEspacio = txtEspacioAsignado.getText().trim();
        String horaStr = txtHoraIngreso.getText().trim();
        tipoVehiculo tipo = cmbTipoVehiculo != null ? cmbTipoVehiculo.getValue() : null;

        if (placa.isEmpty() || nombre.isEmpty() || id.isEmpty() || codEspacio.isEmpty() || horaStr.isEmpty() || tipo == null) {
            mostrarMensaje("Error", "Campos vacíos", "Por favor complete todos los datos del vehículo.", Alert.AlertType.ERROR);
            return;
        }

        LocalDateTime horaIngreso;
        try {
            horaIngreso = LocalDateTime.parse(horaStr, dtf);
        } catch (DateTimeParseException e) {
            mostrarMensaje("Error", "Formato de hora inválido", "Use el formato: yyyy-MM-dd HH:mm:ss", Alert.AlertType.ERROR);
            return;
        }

        String resultado = controller.ingresarVehiculo(placa, nombre, id, codEspacio, horaIngreso, tipo);

        switch (resultado) {
            case "ESPACIO_INEXISTENTE":
                mostrarMensaje("Error", "Espacio inexistente", "El código de espacio '" + codEspacio + "' no ha sido creado por el administrador.", Alert.AlertType.ERROR);
                break;
            case "FUERA_DE_SERVICIO":
                mostrarMensaje("Error", "No disponible", "El espacio está FUERA DE SERVICIO.", Alert.AlertType.ERROR);
                break;
            case "OCUPADO":
                mostrarMensaje("Error", "Ocupado", "Este espacio ya tiene un vehículo.", Alert.AlertType.ERROR);
                break;
            case "EXITO_FRECUENTE":
                mostrarMensaje("Éxito", "Vehículo Registrado", "El ingreso se realizó con éxito. Conductor registrado como: Usuario Frecuente", Alert.AlertType.INFORMATION);
                limpiarCampos();
                break;
            case "EXITO_VISITANTE":
                mostrarMensaje("Éxito", "Vehículo Registrado", "El ingreso se realizó con éxito. Conductor registrado como: Visitante", Alert.AlertType.INFORMATION);
                limpiarCampos();
                break;
        }
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
        txtHoraIngreso.clear();
        if (cmbTipoVehiculo != null) cmbTipoVehiculo.getSelectionModel().clearSelection();
        if (cmbEstadoVehiculo != null) cmbEstadoVehiculo.getSelectionModel().clearSelection();
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
        if (cmbTipoVehiculo != null) {
            cmbTipoVehiculo.setItems(FXCollections.observableArrayList(tipoVehiculo.values()));
        }
        if (cmbEstadoVehiculo != null) {
            cmbEstadoVehiculo.setItems(FXCollections.observableArrayList(estadoVehiculo.values()));
        }

        txtHoraIngreso.setPromptText("yyyy-MM-dd HH:mm:ss");
        txtHoraIngreso.setEditable(true);

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
