/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author denni
 */
public class Pasajeros {
    private int pasajeros_id;
    private String pasajeros_nombre;
    private String pasajeros_apellido;
    private String pasajeros_correo;
    private String pasajeros_celular;
    private String pasajeros_cedula;
    private int tipoPasajeroId;
    private int generoId;
    private int precioVueloFinalId;

    public Pasajeros(){
    }
    
    public Pasajeros(int pasajeros_id, String pasajeros_nombre, String pasajeros_apellidos, String pasajeros_correo, String pasajeros_celular,
            String pasajeros_cedula, int tipoPasajeroId, int generoId, int precioVueloFinalId){
        this.pasajeros_id = pasajeros_id;
        this.pasajeros_nombre = pasajeros_nombre;
        this.pasajeros_apellido = pasajeros_apellidos;
        this.pasajeros_correo = pasajeros_correo;
        this.pasajeros_celular = pasajeros_celular;
        this.pasajeros_cedula = pasajeros_cedula;
        this.tipoPasajeroId = tipoPasajeroId;
        this.generoId = generoId;
        this.precioVueloFinalId = precioVueloFinalId;
    }
    
    public int getPasajeros_id() {
        return pasajeros_id;
    }

    public void setPasajeros_id(int pasajeros_id) {
        this.pasajeros_id = pasajeros_id;
    }

    public String getPasajeros_nombre() {
        return pasajeros_nombre;
    }

    public void setPasajeros_nombre(String pasajeros_nombre) {
        this.pasajeros_nombre = pasajeros_nombre;
    }

    public String getPasajeros_apellido() {
        return pasajeros_apellido;
    }

    public void setPasajeros_apellido(String pasajeros_apellido) {
        this.pasajeros_apellido = pasajeros_apellido;
    }

    public String getPasajeros_correo() {
        return pasajeros_correo;
    }

    public void setPasajeros_correo(String pasajeros_correo) {
        this.pasajeros_correo = pasajeros_correo;
    }

    public String getPasajeros_celular() {
        return pasajeros_celular;
    }

    public void setPasajeros_celular(String pasajeros_celular) {
        this.pasajeros_celular = pasajeros_celular;
    }

    public String getPasajeros_cedula() {
        return pasajeros_cedula;
    }

    public void setPasajeros_cedula(String pasajeros_cedula) {
        this.pasajeros_cedula = pasajeros_cedula;
    }

    public int getTipoPasajeroId() {
        return tipoPasajeroId;
    }

    public void setTipoPasajeroId(int tipoPasajeroId) {
        this.tipoPasajeroId = tipoPasajeroId;
    }

    public int getGeneroId() {
        return generoId;
    }

    public void setGeneroId(int generoId) {
        this.generoId = generoId;
    }

    public int getPrecioVueloFinalId() {
        return precioVueloFinalId;
    }

    public void setPrecioVueloFinalId(int precioVueloFinalId) {
        this.precioVueloFinalId = precioVueloFinalId;
    }
    
    

    
}
