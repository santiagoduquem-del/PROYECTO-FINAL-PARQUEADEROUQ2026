package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

public class LoginMicrosoftController {

    public boolean validarUsuario(String user) {
        return "1234567".equals(user) || "ADMIN".equals(user);
    }
}
