package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import co.edu.uniquindio.parqueaderouq.parquearoapp.ClienteApplication;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.Espacio;
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

public class ListaEspaciosController {

    @FXML
    private StackPane mainStackPane;

    @FXML
    private Group scalingGroup;

    @FXML
    private TableView<Espacio> tblEspacios;

    @FXML
    private TableColumn<Espacio, String> colCodigo;

    @FXML
    private TableColumn<Espacio, String> colEstado;

    @FXML
    private TableColumn<Espacio, String> colTipo;

    @FXML
    private TableColumn<Espacio, String> colVehiculo;

    @FXML
    private Button btnRegresarMenuOperador;

    @FXML
    private Button btnRecargarTabla;

    @FXML
    void onRecargarTabla(ActionEvent event) {
        // Solución técnica: Refrescar la tabla manualmente para asegurar que los cambios se reflejen
        tblEspacios.refresh();
        // Además, reasignamos la lista para forzar la actualización visual total
        tblEspacios.setItems(null);
        tblEspacios.setItems(DataHolder.getInstance().getSpaceList());
    }

    @FXML
    void onRegresarMenuOperador(ActionEvent event) {
        String role = DataHolder.getInstance().getLoginRole();
        String targetFxml = "MenuFuncionesOperador.fxml";
        String title = "Menú Operador - Parqueadero UQ";

        if ("ADMIN".equals(role)) {
            targetFxml = "MenuFuncionesAdministrador.fxml";
            title = "Menú Administrador - Parqueadero UQ";
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource(targetFxml));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnRegresarMenuOperador.getScene().getWindow();
            stage.setTitle(title);
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
                double scale = Math.min(newVal.doubleValue() / initialWidth, mainStackPane.getHeight() / initialHeight);
                scalingGroup.setScaleX(scale); scalingGroup.setScaleY(scale);
            });
            mainStackPane.heightProperty().addListener((obs, oldVal, newVal) -> {
                double scale = Math.min(mainStackPane.getWidth() / initialWidth, newVal.doubleValue() / initialHeight);
                scalingGroup.setScaleX(scale); scalingGroup.setScaleY(scale);
            });
        }

        colCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        colTipo.setCellValueFactory(cellData -> new SimpleStringProperty(
            cellData.getValue().getTipo() != null ? cellData.getValue().getTipo().toString() : "No definido"
        ));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstado().toString()));
        colVehiculo.setCellValueFactory(cellData -> new SimpleStringProperty(
            cellData.getValue().getVehiculoAsignado() != null ? cellData.getValue().getVehiculoAsignado().getPlaca() : "Libre"
        ));

        tblEspacios.setItems(DataHolder.getInstance().getSpaceList());
    }
}
