package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import co.edu.uniquindio.parqueaderouq.parquearoapp.ClienteApplication;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.Vehiculo;
import co.edu.uniquindio.parqueaderouq.parquearoapp.utils.DataHolder;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ListaVehiculosController {

    @FXML
    private StackPane mainStackPane;

    @FXML
    private Group scalingGroup;

    @FXML
    private TableView<Vehiculo> tblVehiculos;

    @FXML
    private TableColumn<Vehiculo, String> colPlaca;

    @FXML
    private TableColumn<Vehiculo, String> colTipo;

    @FXML
    private TableColumn<Vehiculo, String> colConductor;

    @FXML
    private TableColumn<Vehiculo, String> colID;

    @FXML
    private TableColumn<Vehiculo, String> colHora;

    @FXML
    private TableColumn<Vehiculo, String> colEspacio;

    @FXML
    private TableColumn<Vehiculo, String> colEstado;

    @FXML
    private Button btnRegresarMenuOperador;

    @FXML
    void onRegresarMenuOperador(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("MenuFuncionesOperador.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnRegresarMenuOperador.getScene().getWindow();
            stage.setTitle("Menú Operador - Parqueadero UQ");
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

        colPlaca.setCellValueFactory(cellData -> new SimpleStringProperty(
            cellData.getValue().getPlaca() != null ? cellData.getValue().getPlaca() : ""
        ));
        colTipo.setCellValueFactory(cellData -> new SimpleStringProperty(
            cellData.getValue().getTipoVehiculo() != null ? cellData.getValue().getTipoVehiculo().toString() : "N/A"
        ));
        colConductor.setCellValueFactory(cellData -> new SimpleStringProperty(
            cellData.getValue().getNombreConductor() != null ? cellData.getValue().getNombreConductor() : ""
        ));
        colID.setCellValueFactory(cellData -> new SimpleStringProperty(
            cellData.getValue().getIdentificacionConductor() != null ? cellData.getValue().getIdentificacionConductor() : ""
        ));
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        colHora.setCellValueFactory(cellData -> new SimpleStringProperty(
            cellData.getValue().getHoraIngreso() != null ? cellData.getValue().getHoraIngreso().format(dtf) : ""
        ));
        colEspacio.setCellValueFactory(cellData -> new SimpleStringProperty(
            cellData.getValue().getEspacioAsignado() != null ? cellData.getValue().getEspacioAsignado().getCodigo() : "N/A"
        ));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(
            cellData.getValue().getEstado() != null ? cellData.getValue().getEstado().toString() : "N/A"
        ));

        tblVehiculos.setItems(DataHolder.getInstance().getVehicleList());
    }
}
