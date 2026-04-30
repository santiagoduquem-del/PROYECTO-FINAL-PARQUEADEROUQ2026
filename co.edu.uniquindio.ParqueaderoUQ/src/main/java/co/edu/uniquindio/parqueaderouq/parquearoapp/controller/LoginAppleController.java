package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

public class LoginAppleController {

    public String verificarAcceso(String contrasena) {
        if ("1228".equals(contrasena)) {
            return "ADMIN";
        } else if ("1224".equals(contrasena)) {
            return "OPERADOR";
        }
        return null;
    }
}
