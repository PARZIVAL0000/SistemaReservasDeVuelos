/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author denni
 */
public class PrecioVueloFinal {
    private int precioVueloFinal_id;
    private double precioVueloFinal_cantidad;

    public PrecioVueloFinal(){
    }
    
    public PrecioVueloFinal(int precioVueloFinal_id, double precioVueloFinal_cantidad){
        this.precioVueloFinal_id = precioVueloFinal_id;
        this.precioVueloFinal_cantidad = precioVueloFinal_cantidad;
    }
    
    public int getPrecioVueloFinal_id() {
        return precioVueloFinal_id;
    }

    public void setPrecioVueloFinal_id(int precioVueloFinal_id) {
        this.precioVueloFinal_id = precioVueloFinal_id;
    }

    public double getPrecioVueloFinal_cantidad() {
        return precioVueloFinal_cantidad;
    }

    public void setPrecioVueloFinal_cantidad(double precioVueloFinal_cantidad) {
        this.precioVueloFinal_cantidad = precioVueloFinal_cantidad;
    }
    
    
}
