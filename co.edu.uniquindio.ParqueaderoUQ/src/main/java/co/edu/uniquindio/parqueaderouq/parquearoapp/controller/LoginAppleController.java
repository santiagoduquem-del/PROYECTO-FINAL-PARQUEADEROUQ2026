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

public class LoginAppleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane mainStackPane;

    @FXML
    private Group scalingGroup;

    @FXML
    private PasswordField PasswordContraseñaApple;

    @FXML
    private Button btnContinuarAppleLogin;

    @FXML
    void onContraseñaApple(ActionEvent event) {
        verificarAcceso();
    }

    @FXML
    void onContinuarAppleLogin(ActionEvent event) {
        verificarAcceso();
    }

    private void verificarAcceso() {
        String contrasena = PasswordContraseñaApple.getText();
        String contrasenaCorrecta = "1224"; // Usando la misma contraseña de operador para consistencia

        if (contrasena == null || contrasena.isEmpty()) {
            mostrarMensaje("Error", "Campo vacío", "Por favor ingrese su Apple ID password.", Alert.AlertType.ERROR);
            return;
        }

        if (contrasena.equals(contrasenaCorrecta)) {
            mostrarMensaje("Éxito", "Autenticación Apple ID", "Acceso concedido exitosamente.", Alert.AlertType.INFORMATION);
            abrirVentanaCliente();
        } else {
            mostrarMensaje("Error", "Autenticación fallida", "La contraseña de Apple ID es incorrecta.", Alert.AlertType.ERROR);
        }
    }

    private void abrirVentanaCliente() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("Cliente.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnContinuarAppleLogin.getScene().getWindow();
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
        assert PasswordContraseñaApple != null : "fx:id=\"PasswordContraseñaApple\" was not injected: check your FXML file 'LoginApple.fxml'.";
        assert btnContinuarAppleLogin != null : "fx:id=\"btnContinuarAppleLogin\" was not injected: check your FXML file 'LoginApple.fxml'.";

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
