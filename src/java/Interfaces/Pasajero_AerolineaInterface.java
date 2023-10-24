package Interfaces;

import Modelo.Pasajero_Aerolinea;
import java.util.List;
/**
 *
 * @author denni
 */
public interface Pasajero_AerolineaInterface {
    
    public List<Pasajero_Aerolinea> listarPasajerosAerolineas();
    public List<Pasajero_Aerolinea> listarPasajeroAerolinea(int pasajerosAerolineaId);
    public boolean eliminarPasajeroAerolinea(int pasajerosAerolineaId);
    public boolean actualizarPasajeroAerolinea(Pasajero_Aerolinea pasajero_aerolinea);
    public boolean crearPasajeroAerolinea(List<Pasajero_Aerolinea> pasajeros_aerolineas);
}
