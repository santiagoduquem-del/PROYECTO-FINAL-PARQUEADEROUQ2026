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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import co.edu.uniquindio.parqueaderouq.parquearoapp.ClienteApplication;
import co.edu.uniquindio.parqueaderouq.parquearoapp.utils.DataHolder;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane mainStackPane;

    @FXML
    private Group scalingGroup;

    @FXML
    private PasswordField PasswordContraseña;

    @FXML
    private Button btnIngreso;

    @FXML
    private Button btnGoogle;
    
    @FXML
    private Button btnApple;
    
    @FXML
    private Button btnMicrosoft;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    void onIngreso(ActionEvent event) {
        String identificacion = txtIdentificacion.getText();
        String contrasena = PasswordContraseña.getText();

        if (identificacion == null || identificacion.isEmpty() || contrasena == null || contrasena.isEmpty()) {
            mostrarMensaje("Error", "Campos vacíos", "Por favor ingrese su identificación y contraseña.", AlertType.ERROR);
            return;
        }

        // Acceso Administrador (ADMIN / 1228)
        if (identificacion.equals("ADMIN") && contrasena.equals("1228")) {
            DataHolder.getInstance().setLoginRole("ADMIN");
            abrirMenuAdministrador();
        } 
        // Acceso Operador (1234567 / 1224)
        else if (identificacion.equals("1234567") && contrasena.equals("1224")) {
            DataHolder.getInstance().setLoginRole("OPERADOR");
            abrirMenuOperador();
        } else {
            mostrarMensaje("Error", "Autenticación fallida", "Identificación o contraseña incorrectas.", AlertType.ERROR);
        }
    }

    @FXML
    void onGoogleLogin(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("LoginGoogle.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnGoogle.getScene().getWindow();
            stage.setTitle("Iniciar sesión con Google");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            mostrarMensaje("Error", "Error de carga", "No se pudo cargar la ventana de Login Google: " + e.getMessage(), AlertType.ERROR);
            e.printStackTrace();
        }
    }
    
    @FXML
    void onAppleLogin(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("LoginApple.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnApple.getScene().getWindow();
            stage.setTitle("Iniciar sesión con Apple");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            mostrarMensaje("Error", "Error de carga", "No se pudo cargar la ventana de Login Apple: " + e.getMessage(), AlertType.ERROR);
            e.printStackTrace();
        }
    }
    
    @FXML
    void onMicrosoftLogin(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("LoginMicrosoft.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnMicrosoft.getScene().getWindow();
            stage.setTitle("Iniciar sesión con Microsoft");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            mostrarMensaje("Error", "Error de carga", "No se pudo cargar la ventana de Login Microsoft: " + e.getMessage(), AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void abrirMenuOperador() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("MenuFuncionesOperador.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnIngreso.getScene().getWindow();
            stage.setTitle("Menú Operador - Parqueadero UQ");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            mostrarMensaje("Error", "Error de carga", "No se pudo cargar el menú del operador: " + e.getMessage(), AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void abrirMenuAdministrador() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClienteApplication.class.getResource("MenuFuncionesAdministrador.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnIngreso.getScene().getWindow();
            stage.setTitle("Menú Administrador - Parqueadero UQ");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            mostrarMensaje("Error", "Error de carga", "No se pudo cargar el menú del administrador: " + e.getMessage(), AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        assert PasswordContraseña != null : "fx:id=\"PasswordContraseña\" was not injected: check your FXML file 'Login.fxml'.";
        assert btnIngreso != null : "fx:id=\"btnIngreso\" was not injected: check your FXML file 'Login.fxml'.";
        assert btnGoogle != null : "fx:id=\"btnGoogle\" was not injected: check your FXML file 'Login.fxml'.";
        assert btnApple != null : "fx:id=\"btnApple\" was not injected: check your FXML file 'Login.fxml'.";
        assert btnMicrosoft != null : "fx:id=\"btnMicrosoft\" was not injected: check your FXML file 'Login.fxml'.";
        assert txtIdentificacion != null : "fx:id=\"txtIdentificacion\" was not injected: check your FXML file 'Login.fxml'.";
        
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
