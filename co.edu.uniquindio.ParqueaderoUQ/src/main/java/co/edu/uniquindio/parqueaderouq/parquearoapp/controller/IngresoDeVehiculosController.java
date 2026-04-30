package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import co.edu.uniquindio.parqueaderouq.parquearoapp.model.Operador;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.tipoVehiculo;
import java.time.LocalDateTime;

public class IngresoDeVehiculosController {

    private Operador operador = new Operador();

    public String ingresarVehiculo(String placa, String nombre, String id, String codEspacio, LocalDateTime horaIngreso, tipoVehiculo tipo) {
        return operador.ingresarVehiculo(placa, nombre, id, codEspacio, horaIngreso, tipo);
    }
}
