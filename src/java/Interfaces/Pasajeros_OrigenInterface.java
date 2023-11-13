/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;
import Modelo.Pasajeros_Origen;
import java.util.List;
/**
 *
 * @author denni
 */
public interface Pasajeros_OrigenInterface {
    
    public List<Pasajeros_Origen> listadoPasajerosOrigen();
    public List<Pasajeros_Origen> listadoPasajeroOrigen(int pasajeroOrigenId);
    public boolean actualizarPasajeroOrigen(Pasajeros_Origen pasajeroOrigen);
    public boolean eliminarPasajeroOrigen(int pasajeroOrigenId);
    public boolean crearPasajeroOrigen(Pasajeros_Origen pasajeros);
}
