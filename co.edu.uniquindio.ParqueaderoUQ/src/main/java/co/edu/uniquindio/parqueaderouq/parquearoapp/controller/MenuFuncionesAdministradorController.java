package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import co.edu.uniquindio.parqueaderouq.parquearoapp.ClienteApplication;

public class MenuFuncionesAdministradorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane mainStackPane;

    @FXML
    private Group scalingGroup;

    @FXML
    private Button btnGestionarEspacios;

    @FXML
    private Button btnGestionarTarifas;

    @FXML
    private Button btnRegistroDeUsuarios;

    @FXML
    private Button btnRegresarLogin;

    @FXML
    private Label lblGestionEspacios;

    @FXML
    private Label lblGestionTarifas;

    @FXML
    private Label lblRegistroDeUsuarios;

    @FXML
    private Text txtMenuAdmin;

    @FXML
    void onGestionarEspacios(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("GestionDeEspacios.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnGestionarEspacios.getScene().getWindow();
            stage.setTitle("Gestión de Espacios - Parqueadero UQ");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onGestionarTarifas(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("GestiónDeTarifas.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnGestionarTarifas.getScene().getWindow();
            stage.setTitle("Gestión de Tarifas - Parqueadero UQ");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onRegistroDeUsuarios(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("Cliente.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnRegistroDeUsuarios.getScene().getWindow();
            stage.setTitle("Gestión de Usuarios - Parqueadero UQ");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onRegresarLogin(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnRegresarLogin.getScene().getWindow();
            stage.setTitle("Login - Parqueadero UQ");
            stage.setScene(scene);
            stage.setFullScreen(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
