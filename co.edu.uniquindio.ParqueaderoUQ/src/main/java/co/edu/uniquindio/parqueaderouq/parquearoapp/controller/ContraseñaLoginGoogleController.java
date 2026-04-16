package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import co.edu.uniquindio.parqueaderouq.parquearoapp.ClienteApplication;

public class ContraseñaLoginGoogleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane mainStackPane;

    @FXML
    private Group scalingGroup;

    @FXML
    private PasswordField ContraseñaOperadorGoogle;

    @FXML
    private Button btnSiguienteGoogle;

    @FXML
    void onVerificarContraseña(ActionEvent event) {
        verificarContraseña();
    }

    @FXML
    void onSiguienteGoogle(ActionEvent event) {
        verificarContraseña();
    }

    private void verificarContraseña() {
        String contrasenaIngresada = ContraseñaOperadorGoogle.getText();
        String contrasenaCorrecta = "1224"; // La contraseña del operador

        if (contrasenaIngresada == null || contrasenaIngresada.isEmpty()) {
            mostrarMensaje("Error", "Campo vacío", "Por favor ingrese la contraseña.", Alert.AlertType.ERROR);
            return;
        }

        if (contrasenaIngresada.equals(contrasenaCorrecta)) {
            mostrarMensaje("Éxito", "Contraseña correcta", "Acceso concedido.", Alert.AlertType.INFORMATION);
            abrirVentanaCliente();
        } else {
            mostrarMensaje("Error", "Contraseña incorrecta", "La contraseña ingresada no es válida.", Alert.AlertType.ERROR);
        }
    }

    private void abrirVentanaCliente() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("Cliente.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ContraseñaOperadorGoogle.getScene().getWindow();
            stage.setTitle("Gestión de Clientes - Parqueadero UQ");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            mostrarMensaje("Error", "Error de carga", "No se pudo cargar la ventana de clientes: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
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
        assert ContraseñaOperadorGoogle != null : "fx:id=\"ContraseñaOperadorGoogle\" was not injected: check your FXML file 'ContraseñaLoginGoogle.fxml'.";
        assert btnSiguienteGoogle != null : "fx:id=\"btnSiguienteGoogle\" was not injected: check your FXML file 'ContraseñaLoginGoogle.fxml'.";
        
        if (mainStackPane != null && scalingGroup != null) {
            double initialWidth = 988.0;
            double initialHeight = 634.0;

            mainStackPane.widthProperty().addListener((obs, oldVal, newVal) -> {
                double scaleX = newVal.doubleValue() / initialWidth;
                double scaleY = mainStackPane.getHeight() / initialHeight;
                double scale = Math.min(scaleX, scaleY);
                scalingGroup.setScaleX(scale);
                scalingGroup.setScaleY(scale);
            });

            mainStackPane.heightProperty().addListener((obs, oldVal, newVal) -> {
                double scaleX = mainStackPane.getWidth() / initialWidth;
                double scaleY = newVal.doubleValue() / initialHeight;
                double scale = Math.min(scaleX, scaleY);
                scalingGroup.setScaleX(scale);
                scalingGroup.setScaleY(scale);
            });
        }
    }
}
