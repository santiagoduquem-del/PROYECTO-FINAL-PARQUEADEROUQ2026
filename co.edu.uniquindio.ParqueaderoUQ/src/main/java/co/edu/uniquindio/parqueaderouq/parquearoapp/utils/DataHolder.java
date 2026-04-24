package co.edu.uniquindio.parqueaderouq.parquearoapp.utils;

import co.edu.uniquindio.parqueaderouq.parquearoapp.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;

public class DataHolder {
    private static DataHolder instance;
    private tipoUsuario selectedType;
    private String loginRole;
    private final ObservableList<Usuario> userList = FXCollections.observableArrayList();
    private final ObservableList<Vehiculo> vehicleList = FXCollections.observableArrayList();
    private final ObservableList<Espacio> spaceList = FXCollections.observableArrayList();
    private final ObservableList<Tarifa> tariffList = FXCollections.observableArrayList();

    private DataHolder() {
        userList.addAll(Persistencia.cargarUsuarios());
        vehicleList.addAll(Persistencia.cargarVehiculos());
        spaceList.addAll(Persistencia.cargarEspacios());
        tariffList.addAll(Persistencia.cargarTarifas());

        // --- CORRECCIÓN DE SINCRONIZACIÓN AL INICIO ---
        // Aseguramos que los espacios solo tengan vehículos que realmente estén DENTRO
        for (Espacio e : spaceList) {
            if (e.getVehiculoAsignado() != null) {
                String placa = e.getVehiculoAsignado().getPlaca();
                boolean sigueDentro = vehicleList.stream()
                        .anyMatch(v -> v.getPlaca().equalsIgnoreCase(placa) && v.getEstado() == estadoVehiculo.DENTRO);
                
                if (!sigueDentro) {
                    e.setVehiculoAsignado(null);
                }
            }
        }
        Persistencia.guardarEspacios(spaceList);
        // ----------------------------------------------

        if (tariffList.isEmpty()) {
            tariffList.add(new Tarifa(tipoVehiculo.CARRO, 5000, 0));
            tariffList.add(new Tarifa(tipoVehiculo.MOTO, 2000, 0));
            tariffList.add(new Tarifa(tipoVehiculo.BICICLETA, 1000, 0));
        }

        userList.addListener((ListChangeListener<Usuario>) change -> Persistencia.guardarUsuarios(userList));
        vehicleList.addListener((ListChangeListener<Vehiculo>) change -> Persistencia.guardarVehiculos(vehicleList));
        spaceList.addListener((ListChangeListener<Espacio>) change -> Persistencia.guardarEspacios(spaceList));
        tariffList.addListener((ListChangeListener<Tarifa>) change -> Persistencia.guardarTarifas(tariffList));
    }

    public static DataHolder getInstance() {
        if (instance == null) { instance = new DataHolder(); }
        return instance;
    }

    public tipoUsuario getSelectedType() { return selectedType; }
    public void setSelectedType(tipoUsuario selectedType) { this.selectedType = selectedType; }
    public String getLoginRole() { return loginRole; }
    public void setLoginRole(String loginRole) { this.loginRole = loginRole; }
    public ObservableList<Usuario> getUserList() { return userList; }
    public ObservableList<Vehiculo> getVehicleList() { return vehicleList; }
    public ObservableList<Espacio> getSpaceList() { return spaceList; }
    public ObservableList<Tarifa> getTariffList() { return tariffList; }
}
