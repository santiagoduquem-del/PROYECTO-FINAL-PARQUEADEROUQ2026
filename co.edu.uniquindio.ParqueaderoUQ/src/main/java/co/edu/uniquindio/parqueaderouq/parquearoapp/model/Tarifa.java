package co.edu.uniquindio.parqueaderouq.parquearoapp.model;

public class Tarifa {

    private String tipoVehiculo;
    private double valorPorHora;
    private double descuento;


    public Tarifa(String tipoVehiculo, double valorPorHora, double descuento) {
        this.tipoVehiculo = tipoVehiculo;
        this.valorPorHora = valorPorHora;
        this.descuento = descuento;
    }


    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public double getValorPorHora() {
        return valorPorHora;
    }

    public void setValorPorHora(double valorPorHora) {
        this.valorPorHora = valorPorHora;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "Tarifa{" +
                "tipoVehiculo='" + tipoVehiculo + '\'' +
                ", valorPorHora=" + valorPorHora +
                ", descuento=" + descuento +
                '}';
    }
}


