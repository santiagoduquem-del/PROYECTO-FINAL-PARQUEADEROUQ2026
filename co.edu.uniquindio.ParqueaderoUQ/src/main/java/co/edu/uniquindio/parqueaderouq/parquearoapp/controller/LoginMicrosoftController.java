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

public class LoginMicrosoftController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane mainStackPane;

    @FXML
    private Group scalingGroup;

    @FXML
    private PasswordField UserMicrosoftLogin;

    @FXML
    private Button btnSiguienteMicrosoft;

    @FXML
    void OnSiguienteMicrosoft(ActionEvent event) {
        continuarAPassword();
    }

    @FXML
    void onUserMicrosoftLogin(ActionEvent event) {
        continuarAPassword();
    }

    private void continuarAPassword() {
        String user = UserMicrosoftLogin.getText();

        if (user == null || user.isEmpty()) {
            mostrarMensaje("Error", "Campo vacío", "Por favor ingrese su Microsoft ID.", Alert.AlertType.ERROR);
            return;
        }

        // Permitir avanzar si es el ID del operador o el ID ADMIN
        if (user.equals("1234567") || user.equals("ADMIN")) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("ContraseñaLoginMicrosoft.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) btnSiguienteMicrosoft.getScene().getWindow();
                stage.setTitle("Contraseña Microsoft - Parqueadero UQ");
                stage.setScene(scene);
                stage.setFullScreen(true);
                stage.show();
            } catch (IOException e) {
                mostrarMensaje("Error", "Error de carga", "No se pudo cargar la ventana de contraseña: " + e.getMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        } else {
            mostrarMensaje("Error", "ID Incorrecto", "El Microsoft ID ingresado no es válido.", Alert.AlertType.ERROR);
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
        assert UserMicrosoftLogin != null : "fx:id=\"UserMicrosoftLogin\" was not injected: check your FXML file 'LoginMicrosoft.fxml'.";
        assert btnSiguienteMicrosoft != null : "fx:id=\"btnSiguienteMicrosoft\" was not injected: check your FXML file 'LoginMicrosoft.fxml'.";

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
