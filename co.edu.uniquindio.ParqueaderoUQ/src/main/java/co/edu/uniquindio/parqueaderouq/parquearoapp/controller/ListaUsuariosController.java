package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import co.edu.uniquindio.parqueaderouq.parquearoapp.model.Usuario;
import co.edu.uniquindio.parqueaderouq.parquearoapp.utils.DataHolder;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

public class ListaUsuariosController {

    @FXML
    private StackPane mainStackPane;

    @FXML
    private Group scalingGroup;

    @FXML
    private TableView<Usuario> tblUsuarios;

    @FXML
    private TableColumn<Usuario, String> colNombre;

    @FXML
    private TableColumn<Usuario, String> colID;

    @FXML
    private TableColumn<Usuario, String> colCorreo;

    @FXML
    private TableColumn<Usuario, String> colTelefono;

    @FXML
    private TableColumn<Usuario, String> colTipo;

    @FXML
    void initialize() {
        // Configuración del escalado dinámico
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

        // Configurar las columnas para asegurar que se muestren los datos correctamente
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdentificacion()));
        colCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo() != null ? cellData.getValue().getCorreo() : ""));
        colTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono() != null ? cellData.getValue().getTelefono() : ""));
        colTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipo() != null ? cellData.getValue().getTipo().toString() : "N/A"));

        // Vincular los datos de DataHolder a la tabla
        tblUsuarios.setItems(DataHolder.getInstance().getUserList());
    }
}
