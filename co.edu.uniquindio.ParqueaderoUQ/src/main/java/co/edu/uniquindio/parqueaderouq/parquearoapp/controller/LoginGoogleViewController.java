package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import co.edu.uniquindio.parqueaderouq.parquearoapp.utils.DataHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import co.edu.uniquindio.parqueaderouq.parquearoapp.ClienteApplication;

public class LoginGoogleViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane mainStackPane;

    @FXML
    private Group scalingGroup;

    @FXML
    private Button btnIngresoGoogleSantiagoOperador;

    @FXML
    private Button btnIngresoGoogleSantiagoAdministrador;

    @FXML
    void onIngresarGoogleSantiagoOperador(ActionEvent event) {
        DataHolder.getInstance().setLoginRole("OPERADOR");
        irAContrasenaGoogle(btnIngresoGoogleSantiagoOperador);
    }

    @FXML
    void onIngresoGoogleSantiagoAdministrador(ActionEvent event) {
        DataHolder.getInstance().setLoginRole("ADMIN");
        irAContrasenaGoogle(btnIngresoGoogleSantiagoAdministrador);
    }

    private void irAContrasenaGoogle(Button botonOrigen) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("ContraseñaLoginGoogle.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) botonOrigen.getScene().getWindow();
            stage.setTitle("Contraseña Google - Parqueadero UQ");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            mostrarMensaje("Error", "Error de carga", "No se pudo cargar la ventana de contraseña: " + e.getMessage(), Alert.AlertType.ERROR);
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
