package co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

import co.edu.uniquindio.parqueaderouq.parquearoapp.model.Administrador;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.tipoEspacio;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.estadoEspacio;

public class GestionDeEspaciosController {

    private Administrador administrador = new Administrador();

    public boolean crearEspacio(String id, tipoEspacio tipo, estadoEspacio estado) {
        return administrador.crearEspacio(id, tipo, estado);
    }

    public boolean modificarEspacio(String id, tipoEspacio nuevoTipo, estadoEspacio nuevoEstado) {
        return administrador.modificarEspacio(id, nuevoTipo, nuevoEstado);
    }

    public boolean eliminarEspacio(String id) {
        return administrador.eliminarEspacio(id);
    }
}
