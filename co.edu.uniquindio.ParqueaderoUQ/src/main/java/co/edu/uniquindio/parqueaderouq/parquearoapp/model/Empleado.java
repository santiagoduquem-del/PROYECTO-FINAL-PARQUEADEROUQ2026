package co.edu.uniquindio.parqueaderouq.parquearoapp.model;

public abstract class Empleado {

    private String nombre;
    private int identificacion;
    private String correo;
    private int telefono;
    private String usuario;
    private String clave;
    private String rol;


    public Empleado(String nombre, int identificacion,
                    String correo, int telefono, String usuario,
                    String rol, String clave) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.correo = correo;
        this.telefono = telefono;
        this.usuario = usuario;
        this.rol = rol;
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", identificacion=" + identificacion +
                ", correo='" + correo + '\'' +
                ", telefono=" + telefono +
                ", usuario='" + usuario + '\'' +
                ", clave='" + clave + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}



