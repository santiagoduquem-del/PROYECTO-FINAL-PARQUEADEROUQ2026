package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import co.edu.uniquindio.parqueaderouq.parquearoapp.model.Administrador;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.tipoUsuario;

public class ClienteController {

    private Administrador administrador = new Administrador();

    public boolean crearUsuario(String nombre, String id, String correo, String tel, tipoUsuario tipo) {
        return administrador.crearUsuario(nombre, id, correo, tel, tipo);
    }

    public boolean actualizarUsuario(String nombre, String id, String correo, String tel, tipoUsuario tipo) {
        return administrador.actualizarUsuario(nombre, id, correo, tel, tipo);
    }

    public boolean eliminarUsuario(String id) {
        return administrador.eliminarUsuario(id);
    }
}
