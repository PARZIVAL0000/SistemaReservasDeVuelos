/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author denni
 */
public class Destino {
    private int id;
    private String fecha;
    private String hora_salida;
    private String hora_llegada;
    private int pais_id;
    private int aerolinea_id;
    //nuestro atributo de aqui sera algo temporal, para darle un valor con el InnerJOIN
    private String paisnombre;
    private String aero_nombre;
    
    public Destino(){
    }


    public void setAero_nombre(String aero_nombre){
        this.aero_nombre = aero_nombre;
    }
    public String getAero_nombre(){
        return aero_nombre;
    }
    
    
    public void setPaisnombre(String paisnombre){
        this.paisnombre = paisnombre;
    }
    public String getPaisnombre(){
        return paisnombre;
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
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the hora_salida
     */
    public String getHora_salida() {
        return hora_salida;
    }

    /**
     * @param hora_salida the hora_salida to set
     */
    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    /**
     * @return the hora_llegada
     */
    public String getHora_llegada() {
        return hora_llegada;
    }

    /**
     * @param hora_llegada the hora_llegada to set
     */
    public void setHora_llegada(String hora_llegada) {
        this.hora_llegada = hora_llegada;
    }

    /**
     * @return the pais_id
     */
    public int getPais_id() {
        return pais_id;
    }

    /**
     * @param pais_id the pais_id to set
     */
    public void setPais_id(int pais_id) {
        this.pais_id = pais_id;
    }
    
    /**
     * @param aerolinea_id
     */
    public void setAerolinea_id(int aerolinea_id){
        this.aerolinea_id = aerolinea_id;
    }
    
    /**
     * @return aerolinea_id
     */
    public int getAerolinea_id(){
        return aerolinea_id;
    }
    
}
