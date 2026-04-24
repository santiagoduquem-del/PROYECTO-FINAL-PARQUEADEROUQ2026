package co.edu.uniquindio.parqueaderouq.parquearoapp.model;

public class Administrativo extends Usuario {
    public Administrativo(String nombre, String identificacion, String correo, String telefono, tipoUsuario tipo) {
        super(nombre, identificacion, correo, telefono, tipo);
    }

    public Administrativo() {
        super();
    }
}
