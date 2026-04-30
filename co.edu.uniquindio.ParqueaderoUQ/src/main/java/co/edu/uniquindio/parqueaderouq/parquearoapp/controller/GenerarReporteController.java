package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import co.edu.uniquindio.parqueaderouq.parquearoapp.model.Operador;
import java.time.LocalDate;

public class GenerarReporteController {

    private Operador operador = new Operador();

    public Operador.ReporteData generarReporte(LocalDate fecha) {
        return operador.generarReporte(fecha);
    }
}
