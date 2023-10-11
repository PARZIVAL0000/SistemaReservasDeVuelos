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
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private String celular;
    private String cedula;
    private int pasajeroId;
    private int generoId;
    private int provinciaId;
   
    //vamos a generar campos adicionales...
    private String tipoPasajero;
    private String genero;
    private String provincia;
    
    
    public Usuarios(){
    }
    
    public Usuarios(int id, String nombre, String apellido, String correo, String celular, String cedula, int pasajeroId, int generoId){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.celular = celular;
        this.cedula = cedula;
        this.pasajeroId = pasajeroId;
        this.generoId = generoId;

    }
    
    public Usuarios(int id, String nombre, String apellido, String correo, String celular, String cedula,
            int pasajeroId, int generoId, int provinciaId, String tipoPasajero, String genero, String provincia){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.celular = celular;
        this.cedula = cedula;
        this.pasajeroId = pasajeroId;
        this.generoId = generoId;
        this.provinciaId = provinciaId;
        this.tipoPasajero = tipoPasajero;
        this.genero = genero;
        this.provincia = provincia; 
    }

    public void setProvincia(String provincia){
        this.provincia = provincia;
    }
    public String getProvincia(){
        return provincia;
    }
    
    public void setGenero(String genero){
        this.genero = genero;
    }
    public String getGenero(){
        return genero;
    }
    
    public void setTipoPasajero(String tipoPasajero){
        this.tipoPasajero = tipoPasajero;
    }
    public String getTipoPasajero(){
        return tipoPasajero;
    }
    
    public int getPasajeroId(){
        return pasajeroId;
    }
    public void setPasajeroId(int id){
        this.pasajeroId = id;
    }
    
    
    public int getGeneroId(){
        return generoId;
    }
    public void setGeneroId(int id){
        this.generoId = id;
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }


    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the provinciaId
     */
    public int getProvinciaId() {
        return provinciaId;
    }

    /**
     * @param provinciaId the provinciaId to set
     */
    public void setProvinciaId(int provinciaId) {
        this.provinciaId = provinciaId;
    }

}
