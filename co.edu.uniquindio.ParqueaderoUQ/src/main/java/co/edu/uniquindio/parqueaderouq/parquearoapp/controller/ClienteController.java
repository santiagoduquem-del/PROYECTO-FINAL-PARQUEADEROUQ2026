package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;

public class ClienteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane mainStackPane;

    @FXML
    private Group scalingGroup;

    @FXML
    private Button btnActualizarCliente;

    @FXML
    private Button btnAgregarCliente;

    @FXML
    private Button btnCrearCliente;

    @FXML
    private Button btnEliminarCliente;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    void onActualizarCliente(ActionEvent event) {

    }

    @FXML
    void onAgregarCliente(ActionEvent event) {

    }

    @FXML
    void onCrearCliente(ActionEvent event) {

    }

    @FXML
    void onEliminarCliente(ActionEvent event) {

    }

    @FXML
    void initialize() {
        if (mainStackPane != null && scalingGroup != null) {
            double initialWidth = 988.0;
            double initialHeight = 654.0;

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
