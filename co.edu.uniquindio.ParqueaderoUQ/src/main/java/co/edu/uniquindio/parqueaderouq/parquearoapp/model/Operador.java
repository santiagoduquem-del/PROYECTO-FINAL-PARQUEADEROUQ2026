package co.edu.uniquindio.parqueaderouq.parquearoapp.model;

import co.edu.uniquindio.parqueaderouq.parquearoapp.utils.DataHolder;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

public class Operador extends Empleado {

    public Operador(String nombre, String identificacion, String cargo, String correo, String telefono) {
        super(nombre, identificacion, cargo, correo, telefono);
    }

    public Operador() {
        super();
    }

    public String ingresarVehiculo(String placa, String nombre, String id, String codEspacio, LocalDateTime horaIngreso, tipoVehiculo tipo) {
        // --- LÓGICA DE REGISTRO AUTOMÁTICO DE VISITANTE ---
        boolean usuarioRegistrado = DataHolder.getInstance().getUserList().stream()
                .anyMatch(u -> u.getIdentificacion().equals(id));

        if (!usuarioRegistrado) {
            Visitante nuevoVisitante = new Visitante(nombre, id, "", "", tipoUsuario.VISITANTE);
            DataHolder.getInstance().getUserList().add(nuevoVisitante);
        }

        // --- VALIDACIÓN DE ESPACIO ---
        Espacio espacioReal = DataHolder.getInstance().getSpaceList().stream()
                .filter(e -> e.getCodigo().equalsIgnoreCase(codEspacio))
                .findFirst().orElse(null);

        if (espacioReal == null) return "ESPACIO_INEXISTENTE";
        if (espacioReal.getEstado() == estadoEspacio.FUERA_DE_SERVICIO) return "FUERA_DE_SERVICIO";
        if (espacioReal.getVehiculoAsignado() != null) return "OCUPADO";

        // --- REGISTRO DE VEHÍCULO ---
        Vehiculo nuevoVehiculo = new Vehiculo(placa, tipo, nombre, id, horaIngreso, espacioReal, estadoVehiculo.DENTRO);

        int indexEspacio = DataHolder.getInstance().getSpaceList().indexOf(espacioReal);
        espacioReal.setVehiculoAsignado(nuevoVehiculo);
        DataHolder.getInstance().getSpaceList().set(indexEspacio, espacioReal);

        DataHolder.getInstance().getVehicleList().add(nuevoVehiculo);

        return usuarioRegistrado ? "EXITO_FRECUENTE" : "EXITO_VISITANTE";
    }

    public double registrarSalidaVehiculo(String placa, LocalDateTime horaSalida, double porcentajeDescuento) {
        Vehiculo vehiculo = DataHolder.getInstance().getVehicleList().stream()
                .filter(v -> v.getPlaca().equalsIgnoreCase(placa) && v.getEstado() == estadoVehiculo.DENTRO)
                .findFirst().orElse(null);

        if (vehiculo == null) return -1;

        // --- CÁLCULO DE FACTURA ---
        Duration duracion = Duration.between(vehiculo.getHoraIngreso(), horaSalida);
        long totalMinutos = Math.max(1, duracion.toMinutes());
        double valorHora = DataHolder.getInstance().getTariffList().stream()
                .filter(t -> t.getTipo() == vehiculo.getTipoVehiculo()).mapToDouble(Tarifa::getValorPorHora).findFirst().orElse(0);
        double total = (totalMinutos / 60.0) * valorHora * (1 - (porcentajeDescuento / 100));

        // --- ACTUALIZACIÓN DE DATOS ---
        vehiculo.setHoraSalida(horaSalida);
        vehiculo.setValorPagado(total);
        vehiculo.setEstado(estadoVehiculo.SALIO);

        if (vehiculo.getEspacioAsignado() != null) {
            String codigoEspacio = vehiculo.getEspacioAsignado().getCodigo();
            for (int i = 0; i < DataHolder.getInstance().getSpaceList().size(); i++) {
                Espacio e = DataHolder.getInstance().getSpaceList().get(i);
                if (e.getCodigo().equalsIgnoreCase(codigoEspacio)) {
                    e.setVehiculoAsignado(null);
                    DataHolder.getInstance().getSpaceList().set(i, e);
                    break;
                }
            }
        }

        int indexV = DataHolder.getInstance().getVehicleList().indexOf(vehiculo);
        if (indexV != -1) DataHolder.getInstance().getVehicleList().set(indexV, vehiculo);

        return total;
    }

    public ReporteData generarReporte(LocalDate fecha) {
        List<Vehiculo> vehiculosFecha = DataHolder.getInstance().getVehicleList().stream()
                .filter(v -> v.getHoraIngreso() != null && v.getHoraIngreso().toLocalDate().equals(fecha))
                .collect(Collectors.toList());

        int total = vehiculosFecha.size();
        double ingresos = vehiculosFecha.stream()
                .filter(v -> v.getEstado() == estadoVehiculo.SALIO)
                .mapToDouble(Vehiculo::getValorPagado)
                .sum();

        double promedioMinutos = vehiculosFecha.stream()
                .filter(v -> v.getEstado() == estadoVehiculo.SALIO && v.getHoraSalida() != null)
                .mapToLong(v -> Duration.between(v.getHoraIngreso(), v.getHoraSalida()).toMinutes())
                .average().orElse(0);

        return new ReporteData(total, ingresos, promedioMinutos / 60.0, vehiculosFecha);
    }

    public static class ReporteData {
        public final int totalVehiculos;
        public final double ingresosGenerados;
        public final double tiempoPromedioHoras;
        public final List<Vehiculo> vehiculos;

        public ReporteData(int total, double ingresos, double promedio, List<Vehiculo> vehiculos) {
            this.totalVehiculos = total;
            this.ingresosGenerados = ingresos;
            this.tiempoPromedioHoras = promedio;
            this.vehiculos = vehiculos;
        }
    }
}
