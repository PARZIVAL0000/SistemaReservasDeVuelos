package com.example.sistemareservadevuelos.Modelo;

/**
 *
 * @author denni
 */
public class Pais {
    private int id;
    private String paisNombre;
    
    public Pais(){
    
    }
    
    public Pais(int id, String paisNombre){
        this.id = id;
        this.paisNombre = paisNombre;
    }
    
    public int getId(){
        return id;
    }
    public String getPaisNombre(){
        return paisNombre;
    }
    
    public void setId(int id){
        this.id = id;
    }
    public void setPaisNombre(String pais){
        this.paisNombre = pais;
    }
}
