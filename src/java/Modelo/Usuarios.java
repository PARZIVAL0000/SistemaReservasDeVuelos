/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author denni
 */
public class Usuarios {
    private int usuario_id;
    private int pasajero_id;
    
    public Usuarios(){
        
    }
    
    public Usuarios(int usuario_id, int pasajero_id){
        this.usuario_id = usuario_id;
        this.pasajero_id = pasajero_id;
    }
    
    
    public void setUsuarioId(int usuario_id){
        this.usuario_id = usuario_id;
    }
    public int getUsuarioId(){
        return usuario_id;
    }
    
    public void setPasajeroId(int pasajero_id){
        this.pasajero_id = pasajero_id;
    }
    public int getPasajeroId(){
        return pasajero_id;
    }
}
