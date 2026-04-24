package co.edu.uniquindio.parqueaderouq.parquearoapp.model;

import java.io.Serializable;

public class Espacio implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigo;
    private tipoEspacio tipo;
    private estadoEspacio estado;
    private Vehiculo vehiculoAsignado;

    public Espacio(String codigo, tipoEspacio tipo, estadoEspacio estado, Vehiculo vehiculoAsignado) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.estado = estado;
        this.vehiculoAsignado = vehiculoAsignado;
    }

    public Espacio() {
        this.vehiculoAsignado = null;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public tipoEspacio getTipo() {
        return tipo;
    }

    public void setTipo(tipoEspacio tipo) {
        this.tipo = tipo;
    }

    public estadoEspacio getEstado() {
        return estado;
    }

    public void setEstado(estadoEspacio estado) {
        this.estado = estado;
    }

    public Vehiculo getVehiculoAsignado() {
        return vehiculoAsignado;
    }

    public void setVehiculoAsignado(Vehiculo vehiculoAsignado) {
        this.vehiculoAsignado = vehiculoAsignado;
    }
}
