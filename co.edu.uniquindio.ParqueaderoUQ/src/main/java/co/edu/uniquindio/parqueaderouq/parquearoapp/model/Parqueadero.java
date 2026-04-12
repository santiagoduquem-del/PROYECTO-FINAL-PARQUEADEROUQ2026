package co.edu.uniquindio.parqueaderouq.parquearoapp.model;

import java.util.ArrayList;
import java.util.List;

public class Parqueadero {

    private String nombre;
    private String direccion;
    private int nit;
    private List<EspacioParqueadero> listEspacioParqueadero;
    private List<Vehiculo> listVehiculoParqueadero;
    private List<Usuario> listUsuarioParqueadero;
    private List<Tarifa> listTarifaParqueadero;
    private List<Empleado> listEmpreadoParqueadero;


    public Parqueadero(String nombre, String direccion,int nit) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.nit = nit;
        this.listEspacioParqueadero = new ArrayList<>();
        this.listVehiculoParqueadero = new ArrayList<>();
        this.listUsuarioParqueadero = new ArrayList<>();
        this.listTarifaParqueadero = new ArrayList<>();
        this.listEmpreadoParqueadero = new ArrayList<>();
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public List<EspacioParqueadero> getListEspacioParqueadero() {

        return listEspacioParqueadero;
    }

    public void setListEspacioParqueadero(List<EspacioParqueadero> listEspacioVehiculoParqueadero) {

        this.listEspacioParqueadero = listEspacioParqueadero;
    }

    public List<Vehiculo> getListVehiculoParqueadero() {

        return listVehiculoParqueadero;
    }

    public void setListVehiculoParqueadero(List<Vehiculo> listVehiculoParqueadero) {

        this.listVehiculoParqueadero = listVehiculoParqueadero;
    }

    public List<Usuario> getListUsuarioParqueadero() {

        return listUsuarioParqueadero;
    }

    public void setListUsuarioParqueadero(List<Usuario> listUsuarioParqueadero) {
        this.listUsuarioParqueadero = listUsuarioParqueadero;
    }

    public List<Tarifa> getListTarifaParqueadero() {
        return listTarifaParqueadero;
    }

    public void setListTarifaParqueadero(List<Tarifa> listTarifaParqueadero) {
        this.listTarifaParqueadero = listTarifaParqueadero;
    }

    public List<Empleado> getListEmpreadoParqueadero() {
        return listEmpreadoParqueadero;
    }

    public void setListEmpreadoParqueadero(List<Empleado> listEmpreadoParqueadero) {
        this.listEmpreadoParqueadero = listEmpreadoParqueadero;
    }

    @Override
    public String toString() {
        return "Parqueadero{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", nit=" + nit +
                ", listEspacioParqueadero=" + listEspacioParqueadero +
                ", listVehiculoParqueadero=" + listVehiculoParqueadero +
                ", listUsuarioParqueadero=" + listUsuarioParqueadero +
                ", listTarifaParqueadero=" + listTarifaParqueadero +
                ", listEmpreadoParqueadero=" + listEmpreadoParqueadero +
                '}';
    }
}

