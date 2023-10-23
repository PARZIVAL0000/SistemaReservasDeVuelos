/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author denni
 */
public class Pasajeros_Destino {
    private int pasajero_destino_id;
    private int destino_id;
    private int pasajero_id;
    
    public Pasajeros_Destino(){
        
    }
    
    public Pasajeros_Destino(int pasajero_destino_id, int destino_id, int pasajero_id){
        this.pasajero_destino_id = pasajero_destino_id;
        this.destino_id = destino_id;
        this.pasajero_id = pasajero_id;
    }

    public void setPasajeroDestinoId(int pasajeroDestinoId){
        this.pasajero_destino_id = pasajeroDestinoId;
    }
    public int getPasajeroDestinoId(){
        return pasajero_destino_id;
    }
    
    public void setDestinoId(int DestinoId){
        this.destino_id = DestinoId;
    }
    public int getDestinoId(){
        return destino_id;
    }
    
    public void setPasajeroId(int pasajeroId){
        this.pasajero_id = pasajeroId;
    }
    public int getPasajeroId(){
        return pasajero_id;
    }
}
