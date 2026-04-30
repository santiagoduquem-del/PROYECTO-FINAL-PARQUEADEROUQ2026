package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import co.edu.uniquindio.parqueaderouq.parquearoapp.model.Operador;
import java.time.LocalDateTime;

public class SalidaDeVehiculosController {

    private Operador operador = new Operador();

    public double registrarSalidaVehiculo(String placa, LocalDateTime horaSalida, double porcentajeDescuento) {
        return operador.registrarSalidaVehiculo(placa, horaSalida, porcentajeDescuento);
    }
}
