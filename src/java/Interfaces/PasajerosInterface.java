package Interfaces;

import Modelo.Pasajeros;
import java.util.List;

/**
 *
 * @author denni
 */
public interface PasajerosInterface {
    
    public List<Pasajeros> listarPasajeros();
    public List<Pasajeros> listarPasajero(int pasajero_id);
    public boolean eliminarPasajero(int pasajero_id);
    public boolean actualizarPasajero(Pasajeros pasajero);
    public boolean crearPasajero(List<Pasajeros> pasajero);
}
