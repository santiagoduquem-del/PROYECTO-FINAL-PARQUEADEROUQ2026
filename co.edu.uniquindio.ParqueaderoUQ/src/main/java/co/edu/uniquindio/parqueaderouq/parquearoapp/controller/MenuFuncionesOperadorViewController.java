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

public class MenuFuncionesOperadorViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane mainStackPane;

    @FXML
    private Group scalingGroup;

    @FXML
    private Button btnEspaciosDisponibles;

    @FXML
    private Button btnGenerarReporte;

    @FXML
    private Button btnIngresoVehiculos;

    @FXML
    private Button btnRegistroDeUsuarios;

    @FXML
    private Button btnSalidaVehiculos;

    @FXML
    private Button btnVehiculosDentro;

    @FXML
    private Label lblEspaciosDisponibles;

    @FXML
    private Label lblGenerarReporte;

    @FXML
    private Label lblIngresoDeVehiculos;

    @FXML
    private Label lblRegistroDeUsuarios;

    @FXML
    private Label lblSalidaDeVehiculos;

    @FXML
    private Label lblVehiculosDentro;

    @FXML
    private Text txtMenuOperador;

    @FXML
    void onEspaciosDisponibles(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("ListaEspacios.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnEspaciosDisponibles.getScene().getWindow();
            stage.setTitle("Estado de Espacios - Parqueadero UQ");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onGenerarReporte(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("GenerarReporte.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnGenerarReporte.getScene().getWindow();
            stage.setTitle("Generación de Reportes - Parqueadero UQ");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onIngresoVehiculos(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("IngresoDeVehiculos.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnIngresoVehiculos.getScene().getWindow();
            stage.setTitle("Ingreso de Vehículos - Parqueadero UQ");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onSalidaVehiculos(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("SalidaDeVehiculos.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnSalidaVehiculos.getScene().getWindow();
            stage.setTitle("Salida de Vehículos - Parqueadero UQ");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onVehiculosDentro(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("ListaVehiculos.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnVehiculosDentro.getScene().getWindow();
            stage.setTitle("Lista de Vehículos - Parqueadero UQ");
            stage.setScene(scene);
            stage.setFullScreen(true);
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
