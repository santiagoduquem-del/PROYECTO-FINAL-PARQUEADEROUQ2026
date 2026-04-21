package co.edu.uniquindio.parqueaderouq.parquearoapp.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Vehiculo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String placa;
    private tipoVehiculo tipoVehiculo;
    private String nombreConductor;
    private String identificacionConductor;
    private LocalDateTime horaIngreso;
    private LocalDateTime horaSalida; // Nuevo campo
    private Espacio espacioAsignado;
    private estadoVehiculo estado;
    private double valorPagado; // Nuevo campo

    public Vehiculo(String placa, tipoVehiculo tipoVehiculo, String nombreConductor, String identificacionConductor, LocalDateTime horaIngreso, Espacio espacioAsignado, estadoVehiculo estado) {
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.nombreConductor = nombreConductor;
        this.identificacionConductor = identificacionConductor;
        this.horaIngreso = horaIngreso;
        this.espacioAsignado = espacioAsignado;
        this.estado = estado;
    }

    public Vehiculo() {
    }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public tipoVehiculo getTipoVehiculo() { return tipoVehiculo; }
    public void setTipoVehiculo(tipoVehiculo tipoVehiculo) { this.tipoVehiculo = tipoVehiculo; }

    public String getNombreConductor() { return nombreConductor; }
    public void setNombreConductor(String nombreConductor) { this.nombreConductor = nombreConductor; }

    public String getIdentificacionConductor() { return identificacionConductor; }
    public void setIdentificacionConductor(String identificacionConductor) { this.identificacionConductor = identificacionConductor; }

    public LocalDateTime getHoraIngreso() { return horaIngreso; }
    public void setHoraIngreso(LocalDateTime horaIngreso) { this.horaIngreso = horaIngreso; }

    public LocalDateTime getHoraSalida() { return horaSalida; }
    public void setHoraSalida(LocalDateTime horaSalida) { this.horaSalida = horaSalida; }

    public Espacio getEspacioAsignado() { return espacioAsignado; }
    public void setEspacioAsignado(Espacio espacioAsignado) { this.espacioAsignado = espacioAsignado; }

    public estadoVehiculo getEstado() { return estado; }
    public void setEstado(estadoVehiculo estado) { this.estado = estado; }

    public double getValorPagado() { return valorPagado; }
    public void setValorPagado(double valorPagado) { this.valorPagado = valorPagado; }

    @Override
    public String toString() {
        return "Vehiculo{" + "placa='" + placa + '\'' + ", estado=" + estado + '}';
    }
}
