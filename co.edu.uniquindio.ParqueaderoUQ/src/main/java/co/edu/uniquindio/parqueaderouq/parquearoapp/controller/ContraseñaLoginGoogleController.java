package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

public class ContraseñaLoginGoogleController {

    public boolean verificarContraseña(String role, String contrasena) {
        if ("ADMIN".equals(role)) {
            return "1228".equals(contrasena);
        } else if ("OPERADOR".equals(role)) {
            return "1224".equals(contrasena);
        }
        return false;
    }
}
