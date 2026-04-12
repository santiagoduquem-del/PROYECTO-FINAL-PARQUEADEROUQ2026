package co.edu.uniquindio.parqueaderouq.parquearoapp.model;

public class EspacioParqueadero {

    private String codigo;
    private String tipoEspacio;
    private String estado;
    private Vehiculo theVehiculoAsignado;


    public EspacioParqueadero(String codigo,
                              String tipoEspacio,
                              String estado, Vehiculo VehiculoAsignado) {
        this.codigo = codigo;
        this.tipoEspacio = tipoEspacio;
        this.estado = estado;
        this.theVehiculoAsignado = VehiculoAsignado;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipoEspacio() {
        return tipoEspacio;
    }

    public void setTipoEspacio(String tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Vehiculo getTheVehiculoAsignado() {
        return theVehiculoAsignado;
    }

    public void setTheVehiculoAsignado(Vehiculo theVehiculoAsignado) {
        this.theVehiculoAsignado = theVehiculoAsignado;
    }

    @Override
    public String toString() {
        return "EspacioParqueadero{" +
                "codigo='" + codigo + '\'' +
                ", tipoEspacio='" + tipoEspacio + '\'' +
                ", estado='" + estado + '\'' +
                ", theVehiculoAsignado=" + theVehiculoAsignado +
                '}';
    }
}
