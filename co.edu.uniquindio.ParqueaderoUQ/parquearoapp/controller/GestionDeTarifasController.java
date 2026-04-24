package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import co.edu.uniquindio.parqueaderouq.parquearoapp.ClienteApplication;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.Tarifa;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.tipoVehiculo;
import co.edu.uniquindio.parqueaderouq.parquearoapp.utils.DataHolder;
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

    @Group
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
            double vCarro = Double.parseDouble(carro);
            double vMoto = Double.parseDouble(moto);
            double vBici = Double.parseDouble(bici);
            
            // Actualizar las tarifas en el DataHolder
            for (Tarifa t : DataHolder.getInstance().getTariffList()) {
                if (t.getTipo() == tipoVehiculo.CARRO) t.setValorPorHora(vCarro);
                if (t.getTipo() == tipoVehiculo.MOTO) t.setValorPorHora(vMoto);
                if (t.getTipo() == tipoVehiculo.BICICLETA) t.setValorPorHora(vBici);
            }
            
            // Forzar actualización para persistencia
            DataHolder.getInstance().getTariffList().setAll(DataHolder.getInstance().getTariffList());
            
            mostrarMensaje("Éxito", "Tarifas Actualizadas", "Las tarifas horarias han sido actualizadas correctamente.", Alert.AlertType.INFORMATION);
            
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

    private void cargarTarifasActuales() {
        for (Tarifa t : DataHolder.getInstance().getTariffList()) {
            if (t.getTipo() == tipoVehiculo.CARRO) txtTarifaCarro.setText(String.valueOf(t.getValorPorHora()));
            if (t.getTipo() == tipoVehiculo.MOTO) txtTarifaMoto.setText(String.valueOf(t.getValorPorHora()));
            if (t.getTipo() == tipoVehiculo.BICICLETA) txtTarifaBicicleta.setText(String.valueOf(t.getValorPorHora()));
        }
    }

    @FXML
    void initialize() {
        cargarTarifasActuales();

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
