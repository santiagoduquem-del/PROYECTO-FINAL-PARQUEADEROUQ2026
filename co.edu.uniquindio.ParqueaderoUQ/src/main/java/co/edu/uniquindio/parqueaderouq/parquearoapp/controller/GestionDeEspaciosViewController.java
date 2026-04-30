package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import co.edu.uniquindio.parqueaderouq.parquearoapp.ClienteApplication;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.tipoEspacio;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.estadoEspacio;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GestionDeEspaciosViewController {

    private GestionDeEspaciosController controller = new GestionDeEspaciosController();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane mainStackPane;

    @FXML
    private Group scalingGroup;

    @FXML
    private Button btnCrearEspacio;

    @FXML
    private Button btnModificarEspacio;

    @FXML
    private Button btnEliminarEspacio;

    @FXML
    private Button btnRegresarMenuAdmin;

    @FXML
    private Button btnMostarListaDeespacios;

    @FXML
    private ComboBox<tipoEspacio> cmbTipoEspacio;

    @FXML
    private ComboBox<estadoEspacio> cmbEstadoEspacio;

    @FXML
    private TextField txtIDEspacio;

    @FXML
    void onCrearEspacio(ActionEvent event) {
        String id = txtIDEspacio.getText();
        tipoEspacio tipo = cmbTipoEspacio.getValue();
        estadoEspacio estado = cmbEstadoEspacio.getValue();

        if (id.isEmpty() || tipo == null || estado == null) {
            mostrarMensaje("Error", "Campos vacíos", "Por favor complete todos los datos del espacio.", Alert.AlertType.ERROR);
            return;
        }

        if (controller.crearEspacio(id, tipo, estado)) {
            mostrarMensaje("Éxito", "Espacio Creado", "El espacio ha sido registrado correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } else {
            mostrarMensaje("Error", "ID duplicado", "Ya existe un espacio con este código.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onModificarEspacio(ActionEvent event) {
        String id = txtIDEspacio.getText();
        tipoEspacio nuevoTipo = cmbTipoEspacio.getValue();
        estadoEspacio nuevoEstado = cmbEstadoEspacio.getValue();

        if (id.isEmpty()) {
            mostrarMensaje("Error", "ID faltante", "Ingrese el ID del espacio que desea modificar.", Alert.AlertType.ERROR);
            return;
        }

        if (controller.modificarEspacio(id, nuevoTipo, nuevoEstado)) {
            mostrarMensaje("Éxito", "Espacio Modificado", "El espacio ha sido actualizado correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } else {
            mostrarMensaje("Error", "No encontrado", "No se encontró un espacio con ese ID.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onEliminarEspacio(ActionEvent event) {
        String id = txtIDEspacio.getText();
        if (id.isEmpty()) {
            mostrarMensaje("Error", "ID faltante", "Ingrese el ID del espacio que desea eliminar.", Alert.AlertType.ERROR);
            return;
        }

        if (controller.eliminarEspacio(id)) {
            mostrarMensaje("Éxito", "Espacio Eliminado", "El espacio ha sido removido correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } else {
            mostrarMensaje("Error", "No encontrado", "No se encontró un espacio con ese ID.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onMostarListaDeespacios(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("ListaEspacios.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Lista de Espacios - Parqueadero UQ");
            stage.setScene(scene);
            stage.setWidth(850);
            stage.setHeight(650);
            stage.show();
        } catch (IOException e) {
            mostrarMensaje("Error", "Error de carga", "No se pudo abrir la lista de espacios: " + e.getMessage(), Alert.AlertType.ERROR);
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

    private void limpiarCampos() {
        txtIDEspacio.clear();
        cmbTipoEspacio.getSelectionModel().clearSelection();
        cmbEstadoEspacio.getSelectionModel().clearSelection();
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
        if (cmbTipoEspacio != null) {
            cmbTipoEspacio.setItems(FXCollections.observableArrayList(tipoEspacio.values()));
        }
        if (cmbEstadoEspacio != null) {
            cmbEstadoEspacio.setItems(FXCollections.observableArrayList(estadoEspacio.values()));
        }

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
