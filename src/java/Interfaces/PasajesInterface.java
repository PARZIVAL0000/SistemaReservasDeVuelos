/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;
import Modelo.Pasajes;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author denni
 */
public interface PasajesInterface {
    
    public List<Pasajes> listarPasajes();
    
    public List<Pasajes> filtrarRegistro(Pasajes p);
}
