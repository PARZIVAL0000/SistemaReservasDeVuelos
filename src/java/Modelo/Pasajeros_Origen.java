/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author denni
 */
public class Pasajeros_Origen {
    private int pasajero_origen_id;
    private int origen_id;
    private int pasajero_id;

    public Pasajeros_Origen(){
    
    }
    
    public Pasajeros_Origen(int pasajeroOrigenId, int origenId, int pasajeroId){
        this.pasajero_origen_id = pasajeroOrigenId;
        this.origen_id = origenId;
        this.pasajero_id = pasajeroId;
    }
    
    public void setPasajeroOrigenId(int pasajeroOrigenId){
        this.pasajero_origen_id = pasajeroOrigenId;
    }
    public int getPasajeroOrigenId(){
        return pasajero_origen_id;
    }
    
    public void setOrigenId(int OrigenId){
        this.origen_id = OrigenId;
    }
    public int getOrigenId(){
        return origen_id;
    }
    
    public void setPasajeroId(int pasajeroId){
        this.pasajero_id = pasajeroId;
    }
    public int getPasajeroId(){
        return pasajero_id;
    }
}
