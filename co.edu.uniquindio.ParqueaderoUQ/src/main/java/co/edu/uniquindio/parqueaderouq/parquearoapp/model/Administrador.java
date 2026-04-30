package co.edu.uniquindio.parqueaderouq.parquearoapp.model;

import co.edu.uniquindio.parqueaderouq.parquearoapp.utils.DataHolder;

public class Administrador extends Empleado {

    public Administrador(String nombre, String identificacion, String cargo, String correo, String telefono) {
        super(nombre, identificacion, cargo, correo, telefono);
    }

    public Administrador() {
        super();
    }

    public boolean crearEspacio(String id, tipoEspacio tipo, estadoEspacio estado) {
        boolean existe = DataHolder.getInstance().getSpaceList().stream()
                .anyMatch(e -> e.getCodigo().equals(id));

        if (existe) return false;

        Espacio nuevoEspacio = new Espacio(id, tipo, estado, null);
        DataHolder.getInstance().getSpaceList().add(nuevoEspacio);
        return true;
    }

    public boolean modificarEspacio(String id, tipoEspacio nuevoTipo, estadoEspacio nuevoEstado) {
        for (int i = 0; i < DataHolder.getInstance().getSpaceList().size(); i++) {
            Espacio e = DataHolder.getInstance().getSpaceList().get(i);
            if (e.getCodigo().equals(id)) {
                if (nuevoTipo != null) e.setTipo(nuevoTipo);
                if (nuevoEstado != null) e.setEstado(nuevoEstado);
                DataHolder.getInstance().getSpaceList().set(i, e);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarEspacio(String id) {
        return DataHolder.getInstance().getSpaceList().removeIf(e -> e.getCodigo().equals(id));
    }

    public boolean crearUsuario(String nombre, String id, String correo, String tel, tipoUsuario tipo) {
        boolean existe = DataHolder.getInstance().getUserList().stream()
                .anyMatch(u -> u.getIdentificacion().equals(id));

        if (existe) return false;

        Usuario nuevoUsuario;
        switch (tipo) {
            case ESTUDIANTE: nuevoUsuario = new Estudiante(nombre, id, correo, tel, tipo); break;
            case DOCENTE: nuevoUsuario = new Docente(nombre, id, correo, tel, tipo); break;
            case ADMINISTRATIVO: nuevoUsuario = new Administrativo(nombre, id, correo, tel, tipo); break;
            case VISITANTE: nuevoUsuario = new Visitante(nombre, id, correo, tel, tipo); break;
            default: return false;
        }
        DataHolder.getInstance().getUserList().add(nuevoUsuario);
        return true;
    }

    public boolean actualizarUsuario(String nombre, String id, String correo, String tel, tipoUsuario tipo) {
        for (int i = 0; i < DataHolder.getInstance().getUserList().size(); i++) {
            if (DataHolder.getInstance().getUserList().get(i).getIdentificacion().equals(id)) {
                Usuario u;
                switch (tipo) {
                    case ESTUDIANTE: u = new Estudiante(nombre, id, correo, tel, tipo); break;
                    case DOCENTE: u = new Docente(nombre, id, correo, tel, tipo); break;
                    case ADMINISTRATIVO: u = new Administrativo(nombre, id, correo, tel, tipo); break;
                    case VISITANTE: u = new Visitante(nombre, id, correo, tel, tipo); break;
                    default: return false;
                }
                DataHolder.getInstance().getUserList().set(i, u);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarUsuario(String id) {
        return DataHolder.getInstance().getUserList().removeIf(u -> u.getIdentificacion().equals(id));
    }

    public void actualizarTarifas(double carro, double moto, double bici) {
        for (Tarifa t : DataHolder.getInstance().getTariffList()) {
            if (t.getTipo() == tipoVehiculo.CARRO) t.setValorPorHora(carro);
            else if (t.getTipo() == tipoVehiculo.MOTO) t.setValorPorHora(moto);
            else if (t.getTipo() == tipoVehiculo.BICICLETA) t.setValorPorHora(bici);
        }
        // Force list update if needed, but since it's an ObservableList of objects,
        // usually we need to set the item or have properties.
        // DataHolder.getInstance().getTariffList().setAll(DataHolder.getInstance().getTariffList());
        // Actually Persistencia.guardarTarifas is called on listener.
    }
}
