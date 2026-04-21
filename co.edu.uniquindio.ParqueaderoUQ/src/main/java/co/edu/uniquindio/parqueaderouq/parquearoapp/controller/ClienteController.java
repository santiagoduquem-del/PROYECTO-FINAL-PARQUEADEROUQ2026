package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.Usuario;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.tipoUsuario;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.Estudiante;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.Docente;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.Administrativo;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.Visitante;
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

public class ClienteController {

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
        for (int i = 0; i < DataHolder.getInstance().getUserList().size(); i++) {
            if (DataHolder.getInstance().getUserList().get(i).getIdentificacion().equals(id)) {
                Usuario u = crearUsuarioDesdeFormulario();
                if (u != null) {
                    DataHolder.getInstance().getUserList().set(i, u);
                    mostrarMensaje("Éxito", "Usuario Actualizado", "El usuario ha sido actualizado correctamente.", Alert.AlertType.INFORMATION);
                    limpiarCampos();
                }
                return;
            }
        }
        mostrarMensaje("Error", "No encontrado", "No se encontró un usuario con esa identificación.", Alert.AlertType.ERROR);
    }

    @FXML
    void onAgregarCliente(ActionEvent event) {
    }

    @FXML
    void onCrearCliente(ActionEvent event) {
        Usuario nuevoUsuario = crearUsuarioDesdeFormulario();
        if (nuevoUsuario != null) {
            boolean existeUsuario = DataHolder.getInstance().getUserList().stream()
                    .anyMatch(u -> u.getIdentificacion().equals(nuevoUsuario.getIdentificacion()));

            if (existeUsuario) {
                mostrarMensaje("Error", "Usuario duplicado", "Ya existe un usuario con esa identificación.", Alert.AlertType.ERROR);
                return;
            }

            DataHolder.getInstance().getUserList().add(nuevoUsuario);
            mostrarMensaje("Éxito", "Usuario Creado", "El usuario " + nuevoUsuario.getNombre() + " (" + nuevoUsuario.getTipo() + ") ha sido registrado.", Alert.AlertType.INFORMATION);
            limpiarCampos();
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
        boolean eliminado = DataHolder.getInstance().getUserList().removeIf(u -> u.getIdentificacion().equals(id));
        if (eliminado) {
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

    private Usuario crearUsuarioDesdeFormulario() {
        String nombre = txtNombre.getText();
        String id = txtIdentificacion.getText();
        String correo = txtCorreo.getText();
        String tel = txtTelefono.getText();
        tipoUsuario tipo = DataHolder.getInstance().getSelectedType();

        if (nombre.isEmpty() || id.isEmpty()) {
            mostrarMensaje("Error", "Faltan datos", "Por favor complete nombre e identificación.", Alert.AlertType.ERROR);
            return null;
        }

        if (tipo == null) {
            mostrarMensaje("Error", "Tipo no seleccionado", "Seleccione un tipo de usuario antes de crear.", Alert.AlertType.ERROR);
            return null;
        }

        switch (tipo) {
            case ESTUDIANTE: return new Estudiante(nombre, id, correo, tel, tipo);
            case DOCENTE: return new Docente(nombre, id, correo, tel, tipo);
            case ADMINISTRATIVO: return new Administrativo(nombre, id, correo, tel, tipo);
            case VISITANTE: return new Visitante(nombre, id, correo, tel, tipo);
            default: return null;
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
