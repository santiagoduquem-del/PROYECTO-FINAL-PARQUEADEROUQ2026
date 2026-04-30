package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import co.edu.uniquindio.parqueaderouq.parquearoapp.ClienteApplication;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.Operador;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.estadoVehiculo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GenerarReporteViewController {

    private GenerarReporteController controller = new GenerarReporteController();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane mainStackPane;

    @FXML
    private Group scalingGroup;

    @FXML
    private Button btnGenerarReporte;

    @FXML
    private Button btnRegresar;

    @FXML
    private Label lblIngresosGenerados;

    @FXML
    private Label lblListaFiltrada;

    @FXML
    private Label lblTiempoPromedio;

    @FXML
    private Label lblTotalVehiculos;

    @FXML
    private TextField txtTiempoFiltro;

    @FXML
    void onGenerarReporte(ActionEvent event) {
        LocalDate hoy = LocalDate.now();
        Operador.ReporteData data = controller.generarReporte(hoy);

        lblTotalVehiculos.setText("Total vehículos ingresados hoy: " + data.totalVehiculos);
        lblIngresosGenerados.setText(String.format("Ingresos generados hoy: $%.2f", data.ingresosGenerados));
        lblTiempoPromedio.setText(String.format("Tiempo promedio de permanencia: %.1f h", data.tiempoPromedioHoras));

        String filtroStr = txtTiempoFiltro.getText();
        if (!filtroStr.isEmpty()) {
            try {
                double horasFiltro = Double.parseDouble(filtroStr);
                long minutosFiltro = (long) (horasFiltro * 60);

                String lista = data.vehiculos.stream()
                        .filter(v -> v.getEstado() == estadoVehiculo.SALIO && v.getHoraSalida() != null)
                        .filter(v -> Duration.between(v.getHoraIngreso(), v.getHoraSalida()).toMinutes() > minutosFiltro)
                        .map(v -> {
                            long minTotales = Duration.between(v.getHoraIngreso(), v.getHoraSalida()).toMinutes();
                            return String.format("%s (%.1f h)", v.getPlaca(), minTotales / 60.0);
                        })
                        .collect(Collectors.joining("\n"));
                lblListaFiltrada.setText(lista.isEmpty() ? "Ninguno" : lista);
            } catch (NumberFormatException e) {
                mostrarMensaje("Error", "Filtro inválido", "Ingrese un número de horas válido (ej: 1.5).", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    void onRegresar(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("MenuFuncionesOperador.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnRegresar.getScene().getWindow();
            stage.setTitle("Menú Operador - Parqueadero UQ");
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
                double scale = Math.min(newVal.doubleValue() / initialWidth, mainStackPane.getHeight() / initialHeight);
                scalingGroup.setScaleX(scale); scalingGroup.setScaleY(scale);
            });
            mainStackPane.heightProperty().addListener((obs, oldVal, newVal) -> {
                double scale = Math.min(mainStackPane.getWidth() / initialWidth, newVal.doubleValue() / initialHeight);
                scalingGroup.setScaleX(scale); scalingGroup.setScaleY(scale);
            });
        }
        onGenerarReporte(null);
    }
}
