package co.edu.uniquindio.parqueaderouq.parquearoapp.model;

public class Estudiante extends Usuario {
    public Estudiante(String nombre, String identificacion, String correo, String telefono, tipoUsuario tipo) {
        super(nombre, identificacion, correo, telefono, tipo);
    }

    public Estudiante() {
        super();
    }
}
