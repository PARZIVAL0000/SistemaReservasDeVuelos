/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.Origen;
import java.util.List;
/**
 *
 * @author denni
 */
public interface OrigenInterface {
    
    
   public List<Origen> listadoDatos();
    
   public boolean insertarDatos(Origen origen);
   
   public List<Origen> filtroRegistro(Origen org);
}
