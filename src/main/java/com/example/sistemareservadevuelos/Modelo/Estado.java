/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sistemareservadevuelos.Modelo;

/**
 *
 * @author denni
 */
public class Estado {
    private int id;
    private int ubicacionPais;
    private String estadoNombre;
    
    public Estado(){
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the ubicacionPais
     */
    public int getUbicacionPais() {
        return ubicacionPais;
    }

    /**
     * @param ubicacionPais the ubicacionPais to set
     */
    public void setUbicacionPais(int ubicacionPais) {
        this.ubicacionPais = ubicacionPais;
    }

    /**
     * @return the estadoNombre
     */
    public String getEstadoNombre() {
        return estadoNombre;
    }

    /**
     * @param estadoNombre the estadoNombre to set
     */
    public void setEstadoNombre(String estadoNombre) {
        this.estadoNombre = estadoNombre;
    }
    
    
}
