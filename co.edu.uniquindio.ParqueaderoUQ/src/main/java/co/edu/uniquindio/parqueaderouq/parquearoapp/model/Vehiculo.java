package co.edu.uniquindio.parqueaderouq.parquearoapp.model;

import java.time.LocalDateTime;

public class Vehiculo {


private String placa;
private String tipoVehiculo;
private String marca;
private String nombreConductor;
private int identificacionConductor;
private LocalDateTime horaIngreso;
private String estado;
private EspacioParqueadero theEspacioAsignado;


    public Vehiculo(String placa, String tipoVehiculo,
                    String marca, String nombreConductor,
                    int identificacionConductor, LocalDateTime horaIngreso,
                    String estado, EspacioParqueadero EspacioAsignado) {
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.marca = marca;
        this.nombreConductor = nombreConductor;
        this.identificacionConductor = identificacionConductor;
        this.horaIngreso = horaIngreso;
        this.estado = estado;
        this.theEspacioAsignado = EspacioAsignado;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    public int getIdentificacionConductor() {
        return identificacionConductor;
    }

    public void setIdentificacionConductor(int identificacionConductor) {
        this.identificacionConductor = identificacionConductor;
    }

    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(LocalDateTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public EspacioParqueadero getEspacioAsignado() {
        return theEspacioAsignado;
    }

    public void setEspacioAsignado(EspacioParqueadero EspacioAsignado) {
        this.theEspacioAsignado = EspacioAsignado;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", tipoVehiculo='" + tipoVehiculo + '\'' +
                ", marca='" + marca + '\'' +
                ", nombreConductor='" + nombreConductor + '\'' +
                ", identificacionConductor=" + identificacionConductor +
                ", horaIngreso=" + horaIngreso +
                ", estado='" + estado + '\'' +
                ", theEspacioAsignado=" + theEspacioAsignado +
                '}';
    }
}
