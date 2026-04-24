package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.tipoUsuario;
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

public class TipoDeUsuarioController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane mainStackPane;

    @FXML
    private Group scalingGroup;

    @FXML
    private Button btnSeleccionarAdmin;

    @FXML
    private Button btnSeleccionarDocente;

    @FXML
    private Button btnSeleccionarEstudiante;

    @FXML
    private Button btnSeleccionarVisitante;

    @FXML
    void onSeleccionarAdmin(ActionEvent event) {
        DataHolder.getInstance().setSelectedType(tipoUsuario.ADMINISTRATIVO);
        volverAVentanaCliente();
    }

    @FXML
    void onSeleccionarDocente(ActionEvent event) {
        DataHolder.getInstance().setSelectedType(tipoUsuario.DOCENTE);
        volverAVentanaCliente();
    }

    @FXML
    void onSeleccionarEstudiante(ActionEvent event) {
        DataHolder.getInstance().setSelectedType(tipoUsuario.ESTUDIANTE);
        volverAVentanaCliente();
    }

    @FXML
    void onSeleccionarVisitante(ActionEvent event) {
        DataHolder.getInstance().setSelectedType(tipoUsuario.VISITANTE);
        volverAVentanaCliente();
    }

    private void volverAVentanaCliente() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("Cliente.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnSeleccionarEstudiante.getScene().getWindow();
            stage.setTitle("Gestión de Clientes - Parqueadero UQ");
            stage.setScene(scene);
            stage.setFullScreen(false);
            stage.show();
        } catch (IOException e) {
            mostrarMensaje("Error", "Error de carga", "No se pudo volver a la ventana de clientes: " + e.getMessage(), Alert.AlertType.ERROR);
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
