package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.tipoUsuario;
import co.edu.uniquindio.parqueaderouq.parquearoapp.utils.DataHolder;
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

public class ClienteViewController {

    private ClienteController controller = new ClienteController();

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
    private Button btnElegirTipoCliente;

    @FXML
    private Button btnMostrarListaDeUsuarios;

    @FXML
    private Button btnRegresarMenuAdministrador;

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
        String id = txtIdentificacion.getText();
        String nombre = txtNombre.getText();
        String correo = txtCorreo.getText();
        String tel = txtTelefono.getText();
        tipoUsuario tipo = DataHolder.getInstance().getSelectedType();

        if (id.isEmpty() || nombre.isEmpty() || tipo == null) {
            mostrarMensaje("Error", "Faltan datos", "Por favor complete nombre, identificación y tipo.", Alert.AlertType.ERROR);
            return;
        }

        if (controller.actualizarUsuario(nombre, id, correo, tel, tipo)) {
            mostrarMensaje("Éxito", "Usuario Actualizado", "El usuario ha sido actualizado correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } else {
            mostrarMensaje("Error", "No encontrado", "No se encontró un usuario con esa identificación.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onAgregarCliente(ActionEvent event) {
    }

    @FXML
    void onCrearCliente(ActionEvent event) {
        String id = txtIdentificacion.getText();
        String nombre = txtNombre.getText();
        String correo = txtCorreo.getText();
        String tel = txtTelefono.getText();
        tipoUsuario tipo = DataHolder.getInstance().getSelectedType();

        if (id.isEmpty() || nombre.isEmpty() || tipo == null) {
            mostrarMensaje("Error", "Faltan datos", "Por favor complete nombre, identificación y tipo.", Alert.AlertType.ERROR);
            return;
        }

        if (controller.crearUsuario(nombre, id, correo, tel, tipo)) {
            mostrarMensaje("Éxito", "Usuario Creado", "El usuario " + nombre + " (" + tipo + ") ha sido registrado.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } else {
            mostrarMensaje("Error", "Usuario duplicado", "Ya existe un usuario con esa identificación.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onElegirTipoCliente(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("TipoDeUsuario.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnElegirTipoCliente.getScene().getWindow();
            stage.setTitle("Seleccionar Tipo de Usuario");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            mostrarMensaje("Error", "Error de carga", "No se pudo abrir la ventana de selección: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onEliminarCliente(ActionEvent event) {
        String id = txtIdentificacion.getText();
        if (id.isEmpty()) {
            mostrarMensaje("Error", "ID faltante", "Ingrese la identificación del usuario a eliminar.", Alert.AlertType.ERROR);
            return;
        }

        if (controller.eliminarUsuario(id)) {
            mostrarMensaje("Éxito", "Usuario Eliminado", "El usuario ha sido eliminado correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } else {
            mostrarMensaje("Error", "No encontrado", "No se encontró un usuario con esa identificación para eliminar.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onMostrarListaDeUsuarios(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("ListaUsuarios.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Lista de Usuarios Registrados");
            stage.setScene(scene);
            stage.setWidth(850);
            stage.setHeight(650);
            stage.show();
        } catch (IOException e) {
            mostrarMensaje("Error", "Error de carga", "No se pudo abrir la lista de usuarios: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onRegresarMenuAdministrador(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("MenuFuncionesAdministrador.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnRegresarMenuAdministrador.getScene().getWindow();
            stage.setTitle("Menú Administrador - Parqueadero UQ");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            mostrarMensaje("Error", "Error de navegación", "No se pudo regresar al menú: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtIdentificacion.clear();
        txtCorreo.clear();
        txtTelefono.clear();
        DataHolder.getInstance().setSelectedType(null);
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
            double initialHeight = 654.0;
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
