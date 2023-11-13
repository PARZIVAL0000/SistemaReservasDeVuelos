/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.Pasajeros_Destino;
import java.util.List;

/**
 *
 * @author denni
 */
public interface Pasajeros_DestinoInterface {
    
    public List<Pasajeros_Destino> listarPasajerosDestino();
    public List<Pasajeros_Destino> listarPasajeroDestino(int pasajeroDestinoId);
    public boolean actualizarPasajerosDestino(Pasajeros_Destino pasajeroDestino);
    public boolean eliminarPasajerosDestino(int pasajeroDestinoId);
    public boolean crearPasajerosDestino(Pasajeros_Destino pasajeros);
}
