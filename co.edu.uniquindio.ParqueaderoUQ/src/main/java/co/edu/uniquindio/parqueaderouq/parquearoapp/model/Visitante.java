package co.edu.uniquindio.parqueaderouq.parquearoapp.model;

public class Visitante extends Usuario {
    public Visitante(String nombre, String identificacion, String correo, String telefono, tipoUsuario tipo) {
        super(nombre, identificacion, correo, telefono, tipo);
    }

    public Visitante() {
        super();
    }
}
