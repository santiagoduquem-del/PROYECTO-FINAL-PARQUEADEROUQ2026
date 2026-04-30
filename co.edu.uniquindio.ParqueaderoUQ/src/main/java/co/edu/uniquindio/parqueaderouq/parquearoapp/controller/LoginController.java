package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

public class LoginController {

    public String autenticar(String identificacion, String contrasena) {
        if (identificacion.equals("ADMIN") && contrasena.equals("1228")) {
            return "ADMIN";
        } 
        else if (identificacion.equals("1234567") && contrasena.equals("1224")) {
            return "OPERADOR";
        }
        return null;
    }
}
