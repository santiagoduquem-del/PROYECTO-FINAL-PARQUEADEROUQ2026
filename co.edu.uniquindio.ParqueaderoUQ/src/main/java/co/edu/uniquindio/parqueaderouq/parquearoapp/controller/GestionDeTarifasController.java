package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import co.edu.uniquindio.parqueaderouq.parquearoapp.model.Administrador;

public class GestionDeTarifasController {

    private Administrador administrador = new Administrador();

    public void actualizarTarifas(double carro, double moto, double bici) {
        administrador.actualizarTarifas(carro, moto, bici);
    }
}
