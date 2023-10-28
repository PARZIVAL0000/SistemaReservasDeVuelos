/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.PrecioVueloFinal;
import java.util.List;

/**
 *
 * @author denni
 */
public interface PrecioVueloFinalInterface {
    
    public List<PrecioVueloFinal> listarPreciosVuelosFinal();
    public List<PrecioVueloFinal> listarPrecioVueloFinal(int precioVueloFinalId);
    public boolean eliminarPrecioVueloFinal(int precioVueloFinalId);
    public boolean actualizarPrecioVueloFinal(PrecioVueloFinal precio_vuelo_final);
    public boolean crearPrecioVueloFinal(List<PrecioVueloFinal> precio_vuelo_final);
}
