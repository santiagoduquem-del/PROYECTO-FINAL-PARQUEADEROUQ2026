package co.edu.uniquindio.parqueaderouq.parquearoapp.utils;

import co.edu.uniquindio.parqueaderouq.parquearoapp.model.Usuario;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.Vehiculo;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.Espacio;
import co.edu.uniquindio.parqueaderouq.parquearoapp.model.Tarifa;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {
    private static final String RUTA_USUARIOS = "usuarios.dat";
    private static final String RUTA_VEHICULOS = "vehiculos.dat";
    private static final String RUTA_ESPACIOS = "espacios.dat";
    private static final String RUTA_TARIFAS = "tarifas.dat";

    public static void guardarUsuarios(List<Usuario> usuarios) { escribirArchivo(RUTA_USUARIOS, usuarios); }
    @SuppressWarnings("unchecked")
    public static List<Usuario> cargarUsuarios() { return (List<Usuario>) leerArchivo(RUTA_USUARIOS); }

    public static void guardarVehiculos(List<Vehiculo> vehiculos) { escribirArchivo(RUTA_VEHICULOS, vehiculos); }
    @SuppressWarnings("unchecked")
    public static List<Vehiculo> cargarVehiculos() { return (List<Vehiculo>) leerArchivo(RUTA_VEHICULOS); }

    public static void guardarEspacios(List<Espacio> espacios) { escribirArchivo(RUTA_ESPACIOS, espacios); }
    @SuppressWarnings("unchecked")
    public static List<Espacio> cargarEspacios() { return (List<Espacio>) leerArchivo(RUTA_ESPACIOS); }

    public static void guardarTarifas(List<Tarifa> tarifas) { escribirArchivo(RUTA_TARIFAS, tarifas); }
    @SuppressWarnings("unchecked")
    public static List<Tarifa> cargarTarifas() { return (List<Tarifa>) leerArchivo(RUTA_TARIFAS); }

    private static void escribirArchivo(String ruta, Object data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(new ArrayList<>((List<?>) data));
        } catch (IOException e) {
            System.err.println("Error al guardar datos: " + e.getMessage());
        }
    }

    private static List<?> leerArchivo(String ruta) {
        File archivo = new File(ruta);
        if (!archivo.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return (List<?>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
