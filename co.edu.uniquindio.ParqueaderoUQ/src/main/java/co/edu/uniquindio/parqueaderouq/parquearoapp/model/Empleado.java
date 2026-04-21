package co.edu.uniquindio.parqueaderouq.parquearoapp.model;

public abstract class Empleado {

    private String nombre;
    private String identificacion;
    private String cargo;
    private String correo;
    private String telefono;

    public Empleado(String nombre, String identificacion, String cargo, String correo, String telefono) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.cargo = cargo;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Empleado() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", cargo='" + cargo + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
