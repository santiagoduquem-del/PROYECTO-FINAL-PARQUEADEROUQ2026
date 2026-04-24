package co.edu.uniquindio.parqueaderouq.parquearoapp.model;

public class Tarifa {
    private tipoVehiculo tipo;
    private double valorPorHora;
    private double descuento;

    public Tarifa(tipoVehiculo tipo, double valorPorHora, double descuento) {
        this.tipo = tipo;
        this.valorPorHora = valorPorHora;
        this.descuento = descuento;
    }

    public Tarifa() {
    }

    public tipoVehiculo getTipo() {
        return tipo;
    }

    public void setTipo(tipoVehiculo tipo) {
        this.tipo = tipo;
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

    /**
     * Calcula el valor neto por hora aplicando el descuento si existe.
     * @return El valor por hora con el descuento aplicado.
     */
    public double calcularValorConDescuento() {
        return valorPorHora * (1 - (descuento / 100));
    }

    @Override
    public String toString() {
        return "Tarifa{" +
                "tipo=" + tipo +
                ", valorPorHora=" + valorPorHora +
                ", descuento=" + descuento + "%" +
                '}';
    }
}
