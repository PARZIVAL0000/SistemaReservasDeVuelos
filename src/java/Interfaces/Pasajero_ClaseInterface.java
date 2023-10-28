/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.Pasajero_Clase;
import java.util.List;

/**
 *
 * @author denni
 */
public interface Pasajero_ClaseInterface {
    
    public List<Pasajero_Clase> listarPasajerosClases();
    public List<Pasajero_Clase> listarPasajeroClase(int pasajeroClaseId);
    public boolean eliminarPasajeroClase(int pasajeroClaseId);
    public boolean actualizarPasajeroClase(Pasajero_Clase pasajeroClase);
    public boolean crearPasajeroClase(List<Pasajero_Clase> pasajeroClase);
    
    
}
