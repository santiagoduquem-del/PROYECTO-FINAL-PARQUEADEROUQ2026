package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import co.edu.uniquindio.parqueaderouq.parquearoapp.ClienteApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GestionDeTarifasController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane mainStackPane;

    @FXML
    private Group scalingGroup;

    @FXML
    private Button btnActualizarTarifas;

    @FXML
    private Button btnRegresarMenuAdmin;

    @FXML
    private TextField txtTarifaBicicleta;

    @FXML
    private TextField txtTarifaCarro;

    @FXML
    private TextField txtTarifaMoto;

    @FXML
    void onActualizarTarifas(ActionEvent event) {
        String carro = txtTarifaCarro.getText();
        String moto = txtTarifaMoto.getText();
        String bici = txtTarifaBicicleta.getText();

        if (carro.isEmpty() || moto.isEmpty() || bici.isEmpty()) {
            mostrarMensaje("Error", "Campos vacíos", "Por favor ingrese todos los valores de las tarifas.", Alert.AlertType.ERROR);
            return;
        }

        try {
            Double.parseDouble(carro);
            Double.parseDouble(moto);
            Double.parseDouble(bici);
            
            // Aquí se guardaría en el modelo en el futuro
            System.out.println("Tarifas actualizadas: Carro=" + carro + ", Moto=" + moto + ", Bici=" + bici);
            mostrarMensaje("Éxito", "Tarifas Actualizadas", "Los valores han sido guardados correctamente.", Alert.AlertType.INFORMATION);
            
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "Formato inválido", "Asegúrese de ingresar solo números en las tarifas.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onRegresarMenuAdmin(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("MenuFuncionesAdministrador.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnRegresarMenuAdmin.getScene().getWindow();
            stage.setTitle("Menú Administrador - Parqueadero UQ");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
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
